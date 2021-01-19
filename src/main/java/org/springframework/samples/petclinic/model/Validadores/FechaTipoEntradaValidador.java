package org.springframework.samples.petclinic.model.Validadores;


import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.service.EventoService;

public class FechaTipoEntradaValidador implements ConstraintValidator<FechaTipoEntradaRestriccion, Object> {

    private String fechaInicio;
    private String fechaFin;
    private String nombreEntrada;
    //private String evento_id;
    //@Autowired
    //private EventoService eventoService;

    @Override
    public void initialize(FechaTipoEntradaRestriccion TipoEntrada) {
        this.fechaInicio = TipoEntrada.fechaInicio();
        this.fechaFin = TipoEntrada.fechaFin();
        this.nombreEntrada = TipoEntrada.nombreEntrada();
        //this.evento_id = TipoEntrada.evento_id();

    }

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        Object campoFechaInicio = new BeanWrapperImpl(objeto).getPropertyValue(fechaInicio);
        Object campoFechaFin = new BeanWrapperImpl(objeto).getPropertyValue(fechaFin);
        Object campoNombre = new BeanWrapperImpl(objeto).getPropertyValue(nombreEntrada);
        //Object campoEventoId = new BeanWrapperImpl(objeto).getPropertyValue(evento_id);
        LocalDateTime fechaInicio = LocalDateTime.parse(campoFechaInicio.toString());
        LocalDateTime fechaFin = LocalDateTime.parse(campoFechaFin.toString());
        NombreTiposEntrada nombreEntrada = NombreTiposEntrada.valueOf(campoNombre.toString());
        if (fechaInicio.isBefore(LocalDateTime.now())) {
            return false;
        } else if (fechaInicio.isAfter(fechaFin)) {
            return false;
        } else {
            return compruebaFechaInicio(fechaInicio, fechaFin, nombreEntrada);
        }

    }

    // Comprueba que la fecha de Inicio se corresponda con el tipo de entrada, si es
    // diurna no puede comenzar
    // Después de las 15 horas, al igual de si es noctura no puede empezar antes de
    // dicha
    private boolean compruebaFechaInicio(LocalDateTime fechaInicio, LocalDateTime fechaFin,
            NombreTiposEntrada nombreEntrada) {
        boolean res = true;
        switch (nombreEntrada) {
            case DIURNA:
                // Si es después de las 15:59 o fechaInicio y fechaFin no son el mismo dia, no
                // puede ser diurna
                if (fechaInicio
                        .isAfter(LocalDateTime.of(fechaInicio.getYear(), fechaInicio.getMonth(),
                                fechaInicio.getDayOfMonth(), 15, 59))
                        || fechaInicio.getDayOfMonth() != fechaFin.getDayOfMonth() || fechaFin.isAfter(LocalDateTime.of(fechaFin.getYear(), fechaFin.getMonth(), fechaFin.getDayOfMonth(), 15, 59))) {
                    res = false;
                }
                break;
            case NOCTURNA:
                // Si es antes de las 15:59 no puede ser nocturna
                if (fechaInicio
                        .isBefore(LocalDateTime.of(fechaInicio.getYear(), fechaInicio.getMonth(),
                                fechaInicio.getDayOfMonth(), 15, 59))
                        || fechaInicio.getDayOfMonth() != fechaFin.getDayOfMonth()) {
                    res = false;
                }
                break;

            case PASE_UN_DIA:
                // Si es de un solo dia no puede ser fechas en dias distintos
                if (!fechaInicio.toLocalDate().equals(fechaFin.toLocalDate())) {
                    res = false;
                }
                break;

            case PASE_VARIOS_DIAS:
                // Si es de varios dias no puede ser fechas en el mismo dia
                if (fechaInicio.toLocalDate().equals(fechaFin.toLocalDate())) {
                    res = false;
                }
                break;
            default:

                break;
        }
        return res;
    }

}
