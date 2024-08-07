package org.aseguradora.controllers;


import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CotizacionVidaController {

    private PolicyService policyService;

    private InsuranceService insuranceService;

    private LiveService liveService;

    @Autowired
    public CotizacionVidaController(PolicyService policyService, InsuranceService insuranceService, LiveService liveService) {
        this.policyService = policyService;
        this.insuranceService = insuranceService;
        this.liveService = liveService;
    }

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
    public ModelAndView guardarPasoUno(@ModelAttribute("almacenar") AlmacenarVidaDTO almacenar, ModelMap model) {

        if (almacenar.getOficio() != null) {

            List<Integer> anios = liveService.buscarAnioPorOficio(almacenar.getOficio());
            model.put("anios", anios);

            return new ModelAndView("paso_dos_vida", model);
        }

        return new ModelAndView("redirect:/paso_uno_vida");
    }


    @GetMapping("/guardar_paso_dos_vida")
    public ModelAndView guardaPasoDos(@ModelAttribute("almacenar") AlmacenarVidaDTO almacenar, ModelMap model) {

        if (almacenar.getOficio() != null && almacenar.getAnio() != null && almacenar.getType() != null) {

            Double precio = liveService.buscarPrecioPorAnioYOficio(almacenar.getOficio(), almacenar.getAnio());

            Double cotizacion = liveService.applyQuote(precio, almacenar.getType());

            almacenar.setPrecio(precio);
            almacenar.setCotizacion(cotizacion);

            model.put("almacenar", almacenar);

            return new ModelAndView("resultado_final_vida", model);
        }

        return new ModelAndView("redirect:/paso_uno_vida");

    }

    @PostMapping("/crear_poliza_vida")
    public ModelAndView cotizarVida(@ModelAttribute("almacenar") AlmacenarVidaDTO almacenar, RedirectAttributes flash, HttpServletRequest request) {

        Policy policy = new Policy();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {

            policy.setCustomer(customer);
            Insurance insurance = insuranceService.findById(3L);
            policy.setInsurance(insurance);
            policy.setCoverage(almacenar.getCotizacion());
            policyService.save(policy);
            
            

            flash.addFlashAttribute("info", "Ha generado una nueva póliza!");
            
            policyService.sendNotificacion(policy);
            
            return new ModelAndView("redirect:/polizas");
        }

        return new ModelAndView("redirect:/login");
    }


}
