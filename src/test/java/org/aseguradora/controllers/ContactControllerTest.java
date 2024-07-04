package org.aseguradora.controllers;

import org.aseguradora.entity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactControllerTest {

    private ContactController contactController;

    @BeforeEach
    public void init(){
        this.contactController = new ContactController();
    }

    @Test
    public void queRetorneElFormularioDeContacto(){
        ModelAndView mav = this.contactController.showContact();

        assertThat(mav.getViewName(), equalToIgnoringCase("contact/contactForm"));
    }

    @Test
    public void queSeEnvieElFormularioDeContacto(){
        Contact contact = new Contact("Pedro", "pedro@gmail.com", "1164852678", "Cotizacion", "Solicito cotizacion");

        String post = this.contactController.submitContact(contact, new ModelMap());

        assertThat(post, equalToIgnoringCase("contact/resultado"));
    }

}
