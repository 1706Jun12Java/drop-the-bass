package main.java.com.revature.controllers;

import main.java.com.revature.dao.hibernate.access.UserDA;
import main.java.com.revature.domain.Artist;
import main.java.com.revature.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value="/dashboard",method = RequestMethod.GET)
    public String dashboardPage(Model m){
        return "VODashboard";
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
                    case "venueowner": return "VOSettings";
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
        Artist artist = new Artist();
        artist.setPicture("Set picture");
        model.addAttribute("fileName", artist);
        return "upload";
    }

    @RequestMapping(value = "/upload-save", method = RequestMethod.POST)
    public String upload(HttpServletRequest req, HttpServletResponse res, @ModelAttribute(value = "fileName") Artist a)
    {

        System.out.println("Trying to save");
        LOGGER.info("file url is: " + a.getPicture() );
        LOGGER.info("file url is: " + a.getGenre() );
        LOGGER.info("file url is: " + a.getSoundCloudURL() );
        LOGGER.info("file url is: " + a.getWebsite() );
        LOGGER.info("file url is: " + a.getDescription() );
        return "redirect:/upload";

//        HttpSession session = req.getSession(false);
//        if (session != null)
//        {
//
//        }
//        else
//            return "redirect:/"; // Login is in index page
    }
}
