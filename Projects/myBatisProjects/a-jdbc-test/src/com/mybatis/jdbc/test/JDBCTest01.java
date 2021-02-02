package com.mybatis.jdbc.test;

import com.mybatis.jdbc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest01 {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<User> userList = new ArrayList();

        try {
            //
            Class.forName("com.mysql.jdbc.Driver");

            //
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSH=false","root","rong195302");

            //
            String sql = "select id,loginName,loginPwd,realName from t_user";
            ps = conn.prepareStatement(sql);

            //
            rs = ps.executeQuery();

            //
            while (rs.next()){

                //从结果集中获取数据
                int id = rs.getInt("id");
                String loginName = rs.getString("loginName");
                String loginPwd = rs.getString("loginPwd");
                String realName = rs.getString("realName");

                //将上面零散的数据封装到一个user对象中（即封装成javabean）
                //将javabean放到容器userList集合中
                User user = new User();
                user.setId(id);
                user.setLoginName(loginName);
                user.setLoginPwd(loginPwd);
                user.setRealName(realName);
                userList.add(user);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        //拿着List集合去做展示（MVC中的View）
        for (User user:
             userList) {
            System.out.println(user);
        }

    }

}
