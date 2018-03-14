package com.zee.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 最基础的实体类，其他实体类继承此实体类，带有最基本的实体持久化操作方法和每个实体必须要有的字段
 * @param <T>
 */

@Getter
@Setter
public class BaseModel<T extends Model> extends Model<BaseModel> {

    private String id;
    private Date createTime;
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
