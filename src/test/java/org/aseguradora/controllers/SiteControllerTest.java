package org.aseguradora.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SiteControllerTest {

    private SiteController siteController;

    @BeforeEach
    public void init(){
        this.siteController = new SiteController();
    }

    @Test
    public void queRetorneLaVistaProteccionDeDatos(){
        ModelAndView mav = this.siteController.proteccion();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/proteccion"));
    }

    @Test
    public void queRetorneLaVistaLibroDeQuejas(){
        ModelAndView mav = this.siteController.quejas();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/quejas"));
    }



}
