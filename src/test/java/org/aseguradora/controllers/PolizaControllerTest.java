package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

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

    private HttpSession session;
    private HttpServletRequest request;

    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
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
    public void queSeRetorneUnaPolizaEspecificaDeUnCliente(){
        Policy policyMock = new Policy();
        policyMock.setId(3L);

        when(this.policyService.findById(policyMock.getId())).thenReturn(policyMock);

        ModelAndView mav = this.polizaController.vePolizaPorId(3L);

        assertThat(mav.getViewName(), equalToIgnoringCase("policy_by_id"));
        assertThat(mav.getModel().get("policy"), equalToObject(policyMock));
    }

}
