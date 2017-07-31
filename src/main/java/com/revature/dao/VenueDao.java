package main.java.com.revature.dao;


import main.java.com.revature.domain.Venue;

public interface VenueDao
{
    int createVenue(Venue venue);

    Venue getVenueById(int id);
}
