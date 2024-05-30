package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PolizaController {


    private PolicyService policyService;
    private CustomerService customerService;

    @Autowired
    public PolizaController(PolicyService policyService, CustomerService customerService) {
        this.policyService = policyService;
        this.customerService = customerService;
    }

    @GetMapping("/polizas")
    public ModelAndView verPolizas(HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        List<Policy> policyList = customerService.findPoliciesByCustomerId(customer.getId());
        ModelMap model = new ModelMap();
        model.put("polizas_by_id", policyList);

        return new ModelAndView("by-id", model);
    }

    @GetMapping("/polizas/3/{id_policy}")
    public ModelAndView vePolizaPorId(@PathVariable("id_policy") Long idPolicy) {
        Policy policy = policyService.findById(idPolicy);
        ModelMap model = new ModelMap();
        model.put("policy", policy);
        return new ModelAndView("policy_by_id", model);
    }

}
