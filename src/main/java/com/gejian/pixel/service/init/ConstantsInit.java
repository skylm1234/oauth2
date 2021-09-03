package com.gejian.pixel.service.init;

import com.gejian.pixel.proto.ConstBackpackTableItemExProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.ConstantsProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/9/3$
 */
@Component
public class ConstantsInit {

	@Autowired
	private List<ConstantsProto> constantsProtoList;

	@PostConstruct
	public void init(){
		ConstTablesProtobuf.ConstTables.Builder builder = ConstTablesProtobuf.ConstTables.newBuilder();
		constantsProtoList.forEach(item->{
			item.build(builder);
		});
		ConstTablesProtobuf.ConstTables constTables = builder.build();
	}

}
