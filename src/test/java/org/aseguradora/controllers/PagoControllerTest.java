package org.aseguradora.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class PagoControllerTest {


    PagoController pagoController = new PagoController();

    @Test
    public void queRetorneLaVistaPago(){
        Long id = 1L;
        ModelAndView view = pagoController.pagar(id);

        assertThat(view.getViewName(), equalToIgnoringCase("pago"));
    }
}
