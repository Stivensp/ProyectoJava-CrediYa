package com.finalproyectjava.dao.interfaces;

import java.util.List;

import com.finalproyectjava.model.Empleado;

public interface  EmpleadoDAO {
    Empleado registrarEmpleadoDAO(Empleado e);
    List<Empleado> listaEmpleadosDAO();
    Empleado buscarEmpleadoIdDAO(int id);
    Empleado actualizarEmpleadoDAO(Empleado e);
    boolean eliminarEmpleadoDAO(int id);
}
