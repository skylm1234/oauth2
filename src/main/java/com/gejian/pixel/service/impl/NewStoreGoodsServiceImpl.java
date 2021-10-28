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
import com.gejian.pixel.dto.store.StoreDTO;
import com.gejian.pixel.dto.store.StoreGoodsDTO;
import com.gejian.pixel.dto.store.StorePageDTO;
import com.gejian.pixel.dto.store.StoreQueryDTO;
import com.gejian.pixel.dto.store.StoreRefreshDTO;
import com.gejian.pixel.dto.store.StoreTypeDTO;
import com.gejian.pixel.entity.GoodsFomula;
import com.gejian.pixel.entity.Named;
import com.gejian.pixel.entity.NewStoreGoods;
import com.gejian.pixel.entity.NewStoreRefresh;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.entity.ext.StoreQueryDO;
import com.gejian.pixel.enums.ShopTypeEnum;
import com.gejian.pixel.enums.StoreCurrencyTypeEnum;
import com.gejian.pixel.enums.StoreTypeEnum;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.mapper.NewStoreGoodsMapper;
import com.gejian.pixel.service.NamedService;
import com.gejian.pixel.service.NewStoreDiscountService;
import com.gejian.pixel.service.NewStoreGoodsService;
import com.gejian.pixel.service.NewStoreHotService;
import com.gejian.pixel.service.NewStoreRefreshService;
import com.gejian.pixel.service.NewStoreTimeLimitService;
import com.gejian.pixel.service.StoreViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

	@Autowired
	private StoreViewService storeViewService;

	@Override
	public List<StoreTypeDTO> getType() {
		List<StoreTypeEnum> storeTypeEnums = Arrays.asList(StoreTypeEnum.values());
		return BeanUtil.copyToList(storeTypeEnums,StoreTypeDTO.class);
	}

	@Override
	public List<StoreGoodsDTO> getListByType(StoreTypeEnum type) {
		if (type == StoreTypeEnum.GOLD){
			return new ArrayList<>();
		}
		String[] range = type.getRange().split("-");
		String prefix = type.getPrefix();
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
		//商品对应的id
		List<Integer> skill = new ArrayList<>();
		List<Integer> soul = new ArrayList<>();
		List<Integer> exp = new ArrayList<>();
		long count = 0;
		List<NewStoreGoods> newStoreGoodsList;
		LambdaQueryWrapper<NewStoreGoods> wrapper = new LambdaQueryWrapper<>();
		if (Objects.nonNull(storeQueryDTO.getContent())){
			wrapper.like(NewStoreGoods::getName, storeQueryDTO.getContent());
			newStoreGoodsList = baseMapper.selectList(wrapper);
			if (!CollectionUtils.isEmpty(newStoreGoodsList)){
				skill = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.BOOK_SKILL_AUTO.getPrefix()))
						.map(newStoreGoods -> Integer.parseInt(newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_") + 1)))
						.collect(Collectors.toList());
				soul = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.PRIVATE_SOULCHIP_BLUE.getPrefix()))
						.map(newStoreGoods -> Integer.parseInt(newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_") +1 )))
						.collect(Collectors.toList());
				exp = newStoreGoodsList.stream()
						.filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.EXP_BOOK_1.getPrefix()))
						.map(newStoreGoods -> Integer.parseInt(newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_") + 1 )))
						.collect(Collectors.toList());
				count = newStoreGoodsList.stream().filter(newStoreGoods -> newStoreGoods.getId().contains(StoreTypeEnum.GOLD.getPrefix())).count();
			}
		}else {
			newStoreGoodsList = baseMapper.selectList(wrapper);
		}
		StoreQueryDO storeQueryDO = new StoreQueryDO();
		storeQueryDO.setCount(count);
		storeQueryDO.setExp(exp);
		storeQueryDO.setType(storeQueryDTO.getShop());
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
				List<NewStoreGoods> subList;
				if (Objects.equals(goodsFomula.getGoodPrefix(), StoreTypeEnum.GOLD.getPrefix())){
					NewStoreGoods newStoreGoods = new NewStoreGoods();
					newStoreGoods.setName(StoreTypeEnum.GOLD.getType());
					subList = new ArrayList<>();
					subList.add(newStoreGoods);
				} else {
					subList = newStoreGoodsList.stream().filter(newStoreGoods -> !newStoreGoods.getId().contains(StoreTypeEnum.GOLD.getPrefix())).filter(newStoreGoods -> {
						String prefix = goodsFomula.getGoodPrefix();
						int id = Integer.parseInt(newStoreGoods.getId().substring(newStoreGoods.getId().lastIndexOf("_" ) + 1));
						return newStoreGoods.getId().contains(prefix) && id >= goodsFomula.getFrom() && id <= goodsFomula.getTo();
					}).collect(Collectors.toList());
				}
				StorePageDTO storePageDTO = new StorePageDTO();
				storePageDTO.setGoods(subList);
				storePageDTO.setCount(goodsFomula.getNumber());
				storePageDTO.setPrice(goodsFomula.getCostNumber());
				storePageDTO.setShop(storePageDO.getType());
				if(StoreTypeEnum.GOLD.getPrefix().equals(goodsFomula.getGoodPrefix())){
					storePageDTO.setStoreType(StoreTypeEnum.GOLD.getCode());
				}else{
					storePageDTO.setStoreType(StoreTypeEnum.valueOfByPrefix(goodsFomula.getGoodPrefix() + "_" + goodsFomula.getFrom().toString().charAt(0)).getCode());
				}
				storePageDTO.setId(storePageDO.getId());
				storePageDTO.setContent(subList.get(0).getName());
				records.add(storePageDTO);
			});
		}
		Page<StorePageDTO> page = new Page<>(storeQueryDTO.getCurrent(),storeQueryDTO.getSize());
		page.setTotal(total);
		page.setRecords(records);
		return page;
	}

	@Override
	public void saveByStore(StoreDTO storeDTO) {
		StorePageDO storePageDO = getStoreDetails(storeDTO);
		int places;
		switch (storeDTO.getShop()){
			case NEW_STORE_DISCOUNT:
				places = newStoreDiscountService.maxPlaces() + 1;
				storePageDO.setPlaces(places);
				newStoreDiscountService.saveByStore(storePageDO);
				break;
			case NEW_STORE_HOT:
				places = newStoreHotService.maxPlaces() + 1;
				storePageDO.setPlaces(places);
				newStoreHotService.saveByStore(storePageDO);
				break;
			case NEW_STORE_TIME_LIMIT:
				places = newStoreTimeLimitService.maxPlaces() + 1;
				storePageDO.setPlaces(places);
				newStoreTimeLimitService.saveByStore(storePageDO);
			default:
				break;
		}
	}

	private StorePageDO getStoreDetails(StoreDTO storeDTO) {
		StorePageDO storePageDO = new StorePageDO();
		try {
			GoodsFomula goodsFomula = new GoodsFomula();
			goodsFomula.setCost(storeDTO.getCost().getType());
			goodsFomula.setCostNumber(storeDTO.getPrice());
			goodsFomula.setFactor("100");
			long from;
			long to;
			if (StoreTypeEnum.GOLD == storeDTO.getType()){
				if(storeDTO.getGoldNumber() == null || storeDTO.getGoldNumber() <= 0){
					throw  new RuntimeException("金币数量(goldNumber)输入异常！");
				}
				from = storeDTO.getGoldNumber();
				to = storeDTO.getGoldNumber();
				//若是金币需添加物品资源
				LambdaQueryWrapper<Named> wrapper = Wrappers.<Named>lambdaQuery()
						.like(Named::getName, StoreTypeEnum.GOLD.getPrefix());
				List<Named> list = namedService.list(wrapper);
				long count = list.stream().filter(named -> Objects.equals(named.getName(), StoreTypeEnum.GOLD.getCode() + "_" + from)).count();
				if (count == 0){
					Named named = list.get(0);
					Named newNamed = BeanUtil.copyProperties(named, Named.class);
					newNamed.setName(StoreTypeEnum.GOLD.getCode() + "_" + from);
					DecimalFormat df = new DecimalFormat("#,###");
					String format = df.format(from);
					newNamed.setDesc(StoreTypeEnum.GOLD.getType() + format);
					namedService.save(newNamed);
				}
			} else {
				from = Integer.parseInt(storeDTO.getFrom().substring(storeDTO.getFrom().lastIndexOf("_") + 1));
				to = Integer.parseInt(storeDTO.getTo().substring(storeDTO.getTo().lastIndexOf("_") + 1));
			}
			goodsFomula.setFrom(Math.min(from,to));
			goodsFomula.setTo(Math.max(from,to));
			goodsFomula.setGoodPrefix(storeDTO.getType().getPrefix());
			goodsFomula.setNumber(storeDTO.getNumber());
			ObjectMapper mapper = new ObjectMapper();
			String goodsFomulaStr = mapper.writeValueAsString(goodsFomula);
			storePageDO.setGoodFomula(goodsFomulaStr);
			String items = "[" + storeDTO.getType().getPrefix() + "_("  + Math.min(from,to) + "-" +
					Math.max(from,to) + ")*100*" + storeDTO.getNumber()+",cost_"+ storeDTO.getCost().getType() +
					"_(" + storeDTO.getPrice() + ")]";
			storePageDO.setItems(items);
			storePageDO.setType(0);
			return storePageDO;
		} catch (JsonProcessingException e) {
			return storePageDO;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateByStore(StoreDTO storeDTO) {
		StorePageDO storePageDO = baseMapper.selectViewById(storeDTO.getId());
		if(storePageDO == null){
			throw new ResourceNotFoundException();
		}
		//判断有没有修改商店
		ShopTypeEnum oldShop = ShopTypeEnum.valueOfByType(storeDTO.getId().substring(0, storeDTO.getId().lastIndexOf("_")));
		if (oldShop != storeDTO.getShop()){
			deleteByStore(storeDTO.getId());
			saveByStore(storeDTO);
			return;
		}
		storePageDO = getStoreDetails(storeDTO);
		storePageDO.setId(storeDTO.getId().substring(storeDTO.getId().lastIndexOf("_") + 1));
		switch (storeDTO.getShop()){
			case NEW_STORE_DISCOUNT: newStoreDiscountService.updateByStore(storePageDO); break;
			case NEW_STORE_HOT: newStoreHotService.updateByStore(storePageDO);break;
			case NEW_STORE_TIME_LIMIT: newStoreTimeLimitService.updateByStore(storePageDO); break;
			default: break;
		}
	}

	@Override
	public void deleteByStore(String id) {
		String tableId = id.substring(id.lastIndexOf("_") + 1);
		String shopType = id.substring(0,id.lastIndexOf("_"));
		ShopTypeEnum shopTypeEnum = ShopTypeEnum.valueOfByType(shopType);
		switch (shopTypeEnum){
			case NEW_STORE_DISCOUNT:  newStoreDiscountService.deleteByStore(tableId); break;
			case NEW_STORE_HOT: newStoreHotService.deleteByStore(tableId);break;
			case NEW_STORE_TIME_LIMIT:  newStoreTimeLimitService.deleteByStore(tableId); break;
			default: break;
		}
	}

	@Override
	public List<StoreRefreshDTO> getListRefresh() {
		List<NewStoreRefresh> list = newStoreRefreshService.list();
		return BeanUtil.copyToList(list, StoreRefreshDTO.class);
	}

	@Override
	public Boolean updateRefresh(StoreRefreshDTO storeRefreshDTO) {
		List<NewStoreRefresh> list = newStoreRefreshService.list();
		list.forEach(newStoreRefresh -> newStoreRefresh.setCheckFlag(Objects.equals(newStoreRefresh.getId(), storeRefreshDTO.getId())));
		return newStoreRefreshService.updateBatchById(list);
	}

	@Override
	public Optional<StoreDTO> selectById(String id) {
		StorePageDO storePageDO = baseMapper.selectViewById(id);
		if(storePageDO == null){
			return Optional.empty();
		}
		StoreDTO storeDTO = new StoreDTO();
		GoodsFomula goodsFomula = JSONObject.parseObject(storePageDO.getGoodFomula()).toJavaObject(GoodsFomula.class);
		if(StoreTypeEnum.GOLD.getPrefix().equals(goodsFomula.getGoodPrefix())){
			storeDTO.setType(StoreTypeEnum.GOLD);
			storeDTO.setGoldNumber(goodsFomula.getFrom());
		}else{
			storeDTO.setType(StoreTypeEnum.valueOfByPrefix(storePageDO.getPrefix() + "_" + storePageDO.getFrom().toString().charAt(0)));
		}
		storeDTO.setId(id);
		storeDTO.setFrom(storePageDO.getPrefix() + "_" + storePageDO.getFrom());
		storeDTO.setTo(storePageDO.getPrefix() + "_" + storePageDO.getTo());
		storeDTO.setCost(StoreCurrencyTypeEnum.valueOf(goodsFomula.getCost().toUpperCase()));
		storeDTO.setPrice(goodsFomula.getCostNumber());
		storeDTO.setShop(ShopTypeEnum.valueOf(id.substring(0,id.lastIndexOf("_")).toUpperCase()));
		storeDTO.setNumber(goodsFomula.getNumber());
		return Optional.of(storeDTO);
	}
}