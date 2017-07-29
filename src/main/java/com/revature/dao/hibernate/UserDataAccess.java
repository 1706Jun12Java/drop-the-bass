package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.UserDao;
import main.java.com.revature.domain.User;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;

public class UserDataAccess implements UserDao
{
    private Session session = HibernateUtil.getSession();

    public static String hashPassword(String password)
    {
        return null;
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
    public boolean loginAuth(String username, String password)
    {
        return false;
    }
}
