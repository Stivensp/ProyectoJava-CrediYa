package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PrestamoDAO;
import com.finalproyectjava.exceptions.DatosPrestamoInvalidosException;
import com.finalproyectjava.exceptions.EstadoPrestamoException;
import com.finalproyectjava.exceptions.PrestamoNoEncontradoException;
import com.finalproyectjava.model.Pago;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class PrestamoService {

    private final PagoService pagoService;
    private final PrestamoDAO prestamoDAO;

    public PrestamoService(PrestamoDAO prestamoDAO, PagoService pagoService) {
        this.prestamoDAO = prestamoDAO;
        this.pagoService = pagoService;
    }

    public Prestamo agregarPrestamo(int clienteId, int empleadoId,
                                    double monto, double interes, int cuotas) {

        if (monto <= 0 || cuotas <= 0) {
            throw new DatosPrestamoInvalidosException(
                    "El monto y las cuotas deben ser mayores a 0"
            );
        }

        LocalDate fechaInicio = LocalDate.now();
        double valorCuota = calcularValorCuota(monto, interes, cuotas);

        Prestamo prestamo = new Prestamo(
                0,
                clienteId,
                empleadoId,
                monto,
                interes,
                cuotas,
                fechaInicio,
                EstadoPrestamo.PENDIENTE,
                valorCuota
        );

        return prestamoDAO.agregarPrestamoDAO(prestamo);
    }

    public List<Prestamo> listaPrestamo() {
        return prestamoDAO.listaPrestamosDAO();
    }

    public Prestamo buscarPrestamoId(int id) {
        Prestamo prestamo = prestamoDAO.buscarPrestamoIdDAO(id);
        if (prestamo == null) {
            throw new PrestamoNoEncontradoException("Préstamo no encontrado");
        }
        return prestamo;
    }

    public boolean cambiarEstadoPrestamo(int id, EstadoPrestamo estado) {
        boolean actualizado = prestamoDAO.cambiarEstadoPrestamoDAO(id, estado);

        if (!actualizado) {
            throw new EstadoPrestamoException(
                    "No se pudo cambiar el estado del préstamo"
            );
        }

        return true;
    }

    public void recalcularCuotas(Prestamo prestamo) {
        if (prestamo.getCuotas() <= 0) {
            throw new DatosPrestamoInvalidosException(
                    "El número de cuotas debe ser mayor a 0"
            );
        }
        prestamoDAO.recalcularCuotasDAO(prestamo);
    }

    public double calcularTotalPrestamo(Prestamo prestamo) {
        return prestamo.getMonto()
                + (prestamo.getMonto() * prestamo.getInteres() / 100);
    }

    public double calcularTotalPagado(List<Pago> pagos) {
        return pagos.stream()
                .mapToDouble(Pago::getMonto)
                .sum();
    }

    public double calcularDeudaRestante(Prestamo prestamo, List<Pago> pagos) {
        return Math.max(
                0,
                calcularTotalPrestamo(prestamo) - calcularTotalPagado(pagos)
        );
    }

    public List<String> generarDetalleCuotas(Prestamo prestamo, List<Pago> pagos) {
        List<String> detalle = new ArrayList<>();
        double restante = calcularTotalPrestamo(prestamo);

        for (int i = 1; i <= prestamo.getCuotas(); i++) {
            restante -= prestamo.getValorCuota();
            detalle.add(String.format(
                    "Cuota %d | Valor: %.2f | Restante: %.2f",
                    i,
                    prestamo.getValorCuota(),
                    Math.max(0, restante)
            ));
        }

        return detalle;
    }

    public void verificarYFinalizarPrestamo(Prestamo prestamo, List<Pago> pagos) {
        if (calcularTotalPagado(pagos) >= calcularTotalPrestamo(prestamo)
                && prestamo.getEstado() != EstadoPrestamo.PAGADO) {

            prestamoDAO.cambiarEstadoPrestamoDAO(
                    prestamo.getId(),
                    EstadoPrestamo.PAGADO
            );
        }
    }

    public List<Pago> obtenerPagosPrestamo(int prestamoId) {
        return pagoService.pagoPorPrestamo(prestamoId);
    }

    public void actualizarEstadoPrestamo(int prestamoId) {
        Prestamo prestamo = buscarPrestamoId(prestamoId);
        List<Pago> pagos = obtenerPagosPrestamo(prestamoId);
        verificarYFinalizarPrestamo(prestamo, pagos);
    }

    private double calcularValorCuota(double monto, double interes, int cuotas) {
        if (cuotas <= 0) {
            throw new DatosPrestamoInvalidosException(
                    "El número de cuotas debe ser mayor a 0"
            );
        }
        return (monto + (monto * interes / 100)) / cuotas;
    }
}
