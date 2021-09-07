package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.PvpRefresh;
import com.gejian.pixel.proto.ConstPvpRefreshTableItemExProtobuf;

/**
 *  Auto created by codeAppend plugin
 */
public interface PvpRefreshService extends IService<PvpRefresh> {

    ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx getItem(Integer id);
}