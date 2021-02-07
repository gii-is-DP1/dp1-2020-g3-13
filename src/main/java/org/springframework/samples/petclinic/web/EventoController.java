package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.repository.EventoRepository;
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
    private EventoRepository eventoRepo;

    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listadoEventos(ModelMap modelMap) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        String vista = "eventos/";
        if (!(clienteService.findClienteByUsuario(usuario) == null) || usuario == "anonymousUser") {
            Iterable<Evento> eventos = eventoService.encuentraTodosPublicos();
            modelMap.addAttribute("eventos", eventos);
            vista = "eventos/listadoEventos";
        } else if (!(organizacionService.encuentraOrganizacionByUsuario(usuario) == null)) {
            Iterable<Evento> eventos = eventoService
                    .listadoEventosDeOrganizacion(organizacionService.encuentraOrganizacionByUsuario(usuario).getId());
            modelMap.addAttribute("eventos", eventos);
            vista = "eventos/listadoEventosOrganizacion";
        } else {
            Iterable<Evento> eventos = eventoService.findAll();
            modelMap.addAttribute("eventos", eventos);
            vista = "eventos/listadoEventosAdmin";
        }

        return vista;
    }

    @GetMapping("/{eventosId}")
    public ModelAndView showEvento(@PathVariable("eventosId") int eventosId) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("eventos/detallesEvento");
        Evento evento = this.eventoService.findEventoById(eventosId);
        if (evento.getFechaInicio().isBefore(LocalDate.now())) {
            mav.setViewName("eventos/eventoFinalizado");
            return mav;
        } else {
            if (!(clienteService.findClienteByUsuario(usuario) == null)) {
                mav.setViewName("eventos/detallesEventoCliente");
            } else {
                Organizacion org = organizacionService.encuentraOrganizacionByUsuario(usuario);
                if (!(organizacionService.encuentraOrganizacionByUsuario(usuario) == null)) {
                    if (evento.getOrganizacion() != org) {
                        mav.setViewName("eventos/organizacionSinPermiso");
                    }
                }
            }
        }
        mav.addObject("sponsors", this.eventoService.getSponsors(eventosId));
        mav.addObject(this.eventoService.findEventoById(eventosId));
        mav.addObject("actividades", this.eventoService.getActividades(eventosId));
        return mav;
    }

    @GetMapping("/{eventosId}/añadirEventosFavoritos")
    public String anadirEventosAFavorito(@PathVariable("eventosId") int eventosId, ModelMap modelMap) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Evento evento = eventoService.findEventoById(eventosId);
        eventoService.anadirEventoAFav(evento, usuario);
        eventoService.save(evento);
        modelMap.addAttribute("message", "Evento añadido a favoritos!");
        return "redirect:/eventos/";
    }

    @GetMapping("/{eventosId}/hacerPublico")
    public String hacerEventoPublico(@PathVariable("eventosId") int eventosId, ModelMap modelMap) {
        Evento evento = eventoService.findEventoById(eventosId);

        if (eventoRepo.getActividades(evento.getId()).size() != 0) {
            eventoService.hacerPublico(eventosId);
            // ModelAndView mav = new ModelAndView("eventos/listadoEventos");
            modelMap.addAttribute("message", "Evento añadido a favoritos!");
            return "redirect:/eventos/{eventosId}";
        } else {
            return "redirect:/eventos/";
        }
    }

    @GetMapping(value = "/nuevo")
    public String crearEvento(ModelMap modelMap) {
        String vista = "eventos/editarEvento";
        List<TipoEvento> tipoEventos = Arrays.asList(TipoEvento.values());
        modelMap.addAttribute("tipoEvento", tipoEventos);
        modelMap.addAttribute("evento", new Evento());
        return vista;
    }

    @PostMapping(value = "/nuevo")
    public String guardarEvento(@Valid Evento evento, BindingResult resultado, ModelMap modelMap) {
        Organizacion org = this.organizacionService
                .encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        if (resultado.hasErrors()) {
            List<TipoEvento> tipoEventos = Arrays.asList(TipoEvento.values());
            modelMap.addAttribute("tipoEvento", tipoEventos);
            modelMap.addAttribute("evento", evento);
            return "eventos/editarEvento";
        } else {
            evento.setOrganizacion(org);
            evento.setEsPublico(false);
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
        } else {
            this.eventoService.modifyEvento(evento, this.eventoService.findEventoById(eventoId));
            return "redirect:/eventos/{eventoId}";
        }

    }

    @GetMapping(value = "/{eventoId}/delete")
    public String deleteEvento(@PathVariable("eventoId") int eventoId, ModelMap model) {
        Organizacion org = this.organizacionService
                .encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        Evento evento = this.eventoService.findEventoById(eventoId);
        if ((org == evento.getOrganizacion()
                || SecurityContextHolder.getContext().getAuthentication().getName() == "admin")
                && !evento.getEsPublico()) {
            this.eventoService.borraSponsor(eventoId);
            this.eventoService.delete(evento);
        } else {
            return "eventos/organizacionSinPermiso";
        }

        return "redirect:/eventos";
    }

}
