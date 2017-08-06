package main.java.com.revature.dao;


import main.java.com.revature.domain.Event;

public interface EventDao
{
    int createEvent(Event event);

    Event getEventById(int id);
}
