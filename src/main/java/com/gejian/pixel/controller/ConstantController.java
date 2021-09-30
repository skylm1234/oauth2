package com.gejian.pixel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author fengliang
 */
@RestController
@RequestMapping("/consts")
@Slf4j
@ApiIgnore
public class ConstantController {

	private final static String CONSTANT_PARENT_PATH =  System.getProperty("user.dir") + "/" + "consts" + "/";

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
