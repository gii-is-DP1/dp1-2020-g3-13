package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.service.PeticionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//www.paco.es/peticiones/listado 

//tienen que aperecer botones a www.paco.es/peticiones/listado/peticion concreto

@Controller
@RequestMapping("/peticion")//seria la pagina donde estan todas las peticiones
public class PeticionController {
    @Autowired
    private PeticionService peticionServ;
    @GetMapping()
    public String ListadoPeticiones(ModelMap modelmap){
        String vista = "/peticion/listado";
        Iterable<Peticion> peticion=peticionServ.dimeTodas(); 
        modelmap.addAttribute("peticion",peticion);
        return vista;

    }
    
}
