package com.gejian.pixel.entity;

import com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar1Protobuf;
import com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStarProtobuf;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhouQiang
 * @date 2021/9/6$
 */
@Data
public class Star implements Serializable {

	private Integer hp;
	private Integer attack;
	private Integer defense;
	private Integer speed;
	private Integer hpUpgrade;
	private Integer attackUpgrade;
	private Integer defenseUpgrade;
	private Integer speedUpgrade;

	public ConstHeroTableItemExStarUpgradeFomulaStarProtobuf.ConstHeroTableItemExStarUpgradeFomulaStar
		toProto(){
		return ConstHeroTableItemExStarUpgradeFomulaStarProtobuf.ConstHeroTableItemExStarUpgradeFomulaStar.newBuilder()
				.setAttack(this.attack)
				.setHp(this.hp)
				.setDefense(this.defense)
				.setHpUpgrade(this.hpUpgrade)
				.setAttackUpgrade(this.attackUpgrade)
				.setDefenseUpgrade(this.defenseUpgrade)
				.setSpeedUpgrade(this.speedUpgrade)
				.build();
	}


}
