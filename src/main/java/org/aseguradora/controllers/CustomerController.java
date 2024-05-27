package org.aseguradora.controllers;


import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView verClientes(){
        ModelMap model = new ModelMap();
        List<Customer> customers = customerService.findAll();
        model.put("customers", customers);
        return new ModelAndView("customer", model);
    }

//    @GetMapping("/mis_datos")
//    public ModelAndView verDatosPorId(HttpServletRequest request){
//        ModelMap model = new ModelMap();
//        Customer customer = customerService.findOne(3L);
//        model.put("customer", customer);
//        return new ModelAndView("mis_datos", model);
//    }

    @RequestMapping(path = "/mis_datos", method = RequestMethod.GET)
    public ModelAndView mostrarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            // Crea el ModelAndView con los datos del customer
            ModelAndView mav = new ModelAndView("mis_datos");
            mav.addObject("customer", customer);
            return mav;
        } else {
            //si no hay customer en la sesión
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(path = "/mi_saldo", method = RequestMethod.GET)
    public ModelAndView mostrarDeuda(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();

        if (customer != null) {
            List<Policy> policies = customerService.findPoliciesByCustomerId(customer.getId());
            model.put("policies", policies);
            final Double[] saldoTotal = {0.0};
            policies.forEach(p-> {
                saldoTotal[0] += p.getCoverage();
            });
            model.put("saldo_total", saldoTotal[0]);
            return new ModelAndView("saldo", model);
        } else {
//            si no hay customer en la sesión
            return new ModelAndView("redirect:/login");
        }
    }
}
