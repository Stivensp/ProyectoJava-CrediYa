package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoService {

    private final PagoDAO pagoDAO;

    public PagoService(PagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    // Registrar pago (fecha automática)
    public Pago registrarPago(int prestamoId, double monto) {
        Pago p = new Pago(
                0,                    // id lo asigna el DAO
                prestamoId,
                LocalDate.now(),       // fecha actual del sistema
                monto
        );
        return pagoDAO.registrarPagoDAO(p);
    }

    // Lista de todos los pagos
    public List<Pago> listaPagos() {
        return pagoDAO.listaPagoDAO();
    }

    // Ver pagos por préstamo
    public List<Pago> pagoPorPrestamo(int prestamoId) {
        return pagoDAO.pagoPorPrestamoDAO(prestamoId);
    }
}
