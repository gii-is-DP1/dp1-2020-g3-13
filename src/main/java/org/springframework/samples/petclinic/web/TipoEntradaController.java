package org.springframework.samples.petclinic.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.TipoEntradaService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String crearTipoEntrada(ModelMap modelMap){
        modelMap.addAttribute("tipoEntrada", new TipoEntrada());
        List<NombreTiposEntrada> nombre =  Arrays.asList(NombreTiposEntrada.values());
        modelMap.addAttribute("NombreTipoEntrada", nombre);
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


            tipoEntradaService.anadirTipoEntrada(evento, tipoEntrada);
            tipoEntradaService.guardar(tipoEntrada);
            modelMap.addAttribute("message", "Tipo de Entrada creada satisfactoriamente!");
            return "redirect:/eventos/{evento_id}";
        }
    }
}
