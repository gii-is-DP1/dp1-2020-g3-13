package org.springframework.samples.petclinic.web;

import javax.print.DocFlavor.STRING;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("eventos/{evento_id}/actividades")
public class ActividadController {

    private static final String VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM = "actividades/crearActividad";
    

    @Autowired
    private ActividadService actividadService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private LugarRealizacionService lugarRealizacionService;

    @GetMapping
    public String listadoActividades(ModelMap modelMap){
        String vista = "actividades/EventoLista";
        Iterable<Actividad> actividades = actividadService.findAll();
        modelMap.addAttribute("actividades", actividades);
        return vista;
    }
    
    @GetMapping(value="/nuevo")
    public String crearActividad(ModelMap modelMap){
        //String vista="eventos/{eventoId}/actividades/nuevo";
        //usuarioService.findAll().iterator().forEachRemaining(actualList::add);
        Iterable<LugarRealizacion> lugaresRealizacion = lugarRealizacionService.findAll();
        List<LugarRealizacion> lugaresLista= new ArrayList<LugarRealizacion>();
        lugaresRealizacion.forEach(lugaresLista::add);
        List<Integer> listaIds = lugarRealizacionService.listaIdLugarRealizacion();
        modelMap.addAttribute("listaId", listaIds);
        modelMap.addAttribute("lugaresRealizacion", lugaresLista);
        modelMap.addAttribute("actividad", new Actividad());
        return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping(value="/nuevo")
    public String guardarActividad(@Valid Actividad actividad,@PathVariable("evento_id") int eventoId ,int lugarRealizacionId,BindingResult resultado, ModelMap modelMap){
        Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("actividad", actividad);
            return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
        }else {
            actividadService.anadirActividadAEvento(evento, actividad);
            
            actividadService.AñadirLugarRealizacionActividad(actividad,lugarRealizacionId);
            actividadService.guardarActividad(actividad);
            String vistaExponente = "redirect:/eventos/{evento_id}/actividades/" + actividad.getId()+"/nuevo";
            modelMap.addAttribute("message", "Actividad guardada satisfactoriamente!");
            return vistaExponente;
        }
        
        
    }


}
