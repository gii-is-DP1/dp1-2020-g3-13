package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConsultaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private static final String VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM = "consultas/nuevaConsulta";

    @GetMapping(value = "/{evento_id}/nuevo")
    public String listadoConsultas(ModelMap modelMap) {
        System.out.println("ezequiel");
        Consulta consulta = new Consulta();
        modelMap.put("consulta", consulta);
        return VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/{evento_id}/nuevo")
    public String guardarEConsulta(@Valid Consulta consulta, @PathVariable("evento_id") int eventoId,
            BindingResult resultado, ModelMap modelMap) {
        System.out.println("hey");
        if (resultado.hasErrors()) {
            modelMap.addAttribute("consulta", consulta);
            return VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM;
        } else {
            String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            Cliente cliente = clienteService.findClienteByUsuario(usuario);
            consultaService.anadirConsulta(consulta, eventoId, cliente);
            return "redirect:/eventos";
        }

    }

}
