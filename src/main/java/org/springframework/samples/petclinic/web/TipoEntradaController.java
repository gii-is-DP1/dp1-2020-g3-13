package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.EventoRepository;
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
    @Autowired
    private EventoRepository eventoRepo;

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

    @PostMapping(value = "/nuevo")
    public String listadoTiposEntrada(@Valid TipoEntrada tipoEntrada, @PathVariable("evento_id") int eventoId ,BindingResult resultado, ModelMap modelMap){
        Evento evento = eventoService.findEventoById(eventoId);
        if(resultado.hasErrors()){
            modelMap.addAttribute("tipoEntrada", tipoEntrada);
            List<NombreTiposEntrada> nombre =  Arrays.asList(NombreTiposEntrada.values());
            modelMap.addAttribute("NombreTipoEntrada", nombre);
            return VIEWS_TIPOS_ENTRADAS_CREATE_OR_UPDATE_FORM;
        }else{

            //TODO
            //Terminar el tipoEntrada
            System.out.println(tipoEntrada.getActividades().size());
            tipoEntradaService.anadirTipoEntrada(evento, tipoEntrada);
            tipoEntradaService.soloVentaAl90PorCiento(tipoEntrada);
            tipoEntradaService.guardar(tipoEntrada);
            modelMap.addAttribute("actividades", eventoService.getActividades(evento.getId()));
            modelMap.addAttribute("message", "Tipo de Entrada creada satisfactoriamente!");
            return "redirect:/eventos/{evento_id}";
        }
    }
}
