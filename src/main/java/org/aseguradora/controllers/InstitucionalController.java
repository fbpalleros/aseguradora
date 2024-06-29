package org.aseguradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstitucionalController {

    @GetMapping("/institucional")
    public ModelAndView showContact(Model model) {
        return new ModelAndView("institucional");

    }

    
}

