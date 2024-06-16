package org.aseguradora.controllers;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.AlmacenarVidaDTO;
import org.aseguradora.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.verify;

public class CotizacionVidaControllerTest {

    private CotizacionVidaController cotizacionController;

    private PolicyService policyService;
    private InsuranceService insuranceService;
    private LiveService liveService;

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
        this.liveService = mock(LiveService.class);
        this.cotizacionController = new CotizacionVidaController(policyService, insuranceService, liveService);
    }

    @Test
    public void queAlSolicitarLaPantallaPasoUnoVidaSeRetorneLaVista() {
        List<String> oficiosMock = new ArrayList<>();
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();

        when(this.liveService.listarTodasLosOficios()).thenReturn(oficiosMock);

        ModelAndView mav = this.cotizacionController.vistaPasoUno();

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_uno_vida"));
        assertThat(mav.getModel().get("oficios"), equalToObject(oficiosMock));
        assertThat(mav.getModel().get("almacenar").getClass(), equalToObject(almacenar.getClass()));
        assertThat(mav.getModel().size(), equalTo(2));
    }

    @Test
    public void queRegreseALaVistaPasoUnoSiElObjetoAlmacenarNoContieneDatosEnGuardarPasoUno(){
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();

        ModelAndView mav = this.cotizacionController.guardarPasoUno(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/paso_uno_vida"));
    }

    @Test
    public void queSeGuardeElPasoUnoYRetorneLaVistaPasoDos() {
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();
        almacenar.setOficio("Camionero");
        List<Integer> aniosMock = new ArrayList<>();

        when(this.liveService.buscarAnioPorOficio(almacenar.getOficio())).thenReturn(aniosMock);

        ModelAndView mav = this.cotizacionController.guardarPasoUno(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("paso_dos_vida"));
        assertThat(mav.getModel().get("anios"), equalTo(aniosMock));
        assertThat(mav.getModel().size(), equalTo(1));
    }

    @Test
    public void queRegreseALaVistaPasoUnoSiElObjetoAlmacenarNoContieneDatosEnGuardarPasoDos(){
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();

        ModelAndView mav = this.cotizacionController.guardaPasoDos(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/paso_uno_vida"));
    }

    @Test
    public void queSeGuardeElPasoDosYRetorneLavistaResultadoFinal() {
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();
        almacenar.setOficio("Camionero");
        almacenar.setAnio(1970);

        Double precio = 0.0;

        when(this.liveService.buscarPrecioPorAnioYOficio(almacenar.getOficio(), almacenar.getAnio())).thenReturn(precio);
        ModelAndView mav = this.cotizacionController.guardaPasoDos(almacenar, new ModelMap());

        assertThat(mav.getViewName(), equalToIgnoringCase("resultado_final_vida"));
        assertThat(mav.getModel().get("almacenar"), equalTo(almacenar));
        assertThat(mav.getModel().size(), equalTo(1));
    }

    @Test
    public void queSeGenereLaPolizaSiElUsuarioEstaLogueado() {
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();
        flash.addFlashAttribute("mensajeExito", "Ha generado una nueva póliza!");

        Customer customer = new Customer();
        customer.setId(3L);
        ;

        Insurance insurance = new Insurance();
        insurance.setId(3L);

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setInsurance(insurance);
        policyService.save(policy);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(customer);

        when(this.insuranceService.findById(3L)).thenReturn(insurance);

        ModelAndView mav = this.cotizacionController.cotizarVida(almacenar, flash, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/polizas"));
        verify(policyService).save(policy);
        verify(flash).addFlashAttribute("mensajeExito", "Ha generado una nueva póliza!");
    }

    @Test
    public void queSeRedirijaAlLoginSinElUsuarioNoEstaLogueado() {
        AlmacenarVidaDTO almacenar = new AlmacenarVidaDTO();
        RedirectAttributes flash = new RedirectAttributesModelMap();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("customer")).thenReturn(null);

        ModelAndView mav = this.cotizacionController.cotizarVida(almacenar, flash, request);

        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/login"));
    }
}
