package main.java.com.revature.dao.hibernate;

import main.java.com.revature.dao.VenueDao;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VenueDataAccess implements VenueDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public int createVenue(Venue venue)
    {
        Transaction tx = session.beginTransaction();
        session.save(venue);
        tx.commit();
        session.close();

        return 1;
    }

    @Override
    public Venue getVenueById(int id)
    {
        Venue venue = (Venue) session.get(Venue.class, id);
        session.close();

        return venue;
    }

    @Override
    public List<Venue> searchVenueByName(String s)
    {
        Query q = session.createQuery("from Venue");
        ArrayList<Venue> result = new ArrayList();
        List<Venue> dbStuff = q.list();
        if(!s.equalsIgnoreCase("all")){
            for(Venue u : dbStuff){
                if(s.equalsIgnoreCase(u.getName())){
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
    public List<Venue> searchVenueOwner(String s)
    {
        Query q = session.createQuery("from Venue");
        ArrayList<Venue> result = new ArrayList();
        List<Venue> dbStuff = q.list();
        s = s.replace(" ", "");
        if(!s.equalsIgnoreCase("all")){
            for(Venue u : dbStuff){
                String name = u.getVenueOwner().getFirstName();
                name += u.getVenueOwner().getLastName();
                if(s.equalsIgnoreCase(name)){
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
}
