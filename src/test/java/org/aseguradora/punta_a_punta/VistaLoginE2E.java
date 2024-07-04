package org.aseguradora.punta_a_punta;

import com.microsoft.playwright.*;
import org.aseguradora.punta_a_punta.vistas.VistaLogin;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VistaLoginE2E {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaLogin vistaLogin;

    @BeforeAll
    static void abrirNavegador() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(800));
    }

    @AfterAll
    static void cerrarNavegador() {
        playwright.close();
    }

    @BeforeEach
    void crearContextoYPagina() {
        context = browser.newContext();
        Page page = context.newPage();
        vistaLogin = new VistaLogin(page);
    }

    @AfterEach
    void cerrarContexto() {
        context.close();
    }

    @Test
    void deberiaDecirIniciarSesionEnElTitulo() {
        String texto = vistaLogin.obtenerTituloPrincipalDeLaPagina();
        assertThat("Iniciar Sesión", equalToIgnoringCase(texto));
    }

    @Test
    void deberiaIniciarSesionCorrectamente() {
        iniciarSesion();

        String url = vistaLogin.obtenerURLActual();

        assertThat(url, containsStringIgnoringCase("/spring/mis_datos"));
    }

    @Test
    void deberiaNavegarAMiDeudaDesdeBotonMiDeudaUbicadoEnMisDatos() {
        iniciarSesion();

        vistaLogin.darClickEnBotonMiDeuda();
        String url = vistaLogin.obtenerURLActual();

        assertThat(url, containsStringIgnoringCase("/spring/mi_saldo"));
    }

    @Order(4)
    @Test
    void deberiaDecirMiSaldoEnElTituloDeLaPagina() {
        iniciarSesion();
        vistaLogin.darClickEnBotonMiDeuda();

        String texto = vistaLogin.obtenerTituloPrincipalDeLaPaginaMiSaldo();

        assertThat(texto, equalToIgnoringCase("Mi Saldo"));
    }

    @Order(5)
    @Test
    void deberiaNavegarAMiDeudaDesdeBotonMiDeudaUbicadoEnMisDatosYClickearElPrimerBotonPagar() {
        iniciarSesion();
        vistaLogin.darClickEnBotonMiDeuda();

        Long id = 1L;
        vistaLogin.darClickEnElBotonPagar(id);

        String url = vistaLogin.obtenerURLActual();

        assertThat(url, containsStringIgnoringCase("/spring/pagar/1"));
    }

    @Test
    void deberiaNavegarALaPaginaCotizacion(){
        iniciarSesion();
        vistaLogin.darClickEnElLinkCotizacion();

        String url = vistaLogin.obtenerURLActual();
        assertThat(url, containsStringIgnoringCase("/spring/cotizacion"));
    }

    @Test
    void deberiaNavegarALaPaginaCotizacionYClickearElBotonCotizarAuto(){
        iniciarSesion();
        pedirCotizacionAuto();

        String url = vistaLogin.obtenerURLActual();
        assertThat(url, containsStringIgnoringCase("/spring/paso_uno"));
    }

    @Test
    void deberiaNavegarALaPaginaCotizacionYClickearElBotonCotizarAutoLuegoClickearElBotonSiguiente(){
        iniciarSesion();
        pedirCotizacionAuto();
        vistaLogin.darClickEnElBotonSiguienteDePasoUno();

        String url = vistaLogin.obtenerURLActual();
        assertThat(url, containsStringIgnoringCase("/spring/guardar_paso_uno?nombre=Fiat"));
    }

    @Test
    void deberiaSeleccionarElTipoDeCobertura(){
        iniciarSesion();
        pedirCotizacionAuto();
        avanzarHastaTipoCoberturaYCotizar("1");

        String url = vistaLogin.obtenerURLActual();
        assertThat(url, containsStringIgnoringCase("/spring/guardar_paso_tres?nombre=Fiat&modelo=Palio&anio=2001&type=1"));
    }


    @Test
    void deberiaCrearUnaPoliza(){
        iniciarSesion();
        pedirCotizacionAuto();
        avanzarHastaTipoCoberturaYCotizar("1");
        vistaLogin.darClickEnElBotonCrearPoliza();

        String url = vistaLogin.obtenerURLActual();

        assertThat(url, containsStringIgnoringCase("/spring/polizas"));
    }

    @Test
    void deberiaEnviarFormularioDeContacto(){
        vistaLogin.darClickEnLinkContacto();
        enviarFormularioDeContacto();

        String url = vistaLogin.obtenerURLActual();
        String message = vistaLogin.obtenerMensajeExito();

        assertThat(url, containsStringIgnoringCase("/spring/contact"));
        assertThat(message, equalToIgnoringCase("¡Formulario enviado correctamente!"));

    }

    private void iniciarSesion() {
        vistaLogin.escribirEmail("example@example.com");
        vistaLogin.escribirClave("111");
        vistaLogin.darClickEnIniciarSesion();
    }

    private void pedirCotizacionAuto() {
        vistaLogin.darClickEnElLinkCotizacion();
        vistaLogin.darClickEnElBotonCotizarAuto();
    }

    private void avanzarHastaTipoCoberturaYCotizar(String value){
        vistaLogin.darClickEnElBotonSiguienteDePasoUno();
        vistaLogin.darClickEnElBotonSiguienteDeGuardarPasoUno();
        vistaLogin.seleccionarTipoDeCobertura(value);
        vistaLogin.darClickEnElBotonCotizar();
    }

    private void enviarFormularioDeContacto(){
        vistaLogin.escribirNombreEnContacto("Facundo");
        vistaLogin.escribirEmailEnContacto("facu@gmail.com");
        vistaLogin.escribirTelefonoEnContacto("1154784952");
        vistaLogin.escribirAsuntoEnContacto("Cotizacion");
        vistaLogin.escribirMensajeEnContacto("Buenas tardes, me gustaría solicitar cotizacion para automotor");
        vistaLogin.darClickEnBotonEnviarContacto();
    }

}
