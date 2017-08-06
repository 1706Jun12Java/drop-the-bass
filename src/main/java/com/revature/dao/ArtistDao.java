package main.java.com.revature.dao;


import main.java.com.revature.domain.Artist;

import java.util.List;

public interface ArtistDao
{
    Artist getArtistById(int id);
    void updateArtist(Artist artist);

    List searchArtist(String s);
}
