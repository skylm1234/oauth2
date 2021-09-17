package com.gejian.pixel.service.init;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
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
		byte[] binBytes = FileUtil.readBytes("consts/consts.bin");
		FileUtil.writeBytes(binBytes,BIN_FILE);
		String md5String = FileUtil.readUtf8String("consts/consts.md5");
		FileUtil.writeUtf8String(md5String,CONSTANTS_DIR_PATH +"/" + "consts.md5");
		String sha1String = FileUtil.readUtf8String("consts/consts.sha1");
		FileUtil.writeUtf8String(sha1String,CONSTANTS_DIR_PATH +"/" + "consts.sha1");

		/*ConstTablesProtobuf.ConstTables constTables = builder.build();
		FileUtil.writeUtf8String(constTables.toString(),CONSTANTS_DIR_PATH +"/" + "consts.txt");
		byte[] bytes = constTables.toByteArray();
		FileUtil.writeBytes(bytes,BIN_FILE);

		FileUtil.writeUtf8String(constTables.toString(),CONSTANTS_DIR_PATH +"/" + "consts.txt");

		String md5 = SecureUtil.md5(new File(BIN_FILE));
		String sha1 = SecureUtil.sha1(new File(BIN_FILE));
		FileUtil.writeUtf8String(md5,CONSTANTS_DIR_PATH +"/" + "consts.md5");
		FileUtil.writeUtf8String(sha1,CONSTANTS_DIR_PATH +"/" + "consts.sha1");*/
	}

	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		byte[] bytes = FileUtil.readBytes("E:/workspace/java-pixel/consts/consts.bin");
		ConstTablesProtobuf.ConstTables constTables = ConstTablesProtobuf.ConstTables.parseFrom(bytes);
		FileUtil.writeUtf8String(constTables.toString(),CONSTANTS_DIR_PATH +"/" + "consts.txt");


		/*String dirPath = "C:/Users/Administrator/Desktop/newConstant";
		JSON json = JSONUtil.readJSON(new File(dirPath + "/" + "constsNotT.txt"), Charset.forName("UTF-8"));
		ConstTablesProtobuf.ConstTables.Builder builder = ConstTablesProtobuf.ConstTables.newBuilder();
		JsonFormat.parser().merge(json.toString(), builder);
		ConstTablesProtobuf.ConstTables constTables = builder.build();
		byte[] bytes = constTables.toByteArray();
		FileUtil.writeBytes(bytes,dirPath + "/" + "consts.bin");*/

	}

	public static String toJson(Message sourceMessage)
			throws IOException {
		String json = JsonFormat.printer().print(sourceMessage);
		return json;
	}


}
