package com.revature.dao.hibernate.access;


import com.revature.dao.hibernate.EventDataAccess;
import com.revature.domain.Event;

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
