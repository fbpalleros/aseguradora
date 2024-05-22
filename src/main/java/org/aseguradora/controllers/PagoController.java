package org.aseguradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagoController {

    @GetMapping("/pagar/{id}")
    public ModelAndView pagar(@PathVariable("id") Long id){
        ModelMap model = new ModelMap();
        return new ModelAndView("pago", model);
    }
}

