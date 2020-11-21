package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.service.TipoEntradaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tiposentrada")
public class TipoEntradaController {
    @Autowired
    private TipoEntradaService tipoEntradaService;

    @GetMapping
    public String listadoTiposEntrada(ModelMap modelMap){
        String vista = "Eventos/listadoTiposEntrada";
        Iterable<TipoEntrada> tiposEntrada = tipoEntradaService.findAll();
        modelMap.addAttribute("TiposEntrada", tiposEntrada);
        return vista;
    }
}
