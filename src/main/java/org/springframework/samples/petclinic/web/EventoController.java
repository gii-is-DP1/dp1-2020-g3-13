package org.springframework.samples.petclinic.web;

import java.util.Map;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/eventos")
public class EventoController {

    private static final String VIEWS_EVENTO_CREATE_OR_UPDATE_FORM = "eventos/editarEvento";

    @Autowired
    private EventoService eventoService;

    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listadoEventos(ModelMap modelMap){
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        String vista = "eventos/";
        if(!(clienteService.findClienteByUsuario(usuario)==null) || usuario=="anonymousUser"){
            vista = "eventos/listadoEventos";
        }else if(!(organizacionService.findOrganizacionByUsuario(usuario)==null)){
            vista = "eventos/listadoEventosOrganizacion";
        }else{
            vista = "eventos/listadoEventosAdmin";
        }
        
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

    @GetMapping(value="/new")
    public String crearEvento(ModelMap modelMap){
        String vista="eventos/editarEvento";
        modelMap.addAttribute("evento", new Evento());
        return vista;
    }
    
    @PostMapping(value="/new")
    public String guardarEvento(@Valid Evento evento, BindingResult resultado, ModelMap modelMap){
        Organizacion org = this.organizacionService.findOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        if(resultado.hasErrors()){
            modelMap.addAttribute("evento", evento);
            return "eventos/editarEvento";
        }else {
            evento.setOrganizacion(org);
            eventoService.save(evento);
            modelMap.addAttribute("message", "Evento guardado satisfactoriamente!");
            return "redirect:/eventos/";
        }
        
        
    }

    @GetMapping(value = "/{eventoId}/edit")
	public String initUpdateEventoForm(@PathVariable("eventoId") int eventoId, ModelMap modelMap) {
		Evento evento = this.eventoService.findEventoById(eventoId);
		modelMap.addAttribute(evento);
		return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
	}
    @PostMapping(value = "/{eventoId}/edit")
	public String processUpdateEventoForm(@Valid Evento evento, BindingResult result,
			@PathVariable("eventoId") int eventoId) {
		if (result.hasErrors()) {
			return VIEWS_EVENTO_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.eventoService.modifyEvento(evento, this.eventoService.findEventoById(eventoId));
			return "redirect:/eventos/{eventoId}";
		}


    }
    @GetMapping(value = "/delete/{eventoId}")
    public String deleteEvento(@PathVariable("eventoId") int eventoId, ModelMap model){
        this.eventoService.delete(eventoService.findEventoById(eventoId));
        return "redirect:/eventos";
    }
    




    

}
