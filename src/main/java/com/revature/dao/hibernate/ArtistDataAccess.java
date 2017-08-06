package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.ArtistDao;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDataAccess implements ArtistDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public Artist getArtistById(int id)
    {
        Artist artist = (Artist) session.get(Artist.class, id);
        session.close();
        return artist;
    }

    @Override
    public void updateArtist(Artist artist)
    {
        Transaction tx = session.beginTransaction();
        session.update(artist);
        tx.commit();

        session.close();
    }
}
