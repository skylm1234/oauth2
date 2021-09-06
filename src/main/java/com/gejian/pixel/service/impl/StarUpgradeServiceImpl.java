package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ConsumeExpand;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.entity.StarUpgrade;
import com.gejian.pixel.mapper.StarUpgradeMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.StarUpgradeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StarUpgradeServiceImpl extends ServiceImpl<StarUpgradeMapper, StarUpgrade>
		implements StarUpgradeService, ConstantsProto {
	private Map<Integer, StarUpgrade> hash = new HashMap<>();

	private List<ConstStarUpgradeTableItemExProtobuf.ConstStarUpgradeTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<StarUpgrade> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstStarUpgradeTableItemExProtobuf.ConstStarUpgradeTableItemEx convert(StarUpgrade item) {
		ConsumeExpand consumeExpand = JSONUtil.toBean(item.getConsumeExpand(), ConsumeExpand.class);
		ConstStarUpgradeTableItemExConsumeExpandProtobuf.ConstStarUpgradeTableItemExConsumeExpand
				build = ConstStarUpgradeTableItemExConsumeExpandProtobuf
				.ConstStarUpgradeTableItemExConsumeExpand.newBuilder()
				.setGold(consumeExpand.getGold())
				.setPrivateSoulchip(consumeExpand.getPrivateSoulchip())
				.build();
		return ConstStarUpgradeTableItemExProtobuf.ConstStarUpgradeTableItemEx
				.newBuilder()
				.setConsume(item.getConsume())
				.setId(item.getId())
				.setStar(item.getStar())
				.setConsumeExpand(build).build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstStarUpgradeTableProtobuf.ConstStarUpgradeTable build =
				ConstStarUpgradeTableProtobuf.ConstStarUpgradeTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setStarUpgrades(build);
	}
}