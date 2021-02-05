package org.springframework.samples.petclinic.model;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.websocket.ClientEndpoint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClienteService;

import org.springframework.samples.petclinic.service.UsuarioService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
public class CarritoValidador {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;

    private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
    }    
    @Test
    void noDebeAñadirPreciosNegativos(){
        //Creamos usuario
        Usuario US = new Usuario();
        US.setNombreUsuario("paquito");
        US.setPassword("paquito1");
        //creamos cliente 
        Cliente cl= new Cliente();
        cl.setApellidos("paquito the boss");
        cl.setNombre("ese");
        cl.setUsuario(US);
        Carrito car= new Carrito();
        car.setCliente(cl);
        car.setTotal(-10.0);
        Validator validator = createValidator();
        Set<ConstraintViolation<Carrito>> constraintViolations = validator.validate(car);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Carrito> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("total");
		assertThat(violation.getMessage()).isEqualTo("tiene que ser mayor o igual que 0");


    }
}
