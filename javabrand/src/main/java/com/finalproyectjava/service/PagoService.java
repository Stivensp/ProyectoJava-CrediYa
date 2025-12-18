package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.exceptions.MontoInvalidoException;
import com.finalproyectjava.exceptions.PagoNoRegistradoException;
import com.finalproyectjava.exceptions.PrestamoIdInvalidoException;
import com.finalproyectjava.model.Pago;

public class PagoService {

    private final PagoDAO pagoDAO;

    public PagoService(PagoDAO pagoDAO) {
        if (pagoDAO == null) {
            throw new IllegalArgumentException("PagoDAO no puede ser null");
        }
        this.pagoDAO = pagoDAO;
    }

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
            throw new PagoNoRegistradoException("No se pudo registrar el pago", e);
        }
    }

    public List<Pago> listaPagos() {
        return pagoDAO.listaPagoDAO();
    }

    public List<Pago> pagoPorPrestamo(int prestamoId) {
        validarPrestamoId(prestamoId);
        return pagoDAO.pagoPorPrestamoDAO(prestamoId);
    }

    public double totalPagadoPorPrestamo(int prestamoId) {
        validarPrestamoId(prestamoId);
        return Math.max(0, pagoDAO.totalPagadoPorPrestamoDAO(prestamoId));
    }

    private void validarPrestamoId(int prestamoId) {
        if (prestamoId <= 0) {
            throw new PrestamoIdInvalidoException("ID de préstamo inválido");
        }
    }

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto del pago debe ser mayor a 0");
        }
    }
}
