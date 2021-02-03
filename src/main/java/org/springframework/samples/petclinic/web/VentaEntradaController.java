package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrito/{carritoId}")
public class VentaEntradaController {

    private static final String VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM = "eventos/createVentaEntrada";
    
    @Autowired
	private VentaEntradaService ventaEntradaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private OrganizacionService organizacionService;
    
    @GetMapping(value ="/finalizarCompra")
	public String initCreationForm(Map<String,Object> model) {
		VentaEntrada venta = new VentaEntrada();
		model.put("ventaEntrada", venta);
		return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
	}
    @PostMapping(value = "/finalizarCompra")
	public String processCreationForm(@Valid VentaEntrada ventaEntrada, @PathVariable("carritoId") int carritoId, BindingResult result, ModelMap model) {
		if (result.hasFieldErrors()) {
			model.put("ventaEntrada", ventaEntrada);
			return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
		}
		else {
			Cliente cliente = clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
				ventaEntradaService.finalizarCompra(carritoId, cliente, ventaEntrada);
				// ventaEntradaService.saveEntrada(ventaEntrada);
			return "redirect:/eventos";
		}

	}
	@GetMapping(value ="/org/finalizarCompra")
	public String carritoOrgForm(Map<String,Object> model) {
		VentaEntrada venta = new VentaEntrada();
		model.put("ventaEntrada", venta);
		return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/org/finalizarCompra")
	public String carritoOrgForm(@Valid VentaEntrada ventaEntrada, @PathVariable("carritoId") int carritoId, BindingResult result, ModelMap model) {
		if (result.hasFieldErrors()) {
			model.put("ventaEntrada", ventaEntrada);
			return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
		}
		else {
			Organizacion organizacion = organizacionService.encuentraOrganizacionByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
				ventaEntradaService.finalizarAlquiler(carritoId, organizacion, ventaEntrada);
			return "redirect:/eventos";
		}
}
}


