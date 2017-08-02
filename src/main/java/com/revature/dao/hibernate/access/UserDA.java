package main.java.com.revature.dao.hibernate.access;


import main.java.com.revature.dao.hibernate.UserDataAccess;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.domain.User;
import main.java.com.revature.domain.VenueOwner;

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

    public static int registerUser(String username, String password, String accountType){
        switch(accountType){
            case "artist":return createArtist(username,password,accountType);
            case "venueowner": return createVenueOwner(username,password,accountType);
        }
        return -1;
    }

    private static int createArtist(String username,String password,String accountType){
        String hashedWord = UserDataAccess.hashPassword(password);
        Artist a = new Artist();
        a.setUsername(username);
        a.setPassword(hashedWord);
        a.setAccountType(accountType);
        int id = save(a);
        return id;
    }

    private static int createVenueOwner(String username, String password,String accountType){
        String hashedWord = UserDataAccess.hashPassword(password);
        VenueOwner vo = new VenueOwner();
        vo.setUsername(username);
        vo.setPassword(hashedWord);
        vo.setAccountType(accountType);
        int id = save(vo);
        return id;
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

    public static boolean loginAuth(String username, String password){return new UserDataAccess().loginAuth(username,password); }
}
