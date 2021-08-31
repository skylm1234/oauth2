package com.gejian.pixel.constants;

import com.gejian.pixel.model.UserInfo;
import io.netty.util.AttributeKey;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
public interface AttributeKeyConstants {

	AttributeKey<UserInfo> USER_INFO_ATTRIBUTE_KEY = AttributeKey.valueOf("USER_INFO");

}
