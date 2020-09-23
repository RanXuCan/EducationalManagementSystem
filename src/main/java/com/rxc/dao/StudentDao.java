package com.rxc.dao;

import com.rxc.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 19:55
 */
public class StudentDao extends BaseDao {

    public boolean addStudent(Student stu) {
        String sql = "insert into student(sno,sname,sidentityNum,ssex,sphone,spassword) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, stu.getSno().trim());
            pstmt.setString(2, stu.getSname());
            pstmt.setString(3, stu.getSidentityNum());
            pstmt.setString(4, stu.getSsex().trim());
            pstmt.setString(5, stu.getSphone().trim());
            pstmt.setString(6, stu.getSpassword().trim());
            int effect = pstmt.executeUpdate();
            return effect > 0;               //被影响行数大于0，则插入成功
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean isStudent(String loginacc, String tpassword) {
        String sql = "select spassword from student where sno=?";
        String existpswd = null;
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, loginacc);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {     //一个账号只对应一个学生，不用while
                existpswd = (String) rst.getString("spassword").trim();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (tpassword.equals(existpswd)) return true;
        else return false;
    }

    public Student findStudent(String sno) {
        Student stu = new Student();
        String sql = "select * from student where sno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, sno);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                stu.setSno(rst.getString(1).trim());
                stu.setSname(rst.getString(2).trim());
                stu.setSidentityNum(rst.getString(3).trim());
                stu.setSsex(rst.getString(4).trim());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return stu;
    }

}
