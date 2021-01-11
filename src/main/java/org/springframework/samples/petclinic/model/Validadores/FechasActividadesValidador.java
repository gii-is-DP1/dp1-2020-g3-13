package org.springframework.samples.petclinic.model.Validadores;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FechasActividadesValidador implements ConstraintValidator<FechasActividadRestriccion, Object> {

    private String fechaInicio;
    private String fechaFin;

    @Override
    public void initialize(FechasActividadRestriccion fecha) {
        
        this.fechaInicio = fecha.fechaInicio();
        this.fechaFin = fecha.fechaFin();
    }

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        
        Object campoFechaInicio = new BeanWrapperImpl(objeto).getPropertyValue(fechaInicio);

        Object campoFechaFin = new BeanWrapperImpl(objeto).getPropertyValue(fechaFin);

        if(campoFechaInicio ==null || campoFechaFin == null){
            return false;
        }else{
                
        LocalDateTime fechaInicio = LocalDateTime.parse(campoFechaInicio.toString());

        LocalDateTime fechaFin = LocalDateTime.parse(campoFechaFin.toString());

       
            if (fechaInicio.isBefore(LocalDateTime.now())) {
           
                return false;
                
            } else if(fechaInicio.isAfter(fechaFin)) {
               
                return false;
            }else{
            return true;
        }
        }
        
}
    
}
