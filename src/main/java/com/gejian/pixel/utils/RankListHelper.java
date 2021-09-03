package com.gejian.pixel.utils;

import cn.hutool.core.bean.BeanUtil;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommRanklistRelativePowerResponseProtobuf;
import com.gejian.pixel.proto.CommRanklistResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.proto.RanklistProtobuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Reese
 * @date 2021/9/2
 */
@Component
public class RankListHelper {

	@Autowired
	private RedisTemplate redisTemplate;


	public void rankListHelper(RanklistBean bean, CommRanklistResponseProtobuf.CommRanklistResponse response) {
		if (bean == null) {
			return;
		}
		Long myRank = bean.getMyRank();
		List<Rank> ranks = bean.getRanks();
		RanklistProtobuf.Ranklist.Builder builder = RanklistProtobuf.Ranklist.newBuilder()
				.setMyrank(myRank.intValue()).setTimestamp((int) Helper.currentTimestamp());
		ranks.forEach(o -> builder.addRanks(PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey(o.getKey()).setValue(o.getValue()).build()));
		response.getRanksList().add(builder.build());
	}

	public RanklistBean topRangePower(Integer identifier, Long n, boolean center) {
		return this.rankList(identifier, "power", n, center);
	}

	public RanklistBean topRangeHonor(Integer identifier, Long n, boolean center) {
		return this.rankList(identifier, "honor", n, center);
	}

	public RanklistBean topRangeRich(Integer identifier, Long n, boolean center) {
		return this.rankList(identifier, "rich", n, center);
	}

	public RanklistBean topRangeTianti(Integer identifier, Long n, boolean center) {
		return this.rankList(identifier, "tianti", n, center);
	}

	private RanklistBean rankList(Integer identifier, String rankName, Long n, boolean center) {
		List<Rank> ranks = new ArrayList<>();
		Long from = 0L;
		Long to = n;
		Long myRank = 0L;

		String rankListKey = getRankListKey(rankName);
		String nickname = Helper.stringValue(redisTemplate, identifier, "nickname");
		if (center) {

			myRank = this.redisTemplate.opsForZSet().reverseRank(rankListKey, Helper.hexEncode(nickname));
			myRank = myRank == null ? -1L : myRank;

			from = myRank <= n ? -1L : myRank - n;

			from = from < 0L ? 0L : from;
			to = myRank + n;
		}

		Set<ZSetOperations.TypedTuple<Object>> set = this.redisTemplate.opsForZSet().reverseRangeWithScores(rankListKey, from, to);

		if (set != null) {
			set.forEach(o -> ranks.add(new Rank(Helper.hexDecode(Objects.requireNonNull(o.getValue()).toString()), Objects.requireNonNull(o.getScore()).longValue())));
		}
		return new RanklistBean(myRank, ranks);
	}

	private String getRankListKey(String rankName) {
		return "ranklist:" + rankName;
	}

	private String getItemsKey(Integer identifier) {
		return "u:#{identifier}:items".replace("#{identifier}", identifier.toString());

	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}


	public int refreshPvp(Integer identifier, boolean refresh,
						  CommRanklistRelativePowerResponseProtobuf.CommRanklistRelativePowerResponse.Builder builder) {
		String itemsKey = getItemsKey(identifier);
		Object vectoryTimes = this.redisTemplate.opsForHash().get(itemsKey, "pvp_vectory_times");
		builder.addItems(PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey("pvp_vectory_times").setValue(this.parseLong(vectoryTimes)).build());

		Object challageTimes = this.redisTemplate.opsForHash().get(itemsKey, "pvp_challage_times");
		builder.addItems(PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey("pvp_challage_times").setValue(this.parseLong(challageTimes)).build());

		Boolean shouldRefresh = this.redisTemplate.opsForHash().hasKey(itemsKey, "should_refresh_pvp_chanllege_ranklist");
		if (shouldRefresh && refresh) {
			CommRanklistResponseProtobuf.CommRanklistResponse commRanklistResponse =
					CommRanklistResponseProtobuf.CommRanklistResponse.getDefaultInstance();
			this.rankListHelper(this.topRangePower(identifier, 10L, true), commRanklistResponse);
			BeanUtil.copyProperties(commRanklistResponse, builder);
			this.redisTemplate.opsForHash().delete(itemsKey, "should_refresh_pvp_chanllege_ranklist");
			return ErrorEnum.ERROR_SUCCESS;
		}

		return ErrorEnum.ERROR_NOT_COOLDOWN;
	}
}

@Data
@AllArgsConstructor
class RanklistBean {

	private Long myRank;

	private List<Rank> ranks;

}

@Data
@AllArgsConstructor
class Rank {

	private String key;

	private Long value;

}

