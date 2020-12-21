package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class CarritoController {

    // private static final String VIEWS_CARRITO = null;
	@Autowired
    private CarritoService carritoService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private VentaEntradaService ventaEntradaService;
    @Autowired
	private ClienteService clienteService;
    
    
    @GetMapping()
	public String miCarrito(ModelMap modelMap) {
		String vista="carrito/miCarrito";
        Iterable<Carrito> carrito = carritoService.listadoObjetosCarrito(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("carrito", carrito);
        return vista;
	}
}