package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommRanklistRequestProtobuf;
import com.gejian.pixel.proto.CommRanklistResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.RankListHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 排行榜
 *
 * @author Reese
 * @date 2021/9/1
 */
@Slf4j
@Service(CommandConstants.RANKLIST)
public class L28RankListImpl implements Process<CommRanklistRequestProtobuf.CommRanklistRequest
		, CommRanklistResponseProtobuf.CommRanklistResponse> {

	@Autowired
	private RankListHelper rankListHelper;

	@Override
	public CommRanklistResponseProtobuf.CommRanklistResponse doProcess(CommRanklistRequestProtobuf.CommRanklistRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommRanklistResponseProtobuf.CommRanklistResponse response = CommRanklistResponseProtobuf.CommRanklistResponse.getDefaultInstance();
		this.rankListHelper.rankListHelper(Objects.requireNonNull(this.rankListHelper.topRangeRich(identifier, 99L, false)), response);
		this.rankListHelper.rankListHelper(Objects.requireNonNull(this.rankListHelper.topRangePower(identifier, 99L, false)), response);
		this.rankListHelper.rankListHelper(Objects.requireNonNull(this.rankListHelper.topRangeHonor(identifier, 99L, false)), response);
		return response;
	}


}

