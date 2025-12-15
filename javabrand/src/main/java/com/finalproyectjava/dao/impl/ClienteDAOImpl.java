package com.finalproyectjava.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.ClienteDAO;
import com.finalproyectjava.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

    private final List<Cliente> clientes = new ArrayList<>();
    private int countIds = 1;

    @Override
    public Cliente registrarClienteDAO(Cliente c) {
        c.setId(countIds++);
        clientes.add(c);
        return c;
    }

    @Override
    public List<Cliente> listaClientesDAO() {
        return clientes;
    }

    @Override
    public Cliente buscarClienteIdDAO(int id) {
        return clientes.stream()
                       .filter(c -> c.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    @Override
    public Cliente actualizarClienteDAO(Cliente c) {
        Cliente existente = buscarClienteIdDAO(c.getId());
        if (existente != null) {
            existente.setNombre(c.getNombre());
            existente.setDocumento(c.getDocumento());
            existente.setCorreo(c.getCorreo());
            existente.setTelefono(c.getTelefono());
        }
        return existente;
    }

    @Override
    public boolean eliminarClienteDAO(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
