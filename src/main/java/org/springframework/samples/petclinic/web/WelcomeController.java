package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.samples.petclinic.model.Persona;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.ArrayList;

@Controller
public class WelcomeController {

	@Autowired
	private EventoService eventoService;
	
	
	  @GetMapping({"/","/index"})
	  public String welcome(Map<String, Object> model) {
		  model.put("eventos", eventoService.eventosDeInicio());
	    return "welcome";
	  }
}
