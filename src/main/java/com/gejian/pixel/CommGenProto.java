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

	private static String  head = "syntax = \"proto3\";\n" +
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
			for (String str : strings) {
				str = str.trim();
				if (str.indexOf("message") > 1 || (str.startsWith("message") && str.contains("."))){
					sb = new StringBuilder();
					break;
				}
				if (str.startsWith("message")) {
					String message = str.replace("message", "").replace("\"", "")
							.trim()
							.toLowerCase();
					className = StrUtil.upperFirst(StrUtil.toCamelCase(message));
					sb.append("").append("message").append("\t").append(className).append("  ").append("{\n");
				} else {
					if (StrUtil.isBlank(str)){
						continue;
					}
					String[] s = str.trim().split(" ");
					if (s.length == 3){
						sb.append("\t").append("optional").append("\t").append(s[1].replace(",","").trim())
								.append("\t").append(s[0].replace(",","").trim())
								.append(" = ").append(s[2]).append(";\n");
					} else {
						sb.append("\t").append(s[0]).append("\t").append(s[2].replace(",", "").trim())
								.append("\t").append(s[1].replace(",", "").trim())
								.append(" = ").append(s[3]).append(";\n");
					}

				}
			}
			if (StrUtil.isNotBlank(sb.toString())){
				sb.append("}");
				String result = sb.toString().replace("${ClassName}", className);
				String path = protoFile + "/" + className + ".proto";
				FileUtil.writeUtf8String(result,path);
				FileUtil.del(file);
			}

		}
	}
}
