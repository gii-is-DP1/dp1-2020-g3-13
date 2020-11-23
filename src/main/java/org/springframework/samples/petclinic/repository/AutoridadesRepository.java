package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Autoridades;

public interface AutoridadesRepository extends CrudRepository<Autoridades, String> {

    @Query("SELECT autoridades FROM Autoridades autoridades WHERE autoridades.usuario.nombreUsuario =:usuario")
    public Collection<Autoridades> listadoAutoridadesByUsuario(String usuario);

    


}
