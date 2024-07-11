package org.aseguradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SiteController {

    @GetMapping("/proteccion")
    public ModelAndView proteccion() {
        return new ModelAndView("site/proteccion");
    }

    @GetMapping("/politicas")
    public ModelAndView politicas() {
        return new ModelAndView("site/politicas");
    }
 
    @GetMapping("/institucional")
    public ModelAndView institucional() {
        return new ModelAndView("site/institucional");
    }
    
    @GetMapping("/terminos")
    public ModelAndView terminos() {
    	return new ModelAndView("site/terminos");
    }
}
