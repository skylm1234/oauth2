package com.gejian.pixel.constants;

/**
 * @author ：lijianghuai
 * @date ：2021-10-19 11:29
 * @description：
 */
public interface CommonConstants {
	String TENANT_ID = "TENANT-ID";

	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 租户ID
	 */
	Integer TENANT_ID_1 = 1;

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "pigx-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "pigx";

	/**
	 * 公共参数
	 */
	String PIG_PUBLIC_PARAM_KEY = "PIG_PUBLIC_PARAM_KEY";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 200;

	/**
	 * 失败标记
	 */
	Integer FAIL = 9999;

	Integer USER_NOT_LOGIN_CODE = 9000;

	/**
	 * 默认存储bucket
	 */
	String BUCKET_NAME = "lengleng";

	/**
	 * 滑块验证码
	 */
	String IMAGE_CODE_TYPE = "blockPuzzle";

	/**
	 * 验证码开关
	 */
	String CAPTCHA_FLAG = "captcha_flag";

	/**
	 * 密码传输是否加密
	 */
	String ENC_FLAG = "enc_flag";
}
