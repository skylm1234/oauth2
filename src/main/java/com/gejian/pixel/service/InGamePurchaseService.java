package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchaseDTO;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchasePageDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderPageDTO;
import com.gejian.pixel.entity.InGamePurchase;

import java.util.Optional;

/**
 *  Auto created by codeAppend plugin
 */
public interface InGamePurchaseService extends IService<InGamePurchase> {

	InGamePurchase getById(String id);

	/**
	 * 分页查询充值管理列表
	 * @param inGamePurchasePageDTO  分页条件
	 * @return List
	 */
	IPage<InGamePurchaseDTO> getPage(InGamePurchasePageDTO inGamePurchasePageDTO);

	/**
	 * 充值记录分页查询
	 * @param orderPageDTO  分页条件
	 * @return  IPage
	 */
	IPage<OrderDTO> getPageOrder(OrderPageDTO orderPageDTO);

	Optional<InGamePurchase> selectById(String id);
}