package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.gamer.GamerDTO;
import com.gejian.pixel.dto.gamer.GamerPageQueryDTO;
import com.gejian.pixel.dto.gamer.GamerSealedPatchDTO;
import com.gejian.pixel.entity.Gamer;
import com.gejian.pixel.entity.GamerLog;
import com.gejian.pixel.entity.GamerSealed;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.mapper.GamerLogMapper;
import com.gejian.pixel.mapper.GamerMapper;
import com.gejian.pixel.mapper.GamerSealedMapper;
import com.gejian.pixel.security.PrincipalUser;
import com.gejian.pixel.security.SecurityUtils;
import com.gejian.pixel.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class GamerServiceImpl extends ServiceImpl<GamerMapper, Gamer> implements GamerService {

	@Autowired
	private GamerSealedMapper gamerSealedMapper;

	@Autowired
	private GamerLogMapper gamerLogMapper;

	@Override
	public IPage<GamerDTO> selectPage(GamerPageQueryDTO gamerPageQueryDTO) {
		LambdaQueryWrapper<Gamer> wrapper = new LambdaQueryWrapper<>();
		if(StrUtil.isNotBlank(gamerPageQueryDTO.getMobile())){
			wrapper.like(Gamer::getMobile,gamerPageQueryDTO.getMobile());
		}
		if(StrUtil.isNotBlank(gamerPageQueryDTO.getUsername())){
			wrapper.like(Gamer::getUsername,gamerPageQueryDTO.getUsername());
		}
		return this.page(gamerPageQueryDTO.page(),wrapper).convert(gamer -> BeanUtil.copyProperties(gamer, GamerDTO.class));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void seal(GamerSealedPatchDTO gamerSealedPatchDTO){
		Gamer gamer	 = new Gamer();
		gamer.setId(gamerSealedPatchDTO.getId());
		gamer.setState(false);
		baseMapper.updateById(gamer);
		GamerSealed gamerSealed = new GamerSealed();
		gamerSealed.setGamerId(gamerSealedPatchDTO.getId());
		gamerSealed.setDays(gamerSealedPatchDTO.getDays());
		gamerSealed.setReason(gamerSealedPatchDTO.getReason());
		LocalDateTime now = LocalDateTime.now();
		gamerSealed.setStartTime(now);
		gamerSealed.setTerminateTime(now.plusDays(gamerSealedPatchDTO.getDays()));
		baseMapper.updateById(gamer);
		gamerSealedMapper.insert(gamerSealed);
		GamerLog gamerLog = new GamerLog();
		gamerLog.setGamerId(gamer.getId());
		gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
		gamerLog.setContext("封禁：" + gamerSealed.getDays() + "天，原因：" + gamerSealed.getReason());
		gamerLogMapper.insert(gamerLog);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void unSeal(Long id) {
		Gamer gamer = Optional.ofNullable(this.getById(id)).orElseThrow(ResourceNotFoundException::new);
		if(gamer.getState()){
			return;
		}
		LambdaQueryWrapper<GamerSealed> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(GamerSealed::getGamerId,id);
		wrapper.eq(GamerSealed::getEnabled,true);
		wrapper.ge(GamerSealed::getTerminateTime,LocalDateTime.now());
		wrapper.orderByDesc(GamerSealed::getTerminateTime);
		List<GamerSealed> gamerSealeds = gamerSealedMapper.selectList(wrapper);
		if(CollectionUtils.isEmpty(gamerSealeds)){
			return;
		}
		gamerSealeds.forEach(gamerSealed -> {
			gamerSealed.setEnabled(false);
			gamerSealedMapper.updateById(gamerSealed);
		});
		gamer.setState(true);
		baseMapper.updateById(gamer);
		GamerLog gamerLog = new GamerLog();
		gamerLog.setGamerId(gamer.getId());
		gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
		gamerLog.setContext("手动解封");
		gamerLogMapper.insert(gamerLog);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateState(Long id, Boolean state) {
		Gamer gamer = new Gamer();
		gamer.setId(id);
		gamer.setState(state);
		baseMapper.updateState(gamer);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean save(Gamer gamer) {
		gamer.setVip(gamer.getVipLevel() != null && gamer.getVipLevel() > 0);
		baseMapper.insert(gamer);
		GamerLog gamerLog = new GamerLog();
		gamerLog.setGamerId(gamer.getId());
		gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
		gamerLog.setContext(buildContext(gamer));
		gamerLogMapper.insert(gamerLog);
		return true;
	}

	@Override
	public boolean updateById(Gamer gamer) {
		Gamer original = baseMapper.selectById(gamer.getId());
		gamer.setVip(gamer.getVipLevel() != null && gamer.getVipLevel() > 0);
		baseMapper.updateById(gamer);
		GamerLog gamerLog = new GamerLog();
		gamerLog.setGamerId(gamer.getId());
		gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
		gamerLog.setContext(buildContext(original,gamer));
		gamerLogMapper.insert(gamerLog);
		return true;
	}

	private String buildContext(Gamer original,Gamer current){
		StringBuilder stringBuilder = new StringBuilder();
		if(!Objects.equals(original.getVip(),current.getVip())){
			if(current.getVip()){
				stringBuilder.append("VIP等级：VIP").append(current.getVipLevel()).append(" ");
			}else{
				stringBuilder.append("VIP等级：无 ");
			}
		}
		if(!Objects.equals(original.getTester(),current.getTester())){
			if(current.getTester()){
				stringBuilder.append("测试账号：是 ");
			}else{
				stringBuilder.append("测试账号：否 ");
			}
		}

		if(!Objects.equals(original.getGold(),current.getGold())){
			if(current.getGold() != null){
				stringBuilder.append("金币：").append(current.getGold()).append(" ");
			}else {
				stringBuilder.append("金币：无 ");
			}
		}

		if(!Objects.equals(original.getStone(),current.getStone())){
			if(current.getStone() != null){
				stringBuilder.append("钻石：").append(current.getStone()).append(" ");
			}else{
				stringBuilder.append("钻石：无");
			}
		}

		if(!Objects.equals(original.getHonor(),current.getHonor())){
			if(current.getHonor() != null){
				stringBuilder.append("荣誉：").append(current.getHonor()).append(" ");
			}else{
				stringBuilder.append("荣誉：无");
			}
		}

		if(!Objects.equals(original.getCe(),current.getCe())){
			if(current.getCe() != null){
				stringBuilder.append("战力：").append(current.getCe()).append(" ");
			}else{
				stringBuilder.append("战力：无");
			}
		}

		return stringBuilder.toString();
	}

	private String buildContext(Gamer gamer){
		StringBuilder stringBuilder = new StringBuilder();
		if(gamer.getVip()){
			stringBuilder.append("VIP等级：VIP").append(gamer.getVipLevel()).append(" ");
		}else{
			stringBuilder.append("VIP等级：无").append(gamer.getVipLevel()).append(" ");
		}
		if(gamer.getTester() != null && gamer.getTester()){
			stringBuilder.append("测试账号：是 ");
		}else{
			stringBuilder.append("测试账号：否 ");
		}
		if(gamer.getGold() != null){
			stringBuilder.append("金币：").append(gamer.getGold()).append(" ");
		}
		if(gamer.getStone() != null){
			stringBuilder.append("钻石：").append(gamer.getStone()).append(" ");
		}
		if(gamer.getHonor() != null){
			stringBuilder.append("荣誉：").append(gamer.getHonor()).append(" ");
		}
		if(gamer.getCe() != null){
			stringBuilder.append("战力：").append(gamer.getCe()).append(" ");
		}
		return stringBuilder.toString();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeById(Serializable id) {
		LambdaQueryWrapper<GamerSealed> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(GamerSealed::getGamerId,id);
		wrapper.eq(GamerSealed::getEnabled,true);
		List<GamerSealed> gamerSealeds = gamerSealedMapper.selectList(wrapper);
		if(!CollectionUtils.isEmpty(gamerSealeds)){
			gamerSealeds.forEach(gamerSealed -> {
				gamerSealed.setEnabled(false);
				gamerSealedMapper.updateById(gamerSealed);
			});
		}
		GamerLog gamerLog = new GamerLog();
		gamerLog.setGamerId((Long) id);
		gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
		gamerLog.setContext("删除玩家");
		gamerLogMapper.insert(gamerLog);
		return super.removeById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		LambdaQueryWrapper<GamerSealed> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(GamerSealed::getGamerId,idList);
		wrapper.eq(GamerSealed::getEnabled,true);
		List<GamerSealed> gamerSealeds = gamerSealedMapper.selectList(wrapper);
		if(!CollectionUtils.isEmpty(gamerSealeds)){
			gamerSealeds.forEach(gamerSealed -> {
				gamerSealed.setEnabled(false);
				gamerSealedMapper.updateById(gamerSealed);
			});
		}
		idList.forEach(id -> {
			GamerLog gamerLog = new GamerLog();
			gamerLog.setGamerId((Long) id);
			gamerLog.setSysUserId(SecurityUtils.getSysUser().map(PrincipalUser::getUserId).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("401")));
			gamerLog.setContext("删除玩家");
			gamerLogMapper.insert(gamerLog);
		});
		return super.removeByIds(idList);
	}
}