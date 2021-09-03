package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhouqiang
 */
@Data
@TableName("`drop`")
public class Drop implements Serializable {
/**
	 * 主键
	 */
	@TableId
	private String id;

	/**
	 * 
	 */
	private String content;

	@TableField("`desc`")
	private String desc;


}