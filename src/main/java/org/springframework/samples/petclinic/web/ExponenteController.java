package org.springframework.samples.petclinic.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.ExponenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/eventos/{evento_id}/actividades/{actividad_id}")
public class ExponenteController {

    @Autowired 
    private ExponenteService exponenteService;
    @Autowired
    private EventoService eventoService;

    @Autowired 
    private ActividadService actividadService;
    private static final String VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM = "exponentes/crearExponentes";

    @GetMapping(value="/nuevo")
    public String guardarExponentes(ModelMap modelMap, @PathVariable("actividad_id") int actividadInt, @PathVariable("evento_id") int evento_id){
        Exponente nuevoExponente = new Exponente();
        Actividad actividad = actividadService.findById(actividadInt);
        modelMap.addAttribute("evento", eventoService.findEventoById(evento_id));
        modelMap.addAttribute("exponente", nuevoExponente);
        modelMap.addAttribute("actividad", actividad);
        modelMap.addAttribute("listaExponentes", actividad.getExponentes());

        return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value="/nuevo")
    public String guardarExponentes(@Valid Exponente exponente,@PathVariable("actividad_id") int actividadInt,@PathVariable("evento_id") int evento_id,
     BindingResult resultado, ModelMap modelMap){

        if(resultado.hasErrors()){
            modelMap.addAttribute("evento", eventoService.findEventoById(evento_id));
            modelMap.addAttribute("exponente", exponente);
            modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());
            return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
        }else {
            exponenteService.anadirExponente(actividadService.findById(actividadInt), exponente);
            return guardarExponentes(modelMap, actividadInt, evento_id);
        } 
    }

    @GetMapping(value = "/{exponenteId}/borrarExponente")
    public String borrarExponente(@PathVariable("exponenteId") int exponenteId,@PathVariable("actividad_id") int actividadInt,@PathVariable("evento_id") int evento_id, ModelMap modelMap){
    Actividad actividad = actividadService.findById(actividadInt);
    Exponente exponente = exponenteService.encuentraExponente(exponenteId);
    modelMap.addAttribute("evento", eventoService.findEventoById(evento_id));
    modelMap.addAttribute("actividad", actividad);
    modelMap.addAttribute("exponente", exponente);
    modelMap.addAttribute("listaExponentes", actividad.getExponentes());
    exponenteService.eliminaExponente(exponente, actividad);
    return "redirect:/eventos/{evento_id}/actividades/{actividad_id}/nuevo";


}
}
