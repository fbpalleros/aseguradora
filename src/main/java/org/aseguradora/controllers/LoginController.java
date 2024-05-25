package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Role;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ModelAndView home() {
            return new ModelAndView("home");
    }

    @GetMapping("/cotizacion")
    public ModelAndView cotizacion() {
        return new ModelAndView("cotizador");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelMap model = new ModelMap();
        model.put("customer", new Customer());
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Customer customerSearched = customerService.validateCredentials(customer.getEmail(), customer.getPassword());
        if (customerSearched != null) {
            request.getSession().setAttribute("customer", customerSearched);
            return new ModelAndView("redirect:/mis_datos");
        } else {
            model.put("error", "Usuario no encontrado");
        }

        return new ModelAndView("login", model);
    }
}
