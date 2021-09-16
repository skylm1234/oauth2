package com.gejian.pixel.service.init;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.gejian.pixel.proto.ConstBackpackTableItemExProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.ConstantsProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/9/3$
 */
@Component
public class ConstantsInit {

	@Autowired
	private List<ConstantsProto> constantsProtoList;

	/**
	 * 常量文件
	 */
	private static final String CONSTANTS_DIR_PATH = System.getProperty("user.dir") + "/" + "consts";

	private static final String BIN_FILE = CONSTANTS_DIR_PATH + "/" + "consts.bin";

	@PostConstruct
	public void init(){
		ConstTablesProtobuf.ConstTables.Builder builder = ConstTablesProtobuf.ConstTables.newBuilder();
		constantsProtoList.forEach(item->{
			item.init();
			item.build(builder);
		});
		ConstTablesProtobuf.ConstTables constTables = builder.build();
		byte[] bytes = constTables.toByteArray();
		FileUtil.writeBytes(bytes,BIN_FILE);

		//FileUtil.writeUtf8String(constTables.toString(),CONSTANTS_DIR_PATH +"/" + "consts.txt");

		String md5 = SecureUtil.md5(new File(BIN_FILE));
		String sha1 = SecureUtil.sha1(new File(BIN_FILE));
		FileUtil.writeUtf8String(md5,CONSTANTS_DIR_PATH +"/" + "consts.md5");
		FileUtil.writeUtf8String(sha1,CONSTANTS_DIR_PATH +"/" + "consts.sha1");
	}

}
