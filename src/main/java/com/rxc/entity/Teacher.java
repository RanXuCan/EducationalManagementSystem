package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:49
 */ 
@Data
public class Teacher implements java.io.Serializable {
    private String tno;
    private String tname;
    private String tidentityNum;
    private String tsex;
    private String ttitle;
    private String tphone;
    private String tpassword;

    public Teacher() {
    }
}
