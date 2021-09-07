package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Vip;
import com.gejian.pixel.entity.VipDaily;
import com.gejian.pixel.mapper.VipDailyMapper;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.proto.ConstVipTableItemExProtobuf;
import com.gejian.pixel.proto.ConstVipTableProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.VipDailyService;
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
public class VipDailyServiceImpl extends ServiceImpl<VipDailyMapper, VipDaily> implements VipDailyService
		, ConstantsProto {

	private Map<Integer, VipDaily> hash = new HashMap<>();


	@Override
	public VipDaily getById(Integer id) {
		return hash.get(id);
	}

	@Override
	public void init(){
		List<VipDaily> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
			});
		}
	}




	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {

	}
   
}