package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

//    CustomerRepository customerRepository = new CustomerRepositoryImpl();
//
//    @GetMapping("/login")
//    public ModelAndView login(){
//        return new ModelAndView("login");
//    }
//
//
//    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
//    public ModelAndView validarLogin(@ModelAttribute("customer") Customer customer){
//        ModelMap model = new ModelMap();
//
//        Long id = 1L;
//        Customer usuarioBuscado = customerRepository.findOne(id);
//
//        if(usuarioBuscado != null){
//            return new ModelAndView("redirect:/polizas");
//        } else {
//            model.put("error", "Usuario no encontrado");
//        }
//
//        return new ModelAndView("login", model);
//
//    }
}
