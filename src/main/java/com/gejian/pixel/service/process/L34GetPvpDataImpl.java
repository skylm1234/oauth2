package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import com.google.protobuf.ProtocolStringList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 获取 PVP 数据
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.GET_PVP_DATA)
public class L34GetPvpDataImpl implements Process<CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest
		, CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse doProcess(CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest commGetPvpDataRequest) throws Exception {
		return null;
	}

	private String getAttributesKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s + ":attributes";
	}

	private String getHeroKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s;
	}

	private String getTeamsKey(Integer identifier) {
		return "u:" + identifier + ":teams";
	}

	private String getHerosKey(Integer identifier) {
		return "u:" + identifier + ":heros";
	}

	private String getSkillsKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s + ":skills";
	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}
}
