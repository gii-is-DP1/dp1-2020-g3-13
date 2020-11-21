package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins")
public class AdminController {
    private static final String VIEWS_ADMIN_CREATE_OR_UPDATE_FORM = "admins/createOrUpdateAdminForm";
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String listadoAdmins(ModelMap modelMap){
        String vista = "admins/listadoAdmins";
        Iterable<Admin> admins = adminService.findAll();
        modelMap.addAttribute("admins", admins);
        return vista;
    }

    @GetMapping(value = "/new")
	public String initCreationForm(Map<String,Object> model) {
		Admin admin = new Admin();
		model.put("admin", admin);
		return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/new")
	public String processCreationForm(@Valid Admin admin, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
		}
		else {
	
			this.adminService.saveAdmin(admin);
			
			return "redirect:/admins/" /*+ admin.getId()*/;
		}
	}

    @GetMapping(value = "/{adminId}/edit")
	public String initUpdateAdminForm(@PathVariable("adminId") int adminId, ModelMap modelMap) {
		Admin admin = this.adminService.findById(adminId);
		modelMap.addAttribute(admin);
		return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
	}
    @PostMapping(value = "/{adminId}/edit")
	public String processUpdateAdminForm(@Valid Admin admin, BindingResult result,
			@PathVariable("adminId") int adminId) {
		if (result.hasErrors()) {
			return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
		}
		else {
			admin.setId(adminId);
			this.adminService.saveAdmin(admin);
			return "redirect:/admins/{adminId}";
		}

    }

    @GetMapping("/{adminId}")
	public ModelAndView showOwner(@PathVariable("adminId") int adminId) {
		ModelAndView mav = new ModelAndView("admins/detallesAdmin");
		mav.addObject(this.adminService.findById(adminId));
		return mav;
    }
    
    @GetMapping(value = "/delete/{adminId}")
    public String deleteAdmin(@PathVariable("adminId") int adminId, ModelMap model){
        this.adminService.deleteAdmin(adminId);
        return "redirect:/admins";
    }
    
}
