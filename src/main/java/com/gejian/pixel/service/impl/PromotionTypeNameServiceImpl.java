package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.PromotionTypeName;
import com.gejian.pixel.mapper.PromotionTypeNameMapper;
import com.gejian.pixel.proto.ConstPromotionTypeNameTableItemExProtobuf;
import com.gejian.pixel.proto.ConstPromotionTypeNameTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PromotionTypeNameService;
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
public class PromotionTypeNameServiceImpl extends ServiceImpl<PromotionTypeNameMapper, PromotionTypeName> implements PromotionTypeNameService , ConstantsProto {

	private Map<Integer, PromotionTypeName> hash = new HashMap<>();

	private List<ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx> table = new ArrayList<>();

	@Override
	public void init(){
		List<PromotionTypeName> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getType(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx convert(PromotionTypeName item) {

		return ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx.newBuilder()
				.setType(item.getType())
				.setName(item.getName())
				.setQ(item.getQ())
				.setGroup(item.getGroup())
				.setGroupName(item.getGroupName())
				.build();

	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPromotionTypeNameTableProtobuf.ConstPromotionTypeNameTable build=
				ConstPromotionTypeNameTableProtobuf.ConstPromotionTypeNameTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPromotionsNames(build);
	}


}