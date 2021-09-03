package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Service(CommandConstants.COMBINE_ITEM)
@Slf4j
public class CombineItemProcessImpl implements
		Process<CommCombineItemRequestProtobuf.CommCombineItemRequest,
				CommCombineItemResponseProtobuf.CommCombineItemResponse> {

	@Autowired
	private StringRedisTemplate redisTemplate;


	private static HashMap<String, String> MAPPING = new HashMap<>();

	static {
		MAPPING.put("exp_book_1", "exp_book_2");
		MAPPING.put("exp_book_2", "exp_book_3");
		MAPPING.put("exp_book_3", "exp_book_4");
		MAPPING.put("exp_book_4", "exp_book_5");
	}

	@Override
	public CommCombineItemResponseProtobuf.CommCombineItemResponse
	doProcess(CommCombineItemRequestProtobuf.CommCombineItemRequest request) throws Exception {
		if (!MAPPING.containsKey(request.getItem()) || !existsItem(request.getItem())) {
			return null;
		}
		String to = MAPPING.get(request.getItem());
		long fromNum;
		long toNum = 0L;
		while (true){
			long num = incrItem(request.getItem(), -10);
			if (num >= 0)  {
				toNum = incrItem(to,1);
			} else {
				fromNum = incrItem(request.getItem(),10);
				break;
			}
		}
		PlayerItemProtobuf.PlayerItem fromItem = PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey(request.getItem())
				.setValue(fromNum)
				.build();
		PlayerItemProtobuf.PlayerItem toItem = PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey(to)
				.setValue(toNum)
				.build();
		return CommCombineItemResponseProtobuf.CommCombineItemResponse
				.newBuilder()
				.addItems(fromItem)
				.addItems(toItem)
				.build();
	}

	private boolean existsItem(String item) {
		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();
		Object o = redisTemplate.opsForHash().get(StrUtil.format(RedisKeyConstants.USER_ITEMS, identifier), item);
		if (Objects.isNull(o)) {
			return false;
		}
		int i = Integer.parseInt(String.valueOf(o));
		return i >= 10;
	}

	private long incrItem(String item,long num){
		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();
		return redisTemplate.opsForHash()
				.increment(StrUtil.format(RedisKeyConstants.USER_ITEMS, identifier), item,num);
	}
}
