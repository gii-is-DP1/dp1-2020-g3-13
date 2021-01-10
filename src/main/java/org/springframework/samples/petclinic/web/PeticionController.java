package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.EnvioEmailService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.PeticionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;



//tienen que aperecer botones a www.paco.es/peticiones/listado/peticion concreto

@Controller
@RequestMapping("/peticion")//seria la pagina donde estan todas las peticiones

public class PeticionController {
    @Autowired
    private AutoridadesService autoridadesService;
    @Autowired
    private PeticionService peticionServ;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private EnvioEmailService envioEmailService;

    private static final String VIEWS_CREATE_FORM = "peticion/CreatePeticionForm";

    @GetMapping()
    public String ListadoPeticiones(ModelMap modelmap){
        String vista = "/peticion/listado";
        Iterable<Peticion> peticion=peticionServ.dimeTodas(); 
        modelmap.addAttribute("peticion",peticion);
        return vista;

    }

    @GetMapping(value="/new")
    public String initCreationForm(Map<String, Object> model){
        Peticion peticion = new Peticion();
       
        model.put("peticion", peticion);
		return VIEWS_CREATE_FORM;

    }
    @PostMapping(value = "/new")
    public String processCreationForm(@Valid Peticion peticion, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_CREATE_FORM;
        }else{
            peticion.setFecha(LocalDate.now());
            this.peticionServ.createPeticion(peticion);
            return "redirect:/"; 
        }

    }

        @GetMapping("/{peticionId}")
        public ModelAndView showPeticion(@PathVariable("peticionId") int peticionId) {
            ModelAndView mav = new ModelAndView("peticion/listadoDetails");
            mav.addObject(this.peticionServ.findPeticionById(peticionId).get());
            return mav;
         }

    
            @GetMapping(path ="/delete/{peticionid}")
             public String borrarPeticion(@PathVariable("peticionid") Integer peticionid,ModelMap modelMap){
                 Optional<Peticion> peti = peticionServ.findPeticionById(peticionid);
                 peticionServ.deletePeticion(peti.get());
                 modelMap.addAttribute("message","event  succesfully deleted!"); 
                 return "redirect:/peticion";
         
             }
           
             
            @GetMapping(path ="/{peticionid}/create")
            public String crearOrganizacionByPeticion(@PathVariable("peticionid") Integer peticionid,ModelMap modelMap){
                Optional<Peticion> peti = peticionServ.findPeticionById(peticionid);
                //Inicia una organizacion y un usuario
                Organizacion newOrg =  new Organizacion();
                Usuario newUsuario = new Usuario();
                //Añade atributos al usuario
                newUsuario.setNombreUsuario(generaUsuario(peti.get().getNombre_organizacion(), peti.get().getCif()));
                newUsuario.setEnabled(true);  
                newUsuario.setPassword(generaContraseña());
                //Añade atributos al usuario     
                newOrg.setUsuario(newUsuario);
                newOrg.setEmail(peti.get().getEmail());
                newOrg.setCif(peti.get().getCif());
                newOrg.setInfo(peti.get().getInfo());
                newOrg.setNombreOrganizacion(peti.get().getNombre_organizacion());
                //Añade la organización a la BD
                this.organizacionService.saveOrganizacion(newOrg); 
                autoridadesService.saveAuthorities(newUsuario.getNombreUsuario(), "organizacion");
                peticionServ.deletePeticion(peti.get());
                envioEmailService.sendEmailSimple(newOrg, "Petición de Organización", "Hola ! Desde Qplan queremos darte la bienvenida después de aceptar tu petición\nYa puedes iniciar sesión en la web con las siguientes credenciales\nUsuario: "+newOrg.getUsuario().getNombreUsuario()+ "\nContraseña: " + newOrg.getUsuario().getPassword() +"\n¡Bienvenid@!");
                //sendEmail(newOrg, "Petición QPlan aceptada", "<html><body><h3>Hola, "+newOrg.getUsuario().getPassword() +"welcome to the Chipping Sodbury On-the-Hill message boards!</h3>");
                //Redirecciona a la lista de peticiones
                return "redirect:/peticion";
                      
                }

                //El metodo genera una contraseña automaticamente a partir del patrón
                //Por defecto es una contraseña de 20 caracteres pero se podría modificar
            private static String generaContraseña(){
                String password = "";
                String patron = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
                Random r = new Random();
                for (int i = 0; i<20; i++){
                    password += patron.charAt(r.nextInt(patron.length()));
                }
                return password;

            }

                //El metodo genera un usuario automaticamente a partir del nombre de organizacion
                //Se podria modificar o simplemente dar como usuario el CIF
            private static String generaUsuario(String nombreOrganizacion, String cif) {
                String res = nombreOrganizacion.replace(" ", "").toLowerCase();
                res = res.substring(0,1).toUpperCase()+res.substring(1) +"-"+cif;
                
                return res;

            }





 

  
                            
}


