package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listadoEventos(ModelMap modelMap){
        String vista = "eventos/listadoEventos";
        Iterable<Evento> eventos = eventoService.findAll();
        modelMap.addAttribute("eventos", eventos);
        return vista;
    }
}
