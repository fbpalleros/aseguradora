package org.aseguradora.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class DenunciaControllerTest {

    DenunciaController denunciaController = new DenunciaController();

    @Test
    public void queRetorneLaVistaDenuncia(){
        ModelAndView view = denunciaController.verPolizas();

        assertThat(view.getViewName(), equalToIgnoringCase("denuncia"));
    }
}
