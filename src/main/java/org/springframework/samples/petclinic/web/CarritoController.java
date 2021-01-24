package org.springframework.samples.petclinic.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    // private static final String VIEWS_CARRITO = null;
	@Autowired
    private CarritoService carritoService;
	
    @GetMapping()
	public String miCarrito(ModelMap modelMap) {
		String vista="carrito/miCarrito";
        Carrito carrito = carritoService.listadoObjetosCarrito(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("carrito", carrito);
        return vista;
	}
}