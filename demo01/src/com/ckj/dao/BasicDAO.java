package com.ckj.dao;


import com.ckj.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 程柯嘉
 * @version 1.0
 * 开发BasicDAO，是其他DAO的父类
 */
@SuppressWarnings({"all"})
public class BasicDAO<T> { //泛型指定具体的类型
    private QueryRunner qr = new QueryRunner();

    //开发通用的dml操作，针对任意的表
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();

            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //返回多个对象（及查询的结果是多行），针对任意表
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();

            List<T> query = qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //查询单行结果的通用方法
    public T querySingle(String sql,Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();

            T t = (T)qr.query(connection, sql, new BeanHandler(clazz), parameters);
            return t;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //查询单行单列的方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();

            Object query = qr.query(connection, sql, new ScalarHandler<>(), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
