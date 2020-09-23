package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:49
 */
@Data
public class Course implements java.io.Serializable {
    private String cno;
    private String cname;
    private String cterm;
    private double ccredit;
    private int chours;

    public Course() {
    }
}
