package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.UsuarioRepository;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public String listadoFacturas(ModelMap modelMap){
        String vista = "facturas/listadoFacturas";
        Usuario usuario = usuarioService.findUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Factura> facturas = facturaService.facturasUsuario(usuario);
        // Iterable<Factura> facturas = facturaService.findAll();
        modelMap.addAttribute("facturas", facturas);
        return vista;
    }
}
