package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.ArtistDao;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Artist> searchArtistName(String s)
    {
        Query q = session.createQuery("from Artist");
        ArrayList<Artist> result = new ArrayList();
        List<Artist> dbStuff = q.list();
        if(!s.equalsIgnoreCase("all")){
            for(Artist u : dbStuff){
                if(s.equalsIgnoreCase(u.getBandName())){
                    result.add(u);
                }
            }
        }else {
            session.close();
            return dbStuff;
        }

        session.close();

        return result;
    }
    @Override
    public List<Artist> searchArtistGenre(String s)
    {
        Query q = session.createQuery("from Artist ");
        ArrayList<Artist> result = new ArrayList();
        List<Artist> dbStuff = q.list();
        if(!s.equalsIgnoreCase("all")){
            for(Artist u : dbStuff){
                if(s.equalsIgnoreCase(u.getGenre())){
                    result.add(u);
                }
            }
        }else {
            session.close();
            return dbStuff;
        }

        session.close();

        return result;
    }

    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}
