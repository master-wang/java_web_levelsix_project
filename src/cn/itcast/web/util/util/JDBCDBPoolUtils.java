package cn.itcast.web.util.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *  Druid 连接池的工具类 用于操作数据库
 */
public class JDBCDBPoolUtils {
    //定义变量
    private static DataSource ds;
    static {

        try {
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCDBPoolUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取datasource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取链接的对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    /**
     * 获取连接池的方法
     */
    public static DataSource getDatasource(){

        return ds;
    }
    /**
     * 释放资源
     */
    public static void close(Statement stmt,Connection conn){
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();//归还链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();//归还链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
