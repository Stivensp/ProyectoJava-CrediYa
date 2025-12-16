package com.finalproyectjava.service;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.ClienteDAO;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;


public class ClienteService {
    private final ClienteDAO clienteDAO; 
    private final PrestamoService ps;

    public ClienteService(ClienteDAO clienteDAO, PrestamoService ps){
        this.clienteDAO = clienteDAO;
        this.ps = ps;
    }

    public Cliente registrarCliente(String nombre, String documento, String correo, String telefono){
        Cliente cliente = new Cliente(0, nombre, documento, correo, telefono); 
        return clienteDAO.registrarClienteDAO(cliente); 
    }

    public List<Cliente> listaClientes(){
        return clienteDAO.listaClientesDAO();
    }

    public Cliente buscarClienteId(int id){
        return clienteDAO.buscarClienteIdDAO(id);
    }

    public Cliente actualizarCliente(int id, String nombre, String documento, String correo, String telefono){
        Cliente c = clienteDAO.buscarClienteIdDAO(id);
        if(c != null){
            c.setNombre(nombre);
            c.setDocumento(documento);
            c.setCorreo(correo);
            c.setTelefono(telefono);
            clienteDAO.actualizarClienteDAO(c);
        }
        return c;
    }

    public boolean eliminarCliente(int id){
        return clienteDAO.eliminarClienteDAO(id);
    }

    public List<Prestamo> prestamosCliente(int clienteId){
       List<Prestamo> prestamoCliente = new ArrayList<>();
       for(Prestamo p : ps.listaPrestamo()){
           if(p.getClienteId() == clienteId ){
               prestamoCliente.add(p);
           }
       }
       return prestamoCliente;
    }
}




