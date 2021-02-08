package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.AlquilerEspacioService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final String VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES = "actividades/EventoLista";
    private static final String VIEW_ALQUILAR_ESPACIO = "alquilerEspacio/alquilerEspacio";
    private static final String VIEW_ACTIVIDAD_DETALLES = "actividades/detallesActividad";

    @Autowired
    private ActividadService actividadService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private LugarRealizacionService lugarRealizacionService;
    @Autowired
    private AlquilerEspacioService alquilerService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private OrganizacionService orgService;


    @GetMapping
    public String listadoActividades(ModelMap modelMap){
        String vista = VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES;
        Iterable<Actividad> actividades = actividadService.findAll();
        modelMap.addAttribute("actividades", actividades);
        return vista;
    }

    @GetMapping("/{actividadId}")
    public String detallesActividad(ModelMap model,@PathVariable("evento_id") int eventoId, @PathVariable("actividadId") int actividadId){
        Actividad actividad = actividadService.findById(actividadId);
        Evento evento = eventoService.findEventoById(eventoId);
        List<Exponente> exponentes = actividad.getExponentes();
        model.addAttribute("actividad", actividad);
        model.addAttribute("evento", evento);
        model.addAttribute("exponentes", exponentes);
        return VIEW_ACTIVIDAD_DETALLES;
    }

    @GetMapping("/{actividadId}/borrarActividad")
    public String borrarActividad(ModelMap model,@PathVariable("evento_id") int eventoId, @PathVariable("actividadId") int actividadId){
        Actividad actividad = actividadService.findById(actividadId);
        Evento evento = eventoService.findEventoById(eventoId);
        model.addAttribute("actividad", actividad);
        model.addAttribute("evento", evento);
        if(actividad.getAlquilerEspacio()==null){
            actividadService.borrarActividad(actividad);
        }else{
            return "exception";
        }
      
        return "redirect:/eventos/{evento_id}";
    }
    
    @GetMapping("/{actividadId}/editar")
    public String editarActividadForm(ModelMap model,@PathVariable("evento_id") int eventoId, @PathVariable("actividadId") int actividadId){
        Actividad actividad = actividadService.findById(actividadId);
        Evento evento = eventoService.findEventoById(eventoId);
        List<Exponente> exponentes = actividad.getExponentes();
        model.addAttribute("actividad", actividad);
        model.addAttribute("evento", evento);
        model.addAttribute("exponentes", exponentes);
        return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{actividadId}/editar")
    public String editarActividad(@Valid Actividad actividad,@PathVariable("evento_id") int eventoId, @PathVariable("actividadId") int actividadId, BindingResult result, ModelMap model){
       if(!(actividadService.findById(actividadId)==null)){
           if(result.hasErrors()){
            model.put("actividad", actividad);
            return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
           }else{
               model.put("actividad", actividad);
                   this.actividadService.modificarActividad(actividad, actividadService.findById(actividadId));
                return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
               }
           } 
       return "redirect:/eventos/{evento_id}/actividades/{actividadId}";      
    }
    @GetMapping(value="/nuevo")
    public String crearActividad(ModelMap modelMap){
        Iterable<LugarRealizacion> lugaresRealizacion = lugarRealizacionService.findAll();
        List<LugarRealizacion> lugaresLista= new ArrayList<LugarRealizacion>();
        lugaresRealizacion.forEach(lugaresLista::add);
        List<Integer> listaIds = lugarRealizacionService.listaIdLugarRealizacion();
        Iterable<LugarRealizacion> lugares = lugarRealizacionService.findAll();
        modelMap.addAttribute("lugares", lugares);
        modelMap.addAttribute("listaId", listaIds);
        modelMap.addAttribute("lugaresRealizacion", lugaresLista);

        modelMap.addAttribute("actividad", new Actividad());
        return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping(value="/nuevo")
    public String guardarActividad(@Valid Actividad actividad,@PathVariable("evento_id") int eventoId,BindingResult resultado, ModelMap modelMap){
        Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("actividad", actividad);
            return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
        }else {
            actividadService.anadirActividadAEvento(evento, actividad);
            //actividadService.AñadirLugarRealizacionActividad(actividad,lugarRealizacionId);  
            actividadService.guardarActividad(actividad);
            String vistaExponente = "redirect:/eventos/{evento_id}/actividades/" + actividad.getId()+"/nuevo";
            modelMap.addAttribute("message", "Actividad guardada satisfactoriamente!");
            return vistaExponente;
        }
        
        
    }

	@GetMapping("/{actividadId}/alquilarEspacio")
	public String alquilarEspacioForm(ModelMap model, @PathVariable("actividadId") int actividadId, @PathVariable("evento_id") int eventoId){
        String vista = VIEW_ALQUILAR_ESPACIO;
        Actividad actividad = actividadService.findById(actividadId);
        Evento ev  = eventoService.findEventoById(eventoId);
        AlquilerEspacio alquiler = new AlquilerEspacio();
        Iterable<LugarRealizacion> lugares = lugarRealizacionService.findAll();
        model.addAttribute("lugares", lugares);
        model.addAttribute("alquilerEspacio", alquiler);
        model.addAttribute("actividad", actividad);
        model.addAttribute("evento", ev);
		return vista;
	}
	@PostMapping("/{actividadId}/alquilarEspacio")
	public String alquilarEspacioProcesarForm(@Valid AlquilerEspacio alquiler, @PathVariable("evento_id") int eventoId ,@PathVariable("actividadId") int actividadId, BindingResult result, ModelMap model){
        Actividad actividad = actividadService.findById(actividadId);
        Evento evento = eventoService.findEventoById(eventoId);
        Organizacion org  = orgService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		if (result.hasErrors()) {
			return VIEW_ALQUILAR_ESPACIO;
		}else {
            try {
                alquilerService.compruebaFechas(alquiler.getLugarRealizacion(), actividad, evento);
            } catch (Exception e) {
                throw new DataAccessException("Ese lugar esta reservado para esas fechas"){};
                    
                }
            }
            alquilerService.alquilerLugarRealizacion(alquiler, actividad, org);
            carritoService.anadirCarritoLugarRealizacion(actividad, org);
            model.addAttribute("alquilerEspacio", alquiler);
            return "redirect:/eventos/{evento_id}";
		}
		
     }

