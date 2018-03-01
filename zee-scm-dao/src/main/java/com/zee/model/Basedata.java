package com.zee.model;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
@TableName("basedata")
public class Basedata extends Model<Basedata> {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String name;

    private String text;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
