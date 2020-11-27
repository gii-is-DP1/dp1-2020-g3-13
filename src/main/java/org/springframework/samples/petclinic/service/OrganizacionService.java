package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.OrganizacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizacionService {

        @Autowired
        private OrganizacionRepository organizacionRepo;
        @Autowired
       // private PeticionRepository peticionrepo;
        @Transactional
        public int organizacionCount(){
            return (int) organizacionRepo.count();
        }

    @Transactional
    public int organizacionCount() {
        return (int) organizacionRepo.count();
    }

    public Iterable<Organizacion> findAll() {
        return organizacionRepo.findAll();
    }

    public Organizacion findOrganizacionByUsuario(String usuario) throws DataAccessException {
        return organizacionRepo.listadoOrganizacionByUsuario(usuario);
    }

    @Transactional
    public void saveOrganizacion(Organizacion organizacion) throws DataAccessException {
        organizacionRepo.save(organizacion);
    }

    @Transactional(readOnly = true)
    public Organizacion findById(int organizacionId) throws DataAccessException {
            return organizacionRepo.findById(organizacionId);
    }
        
    public void deleteOrganizacion(int organizacionId) {
            organizacionRepo.delete(organizacionRepo.findById(organizacionId));
          //  peticionrepo.delete(peticionrepo.findPeticionByOrganizacion(organizacion.getNombreOrganizacion().getNombre_organizacion()));

        }

           @Transactional      
           public void saveOrganizacion(Organizacion organizacion) throws DataAccessException{
            organizacionRepo.save(organizacion);

       }
   
    }

    public void deleteOrg(Organizacion organizacion){
        organizacionRepo.delete(organizacion);
    }

     
}
