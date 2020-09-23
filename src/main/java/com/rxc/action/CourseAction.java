package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rxc.entity.Course;
import com.rxc.service.CourseService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:22
 */
@Controller
public class CourseAction extends ActionSupport {

    @Getter
    @Setter
    private Course course;

    @Resource
    private CourseService courseService;

    public String addCourse() {
        if (courseService.addCourse(course)) return SUCCESS;
        else return ERROR;
    }

    public String findCourse() {            //查找一门课  可能来自管理员,教师，返回的视图多样
        HttpServletRequest request = ServletActionContext.getRequest();
        String selectItem = request.getParameter("selectItem").trim();
        String courseInfor = request.getParameter("courseInfor").trim();
        String comefrom = request.getParameter("comefrom");
        if (courseService.findCourse(selectItem, courseInfor)) {
            if (comefrom.equals("teacher")) return "teachersuccess";
            else return "managersuccess";
        } else if (comefrom.equals("teacher")) return "teachererror";
        else return "managererror";
    }

    public String teacherSeeCourse() {        //教师查看自己的课表
        courseService.teacherSeeCourse();
        return SUCCESS;
    }

    public String getAllCourse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String comefrom = request.getParameter("comefrom");
        if (courseService.getAllCourse() && comefrom.equals("teacher")) return "teachersuccess";
        else if (courseService.getAllCourse() && comefrom.equals("manager")) return "managersuccess";
        else return ERROR;
    }

    public String teacherChooseCourse() {            //教师选择一门课
        if (courseService.teacherChooseCourse(course)) return SUCCESS;
        else return ERROR;
    }

    public String teacherQuitCourse() {            //教师退选一门课
        if (courseService.teacherQuitCourse(course)) return SUCCESS;
        else return ERROR;
    }

    public String findCourseFromStudent() {            //学生查询课，不是从Course而是TC
        HttpServletRequest request = ServletActionContext.getRequest();
        String selectItem = request.getParameter("selectItem").trim();
        String inputInfor = request.getParameter("inputInfor").trim();
        if (courseService.findCourseFromStudent(selectItem, inputInfor)) return SUCCESS;
        else return ERROR;
    }

    public String getAllTC() {
        courseService.getAllTC();
        return SUCCESS;
    }

    public String studentSeeCourse() {        //学生查看自己的课表
        courseService.studentSeeCourse();
        return SUCCESS;
    }

    public String studentChooseCourse() {            //学生选择一门课
        HttpServletRequest request = ServletActionContext.getRequest();
        String cno = request.getParameter("cno");
        String tno = request.getParameter("tno");
        if (courseService.studentChooseCourse(cno, tno)) return SUCCESS;
        else return ERROR;
    }

    public String studentQuitCourse() {            //学生退选一门课
        HttpServletRequest request = ServletActionContext.getRequest();
        String cno = request.getParameter("cno");
        String tno = request.getParameter("tno");
        if (courseService.studentQuitCourse(cno, tno)) return SUCCESS;
        else return ERROR;
    }

    public String teacherSeeDetail() {               //教师查看课程选课人数信息，以便后续录入成绩  只传进来课程名字
        HttpServletRequest request = ServletActionContext.getRequest();
        String cname = request.getParameter("cname");
        courseService.teacherSeeDetail(cname);
        return SUCCESS;
    }

    public String teacherUpdateScore() {                //教师保存课程成绩
        HttpServletRequest request = ServletActionContext.getRequest();
        double cscore = Double.parseDouble(request.getParameter("cscore"));
        int tcid = Integer.parseInt(request.getParameter("tcid"));
        //System.out.println(tcid);
        String sno = request.getParameter("sno");
        if (courseService.teacherUpdateScore(tcid, sno, cscore)) return SUCCESS;
        else return INPUT;
    }

    public String studentSeeScore() {                 //学生查询课程成绩
        courseService.studentSeeScore();
        return SUCCESS;
    }
}

