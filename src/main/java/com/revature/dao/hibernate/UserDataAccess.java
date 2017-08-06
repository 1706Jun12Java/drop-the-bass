package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.UserDao;
import main.java.com.revature.domain.User;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
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
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        return user.getId();
    }

    @Override
    public User getUserById(int id)
    {
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public boolean loginAuth(String username, String password, HttpServletRequest request)
    {
        Query q = session.createQuery("from User where username = :i");
        q.setParameter("i",username);
        List<User> list = q.list();
        if(!list.isEmpty()) {
            User u = list.get(0);
            boolean validPassword = UserDao.checkPassword(password,u.getPassword());
            if(validPassword) {
                HttpSession session = request.getSession();
                session.setAttribute("userID",u.getId());
                session.setAttribute("accountType",u.getAccountType());
                return true;
            }
        }
        return false;
    }
    }

