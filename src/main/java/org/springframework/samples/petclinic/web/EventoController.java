package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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
    @GetMapping("/{eventosId}")
	public ModelAndView showEvento(@PathVariable("eventosId") int eventosId) {
		ModelAndView mav = new ModelAndView("eventos/detallesEvento");
		mav.addObject(this.eventoService.findEventoById(eventosId));
		return mav;
    }

}
