package com.gejian.pixel.schedule;

import com.gejian.pixel.entity.Gamer;
import com.gejian.pixel.entity.GamerLog;
import com.gejian.pixel.entity.GamerSealed;
import com.gejian.pixel.service.GamerLogService;
import com.gejian.pixel.service.GamerSealedService;
import com.gejian.pixel.service.GamerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ：lijianghuai
 * @date ：2021-10-28 14:48
 * @description：
 */

@Component
@Slf4j
public class GamerAutoUnsealTask {

	@Autowired
	private GamerSealedService gamerSealedService;

	@Autowired
	private GamerService gamerService;

	@Autowired
	private GamerLogService gamerLogService;

	@Transactional(rollbackFor = Exception.class)
	@Scheduled(cron = "*/5 * * * * ?")
	public void execute(){
		LocalDateTime current = LocalDateTime.now();
		LocalDateTime last5Second = current.minusSeconds(5);
		List<GamerSealed> list = gamerSealedService.lambdaQuery().eq(GamerSealed::getEnabled,true).gt(GamerSealed::getTerminateTime, last5Second)
				.le(GamerSealed::getTerminateTime, current).list();
		for(GamerSealed gamerSealed: list){
			Gamer gamer = new Gamer();
			gamer.setState(true);
			gamer.setId(gamerSealed.getGamerId());
			gamerService.updateById(gamer);
			gamerSealed.setEnabled(false);
			gamerSealedService.updateById(gamerSealed);
			GamerLog gamerLog = new GamerLog();
			gamerLog.setGamerId(gamer.getId());
			gamerLog.setContext("自动解封");
			gamerLogService.save(gamerLog);
		}
	}
}
