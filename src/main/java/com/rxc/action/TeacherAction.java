package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rxc.entity.Teacher;
import com.rxc.service.TeacherService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:24
 */
@Controller
public class TeacherAction extends ActionSupport {

    @Autowired
    private TeacherService teacherService;

    @Getter
    @Setter
    private Teacher tea;

    public String teacherRegist() {            //添加教师
        HttpServletRequest request = ServletActionContext.getRequest();
        if (tea.getTsex().trim().equals("male")) tea.setTsex("男");
        else if (tea.getTsex().trim().equals("female")) tea.setTsex("女");
        if (request.getParameter("ttitle").trim().equals("1")) tea.setTtitle("无");
        else if (request.getParameter("ttitle").trim().equals("2")) tea.setTtitle("讲师");
        else if (request.getParameter("ttitle").trim().equals("3")) tea.setTtitle("副教授");
        else if (request.getParameter("ttitle").trim().equals("3")) tea.setTtitle("教授");
        if (teacherService.addTeacher(tea)) return SUCCESS;
        else return ERROR;
    }

    public String teacherLogin() {            //教师登录
        if (teacherService.isTeacher(tea)) return SUCCESS;
        else return ERROR;
    }
}
