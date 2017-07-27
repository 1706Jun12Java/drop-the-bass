package com.revature.dao;


import com.revature.domain.Event;

public interface EventDao
{
    int createEvent(Event event);

    Event getEventById(int id);
}
