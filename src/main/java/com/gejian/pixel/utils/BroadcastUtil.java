package com.gejian.pixel.utils;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.http.HttpRequest;
import com.gejian.pixel.proto.MessageBaseProtobuf;

/**
 * @author ljb
 * @date 2021年08月30日 15:30
 * @description
 */
public class BroadcastUtil {

	public static void broadcast(MessageBaseProtobuf.MessageBase data) {
		String broadcastURL = "golang";
		String uri = StrFormatter.format("http://{}:7070/broadcast", broadcastURL);
		HttpRequest.post(uri)
				.header("Content-Type", "application/text")
				.body(data.getData().toByteArray())
				.execute().body();
	}

}
