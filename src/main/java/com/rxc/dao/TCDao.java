package com.rxc.dao;

import com.rxc.entity.Course;
import com.rxc.entity.TC;
import com.rxc.entity.Teacher;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 20:43
 */
public class TCDao extends BaseDao {

    public List<Course> getCourseListByTno(String tno) {           //根据教师工号返回他教的课
        List<Course> courselist = new ArrayList<Course>();
        String sql = "select tc.cno, cname, chours, ccredit from course ,tc "
                + "where tc.cno=course.cno and tno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, tno);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Course cou = new Course();
                cou.setCno(rst.getString(1).trim());
                cou.setCname(rst.getString(2));
                cou.setChours(rst.getInt(3));
                cou.setCcredit(rst.getDouble(4));
                courselist.add(cou);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courselist;
    }

    public List<Teacher> getTeacherListByCno(String cno) {           //根据课程编号返回所有开这门课的教师
        List<Teacher> teacherlist = new ArrayList<Teacher>();
        String sql = "select tc.tno, tname, ttitle from teacher, tc "
                + "where tc.tno=teacher.tno and cno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {                         //教师可能有多名
                Teacher tea = new Teacher();
                tea.setTno(rst.getString(1));
                tea.setTname(rst.getString(2));
                tea.setTtitle(rst.getString(3));
                teacherlist.add(tea);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return teacherlist;
    }

    public boolean isChoosed(String cno, String tno) {        //判断教师是否已经选了这门课
        String sql = "select * from tc where cno=? and tno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            pstmt.setString(2, tno);
            ResultSet rst = pstmt.executeQuery();
            return rst.next();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return true;
        }
    }

    public boolean addTc(String cno, String tno) {             //教师选择一门课
        String sql = "insert into tc(cno,tno) values(?,?)";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            pstmt.setString(2, tno);
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean deleteTc(String cno, String tno) {             //教师退选一门课
        String sql = "delete from tc where cno=? and tno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            pstmt.setString(2, tno);
            int effect = pstmt.executeUpdate();
            return effect > 0;               //被影响行数大于0，则删除成功
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public List<TC> getAllTC() {             //所有被老师选的课
        List<TC> tclist = new ArrayList<TC>();
        String sql = "select * from tc";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                TC tc = new TC();
                tc.setTcid(rst.getInt(1));
                tc.setCno(rst.getString(2).trim());
                tc.setTno(rst.getString(3).trim());
                tclist.add(tc);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return tclist;
    }

    public List<String> studentSeeCourse(String sno) {                //学生查询自己课表
        List<String> strlist = new ArrayList<String>();
        String sql = "select cname,tname " +
                "from course,teacher,tc,grade " +
                "where grade.tcid=tc.tcid and tc.cno=course.cno " +
                "and tc.tno=teacher.tno and grade.sno=" + sno.trim();
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
//            CallableStatement cst=dbconn.prepareCall("{call FIND_STUDENTS_TEACHER(?)}");  //调用存储过程返回某学生的课表
//            cst.setString(1, sno);
//            ResultSet rst = cst.executeQuery();
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                String cname = rst.getString(1);
                strlist.add(cname);
                String tname = rst.getString(2);
                strlist.add(tname);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return strlist;
    }

    public int getTcid(String cno, String tno) {        //根据cno和tno得到Tcid  学生选课用
        String sql = "select tcid from tc where cno=? and tno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            pstmt.setString(2, tno);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) return rst.getInt(1);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return -1;
    }

    public TC getTcByTcid(int tcid) {                 //根据tcid返回这门课的信息，学生查课程成绩用到
        String sql = "select * from tc where tcid=?";
        TC tc = new TC();
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setInt(1, tcid);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                tc.setTcid(tcid);
                tc.setCno(rst.getString(2));
                tc.setTno(rst.getString(3));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return tc;
    }
}

