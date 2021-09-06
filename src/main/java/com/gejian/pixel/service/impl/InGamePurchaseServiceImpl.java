package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.mapper.InGamePurchaseMapper;
import com.gejian.pixel.proto.ConstInGamePurchaseTableItemExProtobuf;
import com.gejian.pixel.proto.ConstInGamePurchaseTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.InGamePurchaseService;
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
public class InGamePurchaseServiceImpl extends ServiceImpl<InGamePurchaseMapper, InGamePurchase>
		implements InGamePurchaseService, ConstantsProto {

	private Map<String, InGamePurchase> hash = new HashMap<>();

	private List<ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<InGamePurchase> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx convert(InGamePurchase item) {
		return ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx
				.newBuilder()
				.setId(item.getId())
				.setCost(item.getCost())
				.setStone(item.getStone())
				.setDesc(item.getDesc())
				.setIcon(item.getIcon())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstInGamePurchaseTableProtobuf.ConstInGamePurchaseTable build = ConstInGamePurchaseTableProtobuf.
				ConstInGamePurchaseTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setInGamePurchases(build);
	}
}