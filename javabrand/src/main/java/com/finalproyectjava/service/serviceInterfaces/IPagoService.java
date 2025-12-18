package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;

import com.finalproyectjava.model.Pago;

public interface IPagoService {

    Pago registrarPago(int prestamoId, double monto);

    List<Pago> listarPagos();

    List<Pago> pagosPorPrestamo(int prestamoId);

    double totalPagadoPorPrestamo(int prestamoId);
}
