package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class PrestamoService {

    private final List<Prestamo> prestamos = new ArrayList<>();
    private int countIds = 1;

    // Agregar préstamo
    public Prestamo agregarPrestamo(int clienteId,int empleadoId, double monto, double interes, int cuotas, LocalDate fechaInicio) {

        double valorCuota = calcularValorCuota(monto, interes, cuotas);
        Prestamo p = new Prestamo(
                countIds++,
                clienteId,
                empleadoId,
                monto,
                interes,
                cuotas,
                fechaInicio,
                EstadoPrestamo.PENDIENTE,
                valorCuota
        );

        prestamos.add(p);
        return p;
    }

    public List<Prestamo> listaPrestamo() {
        return prestamos;
    }

    public Prestamo buscarPrestamoId(int id) {
        for (Prestamo p : prestamos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean cambiarEstadoPrestamo(int id, EstadoPrestamo estado) {
        Prestamo p = buscarPrestamoId(id);
        if (p != null && p.getEstado() != estado) {
            p.setEstado(estado);
            return true;
        }
        return false;
    }

    private double calcularValorCuota(double monto, double interes, int cuotas) {
        if (cuotas <= 0) {
            throw new IllegalArgumentException("Las cuotas deben ser mayores a 0");
        }

        double total = monto + (monto * interes / 100);
        return total / cuotas;
    }

    // Recalcular cuotas
    public void recalcularCuotas(Prestamo prestamo) {
        double nuevaCuota = calcularValorCuota(
                prestamo.getMonto(),
                prestamo.getInteres(),
                prestamo.getCuotas()
        );
        prestamo.setValorCuota(nuevaCuota);
    }
}

