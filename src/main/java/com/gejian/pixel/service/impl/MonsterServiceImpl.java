package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Monster;
import com.gejian.pixel.entity.StageClass;
import com.gejian.pixel.mapper.MonsterMapper;
import com.gejian.pixel.proto.ConstMonsterTableItemExProtobuf;
import com.gejian.pixel.proto.ConstMonsterTableProtobuf;
import com.gejian.pixel.proto.ConstStageClassTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.MonsterService;
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
public class MonsterServiceImpl extends ServiceImpl<MonsterMapper, Monster>
		implements MonsterService, ConstantsProto {

	private Map<Integer, Monster> hash = new HashMap<>();

	private List<ConstMonsterTableItemExProtobuf.ConstMonsterTableItemEx> table = new ArrayList<>();

	@PostConstruct
	public void init() {
		List<Monster> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	private ConstMonsterTableItemExProtobuf.ConstMonsterTableItemEx convert(Monster item) {
		return ConstMonsterTableItemExProtobuf.ConstMonsterTableItemEx.newBuilder()
				.setId(item.getId())
				.setColor(item.getColor())
				.setModel(item.getModel())
				.setName(item.getName())
				.setSkill(item.getSkill())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstMonsterTableProtobuf.ConstMonsterTable build = ConstMonsterTableProtobuf.ConstMonsterTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setMonsters(build);
	}


   
}