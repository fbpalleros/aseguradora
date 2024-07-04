package org.aseguradora.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InstitucionalControllerTest {

    private InstitucionalController institucionalController;

    @BeforeEach
    public void init(){
        this.institucionalController = new InstitucionalController();
    }

    @Test
    public void queRetorneLaVistaInstitucional(){
        ModelAndView mav = this.institucionalController.showContact();

        assertThat(mav.getViewName(), equalToIgnoringCase("institucional"));
    }
}
