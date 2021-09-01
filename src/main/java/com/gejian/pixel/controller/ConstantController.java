package com.gejian.pixel.controller;

import cn.hutool.Hutool;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

/**
 * @author fengliang
 */
@RestController
@RequestMapping("/constant")
@Slf4j
public class ConstantController {


	private final static String CONSTANT_PARENT_PATH=CLASSPATH_URL_PREFIX+"templates/consts/";

	@GetMapping("/{fileName}")
	public void downLoadFile(@PathVariable("fileName")String fileName, HttpServletResponse response)  {
		BufferedInputStream bis = null;
		OutputStream outputStream = null;
		try {
			File file = ResourceUtils.getFile(CONSTANT_PARENT_PATH+fileName);
			bis = new BufferedInputStream(new FileInputStream(file));
			outputStream = response.getOutputStream();
			byte[] bytes = new byte[2048];
			int len;
			while ((len=bis.read(bytes))!=-1){
				outputStream.write(bytes,0,len);
			}
		} catch (IOException e) {
			log.error("写出文件流失败",e);
		} finally {
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}

		}





	}











}
