package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class PrestamoService {
    //Atributos
    private final List<Prestamo> prestamos = new ArrayList<>();
    private int countIds = 1;

    //Agregar prestamo
    public Prestamo agregarPrestamo(int clienteId, int empleadoId, double monto, double interes, int cuotas, LocalDate fechaInicio, EstadoPrestamo estado){
        Prestamo p = new Prestamo(
            countIds++,
            clienteId,
            empleadoId,
            monto,
            interes,
            cuotas,
            fechaInicio,
            estado
        );
        prestamos.add(p);
        return p;
    }
    //Lista de todos los prestamos
    public List<Prestamo> listaPrestamo(){
        return prestamos;
    }
    //Buscar prestamo por id
    public Prestamo buscarPrestamoId(int id){
        for(Prestamo p : prestamos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    //Cambiar estado por id
    public boolean cambiarEstadoPrestamo(int id, EstadoPrestamo estado){
        Prestamo findOut = buscarPrestamoId(id);

        if(findOut != null){
            if(findOut.getEstado() != estado){
                findOut.setEstado(estado);                
                return true;
            }
        }
        return false;
    }
    //Recalcular cuotas
    public void recalcularCuotas(){
        System.out.println("No esta este metodo aun");
    }
}
