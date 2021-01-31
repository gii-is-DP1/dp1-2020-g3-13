package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.AlquilerEspacioService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alquilerEspacio")
public class AlquilerEspacioController {

    private static final String VIEW_ALQUILAR_ESPACIO = "alquilerEspacio/alquilerEspacio";
    private static final String VIEW_EVENTO_DETALLES = "eventos/detallesEvento";
    @Autowired
    private AlquilerEspacioService alquilerEspacioService;
    @Autowired
    private OrganizacionService orgService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private CarritoService carritoService;


    @GetMapping
    public String listadoAlquilerEspacios(ModelMap modelMap){
        String vista = "AlquilerEspacios/listadoAlquilerEspacios";
        Iterable<AlquilerEspacio> alquilerEspacios = alquilerEspacioService.findAll();
        modelMap.addAttribute("AlquilerEspacios", alquilerEspacios);
        return vista;
    }

}
