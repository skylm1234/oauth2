package com.gejian.pixel.schedule;

import com.gejian.pixel.entity.Gamer;
import com.gejian.pixel.entity.GamerSealed;
import com.gejian.pixel.service.GamerSealedService;
import com.gejian.pixel.service.GamerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	@Scheduled(cron = "*/5 * * * * ?")
	public void execute(){
		LocalDateTime current = LocalDateTime.now();
		LocalDateTime last5Second = current.minusSeconds(5);
		List<GamerSealed> list = gamerSealedService.lambdaQuery().gt(GamerSealed::getTerminateTime, last5Second)
				.le(GamerSealed::getTerminateTime, current).list();
		List<Gamer> gamers = list.stream().map(gamerSealed -> {
			Gamer gamer = new Gamer();
			gamer.setState(true);
			gamer.setId(gamerSealed.getGamerId());
			return gamer;
		}).collect(Collectors.toList());
		gamerService.updateBatchById(gamers);
	}
}
