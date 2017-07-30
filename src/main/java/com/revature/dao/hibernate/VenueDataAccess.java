package main.java.com.revature.dao.hibernate;

import main.java.com.revature.dao.VenueDao;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;

public class VenueDataAccess implements VenueDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public int createVenue(Venue venue)
    {
        int id = (int) session.save(venue);
        session.close();

        return id;
    }

    @Override
    public Venue getVenueById(int id)
    {
        Venue venue = (Venue) session.get(Venue.class, id);
        session.close();

        return venue;
    }
}
