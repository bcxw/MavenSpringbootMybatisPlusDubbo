package com.zee.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 公司基本信息
 */
@Getter
@Setter
@TableName("cfg_company_baseinfo")
public class CompanyBaseInfo extends BaseModel<Basedata> {
    //公司编码
    private String companyCode;
    //公司标识
    private String companyMark;
    //公司名字
    private String companyName;
    //公司属性
    private String companyProperty;
    //公司地址
    private String companyAddress;
    //备注
    private String Remark;
    @TableField(exist = false)
    private List<CompanyBrand> Brands;


}
