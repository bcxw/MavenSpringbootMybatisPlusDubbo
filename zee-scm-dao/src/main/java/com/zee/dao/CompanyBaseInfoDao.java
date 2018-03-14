package com.zee.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zee.model.CompanyBaseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 公司信息dao
 */
@Mapper
public interface CompanyBaseInfoDao extends BaseMapper<CompanyBaseInfo> {
    /**
     * 根据搜索条件动态查询公司信息
     *
     * @param conditionMap
     * @return
     */
    @Select("<script>"
            + "	select c.* from cfg_company_baseinfo c"
            + "   <where>"
            + "	 <if test='companyCode != null and companyCode != \"\"'>"
            + " 	and company_code=#{companyCode} "
            + "	 </if>"
            + "	 <if test='companyName != null and companyName != \"\"'>"
            + " 	and company_name like concat(concat('%',#{companyName},'%')) "
            + "	 </if>"
            + "	 <if test='companyProperty != null and companyProperty != \"\"'>"
            + " 	and company_property like concat(concat('%',#{companyProperty},'%')) "
            + "	 </if>"
            + "	  </where>"
            + "limit #{startIndex},#{pageSize} "
            + "</script>")
    List<CompanyBaseInfo> finByCondition(Map<String, Object> conditionMap);


    /**
     * 根据查询条件获取查询寻结果总条数
     *
     * @param conditionMap
     * @return
     */
    @Select("<script>"
            + "	select count(1) from cfg_company_baseinfo "
            + "   <where>"
            + "	 <if test='companyCode != null and companyCode != \"\"'>"
            + " 	and company_code=#{companyCode} "
            + "	 </if>"
            + "	 <if test='companyName != null and companyName != \"\"'>"
            + " 	and company_name like concat(concat('%',#{companyName},'%')) "
            + "	 </if>"
            + "	 <if test='companyProperty != null and companyProperty != \"\"'>"
            + " 	and company_property like concat(concat('%',#{companyProperty},'%')) "
            + "	 </if>"
            + "	  </where>"
            + "limit #{startIndex},#{pageSize} "
            + "</script>")
    int getTotalByCondition(Map<String, Object> conditionMap);


}
