package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerController customerController;

    private CustomerService customerService;

    private HttpSession session;
    private HttpServletRequest request;

    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
        this.customerService = mock(CustomerService.class);
        this.customerController = new CustomerController(customerService);
    }

    @Test
    public void queRetorneLaVistaMisDatos() {
        Customer customer = new Customer();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        ModelAndView mav = this.customerController.mostrarDatos(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("mis_datos"));
    }

    @Test
    public void queRedirijaAlLoginSiElUsuarioEsNulo() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(null);

        ModelAndView mav = this.customerController.mostrarDatos(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void queRetorneLaVistaMiSaldo() {
        Customer customer = new Customer();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        ModelAndView mav = this.customerController.mostrarDeuda(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("saldo"));
    }

    @Test
    public void queRetorneLaVistaMiSaldoYMuestreElSaldoDeLasPolizasSiTuviese() {
        Customer customer = new Customer();
        customer.setId(1L);
        Policy policy = new Policy(1L, customer, 1000.00);

        List<Policy> policies = new ArrayList<>();
        policies.add(policy);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPoliciesByCustomerId(1L)).thenReturn(policies);

        ModelAndView mav = this.customerController.mostrarDeuda(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("saldo"));
        assertThat(mav.getModel().get("policies"), equalToObject(policies));
        assertThat(mav.getModel().get("saldo_total"), equalTo(1000.00));
    }

    @Test
    public void queElSaldoTotalSeaIgualALaSumaDeDeudaDeTodasLasPolizas() {
        Customer customer = new Customer();
        customer.setId(1L);
        Policy p1 = new Policy(1L, customer, 1000.00);
        Policy p2 = new Policy(2L, customer, 1000.00);

        List<Policy> policies = new ArrayList<>();
        policies.add(p1);
        policies.add(p2);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPoliciesByCustomerId(1L)).thenReturn(policies);

        ModelAndView mav = this.customerController.mostrarDeuda(request);

        assertThat(mav.getModel().get("saldo_total"), equalTo(2000.00));
    }

    @Test
    public void queSeActualiceElValorDeLaPolizaAlPagarla() {
        Customer customer = new Customer();
        customer.setId(1L);

        Policy p1 = new Policy(1L, customer, 1000.00);
        Policy p2 = new Policy(1L, customer, 0.0);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPolicyByIdCustomer(customer.getId(), p1.getId())).thenReturn(p1);
        when(this.customerService.pay(p1)).thenReturn((p2));

        ModelAndView mav = this.customerController.pagar(1L, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/mi_saldo"));
        assertThat(p2.getCoverage(), equalTo(0.0));
        verify(this.customerService).pay(p1);
    }

    @Test
    public void queRetorneLaVistaActualizarSiExisteUsuario() {
        Customer customer = new Customer();
        customer.setId(1L);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findOne(customer.getId())).thenReturn(customer);

        ModelAndView mav = this.customerController.vistaActualizar(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("actualizar"));
        assertThat(mav.getModel().get("customer"), equalToObject(customer));
    }




}

