package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarCasaDTO;
import org.aseguradora.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CotizacionInmuebleController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private CityService cityService;


    @GetMapping("/paso_uno_inmu")
    public ModelAndView vistaPasoUno() {
        ModelMap model = new ModelMap();
        AlmacenarCasaDTO almacenar = new AlmacenarCasaDTO();
        List<String> provincias = cityService.listarTodasLasProvincias();
        model.put("provincias", provincias);
        model.put("almacenar", almacenar);
        return new ModelAndView("paso_uno_inmu", model);
    }

    @GetMapping("/guardar_paso_uno_inmu")
    public ModelAndView guardarPasoUno(@ModelAttribute("almacenar") AlmacenarCasaDTO almacenar, ModelMap model){
        List<String> localidad = cityService.buscarDependiendoLaProvincia(almacenar.getProvincia());
        model.put("localidad", localidad);

       return new ModelAndView("paso_dos", model);
    }







}
