package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.ClienteService;
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
        this.clienteService.deleteCliente(c);
        for(Autoridades a: this.autoridadesService.listadoAutoridades(usuarioId)){
                 this.autoridadesService.deleteAutoridades(a);
        }
        
        this.usuarioService.deleteUsuario(u);
        return "redirect:/usuarios";
    }
}
