package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gejian.pixel.dto.store.*;
import com.gejian.pixel.entity.*;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.entity.ext.StoreQueryDO;
import com.gejian.pixel.enums.ShopTypeEnum;
import com.gejian.pixel.enums.StoreCurrencyTypeEnum;
import com.gejian.pixel.enums.StoreTypeEnum;
import com.gejian.pixel.mapper.NewStoreGoodsMapper;
import com.gejian.pixel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class NewStoreGoodsServiceImpl extends ServiceImpl<NewStoreGoodsMapper, NewStoreGoods> implements NewStoreGoodsService {

	@Autowired
	private NewStoreTimeLimitService newStoreTimeLimitService;
	@Autowired
	private NewStoreDiscountService newStoreDiscountService;
	@Autowired
	private NewStoreHotService newStoreHotService;
	@Autowired
	private NamedService namedService;
	@Autowired
	private NewStoreRefreshService newStoreRefreshService;

	@Override
	public List<StoreTypeDTO> getType() {
		List<StoreTypeEnum> storeTypeEnums = Arrays.asList(StoreTypeEnum.values());
		return BeanUtil.copyToList(storeTypeEnums,StoreTypeDTO.class);
	}

	@Override
	public List<StoreGoodsDTO> getListByType(StoreTypeDTO storeTypeDTO) {
		StoreTypeEnum storeTypeEnum = StoreTypeEnum.valueOf(storeTypeDTO.getCode());
		if (Objects.equals(storeTypeEnum, StoreTypeEnum.GOLD)){
			return new ArrayList<>();
		}
		String[] range = storeTypeEnum.getRange().split("-");
		String prefix = storeTypeEnum.getPrefix();
		GoodsFomula goodsFomula = new GoodsFomula();
		goodsFomula.setFrom(Long.parseLong(range[0]));
		goodsFomula.setTo(Long.parseLong(range[1]));
		goodsFomula.setGoodPrefix(prefix);
		List<NewStoreGoods> list = baseMapper.selectnewStoreGoodsList(goodsFomula);
		if (CollectionUtils.isEmpty(list)){
			return new ArrayList<>();
		}
		return BeanUtil.copyToList(list, StoreGoodsDTO.class);
	}

	@Override
	public IPage<StorePageDTO> getPage(StoreQueryDTO storeQueryDTO) {
		//商品对应的名字
		List<String> skill = new ArrayList<>();
		List<String> soul = new ArrayList<>();
		List<String> exp = new ArrayList<>();
		long count = 0;
		if (Objects.nonNull(storeQueryDTO.getType())){
			LambdaQueryWrapper<NewStoreGoods> wrapper = Wrappers.<NewStoreGoods>lambdaQuery()
					.like(NewStoreGoods::getName, storeQueryDTO.getType());
			List<NewStoreGoods> newStoreGoodsList = baseMapper.selectList(wrapper);
			if (!CollectionUtils.isEmpty(newStoreGoodsList)){
				skill = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.BOOK_SKILL_AUTO.getPrefix()))
						.map(newStoreGoods -> newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_")))
						.collect(Collectors.toList());
				soul = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.PRIVATE_SOULCHIP_BLUE.getPrefix()))
						.map(newStoreGoods -> newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_")))
						.collect(Collectors.toList());
				exp = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.EXP_BOOK_1.getPrefix()))
						.map(newStoreGoods -> newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_")))
						.collect(Collectors.toList());
				count = newStoreGoodsList.stream().filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.GOLD.getPrefix())).count();
			}
		}
		String shop = null;
		if (Objects.nonNull(storeQueryDTO.getShop())){
			shop = ShopTypeEnum.valueOf(storeQueryDTO.getShop()).getType();
		}
		StoreQueryDO storeQueryDO = new StoreQueryDO();
		storeQueryDO.setCount(count);
		storeQueryDO.setExp(exp);
		storeQueryDO.setShop(shop);
		storeQueryDO.setSkill(skill);
		storeQueryDO.setSoul(soul);
		storeQueryDO.setCurrent(storeQueryDTO.getCurrent());
		storeQueryDO.setSize(storeQueryDTO.getSize());
		Integer total = baseMapper.selectCountByQuery(storeQueryDO);
		List<StorePageDTO> records = new ArrayList<>();
		if (total > 0) {
			List<StorePageDO> listStore = baseMapper.selectPageByQuery(storeQueryDO);
			listStore.forEach(storePageDO -> {
				GoodsFomula goodsFomula = JSONObject.parseObject(storePageDO.getGoodFomula()).toJavaObject(GoodsFomula.class);
				List<NewStoreGoods> newStoreGoodsList;
				if (Objects.equals(goodsFomula.getGoodPrefix(), StoreTypeEnum.GOLD.getPrefix())){
					NewStoreGoods newStoreGoods = new NewStoreGoods();
					DecimalFormat df = new DecimalFormat("#,###");
					String format = df.format(goodsFomula.getFrom());
					newStoreGoods.setName(StoreTypeEnum.GOLD.getType() + format);
					newStoreGoodsList = new ArrayList<>();
					newStoreGoodsList.add(newStoreGoods);
				} else {
					newStoreGoodsList = baseMapper.selectnewStoreGoodsList(goodsFomula);
				}
				StorePageDTO storePageDTO = new StorePageDTO();
				storePageDTO.setGoods(newStoreGoodsList);
				storePageDTO.setCount(goodsFomula.getNumber());
				storePageDTO.setPrice(goodsFomula.getCostNumber());
				storePageDTO.setShop(ShopTypeEnum.valueOfByType(storePageDO.getTableName()).getCode());
				storePageDTO.setStoreType(StoreTypeEnum.valueOfByPrefix(goodsFomula.getGoodPrefix() + "_" + goodsFomula.getFrom().toString().charAt(0)).getCode());
				storePageDTO.setId(storePageDO.getId());
				records.add(storePageDTO);
			});
		}
		Page<StorePageDTO> page = new Page<>(storeQueryDTO.getCurrent(),storeQueryDTO.getSize());
		page.setTotal(total);
		page.setRecords(records);
		return page;
	}

	@Override
	public Boolean saveByStore(StoreDTO storeDTO) {
		boolean boo;
		StorePageDO storePageDO = getStoreDetails(storeDTO);
		Integer places;
		switch (storeDTO.getShop()){
			case 1:
				places = newStoreDiscountService.list().stream().map(NewStoreDiscount::getPlaces).max(Integer::compareTo).get();
				break;
			case 2:
				places = newStoreHotService.list().stream().map(NewStoreHot::getPlaces).max(Integer::compareTo).get();
				break;
			default:
				places = newStoreTimeLimitService.list().stream().map(NewStoreTimeLimit::getPlaces).max(Integer::compareTo).get();
				break;
		}
		storePageDO.setPlaces(places + 1);
		switch (storeDTO.getShop()){
			case 1: boo = newStoreDiscountService.saveByStore(storePageDO); break;
			case 2: boo = newStoreHotService.saveByStore(storePageDO);break;
			default: boo =  newStoreTimeLimitService.saveByStore(storePageDO); break;
		}
		return boo;

	}

	private StorePageDO getStoreDetails(StoreDTO storeDTO) {
		StorePageDO storePageDO = new StorePageDO();
		try {
			GoodsFomula goodsFomula = new GoodsFomula();
			goodsFomula.setCost(StoreCurrencyTypeEnum.valueOf(storeDTO.getCost()).getType());
			goodsFomula.setCostNumber(storeDTO.getCostNumber());
			goodsFomula.setFactor("100");
			long form;
			long to;
			if (Objects.equals(StoreTypeEnum.GOLD.getCode(), storeDTO.getType())){
				form = storeDTO.getGoldNumber();
				to = storeDTO.getGoldNumber();
				//若是金币需添加物品资源
				LambdaQueryWrapper<Named> wrapper = Wrappers.<Named>lambdaQuery()
						.like(Named::getName, StoreTypeEnum.GOLD.getPrefix());
				List<Named> list = namedService.list(wrapper);
				long count = list.stream().filter(named -> Objects.equals(named.getName(), StoreTypeEnum.GOLD.getCode() + "_" + form)).count();
				if (count == 0){
					Named named = list.get(0);
					Named newNamed = BeanUtil.copyProperties(named, Named.class);
					newNamed.setName(StoreTypeEnum.GOLD.getCode() + "_" + form);
					DecimalFormat df = new DecimalFormat("#,###");
					String format = df.format(form);
					newNamed.setDesc(StoreTypeEnum.GOLD.getType() + format);
					namedService.save(newNamed);
				}
			} else {
				form = Integer.parseInt(storeDTO.getForm().substring(storeDTO.getForm().lastIndexOf("_") + 1));
				to = Integer.parseInt(storeDTO.getTo().substring(storeDTO.getTo().lastIndexOf("_") + 1));
			}
			goodsFomula.setFrom(Math.min(form,to));
			goodsFomula.setTo(Math.max(form,to));
			goodsFomula.setGoodPrefix(StoreTypeEnum.valueOf(storeDTO.getType()).getPrefix());
			goodsFomula.setNumber(storeDTO.getNumber());
			ObjectMapper mapper = new ObjectMapper();
			String goodsFomulaStr = mapper.writeValueAsString(goodsFomula);
			storePageDO.setGoodFomula(goodsFomulaStr);
			String items = "[" + StoreTypeEnum.valueOf(storeDTO.getType()).getPrefix() + "_("  + Math.min(form,to) + "-" +
					Math.max(form,to) + ")*100*" + storeDTO.getNumber()+",cost_"+ StoreCurrencyTypeEnum.valueOf(storeDTO.getCost()).getType() +
					"_(" + storeDTO.getCostNumber() + ")]";
			storePageDO.setItems(items);
			return storePageDO;
		} catch (JsonProcessingException e) {
			return storePageDO;
		}
	}

	@Override
	@Transactional
	public Boolean updateByStore(StoreDTO storeDTO) {
		//判断有没有修改商店
		if (Objects.nonNull(storeDTO.getOldShop()) && !Objects.equals(storeDTO.getOldShop(), storeDTO.getShop())){
			Boolean delete = deleteByStore(storeDTO);
			Boolean save = saveByStore(storeDTO);
			return delete && save;
		}
		boolean boo;
		StorePageDO storePageDO = getStoreDetails(storeDTO);
		storePageDO.setId(Long.parseLong(storeDTO.getId()));
		switch (storeDTO.getShop()){
			case 1: boo = newStoreDiscountService.updateByStore(storePageDO); break;
			case 2: boo = newStoreHotService.updateByStore(storePageDO);break;
			default: boo =  newStoreTimeLimitService.updateByStore(storePageDO); break;
		}
		return boo;
	}

	@Override
	public Boolean deleteByStore(StoreDTO storeDTO) {
		boolean boo;
		switch (storeDTO.getShop()){
			case 1: boo = newStoreDiscountService.deleteByStore(storeDTO.getId()); break;
			case 2: boo = newStoreHotService.deleteByStore(storeDTO.getId());break;
			default: boo =  newStoreTimeLimitService.deleteByStore(storeDTO.getId()); break;
		}
		return boo;
	}

	@Override
	public List<StoreRefreshDTO> getListRefresh() {
		List<NewStoreRefresh> list = newStoreRefreshService.list();
		return BeanUtil.copyToList(list, StoreRefreshDTO.class);
	}

	@Override
	public Boolean updateRefresh(StoreRefreshDTO storeRefreshDTO) {
		List<NewStoreRefresh> list = newStoreRefreshService.list();
		list.forEach(newStoreRefresh -> {
			if (Objects.equals(newStoreRefresh.getId(), storeRefreshDTO.getId())){
				newStoreRefresh.setCheckFlag(true);
			} else {
				newStoreRefresh.setCheckFlag(false);
			}
		});
		return newStoreRefreshService.updateBatchById(list);
	}
}