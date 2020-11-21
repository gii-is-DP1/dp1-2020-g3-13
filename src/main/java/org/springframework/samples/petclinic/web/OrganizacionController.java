package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organizaciones")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping()
    public String listadoOrganizaciones(ModelMap modelMap){
        String vista = "/organizaciones/listadoOrganizaciones";
        Iterable<Organizacion> organizaciones = organizacionService.findAll();
        modelMap.addAttribute("organizaciones", organizaciones);
        return vista;
    }
}
