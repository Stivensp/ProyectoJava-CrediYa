package com.finalproyectjava.dao.interfaces;

import java.util.List;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public interface PrestamoDAO {
    Prestamo agregarPrestamoDAO(Prestamo p);
    List<Prestamo> listaPrestamosDAO();
    Prestamo buscarPrestamoIdDAO(int id);
    boolean cambiarEstadoPrestamoDAO(int id, EstadoPrestamo estado);
    void recalcularCuotasDAO(Prestamo prestamo);
    void actualizarEstadoYCuotaDAO(int id, EstadoPrestamo estado, double valorCuota);
    List<Prestamo> listarPrestamosPorEstadoDAO(EstadoPrestamo estado);
}
