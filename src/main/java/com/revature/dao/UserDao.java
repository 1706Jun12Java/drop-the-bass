package main.java.com.revature.dao;


import main.java.com.revature.domain.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public interface UserDao
{
    int createUser(User user);

    User getUserById(int id);


    /**
     * Hash the password given. Includes a salt
     *
     * @param password The password to be hash
     * @return A hashed password
     */
    static String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    /**
     * Compare the password and hash to check if they match
     *
     * @param password The string of the password
     * @param hashed   The hash password you're checking against
     * @return True if match. Otherwise, false.
     */
    static boolean checkPassword(String password, String hashed)
    {
        return BCrypt.checkpw(password, hashed);
    }

    boolean loginAuth(String username, String password, HttpServletRequest request);
}
