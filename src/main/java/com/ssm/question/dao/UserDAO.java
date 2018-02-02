package com.ssm.question.dao;

import com.ssm.question.pojo.User;

public interface UserDAO {
    //添加用户
    void addUser(User user);

    //验证用户名是否存在
    int getUserCountByName(String username);

    User getUserByName(String username);
}
