package com.zee.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 仓库实体
 */
@Setter
@Getter
@TableName("bd_storage")
public class Storage extends BaseModel {
    //仓库状态
    private String status;
    //仓库类型
    private String storageType;
    //仓库编码
    private String storageCode;
    //仓库名字
    private String storageName;
    //仓库地址
    private String storageAddress;
    //联系人
    private String contact;
    //电话
    private String phone;

}
