package com.zee.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zee.model.CompanyBrand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 公司品牌关联表dao
 */
@Mapper
public interface CompanyBrandDao extends BaseMapper<CompanyBrand> {

    /**
     * 批量添加
     */
    @Insert("<script>"
            + "insert into cfg_company_brand(id,company_id,brand_code,brand_type)"
            + " values"
            + " <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\" >"
            + " (#{item.id},#{item.companyId},#{item.brandCode},#{item.brandType})"
            + "</foreach>"
            + "</script>")
    Integer saveList(List<CompanyBrand> list);
}
