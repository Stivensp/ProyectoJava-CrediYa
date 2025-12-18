package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;

import com.finalproyectjava.exceptions.EmpleadoNoEncontradoException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Empleado;

public interface IEmpleadoService {

    Empleado registrarEmpleado(String nombre, String documento, String rol,
                               String correo, Double salario) throws ValidacionException;

    List<Empleado> listarEmpleados();

    Empleado buscarEmpleadoId(int id) throws EmpleadoNoEncontradoException;

    Empleado actualizarEmpleado(int id, String nombre, String documento,
                                String rol, String correo, Double salario)
            throws EmpleadoNoEncontradoException, ValidacionException;

    void eliminarEmpleado(int id) throws EmpleadoNoEncontradoException;
}
