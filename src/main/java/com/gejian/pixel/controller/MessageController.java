package com.gejian.pixel.controller;

import com.gejian.pixel.dto.MessageDTO;
import com.gejian.pixel.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/messages")
@Api(value = "message", tags = "消息管理")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/boardcast")
	@ApiOperation("发送世界消息")
	public ResponseEntity<Void> sendBoardcast(@RequestBody @Valid MessageDTO messageDTO){
		messageService.sendBoardcastMessage(messageDTO.getMessage());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/notice")
	@ApiOperation("发送公告消息")
	public ResponseEntity<Void> sendNotice(@RequestBody @Valid MessageDTO messageDTO){
		messageService.setNoticeMessage(messageDTO.getMessage());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/maintenance")
	@ApiOperation("发送维护消息")
	public ResponseEntity<Void> sendMaintenance(@RequestBody @Valid MessageDTO messageDTO){
		messageService.setMaintenanceMessage(messageDTO.getMessage());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/notice")
	@ApiOperation("获取公告消息")
	public String getNotice(){
		return messageService.getNoticeMessage();
	}

	@GetMapping("/maintenance")
	@ApiOperation("获取维护消息")
	public String getMaintenance(){
		return messageService.getMaintenanceMessage();
	}
}
