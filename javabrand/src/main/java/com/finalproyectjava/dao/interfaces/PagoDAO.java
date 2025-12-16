package com.finalproyectjava.dao.interfaces;

import java.util.List;

import com.finalproyectjava.model.Pago;

public interface PagoDAO {
    Pago registrarPagoDAO(Pago p);
    List<Pago> listaPagoDAO();
    List<Pago> pagoPorPrestamoDAO(int prestamoId);
    double totalPagadoPorPrestamoDAO(int prestamoId);
}
