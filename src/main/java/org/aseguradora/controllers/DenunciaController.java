package org.aseguradora.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.aseguradora.entity.Denuncia;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.DenunciaService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DenunciaController {

	private PolicyService policyService;
	
	private DenunciaService denunciaService;
	
	 @Autowired
	public DenunciaController(PolicyService policyService, DenunciaService denunciaService) {
		 this.policyService = policyService;
		 this.denunciaService = denunciaService;
	 }
	 
    @GetMapping("/denuncia")
    public ModelAndView verPolizas(){
        ModelMap model = new ModelMap();
        //model.addAttribute("denuncia", new DenunciaVehiculo());
        model.put("polizas", policyService.findAll());
        return new ModelAndView("denuncia", model);
    }
    
    @GetMapping("/denuncia/{id}")
    public ModelAndView verPolizas(@PathVariable("id") Long id) {
        Policy policy = policyService.findById(id);
        ModelMap model = new ModelMap();
        ModelAndView modelAndView;
        
        Denuncia denuncia = new Denuncia();
        denuncia.setPolicy(policy);
        
		model.put("denuncia", denuncia);
        
        switch (policy.getInsurance().getInsuranceType()) {
        	case HOGAR: 
         		modelAndView= new ModelAndView("denuncia/denunciahogar", model);
        		break;
        	case AUTOMOTOR:
                 modelAndView = new ModelAndView("denuncia/denunciavehiculo", model);
                break;
        	case PERSONA:
                 modelAndView = new ModelAndView("denuncia/denunciapersona", model);
                break;
            default: 
                modelAndView = new ModelAndView("denuncia");
        }
        
        return modelAndView;
    }
    
    @SuppressWarnings("deprecation")
	@PostMapping("/submitForm")
    public String submitForm(@ModelAttribute Denuncia denuncia, ModelMap model) throws ParseException {

    	denunciaService.save(denuncia);	
    	
    	model.addAttribute("message", "Gracias por elegirnos");
    	
        return "resultadoformulario";
    }
}

    
