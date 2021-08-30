package com.gejian.pixel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/8/27$
 */
public class CommGenProto {

	public static void main(String[] args) {
		String sourceFilePath = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/resources/aio.txt";
		String outPutFilePath = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/resources/txt/";
		String s = FileUtil.readString(sourceFilePath, StandardCharsets.UTF_8);
		List<String> end = StrUtil.split(s, "end\n");
		for (int i = 0; i < end.size(); i++) {
			String filePath = outPutFilePath + "/" + i + ".txt";
			FileUtil.writeUtf8String(end.get(i), filePath);
		}
		main2();
	}

	private static String head = "syntax = \"proto3\";\n" +
			"${importProto}"+
			"//生成文件所在包名\n" +
			"option java_package = \"com.gejian.pixel.proto\";\n" +
			"//生成的java文件名\n" +
			"option java_outer_classname = \"${ClassName}Protobuf\";\n" +
			"\n";

	public static void main2() {
		String outPutFilePath = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/resources/txt/";
		String protoFile = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/resources/proto";
		List<File> files = FileUtil.loopFiles(outPutFilePath);
		for (File file : files) {
			List<String> strings = FileUtil.readLines(file, StandardCharsets.UTF_8);
			StringBuilder sb = new StringBuilder();
			sb.append(head);
			String className = "";
			StringBuilder importProtoSb = new StringBuilder();
			for (String str : strings) {
				str = str.trim();
				if (str.startsWith("message")) {
					String[] split = str.split("\\.");
					if (split.length > 0) {
						str = split[split.length - 1];
					}
					String message = str.replace("message", "").replace("\"", "")
							.trim()
							.toLowerCase();
					className = StrUtil.upperFirst(StrUtil.toCamelCase(message));
					sb.append("").append("message").append("\t").append(className).append("  ").append("{\n");
				} else {

					if (StrUtil.isBlank(str)) {
						continue;
					}
					String[] s = str.trim().split(" ");
					if (s.length == 3) {
						sb.append("\t").append("optional").append("\t").append(s[1].replace(",", "").trim())
								.append("\t").append(s[0].replace(",", "").trim())
								.append(" = ").append(s[2]).append(";\n");
					} else if (s.length == 5) {
						String clazName = s[4].replace("\"","").trim().toLowerCase();
						String clazzName = StrUtil.upperFirst(StrUtil.toCamelCase(clazName));
						if (!importProtoSb.toString().contains(clazzName)){
							importProtoSb.append("import \"").append(clazzName).append(".proto\";\n");
						}
						sb.append("\t").append(s[0]).append("\t").append(clazzName)
								.append("\t").append(s[1].replace(",", "").trim())
								.append(" = ").append(s[3].replace(",","").trim()).append(";\n");
					}else {
						sb.append("\t").append(s[0]).append("\t").append(s[2].replace(",", "").trim())
								.append("\t").append(s[1].replace(",", "").trim())
								.append(" = ").append(s[3]).append(";\n");
					}


				}
			}
			if (StrUtil.isNotBlank(sb.toString())) {
				sb.append("}");
				String result = sb.toString().replace("${ClassName}", className)
						.replace("${importProto}", importProtoSb.toString())
						.replace("optional","");
				String path = protoFile + "/" + className + ".proto";
				FileUtil.writeUtf8String(result, path);
				FileUtil.del(file);
			}

		}
	}
}
