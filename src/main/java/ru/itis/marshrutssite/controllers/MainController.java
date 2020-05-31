package ru.itis.marshrutssite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.security.details.UserDetailsImpl;

@Controller
public class MainController {

    @GetMapping("/main")
    public String MainPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user",user);
        return "main_page";
    }
}
