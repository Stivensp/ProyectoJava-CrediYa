package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;

import com.finalproyectjava.exceptions.ClienteNoEncontradoException;
import com.finalproyectjava.exceptions.OperacionNoPermitidaException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;

public interface IClienteService {

    Cliente registrarCliente(String nombre, String documento, String correo, String telefono)
            throws ValidacionException;

    List<Cliente> listarClientes();

    Cliente buscarClienteId(int id) throws ClienteNoEncontradoException;

    Cliente actualizarCliente(int id, String nombre, String documento, String correo, String telefono)
            throws ClienteNoEncontradoException, ValidacionException;

    void eliminarCliente(int id) throws ClienteNoEncontradoException, OperacionNoPermitidaException;

    List<Prestamo> prestamosCliente(int clienteId) throws ClienteNoEncontradoException;
}
