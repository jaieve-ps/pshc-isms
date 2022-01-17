package com.pshc.isms.controller;


import com.pshc.isms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger("MainController");

    @GetMapping("/")
    public String index(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            logger.info("user: " + user);
        }

        request.getSession().setMaxInactiveInterval(60 * 60 * 24);
        return "/index";
    }

    @GetMapping("/login")
    public String mainPage(Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        for(GrantedAuthority auth : user.getAuthorities()) {
            logger.info("user: " + user);
            if (auth.getAuthority().equals("ROLE_MANAGER")) {
                return  "/auth/login";
            } else if (auth.getAuthority().equals("ROLE_USERS")) {
                return "/auth/login";
            }
        }
        return "redirect:/";
    }
}
