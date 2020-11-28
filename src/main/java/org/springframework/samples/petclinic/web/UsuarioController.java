package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final String VIEWS_CREATE_FORM = "usuarios/createUsuarioForm";
    private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM ="usuarios/clienteUpdateForm";
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

            if(u.getAutoridades().getAutoridad().equals("cliente")){
                this.clienteService.deleteCliente(c);
            }
            if(u.getAutoridades().getAutoridad().equals("organizacion")){
                this.organizacionService.deleteOrganizacion(o);
            }
        
        this.usuarioService.deleteUsuario(u);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/myprofile")
    public String detallesUsuario(ModelMap modelMap){
        String vista="usuarios/myprofile";
        Cliente cliente = clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
         modelMap.addAttribute("cliente", cliente);
        return vista;
    }

    

    @GetMapping(value = "/myprofile/edit")
    public String initUpdateClienteForm(ModelMap model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente clienteUpd = clienteService.findClienteByUsuario(username);
        model.addAttribute("cliente",clienteUpd);

        return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
    }

    //TODO 
    @PostMapping(value = "/myprofile/edit")
    public String editCliente(@Valid Cliente cliente, BindingResult result, ModelMap model){


        Cliente clienteActual = this.clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());


            if (result.hasErrors()) {
                model.put("cliente", cliente);
                return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
              } else{
                  model.put("cliente", cliente);

            cliente.setId(clienteActual.getId());
            cliente.setUsuario(clienteActual.getUsuario());
            this.clienteService.saveCliente(cliente);
                

                try {
                    this.clienteService.saveCliente(clienteActual);

                } catch (Exception e) {
                  

                    return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
                }
              return "redirect:/usuarios/myprofile";
            }
        }
        @GetMapping(path ="myprofile/delete")
        public String borrarCliente(@Valid Cliente cliente, BindingResult result, ModelMap model){
 
            Cliente clienteActual2 = this.clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            usuarioService.deleteUsuario(clienteActual2.getUsuario());
            clienteService.deleteCliente(clienteActual2);


            return "redirect:/logout";
    
        }
}
