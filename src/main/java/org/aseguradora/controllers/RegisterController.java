package org.aseguradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }
    
    @PostMapping("/register")
    public ModelAndView save(){
        return new ModelAndView("register-save");
    }
}
