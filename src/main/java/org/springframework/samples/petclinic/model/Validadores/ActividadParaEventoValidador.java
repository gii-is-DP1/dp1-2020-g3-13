package org.springframework.samples.petclinic.model.Validadores;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.service.EventoService;
public class ActividadParaEventoValidador implements ConstraintValidator<ActividadParaEventoRestriccion, Object>{
    private String fechaInicio;
    private String fechaFin;
    private String evento_id;
 
    @Override
    public void initialize(ActividadParaEventoRestriccion actividad) {
        this.fechaInicio = actividad.fechaInicio();
        this.fechaFin = actividad.fechaFin();
        this.evento_id = actividad.evento_id();
     
        
    }

   @Autowired
   private EventoService eventoService;

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        Boolean res = true;
        Object campoFechaInicio = new BeanWrapperImpl(objeto).getPropertyValue(fechaInicio);
        Object campoFechaFin = new BeanWrapperImpl(objeto).getPropertyValue(fechaFin);
        LocalDateTime fechaInicio = LocalDateTime.parse(campoFechaInicio.toString());
        LocalDateTime fechaFin = LocalDateTime.parse(campoFechaFin.toString());
       
        Evento event= eventoService.findEventoById(Integer.parseInt(evento_id));
        LocalDate fechaInievento= event.getFechaInicio();
        LocalDate fechaFinEvento= event.getFechaFin();
        LocalDate parseoActIni= fechaInicio.toLocalDate();
        LocalDate parseoActFin= fechaFin.toLocalDate();
        if(fechaInievento.isBefore(parseoActIni)||fechaFinEvento.isAfter(parseoActFin)){
            res = true;
        }
         
        
           

        return res;
    }

}
