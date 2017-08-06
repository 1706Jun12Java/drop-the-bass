package main.java.com.revature.controllers;

import main.java.com.revature.dao.UserDao;
import main.java.com.revature.dao.hibernate.VenueOwnerDataAccess;
import main.java.com.revature.dao.hibernate.access.EventDA;
import main.java.com.revature.dao.hibernate.access.UserDA;
import main.java.com.revature.dao.hibernate.access.VenueDA;
import main.java.com.revature.domain.Event;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.domain.VenueOwner;
import main.java.com.revature.util.S3Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class VOController {
    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @RequestMapping(value ="/dashboard", method= RequestMethod.GET)
    public String voDashboard(HttpServletRequest request ,Model m){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            String accountType = (String) sess.getAttribute("accountType");
            if (accountType.equalsIgnoreCase("venueowner")) {
                int id = (int) sess.getAttribute("userID");
                VenueOwner u = (VenueOwner) UserDA.getUserById(id);
                m.addAttribute("newVenue", new Venue());
                m.addAttribute("Venues", new HashSet<>(u.getVenues()));
                m.addAttribute("owner",u);
            }
        } else {
            return"redirect:/";
        }
        return "VODashboard";
    }

    @RequestMapping(value ="/events", method= RequestMethod.GET)
    public String voEvents(HttpServletRequest request ,Model m){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            String accountType = (String) sess.getAttribute("accountType");
            if (accountType.equalsIgnoreCase("venueowner")) {
                int id = (int) sess.getAttribute("userID");
                VenueOwner u = (VenueOwner) UserDA.getUserById(id);
                List<Event> me = u.getEvents();
                for(Event e: me){
                    System.out.println(e.getName());
                }
                m.addAttribute("currentevents", new HashSet<>(u.getEvents()));
                m.addAttribute("venues", new HashSet<>(u.getVenues()));
                m.addAttribute("newevent",new Event());
            }
        } else {
            return"redirect:/";
        }
        return "EventPage";
    }
    @RequestMapping(value ="/updateEvents", method= RequestMethod.POST)
    public String voEvents(HttpServletRequest request ,Model m,@RequestParam(value = "v") String[] values,Event event,@RequestParam(value = "starttime")String start,@RequestParam(value = "endtime")String end){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            String accountType = (String) sess.getAttribute("accountType");
            if (accountType.equalsIgnoreCase("venueowner")) {
                int id = (int) sess.getAttribute("userID");
                VenueOwner u = (VenueOwner) UserDA.getUserById(id);
                Event newEvent = new Event();
                if (start.lastIndexOf(":")==13) {
                    start += ":00";
                }
                if (end.lastIndexOf(":")==13) {
                    end += ":00";
                }
                newEvent.setStart(Timestamp.valueOf(start.replace("T"," ")));
                newEvent.setEnd(Timestamp.valueOf(end.replace("T"," ")));
                newEvent.setDescription(event.getDescription());
                newEvent.setName(event.getName());
                newEvent.setVenueOwner(u);
                List<Venue> venues = u.getVenues();
                ArrayList<Venue> eventVenues = new ArrayList<>();
                for(Venue venue:venues){
                    for(String value: values) {
                        int vId = Integer.parseInt(value);
                        if (venue.getId() == vId){
                           eventVenues.add(venue);
                        }
                    }
                }
                newEvent.setVenues(eventVenues);
                EventDA.save(newEvent);
            }
        } else {
            return"redirect:/";
        }
        return "redirect:/events";
    }

    @RequestMapping(value = "/updateVO" ,method=RequestMethod.POST)
    public String updateArtist(HttpServletRequest request, Model model, VenueOwner vo){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            VenueOwner dbVO = (VenueOwner) UserDA.getUserById(id);
            if(UserDao.checkPassword(vo.getPassword(),dbVO.getPassword())) {
                if(!vo.getFirstName().equalsIgnoreCase(dbVO.getFirstName())){
                    dbVO.setFirstName(vo.getFirstName());
                }
                if(!vo.getLastName().equalsIgnoreCase(dbVO.getLastName())){
                    dbVO.setLastName(vo.getLastName());
                }
                if(!vo.getPhoneNumber().equalsIgnoreCase(dbVO.getPhoneNumber())){
                    dbVO.setPhoneNumber(vo.getPhoneNumber());
                }
                new VenueOwnerDataAccess().updateVO(dbVO);
            }
        }else {
            return"redirect:/";
        }
        return "redirect:/vosettings";
    }

    @RequestMapping(value = "/vosettings", method = RequestMethod.GET)
    public String editArtistInfo(HttpServletRequest request, Model model)
    {
        LOGGER.debug("In edit venue owner method");

        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            VenueOwner a = (VenueOwner) UserDA.getUserById(id);
            model.addAttribute("VO", a);
        }else {
            return"redirect:/";
        }

        LOGGER.debug("Session was null. Nothing was changes.");
        return "VOSettings";
    }
    @RequestMapping(value ="/createVenue", method= RequestMethod.POST)
    public String createVenue(HttpServletRequest request ,Model m,Venue newVenue,@RequestParam(value = "pic") MultipartFile file){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            String accountType = (String)sess.getAttribute("accountType");
            if(accountType.equalsIgnoreCase("venueowner")) {
                int id = (int) sess.getAttribute("userID");
                VenueOwner u = (VenueOwner) UserDA.getUserById(id);
                Venue v = new Venue();
                v.setVenueOwner(u);
                v.setAddress(newVenue.getAddress());
                v.setName(newVenue.getName());
                v.setWebsite(newVenue.getWebsite());
                if(file != null) {
                    String url = S3Util.uploadPicture(file, sess.getAttribute("userID").toString());
                    v.setPictureURL(url);
                }
                VenueDA.save(v);
            }
        }else {
            return"redirect:/";
        }
        return "redirect:/dashboard";
    }
}
