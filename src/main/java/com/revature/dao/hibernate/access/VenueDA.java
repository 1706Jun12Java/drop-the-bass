package main.java.com.revature.dao.hibernate.access;


import main.java.com.revature.dao.hibernate.VenueDataAccess;
import main.java.com.revature.domain.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wrapper class for Venue model Data Access
 */
@Service
public class VenueDA
{
    /**
     * Save a new Venue model into the database
     *
     * @param venue The venue object to save
     * @return The identifier of new Venue
     */
    public static int save(Venue venue)
    {
        return new VenueDataAccess().createVenue(venue);
    }

    /**
     * Find a venue by its id
     *
     * @param id The id of the venue
     * @return A venue
     */
    public static Venue getVenueById(int id)
    {
        return new VenueDataAccess().getVenueById(id);
    }

    public static List SearchByName(String query)
    {
        return new VenueDataAccess().searchVenueByName(query);
    }
    public static List SearchByOwner(String query)
    {
        return new VenueDataAccess().searchVenueOwner(query);
    }
}
