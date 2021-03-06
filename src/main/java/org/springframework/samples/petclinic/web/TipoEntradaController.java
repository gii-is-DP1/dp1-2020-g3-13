package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.TipoEntradaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventos/{evento_id}/tipoEntradas")
public class TipoEntradaController {

    private static final String VIEWS_TIPOS_ENTRADAS_CREATE_OR_UPDATE_FORM = "tipoentradas/crearTipoEntrada";
    @Autowired
    private TipoEntradaService tipoEntradaService;
    @Autowired
    private EventoService eventoService;

    @GetMapping(value = "/nuevo")
    public String crearTipoEntrada(ModelMap modelMap, @PathVariable("evento_id") int eventoId){
        modelMap.addAttribute("tipoEntrada", new TipoEntrada());
        List<NombreTiposEntrada> nombre =  Arrays.asList(NombreTiposEntrada.values());
        modelMap.addAttribute("NombreTipoEntrada", nombre);
        Predicate<Actividad> pred = x->x.getAlquilerEspacio()!=null ;
        List<Actividad> acts = new ArrayList<Actividad>();
        for(Actividad act : eventoService.getActividades(eventoId)){
            if(pred.test(act)){
                acts.add(act);
            }
        }
        modelMap.addAttribute("actividades", acts);
        return VIEWS_TIPOS_ENTRADAS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = {"/nuevo", "/{tipo_entrada_id}/editar"})
    public String listadoTiposEntrada(@Valid TipoEntrada tipoEntrada, @PathVariable("evento_id") int eventoId ,BindingResult resultado, ModelMap modelMap){
        Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("tipoEntrada", tipoEntrada);
            List<NombreTiposEntrada> nombre =  Arrays.asList(NombreTiposEntrada.values());
            modelMap.addAttribute("NombreTipoEntrada", nombre);
            return VIEWS_TIPOS_ENTRADAS_CREATE_OR_UPDATE_FORM;
        }else{
            tipoEntradaService.anadirTipoEntrada(evento, tipoEntrada);
            tipoEntradaService.soloVentaAl90PorCiento(tipoEntrada);
            tipoEntradaService.guardar(tipoEntrada);
            modelMap.addAttribute("actividades", eventoService.getActividades(evento.getId()));
            modelMap.addAttribute("message", "Tipo de Entrada creada satisfactoriamente!");
            return "redirect:/eventos/{evento_id}";
        }
    }
    @GetMapping(value = "{tipoEntradaId}/borrarTiposEntradas")
    public String borrarTipoEntradaEvento(@PathVariable("evento_id") int eventoId, @PathVariable("tipoEntradaId") int tipoEntradaId, ModelMap model) {
        if(eventoService.findEventoById(eventoId).getEsPublico() != true){
            TipoEntrada tipoEntrada = tipoEntradaService.findById(tipoEntradaId);
            tipoEntradaService.borrarTipoEntrada(tipoEntrada);
        }else{
            throw new DataAccessException("No puedes borrar los tipos de entradas si el evento esta público"){

            };
        }
        
        return "redirect:/eventos/{evento_id}";
    }


    @GetMapping(value = "/{tipo_entrada_id}/editar")
    public String editarTipoEntradaForm(ModelMap modelMap,@PathVariable("evento_id") int eventoId,@PathVariable("tipo_entrada_id") int tipoEntradaId){
        TipoEntrada tipoEntrada = tipoEntradaService.findById(tipoEntradaId);
        modelMap.addAttribute("tipoEntrada",tipoEntrada);
        List<NombreTiposEntrada> nombre =  Arrays.asList(NombreTiposEntrada.values());
        modelMap.addAttribute("NombreTipoEntrada", nombre);
        Predicate<Actividad> pred = x->x.getAlquilerEspacio()!=null ;
        List<Actividad> acts = new ArrayList<Actividad>();
        tipoEntrada.getActividades().clear();
        for(Actividad act : eventoService.getActividades(eventoId)){
            if(pred.test(act)){
                acts.add(act);
            }
        }
        modelMap.addAttribute("actividades", acts);
        return VIEWS_TIPOS_ENTRADAS_CREATE_OR_UPDATE_FORM;
    }
}
