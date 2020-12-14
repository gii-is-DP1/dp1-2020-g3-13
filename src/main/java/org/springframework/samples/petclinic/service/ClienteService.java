package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    @Transactional
    public int clienteCount() {
        return (int) clienteRepo.count();
    }

    @Transactional
    public Iterable<Cliente> findCliente() {
        return clienteRepo.findAll();
    }

    @Transactional
    public void saveCliente(Cliente cliente)throws DataAccessException{
        clienteRepo.save(cliente);
    }

    public Cliente findClienteByUsuario(String usuario) throws DataAccessException{
        return clienteRepo.listadoClienteByUsuario(usuario);
    }

    public void deleteCliente(Cliente cliente) throws DataAccessException{
        clienteRepo.delete(cliente);
    }

    public void modifyUsuarioCliente(Cliente cliente, Cliente clienteActual) throws DataAccessException{
		cliente.setId(clienteActual.getId());
		cliente.setUsuario(clienteActual.getUsuario());
        saveCliente(cliente);
    }
}
