package com.rxc.service;

import com.rxc.dao.TeacherDao;
import com.rxc.entity.Teacher;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:16
 */
public class TeacherService {
    TeacherDao dao = new TeacherDao();

    public boolean isTeacher(Teacher tea) {
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存登录情况的信息
        String message = null;
        if(dao.isTeacher(tea.getTno().trim(), tea.getTpassword().trim())) {
            Teacher existtea = dao.findTeacher(tea.getTno().trim());
            String teachername = existtea.getTname();
            request.getSession().setAttribute("teachername", teachername);        //储存到session 显示教师名称
            request.getSession().setAttribute("tea", existtea);                     //储存到session方便后续教师操作：选课、退课、录入成绩
            return true;
        }
        else {
            message = "账号或密码错误!";
            request.setAttribute("message", message);
            return false;
        }
    }

    public boolean addTeacher(Teacher tea) {
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存登录情况的信息
        String message = null;
        if(dao.addTeacher(tea)) {
            message = "注册教师成功!";
            request.setAttribute("message", message);
            return true;
        }
        else {
            message = "注册教师失败,工号已存在!";
            request.setAttribute("message", message);
            return false;
        }
    }

}

