package org.springframework.samples.petclinic.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.ActividadService;
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
    private EventoRepository eventoRepository;

    @Autowired ActividadService actividadService;
    private static final String VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM = "exponentes/crearExponentes";
    private static final String VIEWS_EXPONENTE_SELECT_FORM = "exponentes/seleccionaExponente";
    
    @GetMapping(value="/seleccionaExponente")
    public String selectExponentes(ModelMap modelMap, @PathVariable("actividad_id") int actividadInt, @PathVariable("evento_id") int evento_id){
        Iterable<Exponente> exponentesDB = exponenteService.listaExponentes();
        Actividad actividad = actividadService.findById(actividadInt);
        modelMap.addAttribute("evento", eventoRepository.findById(evento_id).get());
        modelMap.addAttribute("actividad", actividad);
        modelMap.addAttribute("exponentesDB", exponentesDB);
        modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());

        return VIEWS_EXPONENTE_SELECT_FORM;
    }

    @PostMapping(value="/seleccionaExponente")
    public String selectExponentes(@PathVariable("evento_id") int eventoId,@PathVariable("actividad_id") int actividadInt,@PathVariable("evento_id") int evento_id, BindingResult resultado, ModelMap modelMap){
        if(resultado.hasErrors()){
            modelMap.addAttribute("evento", eventoRepository.findById(evento_id).get());
            modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());
            return VIEWS_EXPONENTE_SELECT_FORM;
        }else {
            return selectExponentes(modelMap, actividadInt, evento_id);
        } 
    }
    @GetMapping(value="/nuevo")
    public String guardarExponentes(ModelMap modelMap, @PathVariable("actividad_id") int actividadInt, @PathVariable("evento_id") int evento_id){
        Iterable<Exponente> exponentesDB = exponenteService.listaExponentes();
        Exponente nuevoExponente = new Exponente();
        Actividad actividad = actividadService.findById(actividadInt);
        modelMap.addAttribute("evento", eventoRepository.findById(evento_id).get());
        modelMap.addAttribute("exponente", nuevoExponente);
        modelMap.addAttribute("actividad", actividad);
        modelMap.addAttribute("exponentesDB", exponentesDB);
        modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());

        return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value="/nuevo")
    public String guardarExponentes(@Valid Exponente exponente,@PathVariable("evento_id") int eventoId,@PathVariable("actividad_id") int actividadInt,@PathVariable("evento_id") int evento_id, BindingResult resultado, ModelMap modelMap){
        if(resultado.hasErrors()){
            modelMap.addAttribute("evento", eventoRepository.findById(evento_id).get());
            modelMap.addAttribute("exponente", exponente);
            modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());
            return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
        }else {
            exponenteService.anadirExponente(actividadService.findById(actividadInt), exponente);
            return guardarExponentes(modelMap, actividadInt, evento_id);
        } 
    }
}
