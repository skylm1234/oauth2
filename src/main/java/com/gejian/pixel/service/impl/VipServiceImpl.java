package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ExpBook;
import com.gejian.pixel.entity.Vip;
import com.gejian.pixel.mapper.VipMapper;
import com.gejian.pixel.proto.ConstExpbookTableItemExProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.proto.ConstVipTableItemExProtobuf;
import com.gejian.pixel.proto.ConstVipTableProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.VipService;
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
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip> implements VipService, ConstantsProto {

	private Map<Integer, Vip> hash = new HashMap<>();

	private List<ConstVipTableItemExProtobuf.ConstVipTableItemEx>
			table = new ArrayList<>();

	@Override
	public Vip getById(Integer id) {
		return hash.get(id);
	}

	@Override
	public void init(){
		List<Vip> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			list.forEach(item->{
				hash.put(item.getId(),item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public ConstVipTableItemExProtobuf.ConstVipTableItemEx getItem(Integer id){
		return this.convert(hash.get(id));
	}

	private ConstVipTableItemExProtobuf.ConstVipTableItemEx convert(Vip item) {
		return ConstVipTableItemExProtobuf.ConstVipTableItemEx.newBuilder()
				.setId(item.getId())
				.setBackpackMax(item.getBackpackMax())
				.setChanllege(item.getChanllege())
				.setCharge(item.getCharge())
				.setFreerefreshTimesGold(item.getFreerefreshTimesGold())
				.setFreerefreshTimesHonor(item.getFreerefreshTimesHonor())
				.setFreerefreshTimesStone(item.getFreerefreshTimesStone())
				.setLevel(item.getLevel())
				.setSkipTimeval(item.getSkipTimeval())
				.setTiantiReset(item.getTiantiReset())
				.setTimevalOfflineKillMonsterRate(item.getTimevalOfflineKillMonsterRate())
				.setDes(item.getDes())
				.setItemid(item.getItemid())
				.setTianti(item.getTianti())
				.setAward(item.getAward())
				.build();
	}


	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstVipTableProtobuf.ConstVipTable build = ConstVipTableProtobuf.ConstVipTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setVips(build);
	}
}