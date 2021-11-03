package com.gejian.pixel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeDTO;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeQueryDTO;
import com.gejian.pixel.entity.SysUpgrade;

/**
 *  Auto created by codeAppend plugin
 */
public interface SysUpgradeService extends IService<SysUpgrade> {

    IPage<SysUpgradeDTO> selectPage(SysUpgradeQueryDTO sysUpgradeQueryDTO);
}