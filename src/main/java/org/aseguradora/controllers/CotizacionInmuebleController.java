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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CotizacionInmuebleController {

    private PolicyService policyService;

    private CustomerService customerService;

    private InsuranceService insuranceService;

    private CityService cityService;

    @Autowired
    public CotizacionInmuebleController(PolicyService policyService, CustomerService customerService, InsuranceService insuranceService, CityService cityService) {
        this.policyService = policyService;
        this.customerService = customerService;
        this.insuranceService = insuranceService;
        this.cityService = cityService;
    }

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
    public ModelAndView guardarPasoUno(@ModelAttribute("almacenar") AlmacenarCasaDTO almacenar, ModelMap model) {
        List<String> localidades = cityService.buscarDependiendoLaProvincia(almacenar.getProvincia());
        model.put("localidades", localidades);

        return new ModelAndView("paso_dos_inmu", model);
    }


    @GetMapping("/guardar_paso_dos_inmu")
    public ModelAndView guardaPasoDos(@ModelAttribute("almacenar") AlmacenarCasaDTO almacenar, ModelMap model) {

        Double cotizacion = almacenar.getMetros() * 1.50 / 6;

        almacenar.setCotizacion(cotizacion);

        model.put("almacenar", almacenar);

        return new ModelAndView("resultado_final_inmu", model);
    }


    @PostMapping("/crear_poliza_inmu")
    public ModelAndView cotizarCasa(@ModelAttribute("almacenar") AlmacenarDTO almacenar, RedirectAttributes flash) {

        Policy policy = new Policy();

        Customer customer = customerService.findOne(3L); //HARDCODE
        policy.setCustomer(customer);
        Insurance insurance = insuranceService.findById(2L);
        policy.setInsurance(insurance);
        policy.setCoverage(almacenar.getCotizacion());
        policyService.save(policy);

        flash.addFlashAttribute("success", "Ha generado una nueva póliza!");

        return new ModelAndView("redirect:/polizas/3");
    }


}
