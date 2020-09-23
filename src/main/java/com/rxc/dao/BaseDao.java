package com.rxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 19:48
 */
public class BaseDao {
    Connection dbconn = null;

    public BaseDao() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/studentmanagesystem?serverTimezone=UTC";
        String username = "root";
        String password = "root";
        try {
            Class.forName(driver);
            dbconn = DriverManager.getConnection(dburl, username, password);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}
