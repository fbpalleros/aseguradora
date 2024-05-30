package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Denuncia;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.DenunciaService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.LiveService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DenunciaControllerTest {

	private RedirectAttributes flash;
	private HttpSession session;
    private HttpServletRequest request;
    
    private DenunciaController denunciaController;
    private PolicyService policyService;
    private CustomerService customerService;
    private DenunciaService denunciaService;
    
    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
        this.policyService = mock(PolicyService.class);
        this.customerService = mock(CustomerService.class);
        this.denunciaService = mock(DenunciaService.class);
        this.denunciaController = new DenunciaController(policyService, 
        												 denunciaService, 
        												 customerService);
    }
    
    @Test
    public void queRetorneLaVistaMisDenunciasSinLogin(){
        ModelAndView view = denunciaController.misDenuncias(request);
        
        assertThat(view.getViewName(), equalToIgnoringCase("login"));
        assertEquals(view.getModel().get("customer").getClass(), Customer.class);
        assertThat(view.getModel().size(), equalTo(1));
    }
    
    @Test
    public void queRetorneLaVistaMisDenunciasConLogin(){
    	Customer customer = new Customer();
    	customer.setId(1L);
    	
    	session.setAttribute("customer",customer);

    	when(request.getSession()).thenReturn(session);
    	when(session.getAttribute("customer")).thenReturn(customer);
    	when(denunciaService.findAllByCustomerId(customer.getId())).thenReturn(new ArrayList<Denuncia>());
    	
        ModelAndView view = denunciaController.misDenuncias(request);
        
        assertThat(view.getViewName(), equalToIgnoringCase("denuncia/misdenuncias"));
        assertEquals(view.getModel().get("denuncias").getClass(), ArrayList.class);
        assertThat(view.getModel().size(), equalTo(1));
    }
}
