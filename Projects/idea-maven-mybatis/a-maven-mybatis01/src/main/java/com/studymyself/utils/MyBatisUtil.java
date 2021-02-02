package com.studymyself.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisUtil {

    //需要在外边定义
    private static SqlSessionFactory sqlSessionFactory = null;
    //SqlSessionFactory对象只能被创建一次
    static {
        //SqlSessionFactoryBuilder对象只是为了创建SqlSessionFactory对象，所以不需要引用指向
        //SqlSessionFactoryBuilder对象，代码执行完毕该对象就被回收了
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义一个集合存储SqlSession对象，确保一次事务（线程、请求）只创建一个
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取当前线程的SqlSession对象
     * @return
     */
    public static SqlSession getSqlSession(){
        //先获取local集合中的SqlSession
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭资源以及移除集合中的sqlSession对象
     */
    public static void close(SqlSession sqlSession){

        if (sqlSession != null) {

            sqlSession.close();

            local.remove();

        }

    }
}
