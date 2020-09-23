package com.rxc.entity;

import lombok.Data;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 16:50
 */
@Data
public class TC implements java.io.Serializable {
    private int tcid;      //数据库自增主键  表示教师开课编号
    private String cno;    //外键  课程编号
    private String tno;    //外键  教师编号

    public TC() {
    }
}
