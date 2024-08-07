package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PolicyService;
import org.aseguradora.view.pdf.PolizaPdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public ModelAndView verPolizas(HttpServletRequest request, RedirectAttributes flash) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null && customer.hasRole("ROLE_USER").isPresent()) {
            List<Policy> policyList = customerService.findPoliciesByCustomerId(customer.getId());
            ModelMap model = new ModelMap();
            model.put("polizas_by_id", policyList);
            return new ModelAndView("by-id", model);
        } else if (customer != null && customer.hasRole("ROLE_ADMIN").isPresent()) {
            flash.addFlashAttribute("notUser", "Debes ser usuario para acceder a esta página!");
            return new ModelAndView("redirect:/quejas");
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/polizas/{id_policy}")
    public ModelAndView verPolizaPorId(@PathVariable("id_policy") Long idPolicy, HttpServletRequest request, RedirectAttributes flash) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null && customer.hasRole("ROLE_USER").isPresent()) {
            Policy policy = policyService.findById(idPolicy);
            ModelMap model = new ModelMap();
            model.put("policy", policy);
            return new ModelAndView("policy_by_id", model);
        } else if (customer != null && customer.hasRole("ROLE_ADMIN").isPresent()) {
            flash.addFlashAttribute("notUser", "Debes ser usuario para acceder a esta página!");
            return new ModelAndView("redirect:/quejas");
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/polizas/{id_policy}/pdf")
    public void exportToPDF(@PathVariable("id_policy") Long idPolicy, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=PolizaVIVIR" + ".pdf";
            response.setHeader(headerKey, headerValue);

            Policy policy = policyService.findById(idPolicy);

            PolizaPdfView exporter = new PolizaPdfView(policy);
            exporter.export(response);
        }
    }

    @GetMapping("/anular/{id_policy}")
    public ModelAndView anularPoliza(@PathVariable("id_policy") Long idPolicy, HttpServletRequest request, RedirectAttributes flash) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            Policy policy = policyService.findById(idPolicy);
            if (policy != null && policy.getCustomer() != null) {
                policyService.delete(policy);
                flash.addFlashAttribute("delete", "Se ha eliminado la póliza con éxito");
                return new ModelAndView("redirect:/polizas");
            }
        }
        return new ModelAndView("redirect:/login");
    }

}
