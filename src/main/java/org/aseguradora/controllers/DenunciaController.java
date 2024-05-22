package org.aseguradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DenunciaController {

    @GetMapping("/denuncia")
    public ModelAndView verPolizas(){
        ModelMap model = new ModelMap();
        return new ModelAndView("denuncia", model);
    }
}
