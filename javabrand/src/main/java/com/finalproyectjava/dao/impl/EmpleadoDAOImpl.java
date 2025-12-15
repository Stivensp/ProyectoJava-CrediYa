package com.finalproyectjava.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.model.Empleado;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    private final List<Empleado> empleados = new ArrayList<>();
    private int countIds = 1;

    @Override
    public Empleado registrarEmpleadoDAO(Empleado e) {
        e.setId(countIds++);
        empleados.add(e);
        return e;
    }

    @Override
    public List<Empleado> listaEmpleadosDAO() {
        return empleados;
    }

    @Override
    public Empleado buscarEmpleadoIdDAO(int id) {
        return empleados.stream()
                        .filter(emp -> emp.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public Empleado actualizarEmpleadoDAO(Empleado e) {
        Empleado existente = buscarEmpleadoIdDAO(e.getId());
        if (existente != null) {
            existente.setNombre(e.getNombre());
            existente.setDocumento(e.getDocumento());
            existente.setRol(e.getRol());
            existente.setCorreo(e.getCorreo());
            existente.setSalario(e.getSalario());
        }
        return existente;
    }

    @Override
    public boolean eliminarEmpleadoDAO(int id) {
        return empleados.removeIf(emp -> emp.getId() == id);
    }
}
