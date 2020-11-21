package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaFactura;
import org.springframework.samples.petclinic.service.LineaFacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lineaFactura")
public class LineaFacturaController {
    @Autowired
    private LineaFacturaService lineaFacturaService;

    @GetMapping
    public String listadolineaFacturas(ModelMap modelMap){
        String vista = "LineaFacturas/listadolineaFacturas";
        Iterable<LineaFactura> lineaFacturas = lineaFacturaService.findAll();
        modelMap.addAttribute("LineaFacturas", lineaFacturas);
        return vista;
    }
}