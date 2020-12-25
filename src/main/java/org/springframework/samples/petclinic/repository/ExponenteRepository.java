package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Exponente;

public interface ExponenteRepository extends CrudRepository<Exponente, Integer>{

    @Query("SELECT exponente FROM Exponente exponete WHERE exponente.nombreExpo =:nombreExpo")
    public Exponente existeExponenteConEsteNombre(String nombreExpo);
    @Query("SELECT exponente FROM Exponente exponete WHERE exponente.apellidosExpo =:apellidosExpo")
    public Exponente existeExponenteConEsteApellidos(String apellidosExpo);
    @Query("SELECT exponente FROM Exponente exponete WHERE exponente.alias =:alias")
    public Exponente existeExponenteConEsteAlias(String alias);
    
}
