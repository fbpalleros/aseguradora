package org.aseguradora.controllers;


import org.aseguradora.entity.*;
import org.aseguradora.services.ComplaintService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    private PaymentService paymentService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    public CustomerController(CustomerService customerService, PaymentService paymentService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
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

    @GetMapping("/historial_quejas")
    public ModelAndView historialQuejas(HttpServletRequest request, RedirectAttributes flash) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();
        if (customer != null) {
            List<Complaint> complaints = complaintService.findByCustomerId(customer.getId());
            model.put("complaints", complaints);
            return new ModelAndView("historial_quejas", model);
        } else {
            flash.addFlashAttribute("error_queja","Debe iniciar sesión para presentar una queja");
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/historial_quejas/{id}")
    public ModelAndView vistarRespuesta(@PathVariable("id") Long idComplaint, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();
        if (customer != null) {
            Complaint complaint = complaintService.findOne(idComplaint);
            model.put("complaint", complaint);
            return new ModelAndView("queja", model);
        }
        return new ModelAndView("redirect:/login");

    }

    @GetMapping("/mis_quejas")
    public ModelAndView vistaMisQuejas(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        ModelMap model = new ModelMap();
        if (customer != null) {
            Complaint complaint = new Complaint();
            complaint.setCustomer(customer);
            model.put("complaint", complaint);
            return new ModelAndView("mis_quejas", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/send_complaint")
    public ModelAndView enviarQueja(@ModelAttribute("complaint") Complaint complaint, HttpServletRequest request) {
        complaint.setStatus(Status.ENVIADO);
        complaintService.save(complaint);
        return new ModelAndView("redirect:/login");
    }


    //ADMIN
    @GetMapping("/quejas")
    public ModelAndView vistarQuejasAdmin() {
        List<Complaint> complaints = complaintService.findAll();
        ModelMap model = new ModelMap();
        model.put("complaints", complaints);
        return new ModelAndView("admin/quejas", model);
    }

    @GetMapping("/quejas/{id}")
    public ModelAndView vistarQueja(@PathVariable("id") Long idComplaint) {
        Complaint complaint = complaintService.findOne(idComplaint);
        ModelMap model = new ModelMap();
        model.put("complaint", complaint);
        return new ModelAndView("admin/queja_id", model);
    }

    @PostMapping("/send_response")
    public ModelAndView responderQueja(@ModelAttribute("complaint") Complaint complaint) {
        Complaint complaintSearched = complaintService.findOne(complaint.getId());
        complaintSearched.setResponse(complaint.getResponse());
        complaintSearched.setStatus(Status.CERRADO);
        complaintService.update(complaintSearched);
        return new ModelAndView("redirect:/login");
    }


}
