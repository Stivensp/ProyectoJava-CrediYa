package com.finalproyectjava.service;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;

public class ClienteService {
    //Atributos
    private final List<Cliente> clientes = new ArrayList<>();
    private int countIds = 1;
    private final PrestamoService ps;

    //Constructor
    public ClienteService(PrestamoService ps){
        this.ps = ps;
    }

    //Metodo Registrar
    public Cliente registrarCliente(String nombre, String documento, String correo, String telefono){
        Cliente cliente = new Cliente(
            countIds++,
            nombre,
            documento,
            correo,
            telefono
        );
        clientes.add(cliente);
        return cliente;
    }    
    //Lista de todos los clientes
    public List<Cliente> listaClientes(){
        return clientes;
    }
    //Buscar cliente por id
    public Cliente buscarClienteId(int id){
        for(Cliente c : clientes){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    //Actualizar cliente por id
    public Cliente actualizarCliente(int id, String nombre, String documento, String correo, String telefono){
        Cliente c = buscarClienteId(id);
        if(c != null){
            c.setNombre(nombre);
            c.setDocumento(documento);
            c.setCorreo(correo);
            c.setTelefono(telefono);
        }
        return c;
    }
    //Eliminar cliente por id
    public boolean eliminarCliente(int id){
        Cliente c = buscarClienteId(id);
        if(c != null){
            clientes.remove(c);
            return true;
        }
        return false;
    }
    // Mostrar préstamos del cliente
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
