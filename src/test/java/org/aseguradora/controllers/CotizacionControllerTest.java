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
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CotizacionControllerTest {

    private CotizacionController cotizacionController;

    private PolicyService policyService;
    private InsuranceService insuranceService;
    private CarService carService;

    private RedirectAttributes flash;
    private HttpSession session;
    private HttpServletRequest request;

    @BeforeEach
    public void init() {
        this.session = mock(HttpSession.class);
        this.request = mock(HttpServletRequest.class);
        this.flash = mock(RedirectAttributes.class);
        this.policyService = mock(PolicyService.class);
        this.insuranceService = mock(InsuranceService.class);
        this.carService = mock(CarService.class);
        this.cotizacionController = new CotizacionController(carService, insuranceService, policyService);
    }

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
    public void queRegreseALaVistaPasoUnoSiElObjetoAlmacenarNoContieneDatosEnGuardarPasoUno() { //si se intenta saltear un paso mediante rutas
        AlmacenarDTO almacenar = new AlmacenarDTO();

        ModelAndView mav = this.cotizacionController.guardarPasoUno(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/paso_uno"));
    }

    @Test
    public void queRegreseALaVistaPasoUnoSiElObjetoAlmacenarNoContieneDatosEnGuardarPasoDos() { //si se intenta saltear un paso mediante rutas
        AlmacenarDTO almacenar = new AlmacenarDTO();

        ModelAndView mav = this.cotizacionController.guardarPasoDos(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/paso_uno"));
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
        almacenar.setNombre("Fiat");
        almacenar.setModelo("Palio");
        almacenar.setAnio(2001);
        List<Integer> years = new ArrayList<>();

        when(this.carService.findDistinctByNameAndModel(almacenar.getNombre(), almacenar.getModelo())).thenReturn(years);

        ModelAndView mav = this.cotizacionController.guardarPasoDos(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_tres"));
        assertThat(mav.getModel().get("years"), equalToObject(years));

    }

    @Test
    public void queRegreseALaVistaPasoUnoSiElObjetoAlmacenarNoContieneDatosEnGuardarPasoTres() {
        AlmacenarDTO almacenar = new AlmacenarDTO();

        ModelAndView mav = this.cotizacionController.guardarPasoTres(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/paso_uno"));
    }

    @Test
    public void queAlCotizarSeMuestreElPrecioDelVehiculoYSuCuota() {

        AlmacenarDTO almacenar = new AlmacenarDTO();
        almacenar.setNombre("Fiat");
        almacenar.setModelo("Palio");
        almacenar.setAnio(2001);
        almacenar.setType(1);
        Double precioMock = 2000.00;
        Double quote = 2600.00;

        when(this.carService.findPrice(almacenar.getNombre(), almacenar.getModelo(), almacenar.getAnio())).thenReturn(precioMock);
        when(this.carService.applyQuote(precioMock, almacenar.getType())).thenReturn(quote);
        ModelAndView mav = this.cotizacionController.guardarPasoTres(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("resultado_final"));
        assertThat(mav.getModel().get("almacenar"), equalToObject(almacenar));
    }

    @Test
    public void queSePuedaCrearLaPolizaYSeEnvieElMensajeDeExito() {
        AlmacenarDTO almacenar = new AlmacenarDTO();
        almacenar.setCotizacion(2000.00);
        almacenar.setType(1);
        RedirectAttributes flash = new RedirectAttributesModelMap();
        flash.addFlashAttribute("mensajeExito", "Ha generado una nueva póliza!");
        Customer customer = new Customer();
        customer.setId(3L);

        Insurance insurance = new Insurance();
        insurance.setId(1L);

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setInsurance(insurance);
        policy.setCoverage(almacenar.getCotizacion());
        policy.setType(almacenar.getType());
        policyService.save(policy);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        when(this.insuranceService.findById(1L)).thenReturn(insurance);

        ModelAndView mav = this.cotizacionController.cotizarAuto(almacenar, flash, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/polizas"));
        verify(policyService).save(policy);
    }

    @Test
    public void queSeRedirijaAlLoginSinElUsuarioNoEstaLogueado() {
        AlmacenarDTO almacenar = new AlmacenarDTO();
        RedirectAttributes flash = new RedirectAttributesModelMap();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(null); // Simulate no customer in session

        ModelAndView mav = this.cotizacionController.cotizarAuto(almacenar, flash, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }


}
