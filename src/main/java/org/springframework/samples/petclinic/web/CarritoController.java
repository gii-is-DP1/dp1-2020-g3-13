package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private VentaEntradaService ventaService;
    
    

    @GetMapping(value ="/ventaEntradas")
	public String initCreationForm(Map<String,Object> model) {
		VentaEntrada venta = new VentaEntrada();
		model.put("ventaEntrada", venta);
		return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/ventaEntradas")
	public String processCreationForm(VentaEntrada ventaEntrada,@PathVariable("eventoId") int eventoId, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
		}
		else {
			ventaEntrada.setEvento(eventoService.findEventoById(eventoId));
			ventaEntrada.setCliente(clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName()));
			this.ventaEntradaService.saveEntrada(ventaEntrada);

			
			return "redirect:/eventos" /*+ admin.getId()*/;
		}
	}
}