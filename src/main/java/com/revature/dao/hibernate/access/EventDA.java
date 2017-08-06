package main.java.com.revature.dao.hibernate.access;


import main.java.com.revature.dao.hibernate.EventDataAccess;
import main.java.com.revature.domain.Event;
import org.springframework.stereotype.Service;

@Service
public class EventDA
{
    /**
     * Save a new event to the database
     *
     * @param event The event you want to save
     * @return The identifier of the new event
     */
    public static int save(Event event)
    {
        return new EventDataAccess().createEvent(event);
    }

    /**
     * Find the event by its id
     *
     * @param id The id of the event
     * @return A event
     */
    public static Event getEventByid(int id)
    {
        return new EventDataAccess().getEventById(id);
    }
}
