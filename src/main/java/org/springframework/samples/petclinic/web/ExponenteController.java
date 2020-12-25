package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Exponente;
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

    private static final String VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM = "exponentes/crearExponentes";
    @GetMapping(value="/nuevo")
    public String guardarExponentes(ModelMap modelMap){
        //String vista="eventos/{eventoId}/actividades/nuevo";
        modelMap.addAttribute("exponentes", new ArrayList<Exponente>());
        return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value="/nuevo")
    public String guardarExponentes(@Valid List<Exponente> exponentes,@PathVariable("evento_id") int eventoId,@PathVariable("actividad_id") int actividadInt, BindingResult resultado, ModelMap modelMap){
      //  Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("exponentes", exponentes);
            return VIEWS_EXPONENTE_CREATE_OR_UPDATE_FORM;
        }else {
            //Seguir a partir de aqui intentando meter la lista de exponentes, ya solo queda de aqui hacia abajo y ver porque no se añade la lista
          
            return "redirect:/";
        }
        
        
    }
}
