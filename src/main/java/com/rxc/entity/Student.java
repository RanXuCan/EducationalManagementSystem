package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:49
 */

@Data
public class Student implements java.io.Serializable {
    private String sno;
    private String sname;
    private String sidentityNum;
    private String ssex;
    private String classno;         //学生所在班级，外键
    private String sphone;
    private double stotalcredit;
    private String spassword;

    public Student() {
    }
}
