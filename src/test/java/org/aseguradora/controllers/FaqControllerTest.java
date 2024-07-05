package org.aseguradora.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class FaqControllerTest {

    private FaqController faqController;

    @BeforeEach
    public void init(){
        this.faqController = new FaqController();
    }
    
    
    @Test
    public void queRetorneLaVistaFaq(){
        ModelAndView mav = this.faqController.faqs();

        assertThat(mav.getViewName(), equalToIgnoringCase("site/faq"));
 }

}  