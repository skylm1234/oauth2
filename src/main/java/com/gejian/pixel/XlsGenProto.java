package com.gejian.pixel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.gejian.pixel.proto.BackpackProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.util.*;

/**
 * @author ZhouQiang
 * @date 2021/8/27$
 */
public class XlsGenProto {

	private static String template = "syntax = \"proto3\";\n" +
			"//生成文件所在包名\n" +
			"option java_package = \"com.gejian.pixel.proto\";\n" +
			"//生成的java文件名\n" +
			"option java_outer_classname = \"${ClassName}Protobuf\";\n" +
			"\n" +
			"message ${ClassName} {\n" +
			"  ${fields}\n" +
			"\n" +
			"}";

	public static void main1(String[] args) {
		String excelFilePath = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/resources/excel";
		String outPutFilePath = "/Users/zhouqiang/workspace/pixel/java-pixel/src/main/proto";
		FileUtil.mkdir(outPutFilePath);
		List<File> files = FileUtil.loopFiles(excelFilePath);
		for (File file : files) {
			String className = FileUtil.mainName(file);
			try{
				ExcelReader reader = ExcelUtil.getReader(file);
				int columnCount = reader.getColumnCount();
				List<LinkedHashMap<Integer,String>> fields = Lists.newArrayList();
				for (int i =0 ;i < columnCount ; i++){
					LinkedHashMap<Integer,String> field = new LinkedHashMap<>();
					for(int j = 0;j<3;j++){
						Cell cell = reader.getCell(i, j);
						if (Objects.isNull(cell)){
							continue;
						}
						CellType cellType = cell.getCellType();

						String value = null;
						if (CellType.STRING.equals(cellType)){
							value = cell.getStringCellValue();
						} else if (CellType.NUMERIC.equals(cellType)){
							value = String.valueOf((int)cell.getNumericCellValue());
						} else {
							System.out.println("需要支持其他类型："+ cellType);
						}
						field.put(j,value);
					}
					fields.add(field);
				}
				StringBuilder sb = new StringBuilder();
				for (int i=0;i<fields.size();i++) {
					LinkedHashMap<Integer, String> field = fields.get(i);
					sb.append("\t/**").append(field.remove(2)).append("*/").append("\n");
					sb.append("\t").append(field.get(0)).append("\t").append(field.get(1)).append(" ").append("= ").append(i)
							.append(";");
					sb.append("\n");
				}
				if (StrUtil.isBlank(sb.toString())){
					continue;
				}
				String resultStr = template.replace("${ClassName}", className);
				resultStr = resultStr.replace("${fields}",sb.toString());
				System.out.println(resultStr);
				String outPutFileName = outPutFilePath + "/" + className + ".proto";
				FileUtil.writeUtf8String(resultStr,outPutFileName);
			} catch (Exception e){
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		String protoFilePath = "E:\\workspace\\java-pixel\\src\\main\\proto";
		List<File> files = FileUtil.loopFiles(protoFilePath);
		for (File file : files) {
			System.out.println("protoc --java_out=E:\\workspace\\java-pixel\\src\\main\\java "+file.getName());
		}
	}


}
