package org.aseguradora.controllers;

import org.aseguradora.entity.dto.AlmacenarDTO;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CarService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CotizacionController {

    private PolicyService policyService;

    private CustomerService customerService;

    private InsuranceService insuranceService;

    private CarService carService;

    @Autowired
    public CotizacionController(CarService carService, InsuranceService insuranceService, CustomerService customerService, PolicyService policyService) {
        this.carService = carService;
        this.insuranceService = insuranceService;
        this.customerService = customerService;
        this.policyService = policyService;
    }

    @GetMapping("/paso_uno")
    public ModelAndView vistaPasoUno() {
        ModelMap model = new ModelMap();
        AlmacenarDTO almacenar = new AlmacenarDTO();
        List<String> names = carService.findDistinctName();
        model.put("names", names);
        model.put("almacenar", almacenar);
        return new ModelAndView("paso_uno", model);
    }

    @GetMapping("/guardar_paso_uno")
    public ModelAndView guardarPasoUno(@ModelAttribute("almacenar") AlmacenarDTO almacenar, ModelMap model) {
        List<String> modelos = carService.findDistinctModelByName(almacenar.getNombre());
        model.put("modelos", modelos);

        return new ModelAndView("paso_dos", model);
    }

    @GetMapping("/guardar_paso_dos")
    public ModelAndView guardarPasoDos(@ModelAttribute("almacenar") AlmacenarDTO almacenar, ModelMap model) {
        List<Integer> years = carService.findDistinctByNameAndModel(almacenar.getNombre(), almacenar.getModelo());
        model.put("years", years);

        return new ModelAndView("paso_tres", model);

    }

    @GetMapping("/guardar_paso_tres")
    public ModelAndView guardarPasoTres(@ModelAttribute("almacenar") AlmacenarDTO almacenar, ModelMap model) {
        Double precio = carService.findPrice(almacenar.getNombre(), almacenar.getModelo(), almacenar.getAnio());

        Double cotizacion = precio * 1.20 / 6;

        almacenar.setPrecio(precio);
        almacenar.setCotizacion(cotizacion);

        model.put("almacenar", almacenar);

        return new ModelAndView("resultado_final", model);
    }

    @PostMapping("/crear_poliza")
    public ModelAndView cotizarAuto(@ModelAttribute("almacenar") AlmacenarDTO almacenar, RedirectAttributes flash, HttpServletRequest request) {

        Policy policy = new Policy();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {

            policy.setCustomer(customer);
            Insurance insurance = insuranceService.findById(1L);
            policy.setInsurance(insurance);
            policy.setCoverage(almacenar.getCotizacion());
            policyService.save(policy);

            flash.addFlashAttribute("success", "Ha generado una nueva póliza!");
        return new ModelAndView("redirect:/polizas");
        }

        return new ModelAndView("redirect:/login");
    }


}
