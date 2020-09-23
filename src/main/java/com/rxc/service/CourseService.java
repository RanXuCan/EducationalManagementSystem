package com.rxc.service;

import com.rxc.dao.*;
import com.rxc.entity.*;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 22:59
 */
public class CourseService {

    private final CourseDao courseDao = new CourseDao();
    private final TCDao tcDao = new TCDao();

    public boolean addCourse(Course course) {
        HttpServletRequest request = ServletActionContext.getRequest();
        if(courseDao.addCourse(course)) {
            request.setAttribute("message", "添加课程成功");
            return true;
        }else {
            request.setAttribute("message", "添加课程失败，此课程编号已存在");
            return false;
        }
    }

    public boolean findCourse(String selectItem,String courseInfor) {              //大致查找课程，不是精确查找
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Course> courselist = courseDao.findCourse(selectItem, courseInfor);
        if(!courselist.isEmpty()) {
            request.setAttribute("courselist", courselist);
            return true;
        }else {
            request.setAttribute("message", "查无此课程");
            return false;
        }
    }

    public void teacherSeeCourse() {               //教师查看自己的课表
        HttpServletRequest request = ServletActionContext.getRequest();
        Teacher tea = (Teacher)request.getSession().getAttribute("tea");
        List<Course> courselist = tcDao.getCourseListByTno(tea.getTno().trim());        //根据教师返回他的课表
        request.setAttribute("courselist", courselist);
    }

    public boolean getAllCourse() {                //返回所有课程
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Course> courselist = courseDao.getAllCourse();
        request.setAttribute("courselist", courselist);
        return true;
    }

    public boolean teacherChooseCourse(Course course) {           //教师选课
        HttpServletRequest request = ServletActionContext.getRequest();
        Teacher tea = (Teacher)request.getSession().getAttribute("tea");
        String cno = course.getCno().trim();
        String tno = tea.getTno().trim();
        if(tcDao.isChoosed(cno, tno)) {          //说明已经选过了
            request.setAttribute("message", "您已经选过此课了");
            return false;
        }
        if(tcDao.addTc(cno, tno)) {
            request.setAttribute("message", "选课成功");
            return true;
        }else {
            request.setAttribute("message", "选课失败");
            return false;
        }
    }

    public boolean teacherQuitCourse(Course course) {                //教师退课
        HttpServletRequest request = ServletActionContext.getRequest();
        Teacher tea = (Teacher)request.getSession().getAttribute("tea");
        String cno = course.getCno().trim();
        String tno = tea.getTno().trim();
        if(!tcDao.isChoosed(cno, tno)) {          //说明还没选过这个课
            request.setAttribute("message", "您还没有选择过此课");
            return false;
        }
        if(tcDao.deleteTc(cno, tno)) {
            request.setAttribute("message", "退选成功");
            return true;
        }else {
            request.setAttribute("message", "退选失败,已经开课（已有学生在您此课的班级）.");
            return false;
        }
    }

    public boolean findCourseFromStudent(String selectItem, String inputInfor) {  //学生查寻课程，可以根据名称，教师
        HttpServletRequest request = ServletActionContext.getRequest();
        if(selectItem.equals("1")) {         //根据的课程名称查找
            List<Course> courselist = courseDao.findCourse("1", inputInfor);   //先得到课程名称含有inputInfor的全部课程;
            List<List<Teacher>> coutealist = new ArrayList<List<Teacher>>();
            for(Course cou : courselist) {
                List<Teacher> teacherlist = tcDao.getTeacherListByCno(cou.getCno().trim());   //教这门课的所有老师
                coutealist.add(teacherlist);
            }
            request.setAttribute("selectItem", selectItem);   //这是来判断学生选的什么，来生成不同的表格    //算了 不搞这个了，界面只有根据名称的表格
            request.setAttribute("courselist", courselist);   //这是课程list
            request.setAttribute("coutealist", coutealist);   //这是课程list的每个课程教的老师的list
        }
        else if(selectItem.equals("2")) {       //根据教师查找
            List<Teacher> teacherlist = (new TeacherDao()).findTeacherByname(inputInfor);       //得到教师名称包含inputInfor的所有教师
            List<List<Course>> teacoulist = new ArrayList<List<Course>>();
            for(Teacher tea : teacherlist) {
                List<Course> courselist = tcDao.getCourseListByTno(tea.getTno().trim());    //这名老师教的所有课
                teacoulist.add(courselist);
            }
            request.setAttribute("selectItem", selectItem);
            request.setAttribute("teacherlist", teacherlist);       //这是教师list
            request.setAttribute("teacoulist", teacoulist);         //这是教师list的每名教师教的所有课程
        }
        return true;
    }

