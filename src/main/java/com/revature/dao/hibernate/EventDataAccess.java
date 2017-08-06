package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.EventDao;
import main.java.com.revature.domain.Event;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventDataAccess implements EventDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public int createEvent(Event event)
    {
        Transaction tx = session.beginTransaction();
        session.save(event);
        tx.commit();
        session.close();
        return event.getId();
    }

    @Override
    public Event getEventById(int id)
    {
        Event event = (Event) session.get(Event.class, id);
        session.close();

        return event;
    }
}
