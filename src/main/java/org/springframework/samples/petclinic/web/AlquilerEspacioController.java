package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.AlquilerEspacio;
import org.springframework.samples.petclinic.service.AlquilerEspacioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alquilerEspacio")
public class AlquilerEspacioController {
    @Autowired
    private AlquilerEspacioService alquilerEspacioService;

    @GetMapping
    public String listadoAlquilerEspacios(ModelMap modelMap){
        String vista = "AlquilerEspacios/listadoAlquilerEspacios";
        Iterable<AlquilerEspacio> alquilerEspacios = alquilerEspacioService.findAll();
        modelMap.addAttribute("AlquilerEspacios", alquilerEspacios);
        return vista;
    }
}
