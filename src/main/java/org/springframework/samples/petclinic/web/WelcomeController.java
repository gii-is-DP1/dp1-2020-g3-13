package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.samples.petclinic.model.Persona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.ArrayList;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		//  List<Persona> persons=new ArrayList<Persona>();
		//  Persona person=new Persona();
		//  person.setFirstName("Pablo");
		//  person.setLastName("Gutierrez");
		//  persons.add(person);
		//  model.put("persons", persons);
		  model.put("title", "G3-13");
		  model.put("group", "G3-13");
	    return "welcome";
	  }
}
