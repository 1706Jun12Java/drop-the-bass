package main.java.com.revature.controllers;

import main.java.com.revature.dao.VenueDao;
import main.java.com.revature.dao.hibernate.VenueDataAccess;
import main.java.com.revature.dao.hibernate.access.UserDA;
import main.java.com.revature.dao.hibernate.access.VenueDA;
import main.java.com.revature.domain.User;
import main.java.com.revature.domain.Venue;
import main.java.com.revature.domain.VenueOwner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VOController {

    @RequestMapping(value ="/dashboard", method= RequestMethod.GET)
    public String voDashboard(HttpServletRequest request ,Model m){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            String accountType = (String) sess.getAttribute("accountType");
            if (accountType.equalsIgnoreCase("venueowner")) {
                int id = (int) sess.getAttribute("userID");
                VenueOwner u = (VenueOwner) UserDA.getUserById(id);
                m.addAttribute("newVenue", new Venue());
                m.addAttribute("Venues", u.getVenues());
            }
        }
        return "VODashboard";
    }

    @RequestMapping(value ="/createVenue", method= RequestMethod.POST)
    public String createVenue(HttpServletRequest request ,Model m,Venue newVenue){
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
                VenueDA.save(v);
            }
        }
        return "redirect:/dashboard";
    }
}
