package org.springframework.samples.petclinic.model.Validadores;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.service.TipoEntradaService;

public class ActividadAforoYNumeroEntradasValidacion
        implements ConstraintValidator<ActividadAforoYNumeroEntradasRestriccion, Object> {

    @Autowired
    private TipoEntradaService tipoEntradaService;

    private String numEntradas;
    private String actividades;

    @Override
    public void initialize(ActividadAforoYNumeroEntradasRestriccion tipoEntrada) {
        this.numEntradas = tipoEntrada.numEntradas();
        this.actividades = tipoEntrada.actividades();

    }

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {
        Boolean res = true;
        Object campoNumEntradas = new BeanWrapperImpl(objeto).getPropertyValue(numEntradas);
        Integer valorNumEntradas = Integer.valueOf(campoNumEntradas.toString());
        Object campoActividades = new BeanWrapperImpl(objeto).getPropertyValue(actividades);
        List<Actividad> listaActividades = (List<Actividad>) campoActividades;
        for (int i = 0; i < listaActividades.size(); i++) {
            Actividad actividadIterada = (Actividad) listaActividades.get(i);
            if (sumaAforo(valorNumEntradas, actividadIterada, actividadIterada.getLugarRealizacion().getAforo())) {
                res = false;
            }

        }
        return res;
    }

    private boolean sumaAforo(Integer valorNumEntradas, Actividad actividadIterada, Integer aforo) {
        if (tipoEntradaService != null) {
            LugarRealizacion lugar = actividadIterada.getLugarRealizacion();
            List<TipoEntrada> tiposEntradaCompartiendoActividad = tipoEntradaService
                    .devuelveTodasLasEntradasParaElLugar(lugar.getId());
            Integer sumaEntradas = tiposEntradaCompartiendoActividad.stream().mapToInt(x -> x.getNumEntradas()).sum();

            return (valorNumEntradas + sumaEntradas) > aforo;
        } else {
            return false;
        }
    }

}
