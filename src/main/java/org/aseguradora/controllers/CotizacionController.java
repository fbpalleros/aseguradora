package org.aseguradora.controllers;

import org.aseguradora.entity.Car;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.CarRepository;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CotizacionController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InsuranceService insuranceService;


    @GetMapping("/cotizacion")
    public String buscar(Model model) {
        Policy policy = new Policy();
        model.addAttribute("policy", policy);
        return "cotizador";
    }


    @PostMapping("/cotizar")
    public String cotizarProducto(@ModelAttribute("policy") Policy policy, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("policy", policy);
        Policy p = (Policy) session.getAttribute("policy");
        p.setCoverage((int) (p.getCoverage()*1.2));
        model.addAttribute("precio_cotizado", p.getCoverage());
        return "resultado";
    }

    @PostMapping("/crear-poliza")
    public ModelAndView crearPoliza(@RequestParam("flexRadioDefault") String valor, HttpServletRequest request) {

        if (valor != null && valor.equals("yes")) {
            try {

                HttpSession session = request.getSession();
                Policy p = (Policy) session.getAttribute("policy");

                //Guardar cliente en base de datos
                Customer customer = new Customer();
                customer.setName("fer");
                customer.setEmail("fer@gmail.com");
                customerService.save(customer);

                //una vez guardado setearlo al policy
                p.setCustomerId(customer);


                //Buscar insurance para atacharlo al contexto y luego setearlo
                Insurance insurance = insuranceService.findById(1L);
                p.setInsuranceId(insurance);

                //Guardar la poliza
                policyService.save(p);

                return new ModelAndView("exito");
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return new ModelAndView("redirect:/cotizacion");
    }


}
