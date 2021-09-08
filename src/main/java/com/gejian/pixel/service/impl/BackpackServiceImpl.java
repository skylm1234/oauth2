package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.mapper.BackpackMapper;
import com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableItemExProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.ConstantsProto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class BackpackServiceImpl extends ServiceImpl<BackpackMapper, Backpack>
		implements BackpackService, ConstantsProto {

	private Map<Integer, Backpack> hash = new HashMap<>();

	private List<ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx> backpacks = new ArrayList<>();

	@Override
	public void init() {
		List<Backpack> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getLevel(),item);
				ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx backpackProto = convert(item);
				backpacks.add(backpackProto);
			});

		}
	}


	private ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx convert(Backpack backpack){
		return ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx.newBuilder()
				.setExpMax(backpack.getExpMax())
				.setLevel(backpack.getLevel())
				.setGoldMax(backpack.getGoldMax())
				.setItemMax(backpack.getItemMax())
				.setPrerequests(backpack.getPrerequests())
				.setFomula(ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.newBuilder()
						.setStone(JSONUtil.parseObj(backpack.getFomula()).getInt("stone")))
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstBackpackTableProtobuf.ConstBackpackTable constBackpackTable = ConstBackpackTableProtobuf
				.ConstBackpackTable.newBuilder()
				.addAllItems(this.backpacks)
				.build();
		builder.setBackpacks(constBackpackTable);
	}

	@Override
	public Backpack getByLevel(Integer level) {
		return hash.get(level);
	}

	@Override
	public ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx getItem(Integer level){
		return this.convert(hash.get(level));
	}
}