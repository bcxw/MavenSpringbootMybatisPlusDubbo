package com.zee.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础数据
 */

@Getter
@Setter
@TableName("sys_basedata")
public class Basedata extends BaseModel<Basedata> {

    private String parentId;
    private String name;
    private String code;
    private String value;
    private String extendJson;
    private String description;
    private Integer sortorder;
    private String status;

}
