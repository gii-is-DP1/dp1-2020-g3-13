package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
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
	public void saveCliente(Cliente cliente) throws DataAccessException {
        clienteRepo.save(cliente);
    }

    @Transactional(readOnly = true)
	public Cliente findById(int clienteId) throws DataAccessException {
		return clienteRepo.findById(clienteId);
    }
    
    public void deleteCliente(int clienteId) {
        clienteRepo.delete(clienteRepo.findById(clienteId));
    }
}
