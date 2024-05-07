package org.aseguradora.controllers;

import org.aseguradora.entity.Car;
import org.aseguradora.repositories.CarRepository;
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

    @PostMapping("/first-select")
    public String firstSelect(@ModelAttribute("marcaSeleccionada")String name, Model model){
        List<String> listadoPrueba = carRepository.findByModelWithParameter("Honda");
        model.addAttribute("listado_pruebas", listadoPrueba);


        return "";
    }



}
