package com.finalproyectjava.service;

import java.util.List;

import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.model.Empleado;

public class EmpleadoService {
    private final EmpleadoDAO empleadoDAO;


    public EmpleadoService(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    // Método Registrar
    public Empleado registrarEmpleado(String nombre, String documento, String rol, String correo, Double salario) {
        Empleado empleado = new Empleado(0, nombre, documento, rol, correo, salario); 
        return empleadoDAO.registrarEmpleadoDAO(empleado);
    }


    public List<Empleado> listaEmpleados() {
        return empleadoDAO.listaEmpleadosDAO(); 
    }


    public Empleado buscarEmpleadoId(int id) {
        return empleadoDAO.buscarEmpleadoIdDAO(id);
    }


    public Empleado actualizarEmpleado(int id, String nombre, String documento, String rol, String correo, Double salario) {
        Empleado e = empleadoDAO.buscarEmpleadoIdDAO(id); 
        if (e != null) {
            e.setNombre(nombre);
            e.setDocumento(documento);
            e.setRol(rol);
            e.setCorreo(correo);
            e.setSalario(salario);
            empleadoDAO.actualizarEmpleadoDAO(e); 
        }
        return e;
    }

    // Eliminar Empleado
    public Boolean eliminarEmpleado(int id) {
        return empleadoDAO.eliminarEmpleadoDAO(id); 
    }
}
