package com.finalproyectjava.service;

import java.util.List;

import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.model.Empleado;

public class EmpleadoService {
    private final EmpleadoDAO empleadoDAO;

    // Constructor que recibe el EmpleadoDAO
    public EmpleadoService(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    // Método Registrar
    public Empleado registrarEmpleado(String nombre, String documento, String rol, String correo, Double salario) {
        Empleado empleado = new Empleado(0, nombre, documento, rol, correo, salario); // id lo asigna MySQL
        return empleadoDAO.registrarEmpleadoDAO(empleado); // Persiste en DB
    }

    // Lista de todos los empleados
    public List<Empleado> listaEmpleados() {
        return empleadoDAO.listaEmpleadosDAO(); // Obtiene de la base de datos
    }

    // Buscar por id empleados
    public Empleado buscarEmpleadoId(int id) {
        return empleadoDAO.buscarEmpleadoIdDAO(id); // Busca en la base de datos
    }

    // Actualizar empleado
    public Empleado actualizarEmpleado(int id, String nombre, String documento, String rol, String correo, Double salario) {
        Empleado e = empleadoDAO.buscarEmpleadoIdDAO(id); // Busca el empleado por id
        if (e != null) {
            e.setNombre(nombre);
            e.setDocumento(documento);
            e.setRol(rol);
            e.setCorreo(correo);
            e.setSalario(salario);
            empleadoDAO.actualizarEmpleadoDAO(e); // Actualiza en la base de datos
        }
        return e;
    }

    // Eliminar Empleado
    public Boolean eliminarEmpleado(int id) {
        return empleadoDAO.eliminarEmpleadoDAO(id); // Elimina de la base de datos
    }
}