    public void getAllTC() {           //学生点击了查询所有可选课程的按钮
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TC> tclist = tcDao.getAllTC();
        List<Course> courselist = new ArrayList<Course>();
        List<Teacher> teacherlist = new ArrayList<Teacher>();
        for(TC tc : tclist) {
            String cno = tc.getCno();
            String tno = tc.getTno();
            Course cou = courseDao.getCourseByCno(cno);
            courselist.add(cou);
            Teacher tea = (new TeacherDao()).findTeacher(tno);
            teacherlist.add(tea);
        }
        request.setAttribute("courselist", courselist);
        request.setAttribute("teacherlist", teacherlist);
    }

    public void studentSeeCourse() {            //学生查看自己课表
        HttpServletRequest request = ServletActionContext.getRequest();
        String sno = ((Student)request.getSession().getAttribute("stu")).getSno().trim();
        List<Course> courselist = new ArrayList<Course>();
        List<Teacher> teacherlist = new ArrayList<Teacher>();
        List<String> strlist = tcDao.studentSeeCourse(sno);          //得到学生的课表，String的list,因为存储过程返回的不是已定义的自定义类型
        for(int i =0;i<strlist.size();i++) {
            if(i%2==0) {
                Course cou = new Course();
                cou.setCname(strlist.get(i));
                courselist.add(cou);
            }else {
                Teacher tea = new Teacher();
                tea.setTname(strlist.get(i));
                teacherlist.add(tea);
            }
        }
        request.setAttribute("courselist", courselist);
        request.setAttribute("teacherlist", teacherlist);
    }

    public boolean studentChooseCourse(String cno, String tno) {            //学生选课
        HttpServletRequest request = ServletActionContext.getRequest();
        String sno = ((Student)request.getSession().getAttribute("stu")).getSno().trim();
        int tcid = tcDao.getTcid(cno, tno);     //先得到这门课的选课记录编号
        if((new GradeDao()).insertIntoGrade(tcid, sno)) {
            request.setAttribute("message", "选课成功");
            return true;
        }
        else {
            request.setAttribute("message", "你已经选过此课了");
            return false;
        }
    }

    public boolean studentQuitCourse(String cno, String tno) {               //学生退课
        HttpServletRequest request = ServletActionContext.getRequest();
        String sno = ((Student)request.getSession().getAttribute("stu")).getSno().trim();
        int tcid = tcDao.getTcid(cno, tno);     //先得到这门课的选课记录编号
        if((new GradeDao()).deleteStudentCourse(tcid, sno)) {
            request.setAttribute("message", "退选成功");
            return true;
        }
        else {
            request.setAttribute("message", "你还未选过此课");
            return false;
        }
    }

    public void teacherSeeDetail(String cname) {             //教师查看他教的某门课的人数情况，以便录入修改成绩
        HttpServletRequest request = ServletActionContext.getRequest();
        Teacher tea = (Teacher)request.getSession().getAttribute("tea");        //获取是哪个老师
        Course cou = courseDao.getCourseByCname(cname);
        int tcid = tcDao.getTcid(cou.getCno(), tea.getTno());        //     获取这门课的选课编号
        List<Grade> gradelist = (new GradeDao()).getAllSnoByTcid(tcid);       //获取上这门课的所有学生
        List<Student> stulist = new ArrayList<Student>();
        for(Grade grade : gradelist) {
            Student stu = (new StudentDao()).findStudent(grade.getSno());         //根据 学号一个一个查学生
            stulist.add(stu);
        }
        request.setAttribute("gradelist", gradelist);
        request.setAttribute("stulist", stulist);
        request.setAttribute("course", cou);
    }

    public boolean teacherUpdateScore(int tcid, String sno, double cscore) {       //教师保存一项成绩,可以是录入，也可以是修改
        HttpServletRequest request = ServletActionContext.getRequest();
        if(new GradeDao().teacherUpdateScore(tcid,sno,cscore)) {
            request.setAttribute("message", "成绩保存成功 ");
            return true;
        }else {
            request.setAttribute("message", "成绩保存失败 ");
            return false;
        }
    }

    public void studentSeeScore() {                    //学生查询课程成绩
        HttpServletRequest request = ServletActionContext.getRequest();
        Student stu = (Student) request.getSession().getAttribute("stu");
        List<Teacher> teacherlist = new ArrayList<Teacher>();
        List<Course> courselist = new ArrayList<Course>();
        List<Grade> gradelist = (new GradeDao()).studentSeeScore(stu.getSno());        //根据学号得到他的课程成绩
        for(Grade grade : gradelist) {
            TC tc = tcDao.getTcByTcid(grade.getTcid());          //得到这门课的任课情况
            Teacher tea = (new TeacherDao()).findTeacher(tc.getTno());
            teacherlist.add(tea);
            Course cou = (new CourseDao()).getCourseByCno(tc.getCno());
            courselist.add(cou);
        }
        request.setAttribute("teacherlist", teacherlist);      //储存课程任课教师
        request.setAttribute("courselist", courselist);        //课程详细情况
        request.setAttribute("gradelist", gradelist);          //课程成绩情况
    }

}
