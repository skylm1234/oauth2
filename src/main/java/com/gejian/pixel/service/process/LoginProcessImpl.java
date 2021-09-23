package com.gejian.pixel.service.process;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.customType.TopRangePower;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.InGamePurchaseService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 *
 * 登陆请求
 *
 * @author ljb
 * @date 2021/8/30$
 */
@Service(CommandConstants.LOGIN_REQUEST)
@Slf4j
@RequiredArgsConstructor
public class LoginProcessImpl implements Process<CommLoginRequestProtobuf.CommLoginRequest
		, CommLoginResponseProtobuf.CommLoginResponse> {

	private final RedisTemplate redisTemplate;

	private static final int MIN_VERSION = 10;

	private final DropService dropService;

	private final InGamePurchaseService inGamePurchaseService;

	@Override
	public CommLoginResponseProtobuf.CommLoginResponse
		doProcess(CommLoginRequestProtobuf.CommLoginRequest request) throws Exception {
		log.info("登陆请求参数：{}",request);
		
		CommLoginResponseProtobuf.CommLoginResponse.Builder replyBuilder = CommLoginResponseProtobuf.CommLoginResponse.newBuilder();
		PlayerInfoProtobuf.PlayerInfo.Builder playerBuilder = PlayerInfoProtobuf.PlayerInfo.newBuilder();

		long currentTimestamp = Helper.currentTimestamp();
		long currentDays = Helper.currentDay();
		replyBuilder.setTimestamp(NumberUtil.parseInt(System.currentTimeMillis() + ""));

		if (request.getVersion() < MIN_VERSION) {
			replyBuilder.setResult(ErrorEnum.ERROR_CLIENT_VERSION_TOOOOO_OLD);
			replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData("http://www.baidu.com").build());
			return replyBuilder.build();
		}

		String s = StrFormatter.format("{} {}  {}   {}",
				request.getData(),
				request.getVersion(),
				request.getIdentifier(),
				NumberUtil.parseLong(request.getData()) * request.getVersion());
		log.info("request:{}", s);
		log.info(s);
		log.info(SecureUtil.sha1(s).toUpperCase());
		if (!SecureUtil.sha1(s).toUpperCase().equals(request.getCipher().toUpperCase())) {
			replyBuilder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER);
			return replyBuilder.build();
		}
		//判断是否开启禁止任何人访问
		Object systemBanAnyone = redisTemplate.opsForValue().get("system:ban_anyone");
		if (systemBanAnyone != null) {
			if (NumberUtil.parseInt(systemBanAnyone + "") == 1) {
				log.error("FAILED: {}=>{}:{}", request.getIdentifier(), CommandConstants.LOGIN_REQUEST, Thread.currentThread().getStackTrace()[1].getLineNumber());
				replyBuilder.setResult(ErrorEnum.ERROR_BANNED);
				//返回禁止登陆原因
				String systemBanyoneReason = String.valueOf(redisTemplate.opsForValue().get("system:ban_anyone_reason"));
				replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData(systemBanyoneReason).build());
				return replyBuilder.build();
			}
		}

		String hexEncodedIdentifier = request.getIdentifier().length() != 0 ? Helper.hexEncode(request.getIdentifier()) : "";

		Integer identifier = null;

		if (StrUtil.hasBlank(request.getIdentifier()) || !redisTemplate.opsForHash().hasKey(RedisKeyConstants.USER_IDENTIFIER, hexEncodedIdentifier)) {
			log.info("new player register");

			identifier = Integer.valueOf(Helper.generateUserIdentifier(redisTemplate));

			hexEncodedIdentifier = Helper.hexEncode(identifier+"");
			replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setIdentifier(identifier+"").build());

			long timestamp = Helper.currentTimestamp();
			long hours = timestamp - timestamp % 3600;
			Map<String, Object> items = new HashMap<>();
			items.put("dungeon_1_not_passed_stage", 1);
			items.put("dungeon_2_not_passed_stage", 1001);
			items.put("dungeon_3_not_passed_stage", 2001);
			items.put("create_at", currentTimestamp);
			items.put("giftbags", 0);
			items.put("vip", 0);//充得多等级高
			items.put("power", 0);//战力，初始为0
			items.put("gold", 0);//金币（游戏币）
			items.put("stone", 0);//石头（充值币）当前
			items.put("honor", 0);//荣誉点
			items.put("total_charged_money", 0);
			items.put("total_honor", 0);
			items.put("daily_check_in_timestamp", 0);
			items.put("daily_check_in_count", 0);
			items.put("tianti_challage_times", 0);
			items.put("pvp_1_vectory", 0);
			items.put("pvp_3_vectory", 0);
			items.put("pvp_9_vectory", 0);
			items.put("should_refresh_pvp_chanllege_ranklist", 1);
			items.put("pvp_vectory_times", 0);
			items.put("pvp_challage_times", 0);
			items.put("buy_hero_1_timestamp", 0);
			items.put("buy_hero_1_times", 0);
			items.put("buy_hero_1_price", 0);
			items.put("buy_hero_2_timestamp", 0);
			items.put("buy_hero_2_times", 0);
			items.put("buy_hero_2_price", 0);
			items.put("buy_hero_3_timestamp", 0);
			items.put("buy_hero_3_times", 0);
			items.put("buy_hero_3_price", 0);

			Map<String, Object> strings = new HashMap<>();
			strings.put("new_store_refresh_desc", Helper.hexEncode("商店刷新时间：每日09:00、12:00、18:00、22：00"));
			strings.put("finished_promotions", Helper.hexEncode("{}"));
			strings.put("finished_daily_promotions", Helper.hexEncode("{}"));

			List<InGamePurchase> inGamePurchases = inGamePurchaseService.list();
			if (inGamePurchases!=null) {
				inGamePurchases.stream().forEach(x-> items.put(x.getId(), 0));
			}

			if (redisTemplate.opsForHash().putIfAbsent(RedisKeyConstants.USER_IDENTIFIER, hexEncodedIdentifier, identifier+"")) {
				redisTemplate.opsForHash().putAll("u:"+identifier+":items", items);
				redisTemplate.opsForHash().putAll("u:"+identifier+":strings", strings);
				redisTemplate.opsForHash().putIfAbsent(RedisKeyConstants.USER_CLEAR_TEXT, identifier+"", identifier);
			} else {
				throw new RuntimeException("failed");
			}

			dropService.dropItem("newbie", identifier, false, null);



			Map<String,Integer> tempbackpack = new HashMap<>();
			tempbackpack.put("level", 1);
			tempbackpack.put("type", 1);
			tempbackpack.put("stage", 1);
			tempbackpack.put("dungeon_enter_timestamp", NumberUtil.parseInt(Helper.currentTimestamp()+""));

			redisTemplate.opsForHash().putAll("u:"+identifier+":temp_backpack",tempbackpack);

			Helper.refreshStore(redisTemplate, Integer.valueOf(identifier), 1);
			Helper.refreshStore(redisTemplate, Integer.valueOf(identifier), 2);
			Helper.refreshStore(redisTemplate, Integer.valueOf(identifier), 3);

		}else {
			identifier = NumberUtil.parseInt(request.getIdentifier());
			if (redisTemplate.opsForHash().hasKey("user:set:ban", hexEncodedIdentifier)) {

				identifier = UserHolder.get().getIdentifier();
				log.error("FAILED: {}=>{}:{}",identifier,Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber());
				Map reason = (Map) redisTemplate.opsForHash().get("user:set:ban", hexEncodedIdentifier);

				replyBuilder.setResult(ErrorEnum.ERROR_BANNED);
				replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData(reason.get(hexEncodedIdentifier)+"").build());

				//这里还有个返回空指针，但是如果返回空指针就不能返回登录的响应对象了
				//return 0, nil
				return replyBuilder.build();
			}
			String boardcast = "";
			if (request.getVersion() >= 11) {
				boardcast = String.valueOf(redisTemplate.opsForValue().get("system:boardcast11"));
			}else {
				boardcast = String.valueOf(redisTemplate.opsForValue().get("system:boardcast"));
			}
			if (Helper.stringValue(redisTemplate, NumberUtil.parseInt(request.getIdentifier()), "nickname") != null) {
				replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData(boardcast).build());
			}
		}

		log.info("on_handle_COMM_LOGIN_REQUEST -> {}",request.getIdentifier());

		Helper.setItemValue(redisTemplate, identifier+"", "giftbags", Integer.valueOf(redisTemplate.opsForHash().size("u:" + identifier + ":giftbags")+""));

		playerBuilder.setIdentifier(identifier+"");

		identifier = NumberUtil.parseInt(redisTemplate.opsForHash().get("user:set:identifier", hexEncodedIdentifier)+"");

		Map heros = redisTemplate.opsForHash().entries("u:" + identifier + ":heros");
		Integer finalIdentifier = identifier;
		heros.forEach((k, v)->{
			Map hero = redisTemplate.opsForHash().entries("u:" + finalIdentifier + ":" + k + ":attributes");
			HeroBasicInfoProtobuf.HeroBasicInfo.Builder hBuilder = HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder();
			hBuilder.setId(NumberUtil.parseInt(hero.get("id") + ""));
			hBuilder.setType(hero.get("type") + "");
			hBuilder.setLevel(NumberUtil.parseInt(hero.get("level") + ""));
			hBuilder.setExp(NumberUtil.parseInt(hero.get("exp") + ""));
			hBuilder.setQuality(NumberUtil.parseInt(hero.get("quality") + ""));
			hBuilder.setStar(NumberUtil.parseInt(hero.get("star") + ""));
			hBuilder.setGrowHp(NumberUtil.parseInt(hero.get("grow_hp") + ""));
			hBuilder.setHp(NumberUtil.parseInt(hero.get("hp") + ""));
			hBuilder.setGrowDef(NumberUtil.parseInt(hero.get("grow_def") + ""));
			hBuilder.setDef(NumberUtil.parseInt(hero.get("def") + ""));
			hBuilder.setGrowAttack(NumberUtil.parseInt(hero.get("grow_attack") + ""));
			hBuilder.setAttack(NumberUtil.parseInt(hero.get("attack") + ""));
			hBuilder.setGrowSpeed(NumberUtil.parseInt(hero.get("grow_speed") + ""));
			hBuilder.setSpeed(NumberUtil.parseInt(hero.get("speed") + ""));
			hBuilder.setNumber(NumberUtil.parseInt(hero.get("number") + ""));

			Map skills = redisTemplate.opsForHash().entries("u:" + finalIdentifier + ":" + k + ":skills");
			int[] currentSkillsIndex = {0};
			skills.forEach((kk,vv)->{
				HeroSkillProtobuf.HeroSkill heroSkill = HeroSkillProtobuf.HeroSkill
						.newBuilder()
						.setType(kk+"")
						.setLevel(NumberUtil.parseInt(vv+""))
						.build();
				//hBuilder.addSkills(currentSkillsIndex[0], heroSkill);
				//currentSkillsIndex[0]++;
				hBuilder.addSkills(heroSkill);
			});

			HeroBasicInfoProtobuf.HeroBasicInfo h = hBuilder.build();
			playerBuilder.addHeros(h);
		});

		Map items = redisTemplate.opsForHash().entries("u:" + identifier + ":items");
		items.forEach((k,v)->{
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(k+"")
					.setValue(Long.parseLong(v+""))
					.build();
			playerBuilder.addItems(item);
		});

		Map teams = redisTemplate.opsForHash().entries("u:" + identifier + ":teams");
		teams.forEach((k,v)->{
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(k+"")
					.setValue(Long.parseLong(v+""))
					.build();
			playerBuilder.addTeams(item);
		});

		Map teamsPvp = redisTemplate.opsForHash().entries("u:" + identifier + ":teams_pvp");
		teamsPvp.forEach((k,v)->{
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(k+"")
					.setValue(Long.parseLong(v+""))
					.build();
			playerBuilder.addTeamsPvp(item);
		});

		Map archives = redisTemplate.opsForHash().entries("u:" + identifier + ":archives");
		archives.forEach((k,v)->{
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(k+"")
					.setValue(Long.parseLong(v+""))
					.build();
			playerBuilder.addArchives(item);
		});

		Map strings = redisTemplate.opsForHash().entries("u:" + identifier + ":strings");
		strings.forEach((k,v)->{
			PlayerStringProtobuf.PlayerString item = PlayerStringProtobuf.PlayerString
					.newBuilder()
					.setKey(k+"")
					.setValue(Helper.hexDecode(v+""))
					.build();
			playerBuilder.addStrings(item);
		});

		Map tempbackpack = redisTemplate.opsForHash().entries("u:" + identifier + ":temp_backpack");
		if (tempbackpack.size()!=0) {
			PlayerTemporaryBackpackProtobuf.PlayerTemporaryBackpack tbp = PlayerTemporaryBackpackProtobuf.PlayerTemporaryBackpack
					.newBuilder()
					.setLevel(NumberUtil.parseInt(tempbackpack.get("level")+""))
					.setType(NumberUtil.parseInt(tempbackpack.get("type")+""))
					.setStage(NumberUtil.parseInt(tempbackpack.get("stage")+""))
					.setDungeonEnterTimestamp(NumberUtil.parseInt(tempbackpack.get("dungeon_enter_timestamp")+""))
					.build();
			playerBuilder.setBackpack(tbp);
		}

		List<StoreItemProtobuf.StoreItem> goods0 = fooCall(identifier, 1);
		List<StoreItemProtobuf.StoreItem> goods1 = fooCall(identifier, 2);
		List<StoreItemProtobuf.StoreItem> goods2 = fooCall(identifier, 3);

		PlayerStoreProtobuf.PlayerStore.Builder storeBuilder = PlayerStoreProtobuf.PlayerStore
				.newBuilder();

		for (StoreItemProtobuf.StoreItem storeItem : goods0) {
			storeBuilder.addGoods0(storeItem);
		}
		for (StoreItemProtobuf.StoreItem storeItem : goods1) {
			storeBuilder.addGoods1(storeItem);
		}
		for (StoreItemProtobuf.StoreItem storeItem : goods2) {
			storeBuilder.addGoods2(storeItem);
		}

		PlayerStoreProtobuf.PlayerStore store = storeBuilder.build();
		playerBuilder.setStore(store);

		if (Helper.stringValue(redisTemplate, identifier, "nickname") != null) {
			// r = COMM_RANKLIST_RELATIVE_POWER_RESPONSE.new
			helperResreshPvp(identifier, request, playerBuilder, true);
		}

		//reply.ping_interval = settings.CLIENT_PING_INTERVAL
		// TODO: 2021/9/1 需要修改成上面的
		replyBuilder.setPingInterval(1);

		Helper.increaseItemValue(redisTemplate, identifier, "total_login_times", 1L);

		CommLoginRequestProtobuf.CommLoginRequest.Builder requestBuilder = CommLoginRequestProtobuf.CommLoginRequest.newBuilder();
		//类似构造方法
		requestBuilder.mergeFrom(request);
		requestBuilder.setIdentifier(identifier.toString());
		request = requestBuilder.build();

		log.info("on_handle_COMM_LOGIN_REQUEST -> "+request.getIdentifier()+" done");
		//这里session用uuid
		String session = IdUtil.fastSimpleUUID();
		redisTemplate.opsForValue().set("u:"+identifier+":session", session);
		Map<String,Integer> itemsMap = new HashMap<>();
		items.put("online",System.currentTimeMillis()/1000);
		redisTemplate.opsForHash().putAll("u:"+identifier+":items",itemsMap);

		playerBuilder.setSession(session);

		PlayerInfoProtobuf.PlayerInfo player = playerBuilder.build();
		replyBuilder.setPlayer(player);


		replyBuilder.setTimestamp(NumberUtil.parseInt(Helper.currentTimestamp()+""));
		replyBuilder.setRequest(request);
		return replyBuilder.build();
	}

	private Integer helperResreshPvp(Integer identifier, CommLoginRequestProtobuf.CommLoginRequest request, PlayerInfoProtobuf.PlayerInfo.Builder reply, Boolean refresh_challage_ranklist) {
		List<String> searchList = Arrays.asList("pvp_vectory_times", "pvp_challage_times");
		List<Object> items = redisTemplate.opsForHash().multiGet("u:" + identifier + ":items", searchList);
		if (items != null || items.size()>0) {
			for (int i = 0; i < searchList.size(); i++) {
				PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
						.newBuilder()
						.setKey(searchList.get(i))
						.setValue(NumberUtil.parseLong(items.get(i)+""))
						.build();
				reply.addItems(item);
			}
		}

		redisTemplate.opsForHash().hasKey("u:"+identifier+":items","should_refresh_pvp_chanllege_ranklist");

		log.info("helper_resresh_pvp => "+redisTemplate.opsForHash().hasKey("u:"+identifier+":items","should_refresh_pvp_chanllege_ranklist")+"refresh_challage_ranklist => "+refresh_challage_ranklist);

		if (redisTemplate.opsForHash().hasKey("u:"+identifier+":items", "should_refresh_pvp_chanllege_ranklist") && refresh_challage_ranklist) {
			log.info("pvp fill data");
			ranklistHelper(redisTemplate, identifier, reply, 10, true);
			redisTemplate.opsForHash().delete("u:"+identifier+":items","should_refresh_pvp_chanllege_ranklist");
			return ErrorEnum.ERROR_SUCCESS;
		}else {
			return ErrorEnum.ERROR_NOT_COOLDOWN;
		}

	}

	private void ranklistHelper(RedisTemplate redisTemplate, Integer identifier, PlayerInfoProtobuf.PlayerInfo.Builder reply, Integer n, Boolean centerN) {
		TopRangePower topRangePower = topRangePower(redisTemplate, identifier, "power", n, centerN);
		Integer myrank = topRangePower.getMyrank();
		List<Map<String, Object>> topX = topRangePower.getRanks();
		RanklistProtobuf.Ranklist.Builder ranklistBuilder = RanklistProtobuf.Ranklist.newBuilder();
		ranklistBuilder.setMyrank(myrank);
		ranklistBuilder.setTimestamp(NumberUtil.parseInt(Helper.currentTimestamp()+""));
		for (Map<String, Object> x : topX) {
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(x.get("key")+"")
					.setValue(Long.parseLong(x.get("value")+""))
					.build();
			ranklistBuilder.addRanks(item);
		}
		RanklistProtobuf.Ranklist ranklist = ranklistBuilder.build();
		reply.addRanks(ranklist);
	}

	private TopRangePower topRangePower(RedisTemplate redisTemplate, Integer identifier, String ranklist, Integer n, Boolean centerN) {
		List<Map<String,Object>> ranks = new ArrayList();
		Integer from = 0;
		Integer to = n;
		Integer myrank = 0;

		if (centerN!=null) {
			if (centerN) {
				Long ranklistByNickname = redisTemplate.opsForZSet().reverseRank("ranklist:" + ranklist, Helper.hexEncode(Helper.stringValue(redisTemplate, identifier, "nickname")));
				if (ranklistByNickname==null) {
					myrank = -1;
				}else {
					myrank = Integer.valueOf(ranklistByNickname+"");
				}
				from = (myrank<=n) ? -1:myrank-n;
				from = from<0 ? 0:from;
				to = myrank+n;
			}
		}

		log.info("__top_n_of_ranklist from {} to {}",from,to);
		Set ar = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("ranklist:" + ranklist, from, to);
		for (Object o : ar) {
			// TODO: 2021/9/10 排名列表可能有问题
			/*
			ar.each do |x|
				puts "__top_n_of_ranklist #{x[0]} #{x[1]}"
				ranks[ranks.length] = {'key' => hex_decode(x[0]), 'value' => x[1]}
			end
			 */
			JSONObject x = JSONUtil.parseObj(o);
			log.info("__top_n_of_ranklist {} {}",x.get("value"),x.get("score"));
			Map<String,Object> rankData = new HashMap<>();
			rankData.put("key", Helper.hexDecode(x.get("value")+""));
			rankData.put("value", NumberUtil.parseLong(x.get("score")+""));
			ranks.add(rankData);

			/*List<String> x = (List<String>) o;
			log.info("__top_n_of_ranklist {} {}",x.get(0),x.get(1));
			Map<String,Object> rankData = new HashMap<>();
			rankData.put("key", Helper.hexDecode(x.get(0)));
			rankData.put("value", Long.parseLong(x.get(1)));
			ranks.add(rankData);*/
		}

		TopRangePower topRangePower = new TopRangePower();
		topRangePower.setMyrank(myrank);
		topRangePower.setRanks(ranks);

		return topRangePower;
	}

	private List<StoreItemProtobuf.StoreItem> fooCall(Integer identifier, Integer type) {
		List<StoreItemProtobuf.StoreItem> storeItems = new ArrayList<>();
		List items = new ArrayList<>();
		Map storeByType = redisTemplate.opsForHash().entries("u:" + identifier + ":store:" + type);
		Map storeByTypeSort = new LinkedHashMap();
		storeByType.forEach((k,v)->{
			storeByTypeSort.put(Integer.parseInt(k+""), v);
		});
		storeByType =  MapUtil.sort(storeByTypeSort);
		storeByType.forEach((k,v)->{
			JSONObject j = JSONUtil.parseObj(v + "");
			List<String> ar = Arrays.asList("name", "number", "cost_type", "cost_number");
			JSONObject h = new JSONObject();
			for (String x : ar) {
				h.putOnce(x, j.get(x));
			}
			items.add(h);
		});
		items.forEach(obj->{
			JSONObject item = (JSONObject) obj;
			StoreItemProtobuf.StoreItem storeItem = StoreItemProtobuf.StoreItem
					.newBuilder()
					.setName(item.get("name")+"")
					.setNumber(NumberUtil.parseInt(item.get("number")+""))
					.setCostType(item.get("cost_type")+"")
					.setCostNumber(NumberUtil.parseInt(item.get("cost_number")+""))
					.build();
			storeItems.add(storeItem);
		});
		return storeItems;
	}

}
