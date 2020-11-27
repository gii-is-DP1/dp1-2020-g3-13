package org.springframework.samples.petclinic.web;

import java.util.Map;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping(path="/new")
    public String crearEvento(ModelMap modelMap){
        String vista = "eventos/editarEvento";
        modelMap.addAttribute("eventos", new Evento());
        return vista;
    }

    @PostMapping(path="/save")
    public String guardarEvento(@Valid Evento evento, BindingResult resultado, ModelMap modelMap){
        String vista = "eventos/listadoEventos";
        if(resultado.hasErrors())
        {
            modelMap.addAttribute("evento", evento);
            return "eventos/editarEvento";
        }
        else
        {
            eventoService.save(evento);
            modelMap.addAttribute("message", "Evento creado correctamente");
        }
        return vista;
    }

    @GetMapping(path="/delete/{eventoId}")
    public String borrarEvento(@PathParam("eventoid") int eventoId, ModelMap modelMap){
        String vista="eventos/listadoEventos";;
        Optional<Evento> evento = eventoService.findEventoById(eventoId);
        if(evento.isPresent()){
            eventoService.delete(evento.get());
            modelMap.addAttribute("message", "Evento eliminado correctamente");
        }else{
            modelMap.addAttribute("message", "No se pudo encontrar el evento");
        }
        return vista;

    }




    

}
