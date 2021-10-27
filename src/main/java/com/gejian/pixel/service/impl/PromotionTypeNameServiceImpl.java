package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.activity.ActivityGroupDTO;
import com.gejian.pixel.dto.activity.type.ActivityGroupTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypePageDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeQueryDTO;
import com.gejian.pixel.entity.PromotionTypeName;
import com.gejian.pixel.mapper.PromotionTypeNameMapper;
import com.gejian.pixel.proto.ConstPromotionTypeNameTableItemExProtobuf;
import com.gejian.pixel.proto.ConstPromotionTypeNameTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.PromotionTypeNameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class PromotionTypeNameServiceImpl extends ServiceImpl<PromotionTypeNameMapper, PromotionTypeName> implements PromotionTypeNameService , ConstantsProto {

	private Map<Integer, PromotionTypeName> hash = new HashMap<>();

	private List<ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx> table = new ArrayList<>();

	private static final String ID_TYPE = "type";
	private static final String ID_GROUP = "group";

	@Override
	public void init(){
		List<PromotionTypeName> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getType(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx convert(PromotionTypeName item) {

		return ConstPromotionTypeNameTableItemExProtobuf.ConstPromotionTypeNameTableItemEx.newBuilder()
				.setType(item.getType())
				.setName(item.getName())
				.setQ(item.getQ())
				.setGroup(item.getGroup())
				.setGroupName(item.getGroupName())
				.build();

	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstPromotionTypeNameTableProtobuf.ConstPromotionTypeNameTable build=
				ConstPromotionTypeNameTableProtobuf.ConstPromotionTypeNameTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setPromotionsNames(build);
	}


	@Override
	public List<ActivityGroupDTO> listToGroup() {
		List<PromotionTypeName> list = this.list(Wrappers.<PromotionTypeName>lambdaQuery().orderByAsc(PromotionTypeName::getQ));
		List<ActivityGroupDTO> result = new ArrayList<>();
		Map<Integer, List<PromotionTypeName>> map = list.stream().collect(Collectors.groupingBy(PromotionTypeName::getGroup, Collectors.toList()));
		map.forEach((key,value) -> {
			for(PromotionTypeName promotionTypeName : list){
				if(promotionTypeName.getGroup().equals(key)){
					ActivityGroupDTO activityGroupDTO = new ActivityGroupDTO();
					activityGroupDTO.setType(key);
					activityGroupDTO.setName(promotionTypeName.getGroupName());
					List<ActivityGroupDTO> children = value.stream().map(child ->{
								ActivityGroupDTO dto = new ActivityGroupDTO();
								dto.setType(child.getType());
								dto.setName(child.getName());
								return dto;
							}).collect(Collectors.toList());
					activityGroupDTO.setChildren(children);
					result.add(activityGroupDTO);
					break;
				}
			}
		});
		return result;
	}

	@Override
	public IPage<ActivityTypePageDTO> selectPage(ActivityTypeQueryDTO activityTypeQueryDTO) {
		long count = baseMapper.countOfView(activityTypeQueryDTO.getTypeName());
		if(count <= 0){
			return new Page<>();
		}
		int start = (activityTypeQueryDTO.getCurrent() - 1) * activityTypeQueryDTO.getSize();
		List<ActivityTypePageDTO> result = baseMapper.pageOfView(activityTypeQueryDTO.getTypeName(), start, activityTypeQueryDTO.getSize());
		return new Page<ActivityTypePageDTO>(activityTypeQueryDTO.getCurrent(),activityTypeQueryDTO.getSize(),count).setRecords(result);
	}

	@Override
	public void save(ActivityTypeDTO activityTypeDTO) {
		PromotionTypeName promotionTypeName = new PromotionTypeName();
		List<PromotionTypeName> list = this.lambdaQuery().eq(PromotionTypeName::getGroupName, activityTypeDTO.getParentTypeName()).list();
		promotionTypeName.setName(activityTypeDTO.getTypeName());
		promotionTypeName.setQ(activityTypeDTO.getIndex());
		promotionTypeName.setGroupName(activityTypeDTO.getParentTypeName());
		if(org.springframework.util.CollectionUtils.isEmpty(list)){
			int index = baseMapper.selectMaxGroupIndex() + 1;
			promotionTypeName.setGroup(index);
		}else{
			promotionTypeName.setGroup(list.get(0).getGroup());
		}
		this.save(promotionTypeName);
	}

	@Override
	public void update(ActivityTypeDTO activityTypeDTO) {
		String[] idSplit = StringUtils.split(activityTypeDTO.getTypeId(), "_");
		int realId = Integer.parseInt(idSplit[1]);
		String idType = idSplit[0];
		if(ID_TYPE.equalsIgnoreCase(idType)){
			PromotionTypeName promotionTypeName = new PromotionTypeName();
			List<PromotionTypeName> list = this.lambdaQuery().eq(PromotionTypeName::getGroupName, activityTypeDTO.getParentTypeName()).list();
			promotionTypeName.setType(realId);
			promotionTypeName.setName(activityTypeDTO.getTypeName());
			promotionTypeName.setQ(activityTypeDTO.getIndex());
			promotionTypeName.setGroupName(activityTypeDTO.getParentTypeName());
			if(org.springframework.util.CollectionUtils.isEmpty(list)){
				int index = baseMapper.selectMaxGroupIndex() + 1;
				promotionTypeName.setGroup(index);
			}else{
				promotionTypeName.setGroup(list.get(0).getGroup());
			}
			this.updateById(promotionTypeName);
		}else if(ID_GROUP.equalsIgnoreCase(idType)){
			PromotionTypeName promotionTypeName = new PromotionTypeName();
			promotionTypeName.setGroupName(activityTypeDTO.getTypeName());
			promotionTypeName.setGroup(realId);
			baseMapper.updateGroupName(promotionTypeName);
		}
	}

	@Override
	public void delete(String id) {
		String[] idSplit = StringUtils.split(id, "_");
		int realId = Integer.parseInt(idSplit[1]);
		String idType = idSplit[0];
		if(ID_TYPE.equalsIgnoreCase(idType)){
			this.removeById(realId);
		}else if(ID_GROUP.equalsIgnoreCase(idType)){
			List<PromotionTypeName> list = this.lambdaQuery().eq(PromotionTypeName::getGroup, realId).list();
			if(list.size() > 1){
				throw new RuntimeException("该分组下包含至少两条子分类，无法删除");
			}else{
				this.removeById(list.get(0).getType());
			}
		}
	}

	@Override
	public List<ActivityGroupTypeDTO> groups() {
		return baseMapper.selectGroups();
	}
}