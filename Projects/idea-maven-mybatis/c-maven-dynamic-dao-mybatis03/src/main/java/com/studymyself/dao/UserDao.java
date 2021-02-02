package com.studymyself.dao;

import com.studymyself.entity.User;

import java.util.List;

//接口操作t_user表
public interface UserDao {

    //该方法查询t_user表中所有数据
    public List<User> getAll();

    //添加数据的方法
    public int addUser(User user);

    //通过id查询数据
    public User selectById(Integer id);

}
