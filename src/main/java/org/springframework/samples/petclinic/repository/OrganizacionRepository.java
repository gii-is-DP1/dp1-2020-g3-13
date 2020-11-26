package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Organizacion;

public interface OrganizacionRepository extends CrudRepository<Organizacion, Integer>{
    

   
    @Query("SELECT organizacion FROM Organizacion organizacion WHERE organizacion.usuario.nombreUsuario =:usuario")
    public Organizacion listadoOrganizacionByUsuario(String usuario);

}
