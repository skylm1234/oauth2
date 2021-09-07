package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ExpBook;
import com.gejian.pixel.mapper.ExpBookMapper;
import com.gejian.pixel.proto.ConstExpbookTableItemExProtobuf;
import com.gejian.pixel.proto.ConstExpbookTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.ExpBookService;
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
public class ExpBookServiceImpl extends ServiceImpl<ExpBookMapper, ExpBook>
		implements ExpBookService, ConstantsProto {

	private Map<String, ExpBook> hash = new HashMap<>();

	private List<ConstExpbookTableItemExProtobuf.ConstExpbookTableItemEx>
			table = new ArrayList<>();

	@Override
	public ExpBook getById(String id){
		return hash.get(id);
	}

	@Override
	public void init() {
		List<ExpBook> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	private ConstExpbookTableItemExProtobuf.ConstExpbookTableItemEx convert(ExpBook item) {
		return ConstExpbookTableItemExProtobuf.ConstExpbookTableItemEx
				.newBuilder()
				.setValue(item.getValue())
				.setId(item.getId())
				.setDesc(item.getDesc())
				.setIcon(item.getIcon())
				.setName(item.getName())
				.setQuality(item.getQuality())
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstExpbookTableProtobuf.ConstExpbookTable build = ConstExpbookTableProtobuf.ConstExpbookTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setExpbooks(build);
	}

	@Override
	public ExpBook getExpBook(String type) {
		return hash.get(type);
	}
}