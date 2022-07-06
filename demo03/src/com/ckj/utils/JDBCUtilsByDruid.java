package com.ckj.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 程柯嘉
 * @version 1.0
 * 基于德鲁伊数据库连接池的工具类
 */
@SuppressWarnings({"all"})
public class JDBCUtilsByDruid {
    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/usr/local/apache-tomcat-8.5.79/webapps/demo03/WEB-INF/classes/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }
    public static void closeConnection(ResultSet r, Connection c) throws SQLException {
        r.close();
        c.close();
    }
    public static void closeConnection(ResultSet r, Statement s, Connection c) throws SQLException {
        r.close();
        s.close();
        c.close();
    }
}
