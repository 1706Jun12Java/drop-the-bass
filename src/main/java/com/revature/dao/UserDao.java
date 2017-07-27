package com.revature.dao;


import com.revature.domain.User;

public interface UserDao
{
    int createUser(String username, String password, int accountType);

    User getUserById(int id);

}
