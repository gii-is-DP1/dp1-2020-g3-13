package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    @Transactional
    public int clienteCount(){
    return (int) clienteRepo.count();
    }

    @Transactional
    public Iterable<Cliente> findCliente(){
        return clienteRepo.findAll();
    }
    

    //@Transactional (readOnly = true)
    //public Cliente findClienteById(int id) throws DataAccessException {
    //return clienteRepo.findById(id);////////
    //}*/

}
