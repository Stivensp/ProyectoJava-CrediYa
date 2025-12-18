package com.finalproyectjava.service;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.ClienteDAO;
import com.finalproyectjava.exceptions.ClienteNoEncontradoException;
import com.finalproyectjava.exceptions.OperacionNoPermitidaException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;

public class ClienteService {

    private final ClienteDAO clienteDAO;
    private final PrestamoService ps;

    public ClienteService(ClienteDAO clienteDAO, PrestamoService ps) {
        this.clienteDAO = clienteDAO;
        this.ps = ps;
    }

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

    public List<Cliente> listaClientes() {
        return clienteDAO.listaClientesDAO();
    }

    public Cliente buscarClienteId(int id) throws ClienteNoEncontradoException {

        Cliente cliente = clienteDAO.buscarClienteIdDAO(id);

        if (cliente == null) {
            throw new ClienteNoEncontradoException(
                "No se encontró el cliente con ID: " + id
            );
        }

        return cliente;
    }

    public Cliente actualizarCliente(int id, String nombre, String documento,
                                    String correo, String telefono)
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

    public void eliminarCliente(int id)
            throws ClienteNoEncontradoException, OperacionNoPermitidaException {

        buscarClienteId(id);

        if (!prestamosCliente(id).isEmpty()) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar el cliente porque tiene préstamos asociados"
            );
        }

        clienteDAO.eliminarClienteDAO(id);
    }

    public List<Prestamo> prestamosCliente(int clienteId)
            throws ClienteNoEncontradoException {

        buscarClienteId(clienteId);

        List<Prestamo> resultado = new ArrayList<>();

        for (Prestamo p : ps.listaPrestamo()) {
            if (p.getClienteId() == clienteId) {
                resultado.add(p);
            }
        }

        return resultado;
    }
}
