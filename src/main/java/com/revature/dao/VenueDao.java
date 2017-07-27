package com.revature.dao;


import com.revature.domain.Venue;

public interface VenueDao
{
    int createVenue(Venue venue);

    Venue getVenueById(int id);
}
