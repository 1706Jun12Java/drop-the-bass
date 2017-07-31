package main.java.com.revature.controllers;


import main.java.com.revature.dao.hibernate.ArtistDataAccess;
import main.java.com.revature.dao.hibernate.access.ArtistDA;
import main.java.com.revature.domain.Artist;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArtistController
{
    private static final Logger LOGGER = Logger.getLogger(Artist.class);

    @RequestMapping(value = "/edit-artist", method = RequestMethod.GET)
    public String editArtistInfo(Model model)
    {
        LOGGER.debug("In edit artist method");
        //TODO Figure out how to find id through session
        Artist a = new ArtistDataAccess().getArtistById(1);
        model.addAttribute("artist", a);
        return "ArtistInformation";
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
