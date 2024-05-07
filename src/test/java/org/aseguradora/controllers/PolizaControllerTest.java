package org.aseguradora.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class PolizaControllerTest {

    PolizaController polizaController = new PolizaController();

    @Test
    public void queRetorneLaVistaCotizacion(){
        ModelAndView view = polizaController.verPolizas();

        assertThat(view.getViewName(), equalToIgnoringCase("polizas"));
    }

}
