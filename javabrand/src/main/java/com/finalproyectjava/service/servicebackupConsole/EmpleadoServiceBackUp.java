/*
package com.finalproyectjava.service;

import com.finalproyectjava.model.Empleado;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoService {
    //Atributos
    private final List<Empleado> empleados = new ArrayList<>();
    private int countIds = 1;
    //Metodo Registrar
    public Empleado registrarEmpleado(String nombre, String documento, String rol, String correo, Double salario){
        Empleado empleado = new Empleado(
            countIds++,
            nombre,
            documento,
            rol,
            correo,
            salario
        );
        empleados.add(empleado);
        return empleado;
    }
    //Lista de todos los empleados
    public List<Empleado> listaEmpleados(){
        return empleados;
    }
    //Buscar por id empleados
    public Empleado buscarEmpleadoId(int id){
        for(Empleado e : empleados){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    //Actualizar empleado
    public Empleado actualizarEmpleado(int id, String nombre, String documento, String rol, String correo, Double salario){
        Empleado e = buscarEmpleadoId(id);
        if(e != null){
            e.setNombre(nombre);
            e.setDocumento(documento);
            e.setRol(rol);
            e.setCorreo(correo);
            e.setSalario(salario);
        }
        return e;
    }
    //Eliminar Empleado
    public Boolean eliminarEmpleado(int id){
        Empleado e = buscarEmpleadoId(id);
        if(e != null){
            empleados.remove(e);
            return true;
        }
        return false;
    }
}
*/