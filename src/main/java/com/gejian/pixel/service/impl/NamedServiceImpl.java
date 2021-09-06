package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Named;
import com.gejian.pixel.mapper.NamedMapper;
import com.gejian.pixel.proto.ConstNamedTableItemExProtobuf;
import com.gejian.pixel.proto.ConstNamedTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.NamedService;
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
public class NamedServiceImpl extends ServiceImpl<NamedMapper, Named> implements NamedService , ConstantsProto {


	private Map<String, Named> hash = new HashMap<>();

	private List<ConstNamedTableItemExProtobuf.ConstNamedTableItemEx> table = new ArrayList<>();

	@Override
	public void init(){
		List<Named> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getName(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstNamedTableItemExProtobuf.ConstNamedTableItemEx convert(Named item) {

			return ConstNamedTableItemExProtobuf.ConstNamedTableItemEx.newBuilder()
					.setName(item.getName())
					.setTitle(item.getTitle())
					.setDesc(item.getDesc())
					.build();

	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstNamedTableProtobuf.ConstNamedTable build=
				ConstNamedTableProtobuf.ConstNamedTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setNames(build);
	}
}