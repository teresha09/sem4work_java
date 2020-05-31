package ru.itis.marshrutssite.controllers;

import jdk.nashorn.internal.ir.Node;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.security.details.UserDetailsImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String index(HttpServletResponse response, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        response.addCookie(new Cookie("email", user.getEmail()));
        return "/Chat";
    }
}
