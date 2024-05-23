package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarCasaDTO;
import org.aseguradora.entity.dto.AlmacenarDTO;
import org.aseguradora.services.CityService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CotizacionInmuebleControllerTest {

    private CotizacionInmuebleController cotizacionInmuebleController;
    private PolicyService policyService;
    private CustomerService customerService;
    private InsuranceService insuranceService;
    private CityService cityService;

    private RedirectAttributes flash;

    @BeforeEach
    public void init() {
        this.flash = mock(RedirectAttributes.class);
        this.policyService = mock(PolicyService.class);
        this.customerService = mock(CustomerService.class);
        this.insuranceService = mock(InsuranceService.class);
        this.cityService = mock(CityService.class);
        this.cotizacionInmuebleController = new CotizacionInmuebleController(policyService, customerService, insuranceService, cityService);
    }

    @Test
    public void queAlSolicitarLaPantallaPasoUnoInmuebleSeRetorneLaVista() {
        List<String> provinciasMock = new ArrayList<>();
        AlmacenarCasaDTO almacenar = new AlmacenarCasaDTO();

        when(this.cityService.listarTodasLasProvincias()).thenReturn(provinciasMock);

        ModelAndView mav = this.cotizacionInmuebleController.vistaPasoUno();

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_uno_inmu"));
        assertThat(mav.getModel().get("provincias"), equalToObject(provinciasMock));
        assertThat(mav.getModel().get("almacenar").getClass(), equalToObject(almacenar.getClass()));
        assertThat(mav.getModel().size(), equalTo(2));
    }

    @Test
    public void queSeGuardeElPasoUnoYRetorneLaVistaPasoDos() {
        AlmacenarCasaDTO almacenar = new AlmacenarCasaDTO();
        almacenar.setProvincia("Buenos Aires");
        List<String> localidadesMock = new ArrayList<>();
        localidadesMock.add("Merlo");
        localidadesMock.add("Morón");
        localidadesMock.add("Moreno");

        when(this.cityService.buscarDependiendoLaProvincia(almacenar.getProvincia())).thenReturn(localidadesMock);

        ModelAndView mav = this.cotizacionInmuebleController.guardarPasoUno(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_dos_inmu"));
        assertThat(mav.getModel().get("localidades"), equalTo(localidadesMock));
        assertThat(mav.getModel().size(), equalTo(1));
    }

    @Test
    public void queSeGuerdeElPasoDosYRetorneElResultadoFinal() {
        AlmacenarCasaDTO almacenar = new AlmacenarCasaDTO();
        almacenar.setMetros(20.00);

        ModelAndView mav = this.cotizacionInmuebleController.guardaPasoDos(almacenar, new ModelMap());

        assertThat(mav.getModel().get("almacenar").getClass(), equalToObject(almacenar.getClass()));
        assertThat(mav.getViewName(), equalToIgnoringCase("resultado_final_inmu"));
    }

    @Test
    public void queSeGenereLaPoliza(){
        AlmacenarCasaDTO almacenar = new AlmacenarCasaDTO();
        flash.addFlashAttribute("mensajeExito", "Ha generado una nueva póliza!");

        Customer customer = new Customer();
        customer.setId(3L);;

        Insurance insurance = new Insurance();
        insurance.setId(2L);

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setInsurance(insurance);
        policyService.save(policy);

        when(this.customerService.findOne(3L)).thenReturn(customer);
        when(this.insuranceService.findById(3L)).thenReturn(insurance);

        ModelAndView mav = this.cotizacionInmuebleController.cotizarCasa(almacenar, flash);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/polizas/3"));
        verify(policyService).save(policy);
        verify(flash).addFlashAttribute("mensajeExito", "Ha generado una nueva póliza!");

    }
}
