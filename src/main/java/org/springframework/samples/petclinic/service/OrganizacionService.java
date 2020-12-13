package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.OrganizacionRepository;
import org.springframework.samples.petclinic.repository.PeticionRepository;
import org.springframework.samples.petclinic.model.Usuario;
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

        public Iterable<Organizacion> findAll(){
            return organizacionRepo.findAll();
        }
        public Organizacion findOrganizacionByUsuario(String usuario) throws DataAccessException{
            return organizacionRepo.listadoOrganizacionByUsuario(usuario);
        }

        public void deleteOrganizacion(Organizacion o) throws DataAccessException{
            organizacionRepo.delete(o);
          //  peticionrepo.delete(peticionrepo.findPeticionByOrganizacion(organizacion.getNombreOrganizacion().getNombre_organizacion()));

        }

           @Transactional
           public void saveOrganizacion(Organizacion organizacion) throws DataAccessException{
            organizacionRepo.save(organizacion);

       }


       
	public void modifyUsuarioOrganizacion(Organizacion organizacion, Organizacion org) throws DataAccessException{
		organizacion.setId(org.getId());
		organizacion.setUsuario(org.getUsuario());
		// organizacion.getUsuario().setEnabled(true);
	    saveOrganizacion(organizacion);
	}



    }