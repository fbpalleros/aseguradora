package org.aseguradora.controllers;


import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Payment;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    private PaymentService paymentService;

    @Autowired
    public CustomerController(CustomerService customerService, PaymentService paymentService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @GetMapping("/customers")
    public ModelAndView verClientes() {
        ModelMap model = new ModelMap();
        List<Customer> customers = customerService.findAll();
        model.put("customers", customers);
        return new ModelAndView("customer", model);
    }

    @RequestMapping(path = "/mis_datos", method = RequestMethod.GET)
    public ModelAndView mostrarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            ModelAndView mav = new ModelAndView("mis_datos");
            mav.addObject("customer", customer);
            return mav;
        } else {
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
            policies.forEach(p -> {
                saldoTotal[0] += p.getCoverage();
            });
            model.put("saldo_total", saldoTotal[0]);
            return new ModelAndView("saldo", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/pagar/{id}")
    public ModelAndView pagar(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();
        if (customer != null) {
            Payment payment = new Payment();
            Policy policy = customerService.findPolicyByIdCustomer(customer.getId(), id);
            payment.setPolicy(policy);
            payment.setCustomer(customer);
            payment.setAmount(policy.getCoverage());
            model.put("payment", payment);
            return new ModelAndView("pago", model);
        }
        return new ModelAndView("redirect:/polizas");
    }

    @PostMapping("/pago-prueba")
    public ModelAndView pagarConTarjeta(@ModelAttribute("payment") Payment payment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            Long idPolicy = payment.getPolicy().getId();
            paymentService.save(payment);
            Policy policy = customerService.findPolicyByIdCustomer(customer.getId(), idPolicy);
            customerService.pay(policy);
            return new ModelAndView("redirect:/mis_pagos");
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/mis_pagos")
    public ModelAndView mostrarPagos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();
        if (customer != null) {
            List<Payment> paidPolicies = paymentService.findByCustomerId(customer.getId());
            model.put("payments", paidPolicies);
            return new ModelAndView("mis_pagos", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/actualizar")
    public ModelAndView vistaActualizar(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelMap model = new ModelMap();
        Customer customerSession = (Customer) session.getAttribute("customer");
        if (customerSession != null) {
            Customer customer = customerService.findOne(customerSession.getId());
            model.put("customer", customer);
            return new ModelAndView("actualizar", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(path = "/actualizar_datos")
    public ModelAndView actualizar(@ModelAttribute("customer") Customer customer) {
        customerService.actualizar(customer);
        return new ModelAndView("redirect:/login");
    }


}
