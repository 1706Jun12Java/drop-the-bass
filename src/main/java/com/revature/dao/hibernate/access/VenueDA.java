package com.revature.dao.hibernate.access;


import com.revature.dao.hibernate.VenueDataAccess;
import com.revature.domain.Venue;

/**
 * Wrapper class for Venue model Data Access
 */
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
}
