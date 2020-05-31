package ru.itis.marshrutssite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.marshrutssite.dto.SignUpDto;
@Controller
public class LoginController {


    @GetMapping("/signIn")
    public String getSignIn(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/main";
        }else{
            return "login";
        }
    }

}
