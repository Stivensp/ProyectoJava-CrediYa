package com.finalproyectjava.dao.impl;

import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoDualDAOImpl implements PagoDAO {

    private final PagoDAO jdbcDAO;
    private final PagoDAO txtDAO;

    public PagoDualDAOImpl(PagoDAO jdbcDAO, PagoDAO txtDAO) {

        if (jdbcDAO == null || txtDAO == null) {
            throw new IllegalArgumentException("Los DAOs no pueden ser null");
        }

        this.jdbcDAO = jdbcDAO;
        this.txtDAO = txtDAO;
    }

    @Override
    public Pago registrarPagoDAO(Pago p) {

        // Guardar primero en BD
        Pago guardado = jdbcDAO.registrarPagoDAO(p);

        // Intentar guardar en TXT (backup)
        try {
            txtDAO.registrarPagoDAO(guardado);
        } catch (RuntimeException e) {

            System.err.println(" No se pudo guardar el pago en TXT: " + e.getMessage());
        }

        return guardado;
    }

    @Override
    public List<Pago> listaPagoDAO() {
        return jdbcDAO.listaPagoDAO(); 
    }

    @Override
    public List<Pago> pagoPorPrestamoDAO(int prestamoId) {
        return jdbcDAO.pagoPorPrestamoDAO(prestamoId);
    }

    @Override
    public double totalPagadoPorPrestamoDAO(int prestamoId) {
        return jdbcDAO.totalPagadoPorPrestamoDAO(prestamoId);
    }
}
