package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @GetMapping
    public String listadoActividades(ModelMap modelMap){
        String vista = "actividades/EventoLista";
        Iterable<Actividad> actividades = actividadService.findAll();
        modelMap.addAttribute("actividades", actividades);
        return vista;
    }
}
