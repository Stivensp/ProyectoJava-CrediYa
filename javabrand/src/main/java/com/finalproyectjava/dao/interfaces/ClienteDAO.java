package com.finalproyectjava.dao.interfaces;

import java.util.List;

import com.finalproyectjava.model.Cliente;

public interface ClienteDAO {
    Cliente registrarClienteDAO(Cliente c);    
    List<Cliente> listaClientesDAO();     
    Cliente buscarClienteIdDAO(int id);      
    Cliente actualizarClienteDAO(Cliente c);  
    boolean eliminarClienteDAO(int id);     
}
