package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            return new ModelAndView("redirect:/");
        }
        ModelMap model = new ModelMap();
        Customer customer = new Customer();
        model.put("customer", customer);
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Customer customerSearched = customerService.findNameCustomer(customer.getEmail(), customer.getPassword());

        if (customerSearched != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customerSearched);
            if(customerSearched.hasRole("ROLE_ADMIN").isPresent()){
                return new ModelAndView("redirect:/quejas");
            }
            return new ModelAndView("redirect:/mis_datos");
        } else {
            model.put("error", "Usuario y/o contraseña incorrecta");
        }

        return new ModelAndView("login", model);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
