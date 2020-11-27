package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.stereotype.Controller;
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
			this.ventaEntradaService.saveEntrada(ventaEntrada);

			
			return "redirect:/eventos" /*+ admin.getId()*/;
		}
	}
}

