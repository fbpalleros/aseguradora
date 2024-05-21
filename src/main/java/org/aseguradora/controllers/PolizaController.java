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
        return new ModelAndView("polizas", model);
    }

    @GetMapping("/polizas/{id}")
    public ModelAndView verPolizas(@PathVariable("id")Long id) {
        List<Policy> policyList = policyService.findByCustomerId(id);
        ModelMap model = new ModelMap();
        model.put("polizas_by_id", policyList);
        return new ModelAndView("by-id", model);
    }

    @GetMapping("/polizas/3/{id_policy}")
    public ModelAndView vePolizaPorId(@PathVariable("id_policy")Long idPolicy) {
        Policy policy = policyService.findById(idPolicy);
        ModelMap model = new ModelMap();
        model.put("policy", policy);
        return new ModelAndView("policy_by_id", model);
    }

}
