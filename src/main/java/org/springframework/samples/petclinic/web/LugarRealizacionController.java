package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lugaresRealizacion")
public class LugarRealizacionController {
    
    @Autowired
    private LugarRealizacionService lugarRealizacionService;

    @GetMapping()
    public String listadoLugaresRealizacion(ModelMap modelMap){
        String vista = "/lugaresRealizacion/listadoLugaresRealizacion";
        Iterable<LugarRealizacion> lugaresRealizacion = lugarRealizacionService.findAll();
        modelMap.addAttribute("lugaresRealizacion", lugaresRealizacion);
        return vista;
    }

    @GetMapping("/{lugarRealizacionId}")
	public ModelAndView showLugarRealizacion(@PathVariable("lugarRealizacionId") int lugarId) {
		ModelAndView mav = new ModelAndView("lugaresRealizacion/detallesLugarRealizacion");
		mav.addObject(this.lugarRealizacionService.findById(lugarId));
		return mav;
    }
}
