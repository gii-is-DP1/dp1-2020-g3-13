package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AutoridadesRepository;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final String VIEWS_CREATE_FORM = "usuarios/createUsuarioForm";
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AutoridadesService autoridadesService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping()
    public String listadoUsuarios(ModelMap modelMap){
        String vista = "/usuarios/listadoUsuarios";
        Iterable<Usuario> usuarios = usuarioService.findAll();
        modelMap.addAttribute("usuarios", usuarios);
        return vista;
    }

    @GetMapping("/{usuarioId}")
	public ModelAndView showUsuario(@PathVariable("usuarioId") String usuarioId) {
		ModelAndView mav = new ModelAndView("usuarios/detallesUsuario");
		mav.addObject("usuario", this.usuarioService.findUsuario(usuarioId));
		return mav;
    }

    @GetMapping(value = "/{usuarioId}/delete")
    public String deleteUsuario(@PathVariable("usuarioId") String usuarioId, ModelMap model){ 
        Usuario u = usuarioService.findUsuario(usuarioId);
        Cliente c = clienteService.findClienteByUsuario(usuarioId);
        Organizacion o = organizacionService.findOrganizacionByUsuario(usuarioId); 

        for(Autoridades a: this.autoridadesService.listadoAutoridades(usuarioId)){
            if(a.getAutoridad().equals("cliente")){
                this.clienteService.deleteCliente(c);
            }
            if(a.getAutoridad().equals("organizacion")){
                this.organizacionService.deleteOrganizacion(o);
            }
            this.autoridadesService.deleteAutoridades(a);
        }
        this.usuarioService.deleteUsuario(u);
        return "redirect:/usuarios";
    }
}
