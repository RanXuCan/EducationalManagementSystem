package com.rxc.service;

import com.rxc.dao.StudentDao;
import com.rxc.entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:15
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao dao;

    public boolean addStudent(Student stu) {
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存注册情况的信息
        String message;
        if(dao.addStudent(stu)) {
            message = "注册学生成功!";
            request.setAttribute("message", message);
            return true;
        }
        else {
            message = "注册学生失败,学号已存在!";
            request.setAttribute("message", message);
            return false;
        }
    }

    public boolean isStudent(Student stu) {
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存登录情况的信息
        String message;
        if(dao.isStudent(stu.getSno().trim(), stu.getSpassword().trim())) {
            Student existstu = dao.findStudent(stu.getSno().trim());
            String studentname = existstu.getSname();
            request.getSession().setAttribute("studentname", studentname);        //储存到session 显示学生名称
            request.getSession().setAttribute("stu", existstu);                     //储存到session方便后续学生操作：选课、退课、查询成绩
            return true;
        }
        else {
            message = "账号或密码错误!";
            request.setAttribute("message", message);
            return false;
        }
    }

    public boolean studentSeeInfor() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Student stu = (Student) request.getSession().getAttribute("stu");
        Student existstu = dao.findStudent(stu.getSno().trim());
        request.getSession().setAttribute("stu", existstu);
        return false;
    }

}

