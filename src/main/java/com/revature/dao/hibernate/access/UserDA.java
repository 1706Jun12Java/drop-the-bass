package main.java.com.revature.dao.hibernate.access;


import main.java.com.revature.dao.hibernate.UserDataAccess;
import main.java.com.revature.domain.User;

/**
 * Wrapper class of User Data Access
 */
public class UserDA
{
    /**
     * Save a user into the database
     *
     * @param user A new user with its related information
     * @return The identifier of the new user
     */
    public static int save(User user)
    {
        return new UserDataAccess().createUser(user);
    }

    /**
     * Find a user by its id
     *
     * @param id The id of the user
     * @return A user
     */
    public static User getUserById(int id)
    {
        return new UserDataAccess().getUserById(id);
    }
}
