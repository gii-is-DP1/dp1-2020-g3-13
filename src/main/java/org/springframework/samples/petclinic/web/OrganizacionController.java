package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.PeticionService;
import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/organizaciones")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private PeticionService peticionservice;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AutoridadesService autoridadesService;
    private static final String VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM ="organizaciones/organizacionUpdateForm";

    
    @GetMapping()
    public String listadoOrganizaciones(ModelMap modelMap){
        String vista = "/organizaciones/listadoOrganizaciones";
        Iterable<Organizacion> organizaciones = organizacionService.findAll();
        modelMap.addAttribute("organizaciones", organizaciones);
        return vista;
    
    }


    @GetMapping(value = "/new")
	public String initCreationForm(Map<String,Object> model) {
		Organizacion organizacion = new Organizacion();
		model.put("organizacion", organizacion);
		return VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/new")
	public String processCreationForm(@Valid Organizacion organizacion, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM;
		}
		else {
            Usuario usuario=organizacion.getUsuario();
            usuario.setEnabled(true);            
            organizacion.setUsuario(usuario);
            this.organizacionService.saveOrganizacion(organizacion);
            autoridadesService.saveAuthorities(usuario.getNombreUsuario(), "organizacion");
			
			return "redirect:/organizacion/" /*+ admin.getId()*/;
		}
    }



    

    
}
