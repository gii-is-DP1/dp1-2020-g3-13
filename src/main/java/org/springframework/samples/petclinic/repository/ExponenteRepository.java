package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Exponente;

public interface ExponenteRepository extends CrudRepository<Exponente, Integer>{
    
    //@Query("SELECT exponente FROM Exponente exponente JOIN FETCH exponente.actividades actividad WHERE actividad.id=:idActividad")
    //public List<Exponente> encuentraExponentesPorActividad(int idActividad);
    
    @Query("SELECT COUNT(exponente) FROM Exponente exponente WHERE exponente.nombreExponente =:nombreExponente AND exponente.apellidosExponente =:apellidosExponente AND exponente.alias =:alias")
    public int existeExponenteConEstosAtributos(String nombreExponente, String apellidosExponente, String alias);
    
}
