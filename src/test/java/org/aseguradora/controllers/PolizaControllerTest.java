package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.*;

public class PolizaControllerTest {

    private PolicyService policyService;
    private CustomerService customerService;
    private PolizaController polizaController;

    private RedirectAttributes flash;
    private HttpSession session;
    private HttpServletRequest request;

    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
        this.flash = mock(RedirectAttributes.class);
        this.policyService = mock(PolicyService.class);
        this.customerService = mock(CustomerService.class);
        this.polizaController = new PolizaController(policyService, customerService);
    }

    @Test
    public void queSeRetornenLasPolizasDelUsuario(){
        List<Policy> policiesMock = new ArrayList<>();
        Customer customer = new Customer();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findByCustomerId(3L)).thenReturn(policiesMock);

        ModelAndView mav = this.polizaController.verPolizas(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("by-id"));
        assertThat(mav.getModel().get("polizas_by_id"), equalToObject(policiesMock));
    }

    @Test
    public void queRetorneAlLoginSiSeQuiereNavegarALasPolizasYElUsuarioNoSeEncuentra(){
        Customer customer = null;
        List<Policy> policiesMock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.customerService.findPoliciesByCustomerId(1L)).thenReturn(policiesMock);

        ModelAndView mav = this.polizaController.verPolizas(request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void queSeRetorneUnaPolizaEspecificaDeUnCliente(){
        Customer customer = new Customer();
        Policy policyMock = new Policy();
        policyMock.setId(3L);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findById(policyMock.getId())).thenReturn(policyMock);

        ModelAndView mav = this.polizaController.verPolizaPorId(3L, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("policy_by_id"));
        assertThat(mav.getModel().get("policy"), equalToObject(policyMock));
    }

    @Test
    public void queRetorneAlLoginSiSeQuiereNavegarAUnaPolizaYElUsuarioNoSeEncuentra(){
        Customer customer = null;
        Policy policy = new Policy(1L, customer, 2000.00);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findById(1L)).thenReturn(policy);

        ModelAndView mav = this.polizaController.verPolizaPorId(1L, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void queSePuedaEliminarLaPolizaSiExiste(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Facu");
        Policy p1 = new Policy(1L, customer, 2000.00);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findById(1L)).thenReturn(p1);

        ModelAndView mav =  this.polizaController.anularPoliza(p1.getId(), request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/polizas"));
    }

    @Test
    public void queRetorneAlLoginSiLaPolizaNoExiste(){
        Customer customer = new Customer();
        customer.setId(1L);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findById(1L)).thenReturn(null);
        ModelAndView mav = this.polizaController.anularPoliza(1L, request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void queRetorneAlLoginSiElUsuarioNoSeEncuentraYNoSeElimineLaPoliza(){
        Customer customer = null;
        Policy p1 = new Policy(1L, customer, 2000.00);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);
        when(this.policyService.findById(1L)).thenReturn(p1);

        ModelAndView mav =  this.polizaController.anularPoliza(p1.getId(), request, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

}
