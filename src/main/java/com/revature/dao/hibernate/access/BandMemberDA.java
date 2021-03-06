package main.java.com.revature.dao.hibernate.access;


import main.java.com.revature.dao.hibernate.BandMemberDataAccess;
import main.java.com.revature.domain.BandMember;
import org.springframework.stereotype.Service;


/**
 * Wrapper class of Data Access Object
 */

@Service
public class BandMemberDA
{
    /**
     * Save the band member into database
     *
     * @param bandMember A band member
     * @return The identifier of that object
     */
    public static int save(BandMember bandMember)
    {
        return new BandMemberDataAccess().createBandMember(bandMember);
    }

    /**
     * Get the band member by it's id
     *
     * @param id The id of a band member
     * @return A band member
     */
    public static BandMember getBandMemberById(int id)
    {
        return new BandMemberDataAccess().getBandMemberById(id);
    }
}
