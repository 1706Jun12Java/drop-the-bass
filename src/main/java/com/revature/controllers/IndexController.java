package main.java.com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController
{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginPage(Model m){
        return"LoginRegister";
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String registerPage(Model m){
        return"Register";
    }
}
