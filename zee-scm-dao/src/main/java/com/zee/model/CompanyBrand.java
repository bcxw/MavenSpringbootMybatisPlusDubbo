package com.zee.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 公司品牌关联实体类
 */
@Getter
@Setter
@TableName("cfg_company_brand")
public class CompanyBrand extends BaseModel<CompanyBrand> {
    //公司id
    private String companyId;
    //品牌编码
    private String brandCode;
    //品牌类型
    private String brandType;


}
