package main.java.com.revature.controllers;


import main.java.com.revature.dao.hibernate.UserDataAccess;
import main.java.com.revature.dao.hibernate.access.ArtistDA;
import main.java.com.revature.domain.Artist;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArtistController
{
    private static final Logger LOGGER = Logger.getLogger(Artist.class);

    @RequestMapping(value = "/asettings", method = RequestMethod.GET)
    public String editArtistInfo(HttpServletRequest request, Model model)
    {
        LOGGER.debug("In edit artist method");

        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            Artist a = ArtistDA.getArtistById(id);
            model.addAttribute("artist", a);
        }

        LOGGER.debug("Session was null. Nothing was changes.");
        return "ArtistSettings";
    }
    @RequestMapping(value = "/updateArtist" ,method=RequestMethod.POST)
    public String updateArtist(HttpServletRequest request, Model model,Artist artist){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            artist.setId(id);
            artist.setPassword(UserDataAccess.hashPassword(artist.getPassword()));
            ArtistDA.update(artist);
            model.addAttribute("artist", artist);
        }
        return "ArtistSettings";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateArtist(@PathVariable("id") int id, @ModelAttribute(value = "artist") Artist artistUpdatedInfo)
    {
        Artist artist = ArtistDA.getArtistById(id);

        // The fields that are to be updated inserted into the old object
        artist.setSoundCloudURL(artistUpdatedInfo.getSoundCloudURL());
        artist.setGenre(artistUpdatedInfo.getGenre());
        artist.setWebsite(artistUpdatedInfo.getWebsite());

        ArtistDA.update(artist);
        LOGGER.debug("Artist was updated");

        return "redirect:/edit-artist";
    }

    @RequestMapping(value = "search/artist/{query}", method = RequestMethod.GET)
    public void search(@PathVariable("query") String s)
    {
        LOGGER.debug("Started searching");
        List list = ArtistDA.search(s);

        for (Object aList : list)
        {
            System.out.println("object: " + aList);
        }
    }
}
