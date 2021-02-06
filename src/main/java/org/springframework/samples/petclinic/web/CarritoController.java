package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.samples.petclinic.service.LineaFacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

	@Autowired
    private CarritoService carritoService;
    @Autowired
    private LineaFacturaService lineaFacturaService;
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private EntradaService entradaService;


    @GetMapping("/cliente")
	public String miCarrito(ModelMap modelMap) {
		String vista="carrito/miCarrito";
        Carrito carrito = carritoService.listadoObjetosCarrito(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("carrito", carrito);
        return vista;
    }
    
    @GetMapping("/organizacion")
	public String miCarritoOrg(ModelMap modelMap) {
		String vista="carrito/miCarritoOrganizacion";
        Carrito carrito = carritoService.listadoObjetosCarritoOrganizacion(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Actividad> actividades = actividadService.encuentraActividadesPorCarrito(carrito);
        
        modelMap.addAttribute("actividades", actividades);
        modelMap.addAttribute("carrito", carrito);
        return vista;
    }
    @GetMapping(value = "/organizacion/{lineaFacturaId}/delete")
    public String deleteLineaFactura(@PathVariable("lineaFacturaId") int lineaFacturaId, ModelMap model){ 
        LineaFactura linea = lineaFacturaService.encuentraLineaFactura(lineaFacturaId);
        Carrito carrito = carritoService.listadoObjetosCarritoOrganizacion(SecurityContextHolder.getContext().getAuthentication().getName());
        carritoService.borrarLineaFactura(carrito, lineaFacturaId);
        actividadService.borrarAlquileres(actividadService.encuentraActividadPorAlquilerId(linea.getAlquilerEspacio().getId()));
        lineaFacturaService.borrarLinea(linea);

        model.addAttribute("carrito", carrito);
        return "redirect:/carrito/organizacion";
    }

    @GetMapping(value = "/cliente/{lineaFacturaId}/delete")
    public String deleteLineaFacturaCliente(@PathVariable("lineaFacturaId") int lineaFacturaId, ModelMap model){ 
        LineaFactura linea = lineaFacturaService.encuentraLineaFactura(lineaFacturaId);
        Carrito carrito = carritoService.listadoObjetosCarrito(SecurityContextHolder.getContext().getAuthentication().getName());
        carritoService.borrarLineaFactura(carrito, lineaFacturaId);
        entradaService.borrarEntrada(entradaService.encuentraEntradaPorId(linea.getEntrada().getId()));
        lineaFacturaService.borrarLinea(linea);
        model.addAttribute("carrito", carrito);
        return "redirect:/carrito/cliente";
    }
}