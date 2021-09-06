package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.AwardFomula;
import com.gejian.pixel.entity.PvpAward;
import com.gejian.pixel.entity.PvpRefresh;
import com.gejian.pixel.mapper.PvpAwardMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PvpAwardService;
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
public class PvpAwardServiceImpl extends ServiceImpl<PvpAwardMapper, PvpAward> implements PvpAwardService, ConstantsProto {

	private Map<Integer, PvpAward> hash = new HashMap<>();

	private List<ConstPvpAwardTableItemExProtobuf.ConstPvpAwardTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init(){
		List<PvpAward> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPvpAwardTableItemExProtobuf.ConstPvpAwardTableItemEx convert(PvpAward item) {
		String awardFomula = item.getAwardFomula();
		AwardFomula awardFomula1 = JSONUtil.toBean(awardFomula, AwardFomula.class);
		ConstPvpAwardTableItemExAwardFomulaProtobuf.ConstPvpAwardTableItemExAwardFomula build =
				ConstPvpAwardTableItemExAwardFomulaProtobuf.ConstPvpAwardTableItemExAwardFomula.newBuilder()
				.setFirstofday(awardFomula1.getFirstofday())
				.setOther(awardFomula1.getOther())
				.build();
		return ConstPvpAwardTableItemExProtobuf.ConstPvpAwardTableItemEx.newBuilder()
				.setAward(item.getAward())
				.setId(item.getId())
				.setVectoryTimes(item.getVectoryTimes())
				.setAwardFomula(build)
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPvpAwardTableProtobuf.ConstPvpAwardTable build = ConstPvpAwardTableProtobuf.ConstPvpAwardTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPvpAwards(build);
	}
}