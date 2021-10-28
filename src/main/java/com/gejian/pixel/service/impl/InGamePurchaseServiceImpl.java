package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchaseDTO;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchasePageDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderPageDTO;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.entity.Order;
import com.gejian.pixel.mapper.InGamePurchaseMapper;
import com.gejian.pixel.proto.ConstInGamePurchaseTableItemExProtobuf;
import com.gejian.pixel.proto.ConstInGamePurchaseTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.InGamePurchaseService;
import com.gejian.pixel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class InGamePurchaseServiceImpl extends ServiceImpl<InGamePurchaseMapper, InGamePurchase>
		implements InGamePurchaseService, ConstantsProto {

	@Autowired
	private OrderService orderService;

	private Map<String, InGamePurchase> hash = new HashMap<>();

	private List<ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx>
			table = new ArrayList<>();


	@Override
	public InGamePurchase getById(String id){
		return hash.get(id);
	}

	@Override
	public IPage<InGamePurchaseDTO> getPage(InGamePurchasePageDTO inGamePurchasePageDTO) {
		LambdaQueryWrapper<InGamePurchase> wrapper = Wrappers.<InGamePurchase>lambdaQuery()
				.orderByAsc(InGamePurchase::getId);
		Page<InGamePurchase> page = baseMapper.selectPage(inGamePurchasePageDTO.page(), wrapper);
		if (CollectionUtils.isEmpty(page.getRecords())){
			return new Page<>(inGamePurchasePageDTO.getCurrent(), inGamePurchasePageDTO.getSize());
		}
		List<InGamePurchaseDTO> responseDTOList = page.getRecords().stream()
				.map(inGamePurchase -> {
					InGamePurchaseDTO inGamePurchaseDTO = new InGamePurchaseDTO();
					inGamePurchaseDTO.setCost(inGamePurchase.getCost());
					inGamePurchaseDTO.setDesc(inGamePurchase.getDesc());
					inGamePurchaseDTO.setIcon(inGamePurchase.getIcon());
					inGamePurchaseDTO.setId(inGamePurchase.getId());
					inGamePurchaseDTO.setStone(inGamePurchase.getStone());
					return inGamePurchaseDTO;
				}).collect(Collectors.toList());
		Page<InGamePurchaseDTO> resultPage = new Page<>(inGamePurchasePageDTO.getCurrent(), inGamePurchasePageDTO.getSize(), page.getTotal());
		resultPage.setRecords(responseDTOList);
		return resultPage;
	}

	@Override
	public IPage<OrderDTO> getPageOrder(OrderPageDTO orderPageDTO) {
		LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
		if (Objects.nonNull(orderPageDTO.getOrderId())){
			wrapper.eq(Order::getOrderId, orderPageDTO.getOrderId());
		}
		if (Objects.nonNull(orderPageDTO.getUser())){
			wrapper.like(Order::getUser, orderPageDTO.getUser());
		}
		Page<Order> page = orderService.page(orderPageDTO.page(), wrapper);
		return page.convert(order -> BeanUtil.copyProperties(order, OrderDTO.class));
	}

	@Override
	public Optional<InGamePurchase> selectById(String id) {
		InGamePurchase inGamePurchase = baseMapper.selectById(id);
		return Optional.ofNullable(inGamePurchase);
	}

	@Override
	public void init(){
		List<InGamePurchase> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	private ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx convert(InGamePurchase item) {
		return ConstInGamePurchaseTableItemExProtobuf.ConstInGamePurchaseTableItemEx
				.newBuilder()
				.setId(item.getId())
				.setCost(item.getCost())
				.setStone(item.getStone())
				.setDesc(item.getDesc())
				.setIcon(item.getIcon())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstInGamePurchaseTableProtobuf.ConstInGamePurchaseTable build = ConstInGamePurchaseTableProtobuf.
				ConstInGamePurchaseTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setInGamePurchases(build);
	}
}