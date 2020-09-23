package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:51
 */

@Data
public class Classes implements java.io.Serializable{
    private String classno;
    private String tno;     //外键，一个班级有一个班主任

    public Classes() {
    }
}
