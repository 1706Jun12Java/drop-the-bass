package main.java.com.revature.dao.hibernate;

import main.java.com.revature.dao.VenueDao;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

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

    @Override
    public List searchVenue(String s)
    {
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Venue.class ).get();
        org.apache.lucene.search.Query query = qb.keyword()
                                                .onField("genre")
                                                .matching(s)
                                                .createQuery();
        // Wrap Lucene query with Hibernate query
        Query hibQuery = fullTextSession.createFullTextQuery(query, Venue.class);

        List result = hibQuery.list();
        tx.commit();
        session.close();

        return result;
    }
}
