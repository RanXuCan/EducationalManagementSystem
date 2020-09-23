package com.rxc.dao;

import com.rxc.entity.Course;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 20:10
 */
@Repository
public class CourseDao extends BaseDao {

    public boolean addCourse(Course course) {               //添加课程
        String sql = "insert into course values(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, course.getCno());
            pstmt.setString(2, course.getCname());
            pstmt.setString(3, course.getCterm());
            pstmt.setDouble(4, course.getCcredit());
            pstmt.setInt(5, course.getChours());
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public List<Course> findCourse(String selectItem, String courseInfor) {         //查找课程
        List<Course> courselist = new ArrayList<Course>();
        String sql = "select * from course ";
        if (selectItem.equals("1")) sql = sql + " where cname like '%" + courseInfor.trim() + "%'";
        else if (selectItem.equals("2")) sql = sql + " where cterm=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            if (selectItem.equals("2")) pstmt.setString(1, courseInfor);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {                   //大致查询，可能有多门课程
                Course cou = new Course();
                cou.setCno(rst.getString(1).trim());
                cou.setCname(rst.getString(2));
                cou.setCterm(rst.getString(3));
                cou.setCcredit(rst.getDouble(4));
                cou.setChours(rst.getInt(5));
                courselist.add(cou);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courselist;
    }

    public List<Course> getAllCourse() {                     //获得所有课程
        List<Course> courselist = new ArrayList<Course>();
        String sql = "select * from course ";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Course cou = new Course();
                cou.setCno(rst.getString(1).trim());
                cou.setCname(rst.getString(2));
                cou.setCterm(rst.getString(3));
                cou.setCcredit(rst.getDouble(4));
                cou.setChours(rst.getInt(5));
                courselist.add(cou);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courselist;
    }

    public Course getCourseByCno(String cno) {         //根据课程编号得到课程
        Course cou = new Course();
        String sql = "select * from course where cno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cno);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {             //结果唯一
                cou.setCno(rst.getString(1).trim());
                cou.setCname(rst.getString(2));
                cou.setCterm(rst.getString(3));
                cou.setCcredit(rst.getDouble(4));
                cou.setChours(rst.getInt(5));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return cou;
    }

    public Course getCourseByCname(String cname) {            //根据课程名称查询课程,教师查询他开的课的人数情况能用
        Course cou = new Course();
        String sql = "select * from course where cname=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, cname);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {             //结果唯一
                cou.setCno(rst.getString(1).trim());
                cou.setCname(rst.getString(2));
                cou.setCterm(rst.getString(3));
                cou.setCcredit(rst.getDouble(4));
                cou.setChours(rst.getInt(5));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return cou;
    }

}

