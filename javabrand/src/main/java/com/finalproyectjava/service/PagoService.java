package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoService {

    private final PagoDAO pagoDAO;

    public PagoService(PagoDAO pagoDAO) {
        if (pagoDAO == null) {
            throw new IllegalArgumentException("PagoDAO no puede ser null");
        }
        this.pagoDAO = pagoDAO;
    }

    // Registrar pago (fecha automática)
    public Pago registrarPago(int prestamoId, double monto) {

        validarPrestamoId(prestamoId);
        validarMonto(monto);

        Pago pago = new Pago(
                0,                 
                prestamoId,
                LocalDate.now(),
                monto
        );

        try {
            return pagoDAO.registrarPagoDAO(pago);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al registrar el pago", e);
        }
    }

    // Lista de todos los pagos
    public List<Pago> listaPagos() {
        return pagoDAO.listaPagoDAO();
    }

    // Pagos por préstamo
    public List<Pago> pagoPorPrestamo(int prestamoId) {

        validarPrestamoId(prestamoId);

        return pagoDAO.pagoPorPrestamoDAO(prestamoId);
    }

    // Total pagado por préstamo
    public double totalPagadoPorPrestamo(int prestamoId) {

        validarPrestamoId(prestamoId);

        double total = pagoDAO.totalPagadoPorPrestamoDAO(prestamoId);
        return Math.max(0, total); 
    }

    private void validarPrestamoId(int prestamoId) {
        if (prestamoId <= 0) {
            throw new IllegalArgumentException("ID de préstamo inválido");
        }
    }

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a 0");
        }
    }
}
