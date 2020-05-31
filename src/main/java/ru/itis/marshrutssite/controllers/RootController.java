package ru.itis.marshrutssite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String rootControl(Authentication authentication){
        if (authentication != null) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }

    }
}
