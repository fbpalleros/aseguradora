package org.aseguradora.controllers;

import org.aseguradora.entity.Car;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.CarRepository;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CotizacionController {

    @Autowired
    private CarRepository carRepository; //esta mal , esta llamando un repositorio deberia llamar una interface

//    @GetMapping("/cotizador")
//    public ModelAndView cotizar(){
//        ModelMap model = new ModelMap();
//        return new ModelAndView("cotizador", model);
//    }

    @Autowired
    private PolicyService policyService;



    @GetMapping("/modeloAuto")
    public String buscar(Model model){

        List<Car> listadocar = carRepository.findAll();
        List<String> listadoMarcas = carRepository.findByName();
        List<String> listadoModelos = carRepository.findByModel();

        model.addAttribute("listado_autos" , listadocar );
        model.addAttribute("listado_marcas", listadoMarcas);
        model.addAttribute("listado_modelos", listadoModelos);
        return "cotizador";
    }

//    @PostMapping("/first-select")
//    public String firstSelect(@ModelAttribute("marcaSeleccionada")String name, Model model){
//        List<String> listadoPrueba = carRepository.findByModelWithParameter("Honda");
//        model.addAttribute("listado_pruebas", listadoPrueba);
//
//
//        return "";
//    }

    @PostMapping("/cotizar")
    public String cotizarProducto(@ModelAttribute("policy") Policy policy, Model model) {
        //  realizar el cálculo de la cotización
        Customer customer = new Customer();
        customer.setName("fer");
        customer.setEmail("fer@gmail.com");
        policy.setCustomerId(customer);
        Insurance insurance = new Insurance();
        insurance.setId(1L);
        policy.setInsuranceId(insurance);
        Integer precioCotizado = (int) (policy.getCoverage() * 1.2);
        policy.setCoverage(precioCotizado);
        policyService.save(policy);
        model.addAttribute("precioCotizado", policy.getCoverage());
        return "resultado";
    }



}
