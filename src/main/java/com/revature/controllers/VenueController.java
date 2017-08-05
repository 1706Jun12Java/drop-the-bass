package main.java.com.revature.controllers;


import main.java.com.revature.dao.hibernate.access.VenueDA;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class VenueController
{
    private static final Logger LOGGER = Logger.getLogger(VenueController.class);

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search()
    {
        return "search";
    }

    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public void searchExecute(@PathVariable("query") String query)
    {

        LOGGER.debug("Started searching");
        List list = VenueDA.Search(query);

        LOGGER.debug("query is: " + query);
        for (Object aList : list)
        {
            System.out.println("object: " + aList);
        }
    }
}
