package com.revature.dao.hibernate;


import com.revature.dao.UserDao;
import com.revature.domain.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDataAccess implements UserDao
{
    private Session session = HibernateUtil.getSession();

    public static String hashPassword(String password)
    {
        return UserDao.hashPassword(password);
    }

    @Override
    public int createUser(User user)
    {
        int id = (int) session.save(user);
        session.close();

        return id;
    }

    @Override
    public User getUserById(int id)
    {
        User user = (User) session.get(User.class, id);
        session.close();

        return user;
    }

    @Override
    public boolean loginAuth(String username, String password) {
        if(username == null || username.length() > 32 || username.length() < 4){
            return false;
        } else if(password == null || password.length() > 32 || password.length() < 4){
            return false;
        }
        Query q = session.createQuery("from User where username = :i");
        q.setParameter("i",username);
        List<User> list = q.list();
        if(!list.isEmpty()) {
            User u = list.get(0);
            boolean validPassword = UserDao.checkPassword(password,u.getPassword());
            if(validPassword){
                return true;
            }
        }
        return false;
    }
}
