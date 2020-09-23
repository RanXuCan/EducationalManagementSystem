package com.rxc.dao;

import com.rxc.entity.Grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 20:17
 */
public class GradeDao extends BaseDao {
    public boolean insertIntoGrade(int tcid, String sno) {     //学生选课，此时score并未保存，为null
        String sql = "insert into grade(tcid,sno) values(?,?)";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setInt(1, tcid);
            pstmt.setString(2, sno);
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudentCourse(int tcid, String sno) {         //学生退选课程
        String sql = "delete from grade where tcid=? and sno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setInt(1, tcid);
            pstmt.setString(2, sno);
            //System.out.println(tcid+"    "+sno);
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public List<Grade> getAllSnoByTcid(int tcid) {                    //教师返回他某门课的所有学生
        List<Grade> gradelist = new ArrayList<Grade>();
        String sql = "select * from grade where tcid=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setInt(1, tcid);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Grade grade = new Grade();
                grade.setTcid(tcid);
                grade.setSno(rst.getString(2));
                grade.setCscore(rst.getDouble(3));
                gradelist.add(grade);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return gradelist;
    }

    public boolean teacherUpdateScore(int tcid, String sno, double cscore) {        //教师保存一个学生成绩  可以是录入也可以是修改
        String sql = "update grade set cscore=? where tcid=? and sno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setDouble(1, cscore);
            pstmt.setInt(2, tcid);
            pstmt.setString(3, sno);
            int effect = pstmt.executeUpdate();
            return effect > 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public List<Grade> studentSeeScore(String sno) {                      //学生查询课程成绩   先得到他的课程
        List<Grade> gradelist = new ArrayList<Grade>();
        String sql = "select * from grade where sno=?";
        try {
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, sno);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                Grade grade = new Grade();
                grade.setTcid(rst.getInt(1));
                grade.setSno(sno);
                grade.setCscore(rst.getDouble(3));
                gradelist.add(grade);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return gradelist;
    }
}
