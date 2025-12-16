package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PrestamoDAO;
import com.finalproyectjava.model.Pago;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class PrestamoService {
    private final PagoService pagoService;
    private final PrestamoDAO prestamoDAO;

    // Constructor que recibe el PrestamoDAO y el PagoService
    public PrestamoService(PrestamoDAO prestamoDAO, PagoService pagoService) {
        this.prestamoDAO = prestamoDAO;
        this.pagoService = pagoService;
    }

    // Método para agregar un nuevo préstamo
    public Prestamo agregarPrestamo(int clienteId, int empleadoId, double monto, double interes, int cuotas) {
        if (monto <= 0 || cuotas <= 0) {
            throw new IllegalArgumentException("El monto y las cuotas deben ser mayores a 0");
        }

        LocalDate fechaInicio = LocalDate.now();
        double valorCuota = calcularValorCuota(monto, interes, cuotas);

        Prestamo prestamo = new Prestamo(0, clienteId, empleadoId, monto, interes, cuotas, fechaInicio, EstadoPrestamo.PENDIENTE, valorCuota);
        return prestamoDAO.agregarPrestamoDAO(prestamo);
    }

    // Obtener lista de todos los préstamos
    public List<Prestamo> listaPrestamo() {
        return prestamoDAO.listaPrestamosDAO();
    }

    // Buscar préstamo por ID
    public Prestamo buscarPrestamoId(int id) {
        Prestamo prestamo = prestamoDAO.buscarPrestamoIdDAO(id);
        if (prestamo == null) {
            throw new IllegalArgumentException("Préstamo no encontrado");
        }
        return prestamo;
    }

    // Cambiar el estado del préstamo (Pendiente/Pagado)
    public boolean cambiarEstadoPrestamo(int id, EstadoPrestamo estado) {
        if (prestamoDAO.cambiarEstadoPrestamoDAO(id, estado)) {
            System.out.println("Estado actualizado a: " + estado);
            return true;
        } else {
            throw new IllegalArgumentException("No se pudo cambiar el estado del préstamo");
        }
    }

    // Recalcular cuotas de un préstamo
    public void recalcularCuotas(Prestamo prestamo) {
        if (prestamo.getCuotas() <= 0) {
            throw new IllegalArgumentException("El número de cuotas debe ser mayor a 0");
        }
        prestamoDAO.recalcularCuotasDAO(prestamo);
    }

    // Calcular el total del préstamo con interés
    public double calcularTotalPrestamo(Prestamo prestamo) {
        return prestamo.getMonto() + (prestamo.getMonto() * prestamo.getInteres() / 100);
    }

    // Calcular el total pagado por un préstamo
    public double calcularTotalPagado(List<Pago> pagos) {
        return pagos.stream().mapToDouble(Pago::getMonto).sum();
    }

    // Calcular la deuda restante de un préstamo
    public double calcularDeudaRestante(Prestamo prestamo, List<Pago> pagos) {
        double totalPrestamo = calcularTotalPrestamo(prestamo);
        double totalPagado = calcularTotalPagado(pagos);
        return Math.max(0, totalPrestamo - totalPagado);
    }

    // Generar el detalle de cuotas de un préstamo
    public List<String> generarDetalleCuotas(Prestamo prestamo, List<Pago> pagos) {
        List<String> detalle = new ArrayList<>();
        double totalPrestamo = calcularTotalPrestamo(prestamo);
        double totalPagado = calcularTotalPagado(pagos);
        double restante = totalPrestamo;

        for (int i = 1; i <= prestamo.getCuotas(); i++) {
            restante -= prestamo.getValorCuota();
            totalPagado = Math.max(0, totalPagado - prestamo.getValorCuota());
            detalle.add(String.format("Cuota %d | Valor: %.2f | Restante: %.2f", i, prestamo.getValorCuota(), Math.max(0, restante)));
        }

        return detalle;
    }

    // Verificar si el préstamo ha sido pagado completamente y actualizar su estado
    public void verificarYFinalizarPrestamo(Prestamo prestamo, List<Pago> pagos) {
        double totalPrestamo = calcularTotalPrestamo(prestamo);
        double totalPagado = calcularTotalPagado(pagos);

        if (totalPagado >= totalPrestamo && prestamo.getEstado() != EstadoPrestamo.PAGADO) {
            prestamoDAO.cambiarEstadoPrestamoDAO(prestamo.getId(), EstadoPrestamo.PAGADO);
            System.out.println("El préstamo ha sido marcado como PAGADO.");
        }
    }

    // Obtener los pagos relacionados a un préstamo específico
    public List<Pago> obtenerPagosPrestamo(int prestamoId) {
        return pagoService.pagoPorPrestamo(prestamoId);
    }

    // Método que actualiza el estado del préstamo tras verificar pagos
    public void actualizarEstadoPrestamo(int prestamoId) {
        Prestamo prestamo = buscarPrestamoId(prestamoId);
        List<Pago> pagos = obtenerPagosPrestamo(prestamoId);
        verificarYFinalizarPrestamo(prestamo, pagos);
    }

    // Calcular el valor de la cuota
    private double calcularValorCuota(double monto, double interes, int cuotas) {
        if (cuotas <= 0) {
            throw new IllegalArgumentException("El número de cuotas debe ser mayor a 0");
        }
        double total = monto + (monto * interes / 100);
        return total / cuotas;
    }
}
