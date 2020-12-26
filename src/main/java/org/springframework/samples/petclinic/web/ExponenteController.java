package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.ExponenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/eventos/{evento_id}/actividades/{actividad_id}")
public class ExponenteController {

    @Autowired 
    private ExponenteService exponenteService;

    @Autowired ActividadService actividadService;
    private static final String VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM = "exponentes/crearExponentes";
    @GetMapping(value="/nuevo")
    public String guardarExponentes(ModelMap modelMap, @PathVariable("actividad_id") int actividadInt){
        //String vista="eventos/{eventoId}/actividades/nuevo";
        Exponente nuevoExponente = new Exponente();

     //   ModelAndView modelMap = new ModelAndView("exponentes");
     System.out.println(actividadService.findById(actividadInt).getExponentes().size());
     modelMap.addAttribute("exponente", nuevoExponente);
     modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());

        return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value="/nuevo")
    public String guardarExponentes(@Valid Exponente exponente,@PathVariable("evento_id") int eventoId,@PathVariable("actividad_id") int actividadInt, BindingResult resultado, ModelMap modelMap){
      //  Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("exponentes", exponente);
            modelMap.addAttribute("listaExponentes", actividadService.findById(actividadInt).getExponentes());
            return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
        }else {
            exponenteService.anadirExponente(actividadService.findById(actividadInt), exponente);
            //Seguir a partir de aqui intentando meter la lista de exponentes, ya solo queda de aqui hacia abajo y ver porque no se añade la lista
          
            return guardarExponentes(modelMap, actividadInt);
        }
        
        
    }
}
