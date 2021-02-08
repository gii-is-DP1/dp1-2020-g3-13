package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ConsultaService;
import org.springframework.samples.petclinic.service.OrganizacionService;
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

    private static final String VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM = "consultas/nuevaConsulta";
    private static final String VIEWS_LISTADO_CONSULTA_ORGANIZACION = "consultas/organizacionMisConsultas";
    private static final String VIEWS_LISTADO_CONSULTA_CLIENTE = "consultas/clienteMisConsultas";
    private static final String VIEWS_CONSULTA_REPLY = "consultas/organizacionMisConsultasRespuesta";
    private static final String VIEWS_DETALLES_CONSULTA_CLIENTE = "consultas/clienteMisRespuestas";

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private OrganizacionService organizacionService;

    // Crea la vista de un formulario de consulta
    @GetMapping(value = "/{evento_id}/nuevo")
    public String formularioConsultas(ModelMap modelMap) {
        Consulta consulta = new Consulta();
        modelMap.put("consulta", consulta);
        return VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM;
    }

    // Manda una petición de creación de consulta y lo añade a la base de datos
    @PostMapping(value = "/{evento_id}/nuevo")
    public String guardarEConsulta(@Valid Consulta consulta, @PathVariable("evento_id") int eventoId,
            BindingResult resultado, ModelMap modelMap) {
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

    // Crea una vista con todas las consultas dirigidas a la organización que está
    // logeada
    @GetMapping(value = "/organizacion/misConsultas")
    public String listadoConsultasOrganizacion(ModelMap modelMap) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Organizacion organizacion = organizacionService.encuentraOrganizacionByUsuario(usuario);
        List<Consulta> listadoConsultasOrganizacion = consultaService
                .devuelveTodasLasConsultasDeOrganizacionConId(organizacion.getId());
        modelMap.addAttribute("consultas", listadoConsultasOrganizacion);
        return VIEWS_LISTADO_CONSULTA_ORGANIZACION;

    }

    // Crea una vista con todas las consultas dirigidas al cliente que está
    // logeado
    @GetMapping(value = "/cliente/misConsultas")
    public String listadoConsultasCliente(ModelMap modelMap) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteService.findClienteByUsuario(usuario);
        List<Consulta> listadoConsultasCliente = consultaService
                .devuelveTodasLasConsultasDeClienteConId(cliente.getId());
        modelMap.addAttribute("consultas", listadoConsultasCliente);
        return VIEWS_LISTADO_CONSULTA_CLIENTE;

    }

    // Crea una vista para la respuesta de la consulta
    @GetMapping(value = "/organizacion/misConsultas/{consulta_id}")
    public String respuestaConsulta(Consulta consulta, ModelMap modelMap, @PathVariable("consulta_id") int consultaId) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Consulta> consultasOrganizacion = consultaService.devuelveTodasLasConsultasDeOrganizacionConId(
                organizacionService.encuentraOrganizacionByUsuario(usuario).getId());
                consulta = consultaService.sacaConsultaDeLista(consultasOrganizacion, consultaId);
        modelMap.addAttribute("consulta", consulta);
        return VIEWS_CONSULTA_REPLY;

    }

    @PostMapping(value = "/organizacion/misConsultas/{consulta_id}")
    public String guardaRespuestaConsulta(@Valid Consulta consulta, ModelMap modelMap, @PathVariable("consulta_id") int consultaId,
            BindingResult resultado) {
        if (resultado.hasErrors()) {
            modelMap.addAttribute("consulta", consulta);
            return VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM;
        } else {
            consultaService.aniadirRespuesta(consulta, consultaId);

            return "redirect:/eventos";
        }

    }

    @GetMapping(value = "/cliente/misConsultas/{consulta_id}")
    public String verDetallesConsultaCliente(ModelMap modelMap, @PathVariable("consulta_id") int consultaId) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Consulta> consultasCliente = consultaService
                .devuelveTodasLasConsultasDeClienteConId(clienteService.findClienteByUsuario(usuario).getId());
        Consulta consulta = consultaService.sacaConsultaDeLista(consultasCliente, consultaId);
        modelMap.addAttribute("consulta", consulta);
        return VIEWS_DETALLES_CONSULTA_CLIENTE;

    }
}
