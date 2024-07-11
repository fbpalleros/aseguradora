package org.aseguradora.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Denuncia;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.DenunciaService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DenunciaController {

    private CustomerService customerService;

    private PolicyService policyService;

    private DenunciaService denunciaService;

    @Autowired
    public DenunciaController(PolicyService policyService,
                              DenunciaService denunciaService,
                              CustomerService customerService) {
        this.policyService = policyService;
        this.denunciaService = denunciaService;
        this.customerService = customerService;
    }

    @GetMapping("/misdenuncias")
    public ModelAndView misDenuncias(HttpServletRequest request, RedirectAttributes flash) {
        ModelMap model = new ModelMap();
        Customer customer = null;

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("customer") == null) {
            customer = new Customer();
            model.put("customer", customer);
            return new ModelAndView("login", model);
        }

        customer = (Customer) session.getAttribute("customer");

        if (customer.hasRole("ROLE_USER").isPresent()) {
            List<Denuncia> denuncias = denunciaService.findAllByCustomerId(customer.getId());
            model.put("denuncias", denuncias);
            return new ModelAndView("denuncia/misdenuncias", model);
        } else if (customer.hasRole("ROLE_ADMIN").isPresent()) {
            flash.addFlashAttribute("notUser", "Debes ser usuario para acceder a esta página!");
            return new ModelAndView("redirect:/quejas", model);
        }
        return new ModelAndView("login", model);

    }

    @GetMapping("/denuncia")
    public ModelAndView crearDenuncias(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Customer customer = null;

        HttpSession session = request.getSession();

        if (session.getAttribute("customer") == null) {
            customer = new Customer();
            model.put("customer", customer);
            return new ModelAndView("login", model);
        }

        customer = (Customer) session.getAttribute("customer");

        List<Policy> policies = customerService.findPoliciesByCustomerId(customer.getId());
        model.put("polizas", policies);

        return new ModelAndView("denuncia/denuncia", model);
    }

    @GetMapping("/denuncia/{id}")
    public ModelAndView formularioDenuncia(@PathVariable("id") Long id) {
        Policy policy = policyService.findById(id);
        ModelMap model = new ModelMap();
        ModelAndView modelAndView;

        Denuncia denuncia = new Denuncia();
        denuncia.setPolicy(policy);

        model.put("denuncia", denuncia);

        switch (policy.getInsurance().getInsuranceType()) {
            case HOGAR:
                modelAndView = new ModelAndView("denuncia/denunciahogar", model);
                break;
            case AUTOMOTOR:
                modelAndView = new ModelAndView("denuncia/denunciavehiculo", model);
                break;
            case PERSONA:
                modelAndView = new ModelAndView("denuncia/denunciapersona", model);
                break;
            default:
                modelAndView = new ModelAndView("misdenuncias");
        }

        return modelAndView;
    }

    @PostMapping("/submitForm")
    public String guardarDenuncia(@ModelAttribute Denuncia denuncia, ModelMap model) throws ParseException {

        denunciaService.save(denuncia);

        model.addAttribute("message", "Gracias por elegirnos, pronto nos comunicaremos con usted");

        return "denuncia/resultadoformulario";
    }
}

    
