package org.aseguradora.controllers;


import org.aseguradora.entity.Customer;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/mis_datos")
    public ModelAndView verDatosPorId(){
        ModelMap model = new ModelMap();
        Customer customer = customerService.findOne(3L);
        model.put("customer", customer);
        return new ModelAndView("mis_datos", model);
    }
}
