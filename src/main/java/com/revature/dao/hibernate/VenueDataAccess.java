package main.java.com.revature.dao.hibernate;

import main.java.com.revature.dao.VenueDao;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
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
}
