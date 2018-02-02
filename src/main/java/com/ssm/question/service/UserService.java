package com.ssm.question.service;

import com.ssm.question.pojo.User;

public interface UserService {
    void addUser(User user) throws Exception;

    int getUserCountByName(String username);

    User doLogin(String username, String password) throws Exception;
}
