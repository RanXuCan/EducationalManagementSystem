package com.rxc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 19:48
 */
public class BaseDao {
    Connection dbconn = null;

    public BaseDao() {
        String driver = null;
        String url = "";
        String username = null;
        String password = null;
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(driver);
            dbconn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}
