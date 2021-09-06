package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Skill;
import com.gejian.pixel.entity.Skill2;
import com.gejian.pixel.mapper.Skill2Mapper;
import com.gejian.pixel.proto.ConstSkill2TableItemExProtobuf;
import com.gejian.pixel.proto.ConstSkill2TableProtobuf;
import com.gejian.pixel.proto.ConstSkillTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.Skill2Service;
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
public class Skill2ServiceImpl extends ServiceImpl<Skill2Mapper, Skill2>
		implements Skill2Service, ConstantsProto {

	private Map<String, Skill2> hash = new HashMap<>();

	private List<ConstSkill2TableItemExProtobuf.ConstSkill2TableItemEx>
			table = new ArrayList<>();

	@Override
	public void init() {
		List<Skill2> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	private ConstSkill2TableItemExProtobuf.ConstSkill2TableItemEx convert(Skill2 item) {
		return ConstSkill2TableItemExProtobuf.ConstSkill2TableItemEx.newBuilder()
				.setAction(item.getAction())
				.setDescription(item.getDescription())
				.setEffect(item.getEffect())
				.setIcon(item.getIcon())
				.setId(item.getId())
				.setLevels(item.getLevels())
				.setLogic(item.getLogic())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstSkill2TableProtobuf.ConstSkill2Table build = ConstSkill2TableProtobuf.ConstSkill2Table.newBuilder()
				.addAllItems(table)
				.build();
		builder.setSkills2(build);
	}

}