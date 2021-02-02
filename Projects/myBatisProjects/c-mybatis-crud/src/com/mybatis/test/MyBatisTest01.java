package com.mybatis.test;

import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest01 {

    public static void main(String[] args) {

        SqlSession sqlSession = null;

        try {

//            String resource = "mybatis-config.xml";
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //合并上面代码
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));

            //开启事务
            sqlSession = sqlSessionFactory.openSession();

            /*
                do work() 编写业务代码
             */
            //insert
//            User user = new User();
//            user.setLoginName("18888688@qq.com");
//            user.setLoginPwd("1238888ffff");
//            user.setRealName("马云&马化腾");
            //sqlSession.insert()方法中的参数可以只传一个sql映射文件中sql语句的id字符串，表示sql映射文件中的insert语句是这样的：
            // insert into t_user(loginName,lodinPwd,realName) values("xxx","yyy","zzz") 所以不需要再传一个javabean对象
            //而这里我们是需要传javabean对象的，传两个参数
            //返回一个int类型数据，表示改动的记录条数
//            int count1 = sqlSession.insert("save",user);

            //update
//            User user = new User();
//            user.setId(5);
//            user.setLoginPwd("88888888");
//            user.setRealName("大马猴");
//            int count = sqlSession.update("update",user);
//            System.out.println(count);

            //selectOne
            User user1 = sqlSession.selectOne("getById",1);
            System.out.println(user1);
            System.out.println("========================================");
            //selectList
            List<User> userList1 = new ArrayList<>();
            userList1 = sqlSession.selectList("getAll");
            for (User user2:
                 userList1) {
                System.out.println(user2);
            }
            System.out.println("========================================");

            //delete
            int count = sqlSession.delete("deleteById",5);
            System.out.println(count);
            System.out.println("========================================");

            //selectList
            List<User> userList2 = new ArrayList<>();
            userList2 = sqlSession.selectList("getAll");
            for (User user2:
                    userList2) {
                System.out.println(user2);
            }

            //事务正常结束，提交
            sqlSession.commit();
        } catch (IOException e) {
            //出现异常，事务回滚
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            //关闭资源
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

}
