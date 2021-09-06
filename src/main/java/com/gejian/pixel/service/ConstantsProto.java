package com.gejian.pixel.service;

import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.google.protobuf.MessageLite;

/**
 * @author ZhouQiang
 * @date 2021/9/3$
 */
public interface ConstantsProto {

	void init();
	/**
	 * 获得常量proto
	 *
	 * @return
	 */
	void build(ConstTablesProtobuf.ConstTables.Builder builder);

}
