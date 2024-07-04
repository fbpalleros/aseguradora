package org.aseguradora.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FaqController {

    @GetMapping("/faq")
    public ModelAndView faq() {
        List<Section> faqs = Arrays.asList(
            new Section("Seguros Vehiculares", Arrays.asList(
                new Faq("¿Qué cubre el seguro vehicular?", "El seguro vehicular cubre daños materiales al vehículo, responsabilidad civil frente a terceros, robo del vehículo y daños personales al conductor y pasajeros."),
                new Faq("¿Qué tipos de coberturas existen para el seguro vehicular?", "Existen coberturas básicas, completas y adicionales. Las básicas suelen cubrir daños a terceros, mientras que las completas incluyen daños propios, robo y asistencia en carretera."),
                new Faq("¿Cómo se determina el costo del seguro vehicular?", "El costo se determina en función de factores como el modelo del vehículo, el año de fabricación, el uso del vehículo, el historial de conducción del asegurado y la cobertura seleccionada."),
                new Faq("¿Qué debo hacer en caso de un accidente de tráfico?", "En caso de accidente, debes contactar a tu aseguradora lo antes posible, informar de los detalles del accidente y seguir sus instrucciones para el proceso de reclamación."),
                new Faq("¿Puedo añadir conductores adicionales a mi póliza?", "Sí, generalmente puedes añadir conductores adicionales, pero esto puede afectar el costo de la póliza."),
                new Faq("¿Qué no cubre el seguro vehicular?", "Usualmente no cubre daños intencionados, desgaste natural, y conducción bajo la influencia de alcohol o drogas.")
            )),
            new Section("Seguros de Hogar", Arrays.asList(
                new Faq("¿Qué cubre el seguro de hogar?", "El seguro de hogar cubre daños a la estructura de la vivienda, contenido del hogar, responsabilidad civil frente a terceros y asistencia en caso de emergencias."),
                new Faq("¿Qué tipos de seguros de hogar existen?", "Existen seguros de hogar para propietarios, inquilinos y seguros específicos para casos de desastres naturales, incendios, robos, entre otros."),
                new Faq("¿Cómo se determina el valor de la cobertura del seguro de hogar?", "El valor de la cobertura se basa en el valor de reconstrucción de la vivienda y el valor de los bienes personales que desees asegurar."),
                new Faq("¿Qué debo hacer en caso de un siniestro en mi hogar?", "Debes informar a tu aseguradora inmediatamente, documentar los daños con fotos y proporcionar toda la información requerida para procesar la reclamación."),
                new Faq("¿El seguro de hogar cubre los daños por desastres naturales?", "Algunos seguros de hogar incluyen cobertura para desastres naturales, pero esto varía según la póliza y la aseguradora. Es importante revisar las condiciones específicas de tu póliza."),
                new Faq("¿Puedo asegurar objetos de valor específicos dentro de mi hogar?", "Sí, generalmente puedes añadir cobertura para objetos de valor específicos como joyas, obras de arte o equipos electrónicos.")
            )),
            new Section("Seguros Personales", Arrays.asList(
                new Faq("¿Qué cubre el seguro personal?", "El seguro personal puede incluir cobertura por accidentes, enfermedades graves, fallecimiento y gastos médicos."),
                new Faq("¿Cuál es la diferencia entre un seguro de vida y un seguro de salud?", "El seguro de vida ofrece una suma asegurada a los beneficiarios en caso de fallecimiento del asegurado. El seguro de salud cubre gastos médicos y hospitalarios en caso de enfermedad o accidente."),
                new Faq("¿Qué tipos de seguros personales existen?", "Existen seguros de vida, seguros de salud, seguros de accidentes personales y seguros de enfermedades graves, entre otros."),
                new Faq("¿Cómo se determina el costo del seguro personal?", "El costo se basa en factores como la edad, estado de salud, estilo de vida, ocupación y la cobertura seleccionada."),
                new Faq("¿Qué debo hacer si necesito hacer una reclamación en mi seguro personal?", "Debes contactar a tu aseguradora, proporcionar la documentación médica necesaria y seguir sus instrucciones para el proceso de reclamación."),
                new Faq("¿El seguro personal cubre enfermedades preexistentes?", "La cobertura de enfermedades preexistentes varía según la aseguradora y la póliza. Es importante revisar los términos y condiciones específicos de tu póliza.")
            ))
        );
        
        ModelMap model = new ModelMap();
        model.addAttribute("faqs", faqs);
        return new ModelAndView("site/faq", model);
    }

    public static class Section {
        private String title;
        private List<Faq> questions;

        public Section(String title, List<Faq> questions) {
            this.title = title;
            this.questions = questions;
        }

        // getters y setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Faq> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Faq> questions) {
            this.questions = questions;
        }
    }

    public static class Faq {
        private String question;
        private String answer;

        public Faq(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        // getters y setters
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
