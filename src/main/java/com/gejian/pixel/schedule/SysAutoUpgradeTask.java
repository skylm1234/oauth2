package com.gejian.pixel.schedule;

import cn.hutool.core.util.NumberUtil;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.SysUpgrade;
import com.gejian.pixel.service.SysUpgradeService;
import com.gejian.pixel.service.init.ConstantsInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 15:52
 * @description：
 */

@Component
@Slf4j
public class SysAutoUpgradeTask {

	@Autowired
	private SysUpgradeService sysUpgradeService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ConstantsInit constantsInit;

	/**
	 * 每分钟执行一次
	 */
	@Transactional(rollbackFor = Exception.class)
	@Scheduled(cron = "0 */1 * * * ?")
	public void execute(){
		log.info("auto upgrade task running...");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<SysUpgrade> sysUpgrades = sysUpgradeService.lambdaQuery().eq(SysUpgrade::getUpgradeTime,dateTimeFormatter.format(LocalDateTime.now()))
				.eq(SysUpgrade::getStatus, false).list();
		if(!CollectionUtils.isEmpty(sysUpgrades)){
			SysUpgrade sysUpgrade = sysUpgrades.get(0);
			int systemBanAnyone = NumberUtil.parseInt(redisTemplate.opsForValue().get(RedisKeyConstants.SYSTEM_BAN_ANYONE));
			if (systemBanAnyone == 0) {
				log.info("start auto upgrade....");
				synchronized (this){
					redisTemplate.opsForValue().set(RedisKeyConstants.SYSTEM_BAN_ANYONE,"1");
					constantsInit.init();
					redisTemplate.opsForValue().set(RedisKeyConstants.SYSTEM_BAN_ANYONE,"0");
					sysUpgrade.setStatus(true);
					sysUpgradeService.updateById(sysUpgrade);
				}
				log.info("auto upgrade finished....");
			}
		}
	}
}
