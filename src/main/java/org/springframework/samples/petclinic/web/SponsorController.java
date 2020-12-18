package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sponsor;
import org.springframework.samples.petclinic.service.SponsorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/sponsors")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;
    @GetMapping
    public String listadoSponsors(ModelMap modelMap){
        String vista = "sponsors/listadoSponsors";
        Iterable<Sponsor> sponsors = sponsorService.findAll();
        modelMap.addAttribute("Sponsors", sponsors);
        return vista;
    }
}