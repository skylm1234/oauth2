package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Exchange;
import com.gejian.pixel.entity.PvpBasic;
import com.gejian.pixel.mapper.PvpBasicMapper;
import com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf;
import com.gejian.pixel.proto.ConstPvpBasicTableItemExProtobuf;
import com.gejian.pixel.proto.ConstPvpBasicTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PvpBasicService;
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
public class PvpBasicServiceImpl extends ServiceImpl<PvpBasicMapper, PvpBasic> implements PvpBasicService, ConstantsProto {

	private Map<Integer, PvpBasic> hash = new HashMap<>();

	private List<ConstPvpBasicTableItemExProtobuf.ConstPvpBasicTableItemEx>
			table = new ArrayList<>();

	@PostConstruct
	public void init(){
		List<PvpBasic> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPvpBasicTableItemExProtobuf.ConstPvpBasicTableItemEx convert(PvpBasic item) {
		return ConstPvpBasicTableItemExProtobuf.ConstPvpBasicTableItemEx
				.newBuilder()
				.setId(item.getId())
				.setRangeMax(item.getRangeMax())
				.setRangeMin(item.getRangeMin())
				.setRange(item.getRange())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPvpBasicTableProtobuf.ConstPvpBasicTable build = ConstPvpBasicTableProtobuf.ConstPvpBasicTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPvpBasics(build);
	}
}