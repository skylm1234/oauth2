package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.BuyHero;
import com.gejian.pixel.mapper.BuyHeroMapper;
import com.gejian.pixel.proto.ConstBuyHeroTableItemExProtobuf;
import com.gejian.pixel.proto.ConstBuyHeroTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.BuyHeroService;
import com.gejian.pixel.service.ConstantsProto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.script.*;
import java.util.*;
import java.util.logging.Handler;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class BuyHeroServiceImpl extends ServiceImpl<BuyHeroMapper, BuyHero>
		implements BuyHeroService, ConstantsProto {

	private Map<Integer, BuyHero> hash = new HashMap<>();

	private List<ConstBuyHeroTableItemExProtobuf.ConstBuyHeroTableItemEx> table = new ArrayList<>();

	private ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");

	@Override
	public void init() {
		List<BuyHero> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getType(), item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public double calculation(Integer id, Integer count) throws ScriptException {
		BuyHero buyHero = hash.get(id);
		if (Objects.isNull(buyHero)) {
			return 0;
		}
		SimpleBindings simpleBindings = new SimpleBindings();
		simpleBindings.put("count", count);
		return (Double)scriptEngine.eval(buyHero.getAmount(), simpleBindings);
	}





	/**
	 * @param item
	 * @return
	 */
	private ConstBuyHeroTableItemExProtobuf.ConstBuyHeroTableItemEx
	convert(BuyHero item) {
		return ConstBuyHeroTableItemExProtobuf.ConstBuyHeroTableItemEx.newBuilder()
				.setType(item.getType())
				.setChips(item.getChips())
				.setCoolwn(item.getCooldown())
				.setDayLimit(item.getDayLimit())
				.setAmount(item.getAmount())
				.setType(item.getType())
				.setDesc(item.getDesc())
				.setAward(item.getAward())
				.setConsume(item.getConsume())
				.setDropid(item.getDropid())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstBuyHeroTableProtobuf.ConstBuyHeroTable build =
				ConstBuyHeroTableProtobuf.ConstBuyHeroTable.newBuilder()
						.addAllItems(table)
						.build();
		builder.setBuyHeros(build);
	}

	@Override
	public BuyHero getHero(int type) {
		return hash.get(type);
	}

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);

}