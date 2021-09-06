package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Exchange;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.mapper.ExchangeMapper;
import com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf;
import com.gejian.pixel.proto.ConstExchangeTableProtobuf;
import com.gejian.pixel.proto.ConstInGamePurchaseTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.ExchangeService;
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
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements ExchangeService,
		ConstantsProto {

	private Map<Integer, Exchange> hash = new HashMap<>();

	private List<ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<Exchange> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getType(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx convert(Exchange item) {
		return ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.newBuilder()
				.setAmount(item.getAmount())
				.setStone(item.getStone())
				.setType(item.getType())
				.setDesc(item.getDesc())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstExchangeTableProtobuf.ConstExchangeTable build = ConstExchangeTableProtobuf.ConstExchangeTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setExchanges(build);
	}
}