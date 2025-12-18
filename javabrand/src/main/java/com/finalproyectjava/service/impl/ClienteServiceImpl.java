package com.finalproyectjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.ClienteDAO;
import com.finalproyectjava.exceptions.ClienteNoEncontradoException;
import com.finalproyectjava.exceptions.OperacionNoPermitidaException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.service.serviceInterfaces.IClienteService;
import com.finalproyectjava.service.serviceInterfaces.IPrestamoService;

public class ClienteServiceImpl implements IClienteService {

    private final ClienteDAO clienteDAO;
    private final IPrestamoService prestamoService;

    public ClienteServiceImpl(ClienteDAO clienteDAO, IPrestamoService prestamoService) {
        this.clienteDAO = clienteDAO;
        this.prestamoService = prestamoService;
    }

    @Override
    public Cliente registrarCliente(String nombre, String documento, String correo, String telefono)
            throws ValidacionException {

        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre no puede estar vacío");
        }

        if (documento == null || documento.isBlank()) {
            throw new ValidacionException("El documento no puede estar vacío");
        }

        Cliente cliente = new Cliente(0, nombre, documento, correo, telefono);
        return clienteDAO.registrarClienteDAO(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteDAO.listaClientesDAO();
    }

    @Override
    public Cliente buscarClienteId(int id) throws ClienteNoEncontradoException {
        Cliente cliente = clienteDAO.buscarClienteIdDAO(id);

        if (cliente == null) {
            throw new ClienteNoEncontradoException("No se encontró el cliente con ID: " + id);
        }

        return cliente;
    }

    @Override
    public Cliente actualizarCliente(int id, String nombre, String documento, String correo, String telefono)
            throws ClienteNoEncontradoException, ValidacionException {

        Cliente cliente = buscarClienteId(id);

        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre no puede estar vacío");
        }

        if (documento == null || documento.isBlank()) {
            throw new ValidacionException("El documento no puede estar vacío");
        }

        cliente.setNombre(nombre);
        cliente.setDocumento(documento);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);

        clienteDAO.actualizarClienteDAO(cliente);
        return cliente;
    }

    @Override
    public void eliminarCliente(int id) throws ClienteNoEncontradoException, OperacionNoPermitidaException {

        buscarClienteId(id);

        if (!prestamosCliente(id).isEmpty()) {
            throw new OperacionNoPermitidaException(
                    "No se puede eliminar el cliente porque tiene préstamos asociados");
        }

        clienteDAO.eliminarClienteDAO(id);
    }

    @Override
    public List<Prestamo> prestamosCliente(int clienteId) throws ClienteNoEncontradoException {

        buscarClienteId(clienteId);

        List<Prestamo> resultado = new ArrayList<>();

        for (Prestamo p : prestamoService.listaPrestamo()) {
            if (p.getClienteId() == clienteId) {
                resultado.add(p);
            }
        }

        return resultado;
    }
}
