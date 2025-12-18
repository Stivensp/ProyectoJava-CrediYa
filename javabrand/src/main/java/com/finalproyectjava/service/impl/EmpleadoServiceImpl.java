package com.finalproyectjava.service.impl;

import java.util.List;

import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.exceptions.EmpleadoNoEncontradoException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.service.serviceInterfaces.IEmpleadoService;

public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoDAO empleadoDAO;

    public EmpleadoServiceImpl(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public Empleado registrarEmpleado(String nombre, String documento, String rol,
                                      String correo, Double salario)
            throws ValidacionException {

        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre no puede estar vacío");
        }

        if (documento == null || documento.isBlank()) {
            throw new ValidacionException("El documento no puede estar vacío");
        }

        if (salario == null || salario <= 0) {
            throw new ValidacionException("El salario debe ser mayor a 0");
        }

        Empleado empleado = new Empleado(0, nombre, documento, rol, correo, salario);
        return empleadoDAO.registrarEmpleadoDAO(empleado);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listaEmpleadosDAO();
    }

    @Override
    public Empleado buscarEmpleadoId(int id) throws EmpleadoNoEncontradoException {
        Empleado empleado = empleadoDAO.buscarEmpleadoIdDAO(id);

        if (empleado == null) {
            throw new EmpleadoNoEncontradoException("No se encontró el empleado con ID: " + id);
        }

        return empleado;
    }

    @Override
    public Empleado actualizarEmpleado(int id, String nombre, String documento,
                                       String rol, String correo, Double salario)
            throws EmpleadoNoEncontradoException, ValidacionException {

        Empleado empleado = buscarEmpleadoId(id);

        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("El nombre no puede estar vacío");
        }

        if (documento == null || documento.isBlank()) {
            throw new ValidacionException("El documento no puede estar vacío");
        }

        if (salario == null || salario <= 0) {
            throw new ValidacionException("El salario debe ser mayor a 0");
        }

        empleado.setNombre(nombre);
        empleado.setDocumento(documento);
        empleado.setRol(rol);
        empleado.setCorreo(correo);
        empleado.setSalario(salario);

        empleadoDAO.actualizarEmpleadoDAO(empleado);
        return empleado;
    }

    @Override
    public void eliminarEmpleado(int id) throws EmpleadoNoEncontradoException {
        buscarEmpleadoId(id);
        empleadoDAO.eliminarEmpleadoDAO(id);
    }
}
