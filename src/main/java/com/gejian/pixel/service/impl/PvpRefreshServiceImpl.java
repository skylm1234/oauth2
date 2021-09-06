package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ConsumeFomula;
import com.gejian.pixel.entity.PvpBasic;
import com.gejian.pixel.entity.PvpRefresh;
import com.gejian.pixel.mapper.PvpRefreshMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PvpRefreshService;
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
public class PvpRefreshServiceImpl extends ServiceImpl<PvpRefreshMapper, PvpRefresh> implements PvpRefreshService
		, ConstantsProto {

	private Map<Integer, PvpRefresh> hash = new HashMap<>();

	private List<ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx>
			table = new ArrayList<>();

	@PostConstruct
	public void init(){
		List<PvpRefresh> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx convert(PvpRefresh item) {
		String consumeFomula = item.getConsumeFomula();
		ConsumeFomula consumeFomula1 = JSONUtil.toBean(consumeFomula, ConsumeFomula.class);
		ConstPvpRefreshTableItemExConsumeFomulaProtobuf.ConstPvpRefreshTableItemExConsumeFomula build = ConstPvpRefreshTableItemExConsumeFomulaProtobuf.ConstPvpRefreshTableItemExConsumeFomula.newBuilder()
				.setStone(consumeFomula1.getStone())
				.build();
		return ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx
				.newBuilder()
				.setConsume(item.getConsume())
				.setRefreshTime(item.getRefreshTime())
				.setId(item.getId())
				.setBattleTimesEachRefresh(item.getBattleTimesEachRefresh())
				.setConsumeFomula(build)
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPvpRefreshTableProtobuf.ConstPvpRefreshTable build = ConstPvpRefreshTableProtobuf.ConstPvpRefreshTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPvpRefreshs(build);
	}
}