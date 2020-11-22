package org.springframework.samples.petclinic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    public Optional<Usuario> findBynombreUsuario(String nombreUsuario);
}
