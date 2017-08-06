package main.java.com.revature.controllers;

import main.java.com.revature.dao.hibernate.access.ArtistDA;
import main.java.com.revature.dao.hibernate.access.UserDA;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.domain.User;
import main.java.com.revature.util.S3Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController
{
    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model m)
    {
        m.addAttribute("newUser",new User());
        return "Login";
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logOut(HttpServletRequest request,Model m){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }


    @RequestMapping(value="/loginError",method = RequestMethod.GET)
    public String loginPage(Model m){
        m.addAttribute("newUser",new User());
        return "Login";
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String registerPage(Model m){
        m.addAttribute("newUser",new User());
        return"Register";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String userLoginValidation(HttpServletRequest request, HttpServletResponse response, User user, BindingResult bindingResult){
        HttpSession sess = request.getSession(false);
        if(sess==null){
            if(UserDA.loginAuth(user.getUsername(),user.getPassword(),request)){
                HttpSession session = request.getSession();
                String accountType = (String) session.getAttribute("accountType");
                switch(accountType){
                    case "artist": return "redirect:/asettings";
                    case "venueowner": return "redirect:/dashboard";
                }
            }
        } else {
            sess.invalidate();
        }
        return "redirect:/loginError";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String userRegistration(HttpServletRequest request, HttpServletResponse response, User user, BindingResult bindingResult){
        UserDA.registerUser(user.getUsername(),user.getPassword(),user.getAccountType());
        return "redirect:/";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage(Model model)
    {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "picture") MultipartFile file, HttpServletRequest req)
    {
        LOGGER.debug("Trying to save");
        HttpSession session  = req.getSession(false);

        if (session != null )
        {
            if (session.getAttribute("user_id") != null)
            {
                Artist artist = ArtistDA.getArtistById((Integer) session.getAttribute("user_id") );
                String url = S3Util.uploadPicture(file, session.getAttribute("user_id").toString() );

                artist.setPicture(url);
                ArtistDA.update(artist);
                LOGGER.debug("Save successful");

                return "redirect:/";
            }

            LOGGER.debug("Session doesn't have a user_id");
            return "redirect:/upload";
        }
        else
        {
            LOGGER.debug("There was no session");
            return "redirect:/upload";
        }
    }
}
