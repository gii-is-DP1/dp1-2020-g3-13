package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
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
        Iterable<Evento> eventos = eventoService.findAll();
        if(!(clienteService.findClienteByUsuario(usuario)==null) || usuario=="anonymousUser"){
            eventos = eventoService.findAll();
            vista = "eventos/listadoEventos";
        }else if(!(organizacionService.encuentraOrganizacionByUsuario(usuario)==null)){
            eventos = eventoService.listadoEventosDeOrganizacion(organizacionService.encuentraOrganizacionByUsuario(usuario).getId());
            vista = "eventos/listadoEventosOrganizacion";
        }else{
            eventos = eventoService.findAll();
            vista = "eventos/listadoEventosAdmin";
        }
        

        modelMap.addAttribute("eventos", eventos);
        return vista;
    }

    @GetMapping("/{eventosId}")
    public ModelAndView showEvento(@PathVariable("eventosId") int eventosId) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("eventos/detallesEvento");
        if(this.eventoService.findEventoById(eventosId).getFechaInicio().isBefore(LocalDate.now())){
            mav.setViewName("eventos/eventoFinalizado");
            return mav;
        }
        else{
            if(!(clienteService.findClienteByUsuario(usuario)==null)){
                mav.setViewName("eventos/detallesEventoCliente");
            } 
        }
        mav.addObject(this.eventoService.findEventoById(eventosId));
            return mav;
    }
    @GetMapping("/{eventosId}/añadirEventosFavoritos")
    public String anadirEventosAFavorito(@PathVariable("eventosId") int eventosId, ModelMap modelMap) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Evento evento =eventoService.findEventoById(eventosId);
      //  ModelAndView mav = new ModelAndView("eventos/listadoEventos");
        eventoService.anadirEventoAFav(evento, usuario);
        System.out.println("AQUI ENTRA=====================================================");
        eventoService.save(evento);
        modelMap.addAttribute("message", "Evento añadido a favoritos!");
        return "redirect:/eventos/";
    }

    // @PostMapping("/{eventosId}/añadirEventosFavoritos")
    // public String anadirEventosAFavorito(@PathVariable("eventosId") int eventosId,BindingResult resultado, ModelMap modelMap) {
    //     String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
    //     Evento evento =eventoService.findEventoById(eventosId);
    //     if(resultado.hasErrors()){
    //         System.out.println("AQUI ENTRA PRIMERO=====================================================");
    //         modelMap.addAttribute("evento", evento);
    //         return "eventos/listadoEventos";
    //     }else {
    //         eventoService.anadirEventoAFav(evento, usuario);
    //         System.out.println("AQUI ENTRA=====================================================");
    //         eventoService.save(evento);
    //         modelMap.addAttribute("message", "Evento añadido a favoritos!");
    //         return "redirect:/eventos/";
    //     }


    // }

    @GetMapping(value="/nuevo")
    public String crearEvento(ModelMap modelMap){
        String vista="eventos/editarEvento";
        modelMap.addAttribute("evento", new Evento());
        return vista;
    }
    
    @PostMapping(value="/nuevo")
    public String guardarEvento(@Valid Evento evento, BindingResult resultado, ModelMap modelMap){
        Organizacion org = this.organizacionService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
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
