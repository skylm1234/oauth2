package com.gejian.pixel.entity;

import lombok.Data;

@Data
public class GoodsFomula {

	//"to": 1011, "cost": "gold", "from": 1001, "factor": 100, "number": 1, "cost_number": 100000, "good_prefix": "book_skill"}

	private Long to;
	private String cost;
	private Long from;
	private String factor;
	private Long number;
	private Long costNumber;
	private String goodPrefix;




}