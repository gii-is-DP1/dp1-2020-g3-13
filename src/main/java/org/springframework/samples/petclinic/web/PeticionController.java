package org.springframework.samples.petclinic.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.service.PeticionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//www.paco.es/peticiones/listado 
import org.springframework.web.servlet.ModelAndView;

//tienen que aperecer botones a www.paco.es/peticiones/listado/peticion concreto

@Controller
@RequestMapping("/peticion")//seria la pagina donde estan todas las peticiones
public class PeticionController {

    private static final String VIEWS_CREATE_FORM = "peticion/CreatePeticionForm";

    @Autowired
    private PeticionService peticionServ;
    @GetMapping(value="/listado")
    public String ListadoPeticiones(ModelMap modelmap){
        String vista = "/peticion/listado";
        Iterable<Peticion> peticion=peticionServ.dimeTodas(); 
        modelmap.addAttribute("peticion",peticion);
        return vista;

    }

    @GetMapping(value="/new")
    public String initCreationForm(Map<String, Object> model){
        Peticion peticion = new Peticion();
        model.put("peticion", peticion);
		return VIEWS_CREATE_FORM;

    }
    @PostMapping(value = "/new")
    public String processCreationForm(@Valid Peticion peticion, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_CREATE_FORM;
        }else{
            this.peticionServ.saveOrganizacion(peticion);
            return "redirect:/"; 
        }

    }

        @GetMapping("/{peticionId}")
        public ModelAndView showPeticion(@PathVariable("peticionId") int peticionId) {
            ModelAndView mav = new ModelAndView("peticion/listadoDetails");
            mav.addObject(this.peticionServ.findPeticionById(peticionId).get());
            return mav;
         }

    
             @GetMapping(path ="/delete/{peticionid}")
             public String borrarPeticion(@PathVariable("peticionid") Integer peticionid,ModelMap modelMap){
                 String vista = "/peticion/listado";
                 Optional<Peticion> peti = peticionServ.findPeticionById(peticionid);
                 peticionServ.deletePeticion(peti.get());
                 modelMap.addAttribute("message","event  succesfully deleted!"); 
                 return "redirect:/peticion/listado";
         
             }
              
          }
