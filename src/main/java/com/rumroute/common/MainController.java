package com.rumroute.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model){
        return "site/home";
    }

    @RequestMapping("/terms-of-use")
    public String terms(){

        return "site/terms";
    }

    @RequestMapping("/privacy-policy")
    public String privacy(){

        return "site/privacy";
    }
}
