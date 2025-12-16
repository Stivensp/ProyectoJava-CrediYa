package com.finalproyectjava.dao.impl;

import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoDualDAOImpl implements PagoDAO {

    private final PagoDAO jdbcDAO;
    private final PagoDAO txtDAO;

    public PagoDualDAOImpl(PagoDAO jdbcDAO, PagoDAO txtDAO) {
        this.jdbcDAO = jdbcDAO;
        this.txtDAO = txtDAO;
    }

    @Override
    public Pago registrarPagoDAO(Pago p) {
        Pago guardado = jdbcDAO.registrarPagoDAO(p);
        txtDAO.registrarPagoDAO(guardado);
        return guardado;
    }

    @Override
    public List<Pago> listaPagoDAO() {
        return jdbcDAO.listaPagoDAO(); // Fuente principal
    }

    @Override
    public List<Pago> pagoPorPrestamoDAO(int prestamoId) {
        return jdbcDAO.pagoPorPrestamoDAO(prestamoId);
    }
}
