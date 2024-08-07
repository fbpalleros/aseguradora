package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Payment;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.Role;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerController customerController;

    private CustomerService customerService;
    private PaymentService paymentService;

    private HttpSession session;
    private HttpServletRequest request;

    private RedirectAttributes flash;

    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
        this.flash = mock(RedirectAttributes.class);
        this.customerService = mock(CustomerService.class);
        this.paymentService = mock(PaymentService.class);
        this.customerController = new CustomerController(customerService, paymentService);
    }

    @Test
    public void queRetorneLaVistaMisDatos() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setRoles(roles);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        ModelAndView mav = this.customerController.mostrarDatos(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("mis_datos"));
    }

    @Test
    public void queRedirijaAlLoginSiElUsuarioEsNulo() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(null);

        ModelAndView mav = this.customerController.mostrarDatos(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void queRetorneLaVistaMiSaldo() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setRoles(roles);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        ModelAndView mav = this.customerController.mostrarDeuda(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("saldo"));
    }

    @Test
    public void queRetorneLaVistaMiSaldoYMuestreElSaldoDeLasPolizasSiTuviese() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);
        Policy policy = new Policy(1L, customer, 1000.00);

        List<Policy> policies = new ArrayList<>();
        policies.add(policy);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPoliciesByCustomerId(1L)).thenReturn(policies);

        ModelAndView mav = this.customerController.mostrarDeuda(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("saldo"));
        assertThat(mav.getModel().get("policies"), equalToObject(policies));
        assertThat(mav.getModel().get("saldo_total"), equalTo(1000.00));
    }

    @Test
    public void queElSaldoTotalSeaIgualALaSumaDeDeudaDeTodasLasPolizas() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);
        Policy p1 = new Policy(1L, customer, 1000.00);
        Policy p2 = new Policy(2L, customer, 1000.00);

        List<Policy> policies = new ArrayList<>();
        policies.add(p1);
        policies.add(p2);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPoliciesByCustomerId(1L)).thenReturn(policies);

        ModelAndView mav = this.customerController.mostrarDeuda(request, flash);

        assertThat(mav.getModel().get("saldo_total"), equalTo(2000.00));
    }

    @Test
    public void queSeActualiceElValorDeLaPolizaAlPagarla() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);

        Policy p1 = new Policy(1L, customer, 1000.00);
        Policy p2 = new Policy(1L, customer, 0.0);

        Payment payment = new Payment();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPolicyByIdCustomer(customer.getId(), p1.getId())).thenReturn(p1);
        when(this.customerService.pay(p1)).thenReturn((p2));

        ModelAndView mav = this.customerController.pagar(1L, request, flash);
        mav.addObject(payment);

        assertThat(mav.getViewName(), equalToIgnoringCase("pago"));
        assertThat(p2.getCoverage(), equalTo(0.0));
        assertThat(mav.getModel().get("payment"), equalToObject(payment));
    }


    @Test
    public void queAlIntentarPagarUnaPolizaRetorneLaVentanaDePago() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);

        Policy policy = new Policy(1L, customer, 2000.00);
        Payment payment = new Payment();
        payment.setPolicy(policy);
        payment.setCustomer(customer);
        payment.setAmount(policy.getCoverage());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPolicyByIdCustomer(1L, 1L)).thenReturn(policy);

        ModelAndView mav = this.customerController.pagar(1L, request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("pago"));
    }

    @Test
    public void queAlEnviarElPagoSeGuardeElMismoYSeActualiceLaPolizaEnCero() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);
        Policy policy = new Policy(1L, customer, 2000.00);
        Payment payment = new Payment();
        payment.setPolicy(policy);
        payment.setCustomer(customer);
        payment.setAmount(policy.getCoverage());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPolicyByIdCustomer(1L, 1L)).thenReturn(policy);
        ModelAndView mav = this.customerController.pagarConTarjeta(payment, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/mis_pagos"));
        verify(this.paymentService, times(1)).save(payment);
        verify(this.customerService, times(1)).pay(policy);
    }

    @Test
    public void queRetorneLaVistaActualizarSiExisteUsuario() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findOne(customer.getId())).thenReturn(customer);

        ModelAndView mav = this.customerController.vistaActualizar(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("actualizar"));
        assertThat(mav.getModel().get("customer"), equalToObject(customer));
    }

    @Test
    public void queSeRetorneLaVentanaMisPagos() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRoles(roles);

        List<Payment> paidPolicies = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.paymentService.findByCustomerId(1L)).thenReturn(paidPolicies);
        ModelAndView mav = this.customerController.mostrarPagos(request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("mis_pagos"));
        assertThat(mav.getModel().get("payments"), equalToObject(paidPolicies));
    }

}

