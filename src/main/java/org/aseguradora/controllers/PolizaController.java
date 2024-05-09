package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;
import org.aseguradora.repositories.InsuranceRepository;
import org.aseguradora.repositories.impl.InsuranceRepositoryImpl;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PolizaController {



    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/polizas")
    public ModelAndView verPolizas() {
        ModelMap model = new ModelMap();
        model.put("polizas", policyService.findAll());
        Customer customer = new Customer();
        customer.setId(1L);
        policyService.findByIdObjeto(customer);
        return new ModelAndView("polizas", model);
    }




    @GetMapping("/polizas/{id}")
    public ModelAndView verPolizas(@PathVariable("id")Long id) {
        //Customer nuevo = new Customer(id,"fernando" ,"fer@gmail.com");
    	//List<Policy> policyList = policyService.findByIdObjeto(nuevo);
    	Policy policy = policyService.findById(id);
        ModelMap model = new ModelMap();
        model.put("policy", policy);
        return new ModelAndView("by-id", model);
    }

//    @GetMapping("/polizas/1")
//    public ModelAndView verTipoSeguro() {
//        Object insuranceType = policyService.findInsuranceType();
//
//
//        ModelMap model = new ModelMap();
//        model.put("insurance", insuranceType);
//        return new ModelAndView("prueba", model);
//    }


    //pruba con customer repository
    @GetMapping("/polizas/cliente/3")
    public ModelAndView verPolizasPorCliente() {

        Customer c1 = customerService.findOne(3L);

        ModelMap model = new ModelMap();
        model.put("customer", c1);
        return new ModelAndView("prueba", model);
    }


}
