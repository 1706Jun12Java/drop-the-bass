package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.EventDao;
import main.java.com.revature.domain.Event;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;

public class EventDataAccess implements EventDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public int createEvent(Event event)
    {
        int id = (int) session.save(event);
        session.close();

        return id;
    }

    @Override
    public Event getEventById(int id)
    {
        Event event = (Event) session.get(Event.class, id);
        session.close();

        return event;
    }
}
