package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ConsumeExpand;
import com.gejian.pixel.entity.QualityUpgrade;
import com.gejian.pixel.entity.SkillScriptRole;
import com.gejian.pixel.mapper.QualityUpgradeMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.QualityUpgradeService;
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
public class QualityUpgradeServiceImpl extends ServiceImpl<QualityUpgradeMapper, QualityUpgrade>
		implements QualityUpgradeService , ConstantsProto {


	private Map<Integer, QualityUpgrade> hash = new HashMap<>();

	private List<ConstQualityUpgradeTableItemExProtobuf.ConstQualityUpgradeTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<QualityUpgrade> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstQualityUpgradeTableItemExProtobuf.ConstQualityUpgradeTableItemEx convert(QualityUpgrade item) {
		ConsumeExpand consumeExpand = JSONUtil.toBean(item.getConsumeExpand(), ConsumeExpand.class);
		ConstQualityUpgradeTableItemExConsumeExpandProtobuf.ConstQualityUpgradeTableItemExConsumeExpand build
				= ConstQualityUpgradeTableItemExConsumeExpandProtobuf.ConstQualityUpgradeTableItemExConsumeExpand.newBuilder()
				.setPrivateSoulchip(consumeExpand.getPrivateSoulchip())
				.build();
		return ConstQualityUpgradeTableItemExProtobuf.ConstQualityUpgradeTableItemEx
				.newBuilder()
				.setQuality(item.getQuality())
				.setId(item.getId())
				.setConsume(item.getConsume())
				.setConsumeExpand(build)
				.setDesc(item.getDesc())
				.build();
	}


	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstQualityUpgradeTableProtobuf.ConstQualityUpgradeTable build = ConstQualityUpgradeTableProtobuf.ConstQualityUpgradeTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setQualityUpgrades(build);
	}
}