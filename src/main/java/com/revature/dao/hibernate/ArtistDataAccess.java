package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.ArtistDao;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

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
    public List searchArtist(String s)
    {
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Artist.class ).get();
        org.apache.lucene.search.Query query = qb.keyword()
                .onField("genre")
                .matching(s)
                .createQuery();
        // Wrap Lucene query with Hibernate query
        Query hibQuery = fullTextSession.createFullTextQuery(query, Artist.class);

        List result = hibQuery.list();
        tx.commit();
        session.close();

        return result;
    }
}
