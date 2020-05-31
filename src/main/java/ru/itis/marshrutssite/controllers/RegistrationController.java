package ru.itis.marshrutssite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.marshrutssite.dto.SignUpDto;
import ru.itis.marshrutssite.dto.UserDto;
import ru.itis.marshrutssite.services.SignUpService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/registration")
    public String signUp(Authentication authentication,Model model) {
        if(authentication != null) {
            return "redirect:/main";
        }else{
            model.addAttribute("SignUpDto", new SignUpDto());
            return "registration";
        }
    }

    @PostMapping("/registration")
    public String signUp(@Valid @ModelAttribute("SignUpDto")SignUpDto form, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()){
            signUpService.signUp(form);
            return "preConfirm_page";
        }else {
            logger.info("Ошибка в форме!");
            return "registration";
        }
    }

}
