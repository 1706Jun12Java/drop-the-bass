package main.java.com.revature.dao.hibernate;

import main.java.com.revature.dao.VenueOwnerDao;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.domain.VenueOwner;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VenueOwnerDataAccess implements VenueOwnerDao {
    private Session session = HibernateUtil.getSession();
    @Override
    public VenueOwner getVOById(int id) {
        VenueOwner venueOwner = (VenueOwner) session.get(VenueOwner.class, id);
        session.close();
        return venueOwner;
    }

    @Override
    public void updateVO(VenueOwner venueOwner) {
        Transaction tx = session.beginTransaction();
        session.update(venueOwner);
        tx.commit();

        session.close();
    }
}
