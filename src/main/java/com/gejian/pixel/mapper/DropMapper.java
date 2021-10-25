package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.Drop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhouqiang Auto created by codeAppend plugin
 */
public interface DropMapper extends BaseMapper<Drop> {

	@Insert("insert into `drop` values(#{id},#{content},#{desc},#{desc2})")
    void save(Drop entity);

	@Update("update `drop` set content = #{content},`desc` = #{desc},desc2 = #{desc2} where id = #{id}")
	void update(Drop entity);
}