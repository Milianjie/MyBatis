package com.studymyself.dao;

import com.studymyself.entity.User;

import java.util.List;

//接口操作t_user表
public interface UserDao {

    //通过if动态sql语句查询数据,参数必须是Java对象
    public List<User> selectByIf(User user);

    //通过if和where动态sql语句查询数据,参数必须是Java对象
    public List<User> selectByWhere(User user);

    //执行查询有in条件的sql语句
    public List<User> selectByForeach(List<Integer> integers);

    //查询数据分页显示
    public List<User> selectForPageHelper();
}
