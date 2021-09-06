package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Promotion;
import com.gejian.pixel.entity.PvpAward;
import com.gejian.pixel.mapper.PromotionMapper;
import com.gejian.pixel.proto.ConstPromotionTableItemExProtobuf;
import com.gejian.pixel.proto.ConstPromotionTableProtobuf;
import com.gejian.pixel.proto.ConstPvpAwardTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PromotionService;
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
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements PromotionService,
		ConstantsProto {
	private Map<Integer, Promotion> hash = new HashMap<>();

	private List<ConstPromotionTableItemExProtobuf.ConstPromotionTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<Promotion> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPromotionTableItemExProtobuf.ConstPromotionTableItemEx convert(Promotion item) {
		return ConstPromotionTableItemExProtobuf.ConstPromotionTableItemEx
				.newBuilder()
				.setType(item.getType())
				.setId(item.getId())
				.setCompareType(item.getCompareType())
				.setParameter(item.getParameter())
				.setNext(item.getNext())
				.setPrecondition(item.getPrecondition())
				.setAward(item.getAward())
				.setDesc(item.getDesc())
				.setPrecondition(item.getPrecondition())
				.setKey(item.getKey())
				.setPercent(item.getPercent())
				.build();
	}


	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPromotionTableProtobuf.ConstPromotionTable build = ConstPromotionTableProtobuf.ConstPromotionTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPromotions(build);
	}
}