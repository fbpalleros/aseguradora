package org.aseguradora.punta_a_punta.vistas;

import com.microsoft.playwright.Page;
import org.aseguradora.punta_a_punta.VistaWeb;

public class VistaLogin extends VistaWeb {


    public VistaLogin(Page page) {
        super(page);
        page.navigate("localhost:8080/spring/login");
    }

    public String obtenerTituloPrincipalDeLaPagina() {
        return this.obtenerTextoDelElemento("h2.title");
    }

    public void escribirEmail(String email) {
        this.escribirEnElElemento("#exampleInputEmail1", email);
    }

    public void escribirClave(String clave) {
        this.escribirEnElElemento("#exampleInputPassword1", clave);
    }

    public void darClickEnIniciarSesion() {
        this.darClickEnElElemento("button.btn.btn-secondary");
    }

    public void darClickEnBotonMiDeuda() {
        this.darClickEnElElemento("#buttonMiDeuda");
    }

    public String obtenerTituloPrincipalDeLaPaginaMiSaldo() {
        return this.obtenerTextoDelElemento("h2.text-center");
    }

    public void darClickEnElBotonPagar(Long id) {
        this.darClickEnElElemento("#button_pagar_" + id);
    }

    public void darClickEnElLinkCotizacion() {
        this.darClickEnElElemento("#link_cotizacion");
    }

    public void darClickEnElBotonCotizarAuto() {
        this.darClickEnElElemento("#boton_cotizar_auto");
    }

    public void darClickEnElBotonSiguienteDePasoUno() {
        this.darClickEnElElemento("#boton_siguiente_paso_uno");
    }

    public void darClickEnElBotonSiguienteDeGuardarPasoUno() {
        this.darClickEnElElemento("#boton_siguiente_paso_dos");
    }

    public void seleccionarTipoDeCobertura(String value) {
        this.seleccionarOption("#tipo_cobertura", value);
    }

    public void darClickEnElBotonCotizar() {
        this.darClickEnElElemento("#cotizar_auto");
    }

    public void darClickEnElBotonCrearPoliza() {
        this.darClickEnElElemento("#boton_crear_poliza_auto");
    }

    public void darClickEnLinkContacto() {
        this.darClickEnElElemento("#link_contacto");
    }

    public void escribirNombreEnContacto(String nombre) {
        this.escribirEnElElemento("#nombre", nombre);
    }

    public void escribirEmailEnContacto(String email) {
        this.escribirEnElElemento("#email", email);
    }

    public void escribirTelefonoEnContacto(String telefono){
        this.escribirEnElElemento("#telefono", telefono);
    }

    public void escribirAsuntoEnContacto(String asunto){
        this.escribirEnElElemento("#asunto", asunto);
    }

    public void escribirMensajeEnContacto(String mensaje){
        this.escribirEnElElemento("#mensaje", mensaje);
    }

    public void darClickEnBotonEnviarContacto(){
        this.darClickEnElElemento("#enviar_contacto");
    }

    public String obtenerMensajeExito(){
        return this.obtenerTextoDelElemento("#mensaje_exito_contacto");
    }
    public void darClickEnElBotonCerrarSesion() {
        this.darClickEnElElemento("#cerrar_sesion");
    }


}
