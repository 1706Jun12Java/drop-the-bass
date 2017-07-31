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
    private static int save(User user)
    {
        return new UserDataAccess().createUser(user);
    }

    public static boolean registerUser(String username, String password, String accountType){
        if(username == null || username.length() > 32 || username.length() < 4){
            return false;
        } else if(password == null || password.length() > 32 || password.length() < 4){
            return false;
        }
        switch(accountType){
            case "artist": createArtist();
            break;
            case "venueowner": createVenueOwner();
            break;
        }
        return false;
    }

    private static void createArtist(){ }

    private static void createVenueOwner(){ }
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

    public static boolean loginAuth(String username, String password){return new UserDataAccess().loginAuth(username,password); }
}
