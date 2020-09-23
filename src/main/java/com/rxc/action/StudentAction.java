package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rxc.entity.Student;
import com.rxc.service.StudentService;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:24
 */
public class StudentAction extends ActionSupport {

    /**
     *
     */
    private Student stu;
    private final StudentService studentService = new StudentService();

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public String studentRegist() {            //添加学生
        if (stu.getSsex().trim().equals("male")) stu.setSsex("男");
        else if (stu.getSsex().trim().equals("female")) stu.setSsex("女");
        if (studentService.addStudent(stu)) return SUCCESS;
        else return ERROR;
    }

    public String studentLogin() {            //学生登录
        if (studentService.isStudent(stu)) return SUCCESS;
        else return ERROR;
    }

    public String studentSeeInfor() {
        if (studentService.studentSeeInfor()) return SUCCESS;
        else return ERROR;
    }
}

