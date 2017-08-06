package main.java.com.revature.dao.hibernate.access;

import main.java.com.revature.dao.hibernate.ArtistDataAccess;
import main.java.com.revature.domain.Artist;
import org.springframework.stereotype.Service;

@Service
public class ArtistDA
{
    public static Artist getArtistById(int id)
    {
        return new ArtistDataAccess().getArtistById(id);
    }

    public static void update(Artist artist)
    {
        new ArtistDataAccess().updateArtist(artist);
    }
}
