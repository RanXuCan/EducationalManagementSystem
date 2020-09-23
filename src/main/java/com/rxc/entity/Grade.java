package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:50
 */
@Data
public class Grade implements java.io.Serializable{
    private int tcid;      //外键, 教师开课编号 不是课程编号 一门课可以被多名老师选择
    private String sno;
    private double cscore;

    public Grade() {
    }
}
