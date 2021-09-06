package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.SkillScriptSkill;
import com.gejian.pixel.mapper.SkillScriptSkillMapper;
import com.gejian.pixel.proto.ConstSkillScriptsSkillTableItemExProtobuf;
import com.gejian.pixel.proto.ConstSkillScriptsSkillTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.SkillScriptSkillService;
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
public class SkillScriptSkillServiceImpl extends ServiceImpl<SkillScriptSkillMapper, SkillScriptSkill> implements SkillScriptSkillService , ConstantsProto {


	private Map<String, SkillScriptSkill> hash=new HashMap<>();

	private List<ConstSkillScriptsSkillTableItemExProtobuf.ConstSkillScriptsSkillTableItemEx> table=new ArrayList<>();

	@Override
	public void init(){
		List<SkillScriptSkill> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstSkillScriptsSkillTableItemExProtobuf.ConstSkillScriptsSkillTableItemEx convert(SkillScriptSkill item){

		return ConstSkillScriptsSkillTableItemExProtobuf.ConstSkillScriptsSkillTableItemEx
				.newBuilder()
				.setId(String.valueOf(item.getId()))
				.setDescription(item.getDescription())
				.setEffect(item.getEffect())
				.setIcon(item.getIcon())
				.setAction(item.getAction())
				.setLevels(item.getLevels())
				.setLogic(item.getLogic())
				.setName(item.getName())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstSkillScriptsSkillTableProtobuf.ConstSkillScriptsSkillTable build=
				ConstSkillScriptsSkillTableProtobuf.ConstSkillScriptsSkillTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setSkillScriptsSkills(build);
	}


}