package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("new_store_goods")
public class NewStoreGoods {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	/**
	 * 商品名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 商品描述
	 */
	@TableField("desc")
	private String desc;


}