package com.finalproyectjava.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PrestamoDAO;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class PrestamoDAOImpl implements PrestamoDAO {

    private final List<Prestamo> prestamos = new ArrayList<>();
    private int countIds = 1;

    @Override
    public Prestamo agregarPrestamoDAO(Prestamo p) {
        p.setId(countIds++);
        prestamos.add(p);
        return p;
    }

    @Override
    public List<Prestamo> listaPrestamosDAO() {
        return prestamos;
    }

    @Override
    public Prestamo buscarPrestamoIdDAO(int id) {
        return prestamos.stream()
                        .filter(prestamo -> prestamo.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public boolean cambiarEstadoPrestamoDAO(int id, EstadoPrestamo estado) {
        Prestamo p = buscarPrestamoIdDAO(id);
        if (p != null && p.getEstado() != estado) {
            p.setEstado(estado);
            return true;
        }
        return false;
    }

    @Override
    public void recalcularCuotasDAO(Prestamo prestamo) {
        if (prestamo.getCuotas() <= 0) {
            throw new IllegalArgumentException("Las cuotas deben ser mayores a 0");
        }
        double total = prestamo.getMonto() + (prestamo.getMonto() * prestamo.getInteres() / 100);
        prestamo.setValorCuota(total / prestamo.getCuotas());
    }
}
