package com.gejian.pixel.entity;

import com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaX1Protobuf;
import com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf;
import lombok.Data;

/**
 * @author ZhouQiang
 * @date 2021/9/6$
 */
@Data
public class UpgradeConsumeFomula {

	private Integer gold;

	private Integer book;


	public ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX
		toProto(){
		return ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.newBuilder()
				.setGold(gold)
				.setBook(book)
				.build();
	}

}
