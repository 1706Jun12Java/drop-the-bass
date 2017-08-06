package main.java.com.revature.controllers;


import main.java.com.revature.dao.hibernate.access.UserDA;
import main.java.com.revature.dao.hibernate.access.VenueDA;
import main.java.com.revature.domain.User;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.domain.VenueOwner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class VenueController
{
    private static final Logger LOGGER = Logger.getLogger(VenueController.class);

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model m, HttpServletRequest request)
    {
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            User a = UserDA.getUserById((int)sess.getAttribute("userID"));
            m.addAttribute("currentUser",a);
            return "search";
        }else{
            return "redirect:/";
        }
    }


    @RequestMapping(value = "/searchvenue", method = RequestMethod.GET)
    public String searchExecute(@RequestParam(value = "query") String query, @RequestParam(value = "searchby") String searchBy,Model m,HttpServletRequest request)
    {
        Set<Venue> results;
        Set<VenueOwner> owners;
        HttpSession session = request.getSession(false);
        LOGGER.debug("Started searching");
        switch(searchBy){
            case "3":
                results = new HashSet<>(VenueDA.SearchByName(query));
                owners = new HashSet<>();
                if(!results.isEmpty()){
                    for(Venue v: results){
                        owners.add(v.getVenueOwner());
                    }
                }
                m.addAttribute("Venues",results);
                m.addAttribute("Owners",owners);
                m.addAttribute("currentUser", UserDA.getUserById((int)session.getAttribute("userID")));
                return "VenueResults";
            case "4":
                results = new HashSet<>(VenueDA.SearchByName(query));
                owners = new HashSet<>();
                if(!results.isEmpty()){
                    for(Venue v: results){
                        owners.add(v.getVenueOwner());
                    }
                }
                m.addAttribute("Venues",results);
                m.addAttribute("Owners",owners);
                m.addAttribute("currentUser", UserDA.getUserById((int)session.getAttribute("userID")));
                return "VenueResults";
        }
        return "redirect:/search";
    }
}
