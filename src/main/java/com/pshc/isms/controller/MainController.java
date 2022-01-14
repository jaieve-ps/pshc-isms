package com.pshc.isms.controller;


import com.pshc.isms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger("MainController");

    @GetMapping("/")
    public String mainPage(Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        String redirect = "redirect/";
        for(GrantedAuthority auth : user.getAuthorities()) {
            logger.info("user: " + user);
            if (auth.getAuthority().equals("MANAGER")) {
                redirect += "mypage";
            } else if (auth.getAuthority().equals("USERS")) {
                redirect += "main";
            }
        }
        return redirect;
    }
}
