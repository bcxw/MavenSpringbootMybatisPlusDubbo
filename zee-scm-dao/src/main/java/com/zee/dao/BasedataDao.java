package com.zee.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zee.model.Basedata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BasedataDao extends BaseMapper<Basedata> {

	String tableName="basedata";

	@Select("select * from basedata")
	public List<Basedata> getAll();
	

}
