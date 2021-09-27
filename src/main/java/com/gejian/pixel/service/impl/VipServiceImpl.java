package com.gejian.pixel.service.impl;

import cn.hutool.core.text.StrFormatter;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Vip;
import com.gejian.pixel.mapper.VipMapper;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.proto.ConstVipTableItemExProtobuf;
import com.gejian.pixel.proto.ConstVipTableProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip> implements VipService, ConstantsProto {

	@Autowired
	private DropService dropService;

	private Map<Integer, Vip> hash = new HashMap<>();

	private List<ConstVipTableItemExProtobuf.ConstVipTableItemEx>
			table = new ArrayList<>();

	@Override
	public Vip getById(Integer id) {
		return hash.get(id);
	}

	@Override
	public void init(){
		List<Vip> list = this.list();
		//需要初始化vip描述信息
		list.stream().map(vip -> {
			if (vip.getLevel()>0) {
				String des = StrFormatter.format("<color=red>VIP{}</color>奖励、特权详情：{}" +
								"$每场可免费挑战<color=red>{}</color>次其他玩家" +
								"$可提升临时背包等级到<color=red>{}</color>级" +
								"$离线战斗遇怪间隔缩短<color=red>{}%</color>" +
								"$<color=red>VIP3</color>级及以上可跳过PVP战斗" +
								"$双倍经验卡获得额外增益<color=red>{}%</color>" +
								"$双倍金币卡额外增益<color=red>{}%</color>",
						vip.getLevel(),
						dropService.getById(vip.getItemid()).getDesc(),
						vip.getChanllege(),
						vip.getBackpackMax(),
						vip.getTimevalOfflineKillMonsterRate(),
						vip.getLevel()*10,
						vip.getLevel()*10);
				vip.setDes(des);
			}
			return vip;
		}).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public ConstVipTableItemExProtobuf.ConstVipTableItemEx getItem(Integer id){
		return this.convert(hash.get(id));
	}

	private ConstVipTableItemExProtobuf.ConstVipTableItemEx convert(Vip item) {
		return ConstVipTableItemExProtobuf.ConstVipTableItemEx.newBuilder()
				.setId(item.getId())
				.setBackpackMax(item.getBackpackMax())
				.setChanllege(item.getChanllege())
				.setCharge(item.getCharge())
				.setFreerefreshTimesGold(item.getFreerefreshTimesGold())
				.setFreerefreshTimesHonor(item.getFreerefreshTimesHonor())
				.setFreerefreshTimesStone(item.getFreerefreshTimesStone())
				.setLevel(item.getLevel())
				.setSkipTimeval(item.getSkipTimeval())
				.setTiantiReset(item.getTiantiReset())
				.setTimevalOfflineKillMonsterRate(item.getTimevalOfflineKillMonsterRate())
				.setDes(item.getDes())
				.setItemid(item.getItemid())
				.setTianti(item.getTianti())
				.setAward(item.getAward())
				.build();
	}


	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstVipTableProtobuf.ConstVipTable build = ConstVipTableProtobuf.ConstVipTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setVips(build);
	}
}