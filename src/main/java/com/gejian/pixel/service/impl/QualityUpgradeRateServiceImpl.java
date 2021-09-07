package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.QualityUpgradeRate;
import com.gejian.pixel.mapper.QualityUpgradeRateMapper;
import com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf;
import com.gejian.pixel.proto.ConstQualityUpgradeRateTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.QualityUpgradeRateService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class QualityUpgradeRateServiceImpl extends ServiceImpl<QualityUpgradeRateMapper, QualityUpgradeRate> implements QualityUpgradeRateService , ConstantsProto {


	private Map<Long, QualityUpgradeRate> hash=new HashMap<>();

	private List<ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx> table=new ArrayList<>();

	@Override
	public void init(){
		List<QualityUpgradeRate> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public QualityUpgradeRate getById(Integer id) {
		return hash.get(id);
	}

	private ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx convert(QualityUpgradeRate item){

		return ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx
				.newBuilder()
				.setId(Integer.valueOf(String.valueOf(item.getId())))
				.setQuality(item.getQuality())
				.setDesc(item.getDesc())
				.setUp(item.getUp())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstQualityUpgradeRateTableProtobuf.ConstQualityUpgradeRateTable build=
				ConstQualityUpgradeRateTableProtobuf.ConstQualityUpgradeRateTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setQualityUpgradeRates(build);
	}

}