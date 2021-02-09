package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/eventos/{eventoId}/{tipoEntradasId}")
public class EntradaController {

	
    public static final String VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM= "entradas/crearEntrada";
    @Autowired
    private EntradaService entradaService; 
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value ="/entrada")
	public String initCreationForm(Map<String,Object> model) {
		Entrada entrada = new Entrada();
		model.put("entrada", entrada);
		return VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/entrada")
	public String processCreationForm(@Valid Entrada entrada,@PathVariable("eventoId") int eventoId,@PathVariable("tipoEntradasId") int tipoEntradaId, BindingResult result, ModelMap model) {
		Carrito car= carritoService.dimeCarritoUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		List<String> nAsists= carritoService.dimeNombreAsistentes(car,eventoId);
		model.addAttribute("entrada", entrada);
		if (result.hasErrors()) {
			return VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM;
		}
		else if(entradaService.existeElNombreEnElCarro(nAsists, entrada.getNombreAsistente())||entradaService.buscaPorEventoYPorNombreAsistene(entrada.getNombreAsistente(), eventoId)){
			return "/entradas/errorAsistente";
		}else{
		   
		   Cliente cliente  = clienteService.findClienteByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		   this.entradaService.crearEntrada(entrada, tipoEntradaId, cliente);
		   this.carritoService.anadirCarrito(entrada, cliente);
			return "redirect:/eventos/{eventoId}";
		}
	}

	
}
