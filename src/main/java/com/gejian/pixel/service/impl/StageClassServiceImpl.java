package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.stage.StageTypeDTO;
import com.gejian.pixel.entity.StageClass;
import com.gejian.pixel.mapper.StageClassMapper;
import com.gejian.pixel.proto.ConstStageClassTableItemExProtobuf;
import com.gejian.pixel.proto.ConstStageClassTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.StageClassService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StageClassServiceImpl extends ServiceImpl<StageClassMapper, StageClass>
		implements StageClassService, ConstantsProto {

	private Map<Integer, StageClass> hash = new HashMap<>();

	private List<ConstStageClassTableItemExProtobuf.ConstStageClassTableItemEx> table = new ArrayList<>();

	@Override
	public void init() {
		List<StageClass> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	private ConstStageClassTableItemExProtobuf.ConstStageClassTableItemEx convert(StageClass item) {
		return ConstStageClassTableItemExProtobuf.ConstStageClassTableItemEx
				.newBuilder()
				.setClassType(item.getClassType())
				.setId(item.getId())
				.setName(item.getName()).build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstStageClassTableProtobuf.ConstStageClassTable build =
				ConstStageClassTableProtobuf.ConstStageClassTable.newBuilder()
				.addAllItems(table).build();
		builder.setStageClass(build);
	}

	@Override
	public List<StageTypeDTO> getType() {
		List<StageClass> list = this.list();
		return list.stream().map(stageClass -> new StageTypeDTO(stageClass.getClassType(),stageClass.getName()))
				.collect(Collectors.toList());
	}
}