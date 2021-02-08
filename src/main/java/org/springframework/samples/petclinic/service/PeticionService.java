package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.PeticionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeticionService {
    @Autowired
    private PeticionRepository peticionRepo;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AutoridadesService autoridadadesService;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private EnvioEmailService envioEmailService;

    public int peticionCount() {
        return (int) peticionRepo.count();
    }

    public Iterable<Peticion> dimeTodas() {
        return peticionRepo.findAll();
    }

    public Optional<Peticion> findPeticionById(int peticionId) {
        return peticionRepo.findById(peticionId);
    }

    @Transactional
    public void savePeticion(Peticion organizacion) throws DataAccessException {
        peticionRepo.save(organizacion);
    }

    @Transactional
    public void deletePeticion(Peticion peticion) {
        peticionRepo.delete(peticion);
    }

    @Transactional
    public void createPeticion(Peticion peticion) {
        peticion.setFecha(LocalDate.now());
        savePeticion(peticion);
    }

    @Transactional
    public void generaOrganizacionAPartirDePeticion(int peticionId) {
        Peticion peticion = findPeticionById(peticionId).get();
        Usuario usuario = usuarioService.creaUsuarioParaPeticion(peticion);
        usuarioService.saveUser(usuario);
        autoridadadesService.saveAuthorities(usuario.getNombreUsuario(), "organizacion");
        Organizacion organizacion = organizacionService.creaOrganizacionParaPeticion(peticion, usuario);
        organizacionService.saveOrganizacion(organizacion);
        deletePeticion(peticion);
        envioEmailService.sendEmailSimple(organizacion, "Petición de Organización",
                "Hola ! Desde Qplan queremos darte la bienvenida después de aceptar tu petición\nYa puedes iniciar sesión en la web con las siguientes credenciales\nUsuario: "
                        + organizacion.getUsuario().getNombreUsuario() + "\nContraseña: "
                        + organizacion.getUsuario().getPassword() + "\n¡Bienvenid@!");

    }

}
