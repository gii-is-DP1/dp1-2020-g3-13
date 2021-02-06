package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.CarritoRepository;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.repository.ConsultaRepository;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.LineaFactura;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private LineaFacturaService lineaFacturaService;
   
    @Autowired
    private CarritoService carritoService;

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
        if(carritoService.dimeCarritoUsuario(cliente.getUsuario().getNombreUsuario())!=null){
            for (LineaFactura lf :  carritoService.dimeLineaFacturasDeCarrito(carritoService.dimeCarritoUsuario(cliente.getUsuario().getNombreUsuario()).getId())) {
                lineaFacturaService.borrarLinea(lf);
            }
           
          //  carritoService.dimeCarritoUsuario(cliente.getUsuario().getNombreUsuario()).getLineasFacturas()
        carritoService.deleteCarrito(carritoService.dimeCarritoUsuario(cliente.getUsuario().getNombreUsuario()));
        for (Entrada en : entradaService.buscaEntradaPorClienteId(cliente.getId())) {
            en.setCliente(null);
            entradaService.guardarEntrada(en);
        }
        
         } //carritoRepo
        consultaService.eliminaTodasConsulta(cliente.getId());
      //  entradaRepoa
        clienteRepo.delete(cliente);
    }

    public void modifyUsuarioCliente(Cliente cliente, Cliente clienteActual) throws DataAccessException{
		cliente.setId(clienteActual.getId());
		cliente.setUsuario(clienteActual.getUsuario());
        saveCliente(cliente);
    }
}
