package main.java.com.revature.dao;


import main.java.com.revature.domain.Venue;

import java.util.List;

public interface VenueDao
{
    int createVenue(Venue venue);

    Venue getVenueById(int id);

    List searchVenue(String s);
}
