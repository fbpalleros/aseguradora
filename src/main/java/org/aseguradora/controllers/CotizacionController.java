package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CarService;
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

    private CarService carService;

    public CotizacionController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/cotizacion")
    public ModelAndView vistarCotizador() {
        ModelMap model = new ModelMap();
        Policy policy = new Policy();
        List<String> names = carService.findDistinctName();
        List<String> models = carService.findDistinctModelByName("Honda");

        model.put("models", models);
        model.put("names", names);
        model.put("policy", policy);
        return new ModelAndView("cotizador", model);
    }


    @PostMapping("/cotizar")
    public ModelAndView cotizarProducto(@ModelAttribute("policy") Policy policy, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("policy", policy);
        Policy p = (Policy) session.getAttribute("policy");
        p.setCoverage((int) (p.getCoverage()*1.2));
        model.put("precio_cotizado", p.getCoverage());
        return new ModelAndView("resultado", model);
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
