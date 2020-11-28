package org.springframework.samples.petclinic.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM ="clientes/clienteUpdateForm";
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutoridadesService autoridadesService;

    @GetMapping()
    public String listadoClientes(ModelMap modelMap){

        String vista="clientes/cliente";
        Iterable<Cliente> clientes = clienteService.findCliente();
        modelMap.addAttribute("clientes", clientes);
        return vista;
    }

    @GetMapping(value = "/new")
	public String initCreationForm(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
		}
		else {
            Usuario usuario=cliente.getUsuario();
            usuario.setEnabled(true);            
            cliente.setUsuario(usuario);
            this.clienteService.saveCliente(cliente);
            autoridadesService.saveAuthorities(usuario.getNombreUsuario(), "cliente");
			
			return "redirect:/clientes/" /*+ admin.getId()*/;
		}
    }


       

    }
      
      
       // }

    
    

       