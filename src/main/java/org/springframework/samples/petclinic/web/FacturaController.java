package org.springframework.samples.petclinic.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;
    @GetMapping
    public String listadoConsultas(ModelMap modelMap){
        String vista = "facturas/UsuarioFactura";
        Iterable<Factura> facturas = facturaService.findAll();
        modelMap.addAttribute("Facturas", facturas);
        return vista;
    }
}
