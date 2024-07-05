package org.aseguradora.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

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

    @Test
    public void queRetorneLaVistaInstitucional(){
        ModelAndView mav = this.siteController.institucional();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/institucional"));
    }

    public void queRetorneLaVistaPoliticas(){
        ModelAndView mav = this.siteController.politicas();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/politicas"));
    }
    
    public void queRetorneLaVistaTerminos(){
        ModelAndView mav = this.siteController.terminos();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/terminos"));
    }
    
}
