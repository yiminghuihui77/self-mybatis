package com.minghui.test;

import com.minghui.model.Account;
import com.minghui.tools.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 查询数据库测试
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 16:30
 **/
public class JdbcDemo {

    public static void main(String[] args) {
      insertOperation();
    }

    /**
     * 查询操作
     */
    public static void queryOperation() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement("select * from account where id = ?");

            //执行SQL
            statement.setInt(1, 1);

            //获取结果
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int account = resultSet.getInt(3);

                Account accountObject = new Account(id, name, account);

                System.out.println(accountObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            JdbcUtils.release(connection, statement, resultSet);
        }
    }

    public static void insertOperation() {
        Account account = new Account(3, "李四", 200);
        int num = JdbcUtils.insert("insert into account(id, name, account) values(?, ?, ?)", account);
        System.out.println("影响行数：" + num);
    }

}
