package org.springframework.samples.petclinic.model.Validadores;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.EventoService;
public class ActividadParaEventoValidador implements ConstraintValidator<ActividadParaEventoRestriccion, Object>{
    private String fechaInicio;
    private String fechaFin;
    private String evento;
 
    @Override
    public void initialize(ActividadParaEventoRestriccion actividad) {
        this.fechaInicio = actividad.fechaInicio();
        this.fechaFin = actividad.fechaFin();
        this.evento = actividad.evento();
        
        
    }

   @Autowired
   private EventoService eventoService;

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        Boolean res = true;
        if(eventoService!=null){
       
        Object campoFechaInicio = new BeanWrapperImpl(objeto).getPropertyValue(fechaInicio);
        Object campoFechaFin = new BeanWrapperImpl(objeto).getPropertyValue(fechaFin);
        LocalDateTime fechaInicio = LocalDateTime.parse(campoFechaInicio.toString());
        LocalDateTime fechaFin = LocalDateTime.parse(campoFechaFin.toString());
        Evento event = (Evento) new BeanWrapperImpl(objeto).getPropertyValue(evento);
        Evento evanto= eventoService.findEventoById(event.getId());

    
        LocalDate fechaInievento= evanto.getFechaInicio();
        LocalDate fechaFinEvento= evanto.getFechaFin();
        LocalDate parseoActIni= fechaInicio.toLocalDate();
        LocalDate parseoActFin= fechaFin.toLocalDate();
        res=((fechaInievento.isBefore(parseoActIni)||fechaInievento.equals(parseoActIni))&&(fechaFinEvento.equals(parseoActFin)||fechaFinEvento.isAfter(parseoActFin)));
             
        
         
    }
           

        return res;
  
}
}
