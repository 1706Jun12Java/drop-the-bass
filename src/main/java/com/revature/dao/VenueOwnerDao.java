package main.java.com.revature.dao;


import main.java.com.revature.domain.VenueOwner;

public interface VenueOwnerDao
{
    VenueOwner getVOById(int id);
    void updateVO(VenueOwner artist);
}