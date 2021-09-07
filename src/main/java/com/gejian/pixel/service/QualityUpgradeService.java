package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.QualityUpgrade;
import com.gejian.pixel.proto.ConstQualityUpgradeTableItemExProtobuf;

/**
 *  Auto created by codeAppend plugin
 */
public interface QualityUpgradeService extends IService<QualityUpgrade> {

	ConstQualityUpgradeTableItemExProtobuf.ConstQualityUpgradeTableItemEx getQualityUpgradeById(Integer qualityValue);

}