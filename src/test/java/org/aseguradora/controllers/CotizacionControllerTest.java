package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarDTO;
import org.aseguradora.services.CarService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
        this.cotizacionController = new CotizacionController(policyService, customerService, insuranceService, carService );
    }

//    @Test
//    public void queAlSolicitarLaPantallaDeCotizacionSeMuestreLaVistaCotizacion() {
//        List<String> names = new ArrayList<>();
//        names.add("Fiat");
//        names.add("Honda");
//        List<String> models = new ArrayList<>();
//        models.add("CR-V");
//        models.add("HR-V");
//
//        when(this.carService.findDistinctName()).thenReturn(names);
//        when(this.carService.findDistinctModelByName("Honda")).thenReturn(models);
//
//        //Ejecución
//        ModelAndView mav = this.cotizacionController.vistarCotizador();
//        System.out.println(mav.getModel().size());
//
//        //Verificación
//        assertThat(mav.getViewName(), equalToIgnoringCase("cotizador"));
//        assertThat(mav.getModel().size(), equalTo(3));
//    }

    @Test
    public void queAlSolicitarLaPantallaCotizacionSeMuestreLaVistaPasoUnoConSelectDeMarcas() {
        List<String> names = new ArrayList<>();
        names.add("Fiat");
        names.add("Honda");
        names.add("Ford");
        names.add("Chevrolet");

        when(this.carService.findDistinctName()).thenReturn(names);
        ModelAndView mav = this.cotizacionController.vistaPasoUno();

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_uno"));
        assertThat(mav.getModel().get("names"), equalToObject(names));
    }

    @Test
    public void queAlSeleccionarLaMarcaSeGuardeElValorYRetorneLaVistaPasoDos() {
        List<String> modelos = new ArrayList<>();
        modelos.add("Fiesta");
        modelos.add("Focus");
        modelos.add("Territory");
        modelos.add("Bronco");

        AlmacenarDTO almacenar = new AlmacenarDTO();
        almacenar.setNombre("Ford");

        when(this.carService.findDistinctModelByName(almacenar.getNombre())).thenReturn(modelos);
        ModelAndView mav = this.cotizacionController.guardarPasoUno(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_dos"));
        assertThat(mav.getModel().get("modelos"), equalToObject(modelos));

    }

    @Test
    public void queAlSeleccionarElModeloSeGuardeElValorYRetorneLaVistaPasoTres() {
        AlmacenarDTO almacenar = new AlmacenarDTO();
        List<Integer> years = new ArrayList<>();

        when(this.carService.findDistinctByNameAndModel(almacenar.getNombre(), almacenar.getModelo())).thenReturn(years);

        ModelAndView mav = this.cotizacionController.guardarPasoDos(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_tres"));
        assertThat(mav.getModel().get("years"), equalToObject(years));

    }

    @Test
    public void queAlCotizarSeMuestreElPrecioDelVehiculoYSuCuota() {

        AlmacenarDTO almacenar = new AlmacenarDTO();
        Double precioMock = 2000.00;

        when(this.carService.findPrice(almacenar.getNombre(), almacenar.getModelo(), almacenar.getAnio())).thenReturn(precioMock);
        ModelAndView mav = this.cotizacionController.guardarPasoTres(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("resultado_final"));
        assertThat(mav.getModel().get("almacenar"), equalToObject(almacenar));
    }

    @Test
    public void queSePuedaCrearLaPoliza() {
        AlmacenarDTO almacenar = new AlmacenarDTO();

        Customer customer = new Customer();
        customer.setId(3L);

        Insurance insurance = new Insurance();
        insurance.setId(1L);

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setInsurance(insurance);
        policyService.save(policy);

        ModelAndView mav = this.cotizacionController.cotizarAuto(almacenar, new ModelMap());

        when(this.customerService.findOne(3L)).thenReturn(customer);
        when(this.insuranceService.findById(1L)).thenReturn(insurance);
        assertThat(mav.getViewName(), equalToIgnoringCase("exito"));
        verify(policyService).save(policy);

    }



}
