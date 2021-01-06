package org.springframework.samples.petclinic.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lugaresRealizacion")
public class LugarRealizacionController {

    private static final String VIEWS_LUGAR_CREATE_OR_UPDATE_FORM = "lugaresRealizacion/createOrUpdatelugaresForm";
    
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

    @GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
        LugarRealizacion lugarRealizacion = new LugarRealizacion();
		model.put("lugarRealizacion", lugarRealizacion);
		return VIEWS_LUGAR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid LugarRealizacion lugarRealizacion, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_LUGAR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.lugarRealizacionService.saveLugarRealizacion(lugarRealizacion);
			
			return "redirect:/lugaresRealizacion/";
		}
	}
	@GetMapping(value = "/{lugarRealizacionId}/edit")
    public String initUpdateLugarRealizacionForm(@PathVariable("lugarRealizacionId") int lugarRealizacionId, ModelMap modelMap) {
			LugarRealizacion lugarRealizacion= this.lugarRealizacionService.findById(lugarRealizacionId);
			modelMap.addAttribute(lugarRealizacion);
          
            return VIEWS_LUGAR_CREATE_OR_UPDATE_FORM;
       
    }

    //TODO 
    @PostMapping(value = "/{lugarRealizacionId}/edit")
    public String editLugarRealizacion(@Valid LugarRealizacion lugarRealizacion, BindingResult result, ModelMap model,@PathVariable("lugarRealizacionId") int lugarRealizacionId){
		String vista = "";
				if(result.hasErrors()){
			return VIEWS_LUGAR_CREATE_OR_UPDATE_FORM;
		}else{
			this.lugarRealizacionService.modifyLugaRealizacion(lugarRealizacion, this.lugarRealizacionService.findById(lugarRealizacionId));
			return "redirect:/lugaresRealizacion/";		}

}
}