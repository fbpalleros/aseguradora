package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarCasaDTO;
import org.aseguradora.entity.dto.AlmacenarDTO;
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
        List<String> localidades = cityService.buscarDependiendoLaProvincia(almacenar.getProvincia());
        model.put("localidades", localidades);

       return new ModelAndView("paso_dos_inmu", model);
    }


    @GetMapping("/guardar_paso_dos_inmu")
    public ModelAndView guardaPasoDos(@ModelAttribute("almacenar") AlmacenarCasaDTO almacenar, ModelMap model){

        Double cotizacion = almacenar.getMetros() * 1.50 / 6;

        almacenar.setCotizacion(cotizacion);

        model.put("almacenar" ,almacenar);

        return new ModelAndView("resultado_final_inmu", model);
    }



    @PostMapping("/crear_poliza_inmu")
    public ModelAndView cotizarAuto(@ModelAttribute("almacenar") AlmacenarDTO almacenar, ModelMap model ){

        Policy policy = new Policy();

        Customer customer = customerService.findOne(3L); //HARDCODE
        policy.setCustomer(customer);
        Insurance insurance = insuranceService.findById(2L);
        policy.setInsurance(insurance);
        policy.setCoverage(almacenar.getCotizacion());
        policyService.save(policy);

        return new ModelAndView("exito", model);
    }







}
