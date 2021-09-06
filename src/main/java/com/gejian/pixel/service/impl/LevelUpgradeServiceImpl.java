package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.mapper.LevelUpgradeMapper;
import com.gejian.pixel.proto.ConstLevelUpgradeTableItemExProtobuf;
import com.gejian.pixel.proto.ConstLevelUpgradeTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.LevelUpgradeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class LevelUpgradeServiceImpl extends ServiceImpl<LevelUpgradeMapper, LevelUpgrade>
		implements LevelUpgradeService, ConstantsProto {

	private Map<Integer,LevelUpgrade> hash = new HashMap<>();

	private List<ConstLevelUpgradeTableItemExProtobuf.ConstLevelUpgradeTableItemEx>
		table = new ArrayList<>();

	@Override
    public void init(){
		List<LevelUpgrade> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstLevelUpgradeTableItemExProtobuf.ConstLevelUpgradeTableItemEx convert(LevelUpgrade item) {
		return ConstLevelUpgradeTableItemExProtobuf.ConstLevelUpgradeTableItemEx
				.newBuilder()
				.setId(item.getId())
				.setStart0(item.getStart0())
				.setStart1(item.getStart1())
				.setStart2(item.getStart2())
				.setStart3(item.getStart3())
				.setStart4(item.getStart4())
				.setStart5(item.getStart5())
				.setStart6(item.getStart6())
				.setStart7(item.getStart7())
				.setStart8(item.getStart8())
				.setStart9(item.getStart9())
				.setStart10(item.getStart10())
				.setStart11(item.getStart11())
				.setStart12(item.getStart12())
				.setStart13(item.getStart13())
				.setStart14(item.getStart14())
				.setStart15(item.getStart15())
				.setStart16(item.getStart16())
				.setStart17(item.getStart17())
				.setStart18(item.getStart18())
				.setStart19(item.getStart19())
				.setStart20(item.getStart20())
				.setStart21(item.getStart21())
				.setStart22(item.getStart22())
				.setStart23(item.getStart23())
				.setStart24(item.getStart24())
				.setStart25(item.getStart25())
				.setLv(item.getLv())
				.build();
	}

	@Override
	public LevelUpgrade get(Integer id){
		return hash.get(id);
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstLevelUpgradeTableProtobuf.ConstLevelUpgradeTable build =
				ConstLevelUpgradeTableProtobuf.ConstLevelUpgradeTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setLevelUpgrades(build);
	}
}