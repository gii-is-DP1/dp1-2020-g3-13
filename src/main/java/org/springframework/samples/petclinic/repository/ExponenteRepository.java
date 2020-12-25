package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Exponente;

public interface ExponenteRepository extends CrudRepository<Exponente, Integer>{

    @Query("SELECT exponente FROM Exponente exponente WHERE exponente.nombreExponente =:nombreExponente")
    public Exponente existeExponenteConEsteNombre(String nombreExponente);
    @Query("SELECT exponente FROM Exponente exponente WHERE exponente.apellidosExponente =:apellidosExponente")
    public Exponente existeExponenteConEsteApellidos(String apellidosExponente);
    @Query("SELECT exponente FROM Exponente exponente WHERE exponente.alias =:alias")
    public Exponente existeExponenteConEsteAlias(String alias);
    
}
