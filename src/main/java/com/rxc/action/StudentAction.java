package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rxc.entity.Student;
import com.rxc.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:24
 */
@Controller
public class StudentAction extends ActionSupport {
    @Autowired
    private StudentService studentService;

    @Getter
    @Setter
    private Student stu;

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

