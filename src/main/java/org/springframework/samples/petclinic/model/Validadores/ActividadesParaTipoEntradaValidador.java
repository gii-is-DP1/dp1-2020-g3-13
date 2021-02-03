package org.springframework.samples.petclinic.model.Validadores;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.samples.petclinic.model.Actividad;


public class ActividadesParaTipoEntradaValidador implements ConstraintValidator<ActividadesParaTipoEntradaRestriccion, Object> {

    private String fechaInicio;
    private String fechaFin;
    private String actividades_id;

    @Override
    public void initialize(ActividadesParaTipoEntradaRestriccion tipoEntrada) {
        this.fechaInicio = tipoEntrada.fechaInicio();
        this.fechaFin = tipoEntrada.fechaFin();
        this.actividades_id = tipoEntrada.actividades();
        
    }

   

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        Boolean res = true;
        Object campoFechaInicio = new BeanWrapperImpl(objeto).getPropertyValue(fechaInicio);
        Object campoFechaFin = new BeanWrapperImpl(objeto).getPropertyValue(fechaFin);
        LocalDateTime fechaInicio = LocalDateTime.parse(campoFechaInicio.toString());
        LocalDateTime fechaFin = LocalDateTime.parse(campoFechaFin.toString());
        Object actividades = new BeanWrapperImpl(objeto).getPropertyValue(actividades_id);
        List<Actividad> listaActividades = (List<Actividad>) actividades;
        for (int i = 0; i < listaActividades.size(); i++) {
            Actividad actividadIterada = (Actividad) listaActividades.get(i);
            if (actividadIterada.getFechaInicio().isBefore(fechaInicio) || actividadIterada.getFechaFin().isAfter(fechaFin)) {
                res = false;
                break;
            } 
        }
           

        return res;
    }

}
