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
 * 资源合成
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
		CommCombineItemResponseProtobuf.CommCombineItemResponse.Builder reply = CommCombineItemResponseProtobuf.CommCombineItemResponse.newBuilder();
		if (MAPPING.containsKey(request.getItem()) && existsItem(request.getItem())) {
			Integer identifier = UserHolder.get().getIdentifier();
			String to = MAPPING.get(request.getItem());
			long fromNum;
			long toNum = 0L;
			//当前资源数量
			Integer itemCount = Helper.itemCount(redisTemplate, identifier, request.getItem());
			if (itemCount<10) {
				//小于10个不允许合成，返回资源不足提示
				reply.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
			}else {
				fromNum = incrItem(request.getItem(), -10);
				toNum = incrItem(to,1);
				PlayerItemProtobuf.PlayerItem fromItem = PlayerItemProtobuf.PlayerItem.newBuilder()
						.setKey(request.getItem())
						.setValue(fromNum)
						.build();
				PlayerItemProtobuf.PlayerItem toItem = PlayerItemProtobuf.PlayerItem.newBuilder()
						.setKey(to)
						.setValue(toNum)
						.build();
				reply.addItems(fromItem).addItems(toItem);
			}
		}else {
			reply.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
		}
		return reply.build();
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
