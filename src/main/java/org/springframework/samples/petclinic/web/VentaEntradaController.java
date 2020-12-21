package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos/{eventoId}")
public class VentaEntradaController {

    private static final String VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM = "eventos/createVentaEntrada";
    
    @Autowired
	private VentaEntradaService ventaEntradaService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private ClienteService clienteService;
    
    @GetMapping(value ="/ventaEntradas")
	public String initCreationForm(Map<String,Object> model) {
		VentaEntrada venta = new VentaEntrada();
		model.put("ventaEntrada", venta);
		return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/ventaEntradas")
	public String processCreationForm(@Valid VentaEntrada ventaEntrada,@PathVariable("eventoId") int eventoId, BindingResult result, ModelMap model) {
		if (result.hasFieldErrors()) {
			model.put("ventaEntrada", ventaEntrada);
			return VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM;
		}
		else {
			model.put("ventaEntrada", ventaEntrada);
			ventaEntrada.setEvento(eventoService.findEventoById(eventoId));
			ventaEntrada.setCliente(clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName()));
			this.ventaEntradaService.saveEntrada(ventaEntrada);

			return "redirect:/eventos" /*+ admin.getId()*/;
		}

	}
}


