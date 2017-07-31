package main.java.com.revature.dao;


import main.java.com.revature.domain.Artist;

public interface ArtistDao
{
    Artist getArtistById(int id);
    void updateArtist(Artist artist);
}
