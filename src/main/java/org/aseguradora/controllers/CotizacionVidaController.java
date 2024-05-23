package org.aseguradora.controllers;


import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarDTO;
import org.aseguradora.entity.dto.AlmacenarVidaDTO;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.aseguradora.services.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CotizacionVidaController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private LiveService liveService;


    @GetMapping("/paso_uno_vida")
    public ModelAndView vistaPasoUno() {
        ModelMap model = new ModelMap();
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();
        List<String> oficios = liveService.listarTodasLosOficios();
        model.put("oficios", oficios);
        model.put("almacenar", almacenar);
        return new ModelAndView("paso_uno_vida", model);
    }

    @GetMapping("/guardar_paso_uno_vida")
    public ModelAndView guardarPasoUno(@ModelAttribute("almacenar") AlmacenarVidaDTO almacenar, ModelMap model){
        List<Integer> anios = liveService.buscarAnioPorOficio(almacenar.getOficio());
        model.put("anios", anios);

        return new ModelAndView("paso_dos_vida", model);
    }


    @GetMapping("/guardar_paso_dos_vida")
    public ModelAndView guardaPasoDos(@ModelAttribute("almacenar") AlmacenarVidaDTO almacenar, ModelMap model){

        Double precio = liveService.buscarPrecioPorAnioYOficio(almacenar.getOficio(),almacenar.getAnio());

        Double cotizacion =  precio * 0.50 / 6;

        almacenar.setPrecio(precio);
        almacenar.setCotizacion(cotizacion);

        model.put("almacenar" ,almacenar);

        return new ModelAndView("resultado_final_vida", model);
    }

    @PostMapping("/crear_poliza_vida")
    public ModelAndView cotizarAuto(@ModelAttribute("almacenar") AlmacenarDTO almacenar, ModelMap model ){

        Policy policy = new Policy();

        Customer customer = customerService.findOne(3L); //HARDCODE
        policy.setCustomer(customer);
        Insurance insurance = insuranceService.findById(1L);
        policy.setInsurance(insurance);
        policy.setCoverage(almacenar.getCotizacion());
        policyService.save(policy);

        return new ModelAndView("exito", model);
    }


}
