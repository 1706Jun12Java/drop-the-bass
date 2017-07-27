package com.revature.dao;


import com.revature.domain.User;

public interface UserDao
{
    int createUser(User user);

    User getUserById(int id);

    boolean loginAuth (String username, String password);
}
