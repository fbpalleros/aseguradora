package org.aseguradora.controllers;

import org.aseguradora.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public ModelAndView showContact() {
        return new ModelAndView("contact/contactForm");

    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, ModelMap model) {
        model.put("message", "¡Formulario enviado correctamente!");
        return "contact/resultado";
    }
    
}

