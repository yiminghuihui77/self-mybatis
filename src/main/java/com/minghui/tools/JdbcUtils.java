package com.minghui.tools;

import java.sql.*;

/**
 * JDBC工具类
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 16:21
 **/
public class JdbcUtils {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8";
    private static String username = "root";
    private static String password = "ymh96122";

    /**
     * 获取数据库连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 释放数据库连接
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn, Statement st, ResultSet rs) {

        if(rs!=null){
            try{
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try{
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try{
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 插入操作
     * @param sql
     * @param params
     * @return
     */
    public static int insert(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);

            for (int i = 1;i <= params.length;i++) {
                statement.setObject(i, params[i-1]);
            }

            //获取结果
            return statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            JdbcUtils.release(connection, statement, null);
        }
        return -1;
    }

}
