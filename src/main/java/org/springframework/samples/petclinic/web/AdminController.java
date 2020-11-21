package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller                 
@RequestMapping("/admins")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String listadoAdmins(ModelMap modelMap){
        String vista = "admins/listadoAdmins";
        Iterable<Admin> admins = adminService.findAll();
        modelMap.addAttribute("admins", admins);
        return vista;
    }
}
