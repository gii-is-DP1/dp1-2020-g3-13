package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Sponsor;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.SponsorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("eventos/{evento_id}/sponsors")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private EventoService eventoService;

    private static final String VIEWS_SPONSOR_CREATE_OR_UPDATE_FORM = "sponsors/crearNuevoSponsor";

    @GetMapping
    public String listadoSponsors(ModelMap modelMap){
        String vista = "sponsors/listadoSponsors";
        Iterable<Sponsor> sponsors = sponsorService.findAll();
        modelMap.addAttribute("Sponsors", sponsors);
        return vista;
    }

    @GetMapping(value="/nuevo")
    public String crearSponsor(ModelMap modelMap){
        modelMap.addAttribute("sponsor", new Sponsor());
        return VIEWS_SPONSOR_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping(value="/nuevo")
    public String guardarSponsor(@Valid Sponsor sponsor,@PathVariable("evento_id") int eventoId ,BindingResult resultado, ModelMap modelMap){
        Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("sponsor", sponsor);
            return VIEWS_SPONSOR_CREATE_OR_UPDATE_FORM;
        }else {
            sponsorService.anadirSponsorAEvento(evento, sponsor);
            sponsorService.guardarSponsor(sponsor);
            String vista = "redirect:/eventos/{evento_id}";
            modelMap.addAttribute("message", "Actividad guardada satisfactoriamente!");
            return vista;
        }
    }
}