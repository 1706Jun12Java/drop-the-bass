package main.java.com.revature.controllers;


import main.java.com.revature.dao.UserDao;
import main.java.com.revature.dao.hibernate.ArtistDataAccess;
import main.java.com.revature.dao.hibernate.UserDataAccess;
import main.java.com.revature.dao.hibernate.access.ArtistDA;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.domain.BandMember;
import main.java.com.revature.util.S3Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        }else {
            return"redirect:/";
        }

        LOGGER.debug("Session was null. Nothing was changes.");
        return "ArtistSettings";
    }

    @RequestMapping(value = "/a-profile", method = RequestMethod.GET)
    public String artistProfileDisplay(HttpServletRequest request, Model model)
    {
        LOGGER.debug("In artist profile method");
        //TODO Figure out how to find id through session
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            Artist a = ArtistDA.getArtistById(id);
            model.addAttribute("artist", a);
            List<BandMember> temp = new ArrayList<BandMember>();

        }else {
            return"redirect:/";
        }
        return "ArtistProfile";
    }

    @RequestMapping(value = "/updateArtist" ,method=RequestMethod.POST)
    public String updateArtist(@RequestParam(value = "pic") MultipartFile file,HttpServletRequest request, Model model, Artist artist){
        HttpSession sess = request.getSession(false);
        if(sess!=null) {
            int id = (int) sess.getAttribute("userID");
            Artist dbArtist = ArtistDA.getArtistById(id);
            if(UserDao.checkPassword(artist.getPassword(),dbArtist.getPassword())) {
                if (!artist.getGenre().equalsIgnoreCase(dbArtist.getGenre())) {
                    dbArtist.setGenre(artist.getGenre());
                }
                if (!artist.getSoundCloudURL().equalsIgnoreCase(dbArtist.getSoundCloudURL())) {
                    dbArtist.setSoundCloudURL(artist.getSoundCloudURL());
                }
                if (!artist.getWebsite().equalsIgnoreCase(dbArtist.getWebsite())) {
                    dbArtist.setWebsite(artist.getWebsite());
                }
                if (!artist.getUsername().equalsIgnoreCase(dbArtist.getUsername())) {
                    dbArtist.setUsername(artist.getWebsite());
                }
                if(!artist.getDescription().equalsIgnoreCase(dbArtist.getDescription())){
                    dbArtist.setDescription(artist.getDescription());
                }
                if(!artist.getBandName().equalsIgnoreCase(dbArtist.getBandName())){
                    dbArtist.setBandName(artist.getBandName());
                }
                if(file != null) {
                    String url = S3Util.uploadPicture(file, sess.getAttribute("userID").toString());
                    artist.setPicture(url);
                }
                ArtistDA.update(dbArtist);
            }
        }else {
            return"redirect:/";
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
}
