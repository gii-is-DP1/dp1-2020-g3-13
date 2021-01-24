package org.springframework.samples.petclinic.repository;

import org.springframework.samples.petclinic.model.Cliente;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface ClienteRepository extends CrudRepository<Cliente,Integer>{

    Cliente findById(int id) throws DataAccessException;

    @Query("SELECT cliente FROM Cliente cliente WHERE cliente.usuario.nombreUsuario =:usuario")
    public Cliente listadoClienteByUsuario(String usuario);


}