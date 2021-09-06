package com.gejian.pixel.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhouQiang
 * @date 2021/9/6$
 */
@Data
public class BasicExpand implements Serializable {
	private Integer hp;
	private Integer attack;
	private Integer defense;
	private Integer speed;
}
