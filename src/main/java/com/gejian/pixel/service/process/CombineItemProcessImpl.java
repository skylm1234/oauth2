package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommCombineItemRequestProtobuf;
import com.gejian.pixel.proto.CommCombineItemResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年09月03日 11:21
 * @description 合并item
 */
@Service(CommandConstants.COMBINE_ITEM)
@Slf4j
@RequiredArgsConstructor
public class CombineItemProcessImpl implements Process<CommCombineItemRequestProtobuf.CommCombineItemRequest,
		CommCombineItemResponseProtobuf.CommCombineItemResponse> {

	private final RedisTemplate redisTemplate;

	@Override
	public CommCombineItemResponseProtobuf.CommCombineItemResponse doProcess(CommCombineItemRequestProtobuf.CommCombineItemRequest request) throws Exception {
		CommCombineItemResponseProtobuf.CommCombineItemResponse.Builder replyBuilder = CommCombineItemResponseProtobuf.CommCombineItemResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		Map<String,String> toHashTable = new HashMap<>();
		toHashTable.put("exp_book_1","exp_book_2");
		toHashTable.put("exp_book_2","exp_book_3");
		toHashTable.put("exp_book_3","exp_book_4");

		if (toHashTable.get(request.getItem())!=null &&
				redisTemplate.opsForHash().hasKey("u:"+identifier+":items", request.getItem()) &&
				Integer.parseInt(redisTemplate.opsForHash().get("u:"+identifier+":items", request.getItem())+"") >= 10) {
			String from = request.getItem();
			String to = toHashTable.get(from);

			Long fromNumber = 0L;
			Long toNumber = 0L;

			while (true) {
				if (redisTemplate.opsForHash().increment("u:"+identifier+":items", from, -10) >= 0) {
					toNumber = redisTemplate.opsForHash().increment("u:"+identifier+":items", to, 1);
				}else {
					fromNumber = redisTemplate.opsForHash().increment("u:"+identifier+":items", from, 10);
					break;
				}
			}

			PlayerItemProtobuf.PlayerItem.Builder ffBuilder = PlayerItemProtobuf.PlayerItem.newBuilder();
			ffBuilder.setKey(from);
			ffBuilder.setValue(fromNumber);

			replyBuilder.addItems(ffBuilder.build());

			ffBuilder = PlayerItemProtobuf.PlayerItem.newBuilder();
			ffBuilder.setKey(to);
			ffBuilder.setValue(toNumber);
			replyBuilder.addItems(ffBuilder.build());

		}

		return replyBuilder.build();
	}
}
