package com.rxc.dao;

import com.rxc.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 22:46
 */
@Repository
public class TeacherDao extends BaseDao {
    public boolean addTeacher(Teacher tea) {             //添加教师
        try {
            String sql = "insert into teacher(tno,tname,tidentityNum,tsex,ttitile,tphone,tpassword) values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
//            CallableStatement cst = dbconn.prepareCall("{call INSERT_INTO_TEACHER(?,?,?,?,?,?)}");  //调用存储过程
//            cst.setString(1, tea.getTno());
//            cst.setString(2, tea.getTname());
//            cst.setString(3, tea.getTidentityNum());
//            cst.setString(4, tea.getTsex());
//            cst.setString(5, tea.getTtitle());
//            cst.setString(6, tea.getTphone());
            if (tea.getTpassword() == null || "".equals(tea.getTpassword())) {
                tea.setTpassword("123456");
            }
            pstmt.setString(1, tea.getTno());
            pstmt.setString(2, tea.getTname());
            pstmt.setString(3, tea.getTidentityNum());
            pstmt.setString(4, tea.getTsex());
            pstmt.setString(5, tea.getTtitle());
            pstmt.setString(6, tea.getTphone());
            pstmt.setString(7, tea.getTpassword());
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean isTeacher(String loginacc, String tpassword) {    //判断教师账户是否合法
        String sql = "select tpassword from teacher where tno=?";
        String existpswd = null;
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, loginacc);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {     //一个账号只对应一个教师，不用while
                existpswd = (String) rst.getString("tpassword").trim();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return tpassword.equals(existpswd);
    }

    public Teacher findTeacher(String tno) {   //根据工号查找教师
        Teacher tea = new Teacher();
        String sql = "select * from teacher where tno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, tno);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {    //每个工号只有一个记录，不用while
                tea.setTno(rst.getString(1).trim());
                tea.setTname(rst.getString(2).trim());
                tea.setTidentityNum(rst.getString(3).trim());
                tea.setTsex(rst.getString(4).trim());
                tea.setTtitle(rst.getString(5).trim());
                tea.setTphone(rst.getString(6).trim());
                tea.setTpassword(rst.getString(7).trim());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return tea;
    }

    public List<Teacher> findTeacherByname(String tname) {   //根据姓名查找教师 ,是大致查询，也可以详细查询
        List<Teacher> teacherlist = new ArrayList<Teacher>();
        String sql = "select * from teacher where tname like '%" + tname.trim() + "%'";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Teacher tea = new Teacher();
                tea.setTno(rst.getString(1).trim());
                tea.setTname(rst.getString(2).trim());
                tea.setTidentityNum(rst.getString(3).trim());
                tea.setTsex(rst.getString(4).trim());
                tea.setTtitle(rst.getString(5).trim());
                tea.setTphone(rst.getString(6).trim());
                tea.setTpassword(rst.getString(7).trim());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return teacherlist;
    }
}

