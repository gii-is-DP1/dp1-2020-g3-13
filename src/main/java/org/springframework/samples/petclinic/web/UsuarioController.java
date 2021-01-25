package org.springframework.samples.petclinic.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.FacturaService;
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
    //private static final String VIEWS_CREATE_FORM = "usuarios/createUsuarioForm";
    private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM ="usuarios/clienteUpdateForm";
    private static final String VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM ="usuarios/organizacionUpdateForm";
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private EventoService eventoService;
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
        adminService.deleteUsuario(usuarioId);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/myprofile")
    public String detallesUsuario(ModelMap modelMap){
        String vista = "redirect:/usuarios/myprofile";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!(clienteService.findClienteByUsuario(username)==null)){
            vista="usuarios/myProfileClientes";
            Cliente cliente = clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            modelMap.addAttribute("cliente", cliente);
        }if(!(organizacionService.encuentraOrganizacionByUsuario(username)==null)){
            vista="usuarios/myProfileOrganizaciones";
            Organizacion organizacion = organizacionService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            modelMap.addAttribute("organizacion", organizacion);
            return vista;
        }
        return vista;
    }

    @GetMapping(value = "/myprofile/edit")
    public String initUpdateClienteForm(ModelMap model) {
  
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!(clienteService.findClienteByUsuario(username)==null)){
            Cliente clienteUpd = clienteService.findClienteByUsuario(username);
            model.addAttribute("cliente",clienteUpd);
            return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
        }
        if(!(organizacionService.encuentraOrganizacionByUsuario(username)==null)){
            Organizacion org = organizacionService.encuentraOrganizacionByUsuario(username);
            model.addAttribute("organizacion",org);
            return VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM;
        }
        return "redirect:/usuarios/myprofile";
    }
    @GetMapping(value = "/myprofile/facturas")
    public String facturas(ModelMap modelMap){
        Usuario usuario = usuarioService.findUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        Organizacion org = organizacionService.encuentraOrganizacionByUsuario(usuario.getNombreUsuario());
        List<Factura> facts = usuario.getFacturas();
        facturaService.calculaPrecioTotal(facts);
        modelMap.addAttribute("usuario", usuario);
        modelMap.addAttribute("organizacion", org);
        String vista="usuarios/myProfileFacturas";
        return vista;
    }

    //TODO
    @PostMapping(value = "/myprofile/edit")
    public String editCliente(Cliente cliente, Organizacion organizacion, BindingResult result, ModelMap model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente clienteActual = this.clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());

        if(!(clienteService.findClienteByUsuario(username)==null)){
            if (result.hasErrors()) {
                model.put("cliente", cliente);
                return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
              } else{
                  model.put("cliente", cliente);
                try {
                    this.clienteService.modifyUsuarioCliente(cliente, clienteActual);
                } catch (Exception e) {
                    return VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM;
                 }
            }
        }
        if(!(organizacionService.encuentraOrganizacionByUsuario(username)==null)){
            Organizacion org = this.organizacionService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            if (result.hasErrors()) {
                model.put("organizacion", organizacion);
                return VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM;
              } else{
                  model.put("organizacion", organizacion);
                try {
                   this.organizacionService.modifyUsuarioOrganizacion(organizacion, org);
                 } catch (Exception e) {
                    return VIEWS_ORGANIZACION_CREATE_OR_UPDATE_FORM;
                }
        }
    }
        return "redirect:/usuarios/myprofile";
        }
        @GetMapping(path="myprofile/eventosFavoritos")
        public String muestraEventosFavoritos(ModelMap model){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cliente cliente = clienteService.findClienteByUsuario(username);
            //List<Evento> eventos = cliente.getEventosFavoritos();
            model.addAttribute("cliente", cliente);
            String vista  = "usuarios/eventosFavoritos";
            return vista;
        }
        @GetMapping(path ="myprofile/delete")
        public String borrarCliente( ModelMap model){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if(!(clienteService.findClienteByUsuario(username)==null)){
                Cliente clienteActual2 = this.clienteService.findClienteByUsuario(username);
                usuarioService.deleteUsuario(clienteActual2.getUsuario());
                clienteService.deleteCliente(clienteActual2);
            }if(!(organizacionService.encuentraOrganizacionByUsuario(username)==null)){
                Organizacion org2 = this.organizacionService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
                usuarioService.deleteUsuario(org2.getUsuario());
                organizacionService.deleteOrganizacion(org2);

            }
            return "redirect:/logout";
    
        } 

        @GetMapping(path ="myprofile/eventosFavoritos/{eventosId}/borrar")
        public String borrarEventoFav( ModelMap model, @PathVariable("eventosId") int eventosId){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cliente cliente = clienteService.findClienteByUsuario(username);
            Evento evento = eventoService.findEventoById(eventosId);
            eventoService.borrarEventoFav(evento, cliente);
            
            return "redirect:/usuarios/myprofile/eventosFavoritos";
    
        }
}
