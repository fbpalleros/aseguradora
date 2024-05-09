package org.aseguradora.controllers;

import org.aseguradora.entity.Policy;
import org.aseguradora.services.CarService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;


public class CotizacionControllerTest {

    private CotizacionController cotizacionController;

    private PolicyService policyService;
    private CustomerService customerService;
    private InsuranceService insuranceService;
    private CarService carService;

    @BeforeEach
    public void init() {
        this.policyService = mock(PolicyService.class);
        this.customerService = mock(CustomerService.class);
        this.insuranceService = mock(InsuranceService.class);
        this.carService = mock(CarService.class);
        this.cotizacionController = new CotizacionController(carService);
    }

    @Test
    public void queAlSolicitarLaPantallaDeCotizacionSeMuestreLaVistaCotizacion() {
        List<String> names = new ArrayList<>();
        names.add("Fiat");
        names.add("Honda");
        List<String> models = new ArrayList<>();
        models.add("CR-V");
        models.add("HR-V");

        when(this.carService.findDistinctName()).thenReturn(names);
        when(this.carService.findDistinctModelByName("Honda")).thenReturn(models);

        //Ejecución
        ModelAndView mav = this.cotizacionController.vistarCotizador();
        System.out.println(mav.getModel().size());

        //Verificación
        assertThat(mav.getViewName(), equalToIgnoringCase("cotizador"));
        assertThat(mav.getModel().size(), equalTo(3));
    }

//    @Test
//    public void queAlCotizarElAutoRetorneLaVistaResultado(){
//        Policy policy = new Policy();
//        policy.setCoverage(100);
//        HttpServletRequest request = new MockHttpServletRequest();
//        ModelMap model = new ModelMap();
//
//
//        ModelAndView mav = this.cotizacionController.cotizarProducto(policy, model, request);
//
//        assertThat(mav.getViewName(), equalToIgnoringCase("resultado"));
//        assertThat(policy.getCoverage(),  eq(200));
//
//
//    }


}
