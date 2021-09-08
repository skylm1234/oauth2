package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.SkillScriptRole;
import com.gejian.pixel.entity.StarUpgrade;
import com.gejian.pixel.mapper.SkillScriptRoleMapper;
import com.gejian.pixel.proto.ConstSkillScriptsRoleTableItemExProtobuf;
import com.gejian.pixel.proto.ConstSkillScriptsRoleTableProtobuf;
import com.gejian.pixel.proto.ConstStarUpgradeTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.SkillScriptRoleService;
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
public class SkillScriptRoleServiceImpl extends ServiceImpl<SkillScriptRoleMapper, SkillScriptRole>
		implements SkillScriptRoleService, ConstantsProto {


	private Map<String, SkillScriptRole> hash = new HashMap<>();

	private List<ConstSkillScriptsRoleTableItemExProtobuf.ConstSkillScriptsRoleTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<SkillScriptRole> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getName(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstSkillScriptsRoleTableItemExProtobuf.ConstSkillScriptsRoleTableItemEx convert(SkillScriptRole item) {
		return ConstSkillScriptsRoleTableItemExProtobuf.ConstSkillScriptsRoleTableItemEx.newBuilder()
				.setAttack(item.getAttack())
				.setName(item.getName())
				.setCrit(item.getCrit())
				.setDefense(item.getDefense())
				.setAttack01Audio(item.getAttack01Audio())
				.setAttack02Audio(item.getAttack02Audio())
				.setAttack03Audio(item.getAttack03Audio())
				.setDeathAudio(item.getDeathAudio())
				.setDge(item.getDodge())
				.setHitAudio(item.getHitAudio())
				.setHp(item.getHp())
				.setNormalSkill(item.getNormalSkill())
				.setSkills(item.getSkills())
				.setSpeed(item.getSpeed())
				.setType(item.getType())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstSkillScriptsRoleTableProtobuf.ConstSkillScriptsRoleTable build =
				ConstSkillScriptsRoleTableProtobuf.ConstSkillScriptsRoleTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setSkillScriptsRoles(build);
	}
}