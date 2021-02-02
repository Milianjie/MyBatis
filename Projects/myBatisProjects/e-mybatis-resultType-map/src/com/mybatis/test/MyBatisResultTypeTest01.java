package com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyBatisResultTypeTest01 {

    public static void main(String[] args) {

        SqlSession sqlSession = null;

        try {

            //获取核心配置文件信息的SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

            //开启事务
            sqlSession = sqlSessionFactory.openSession();

            //do work()
            //查询emp表中的所有员工姓名，用String类型封装数据
//            List<String> empList1 = sqlSession.selectList("getAllName");
//            for (String ename:
//                 empList1) {
//                System.out.println(ename);
//            }

            //查询员工"SMITH"的信息，显示名字、薪资和部门名称
            //这里使用Map集合封装查询结果集和String类型给sql语句传值
            //注意是selectOne()方法
            Map<String,String> smithMap = sqlSession.selectOne("getByEname","SMITH");
            System.out.println(smithMap);
            System.out.println("============================================");

            //查询出所有工作岗位为MANAGER的员工，要求显示员工姓名和部门名称
            //这里使用Map集合封装查询结果集和String类型给sql语句传值
            //因为没有合适的javabean封装查询结果集，所以泛型中填Map
                List<Map> list = sqlSession.selectList("getNameAndDptByJob","MANAGER");
            System.out.println(list.toString());

            //提交事务
            sqlSession.commit();
        } catch (IOException e) {
            //出错，回滚事务
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
