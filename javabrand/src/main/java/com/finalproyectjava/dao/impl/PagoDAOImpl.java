package com.finalproyectjava.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoDAOImpl implements PagoDAO {

    private final List<Pago> pagos = new ArrayList<>();
    private int countIds = 1;

    @Override
    public Pago registrarPagoDAO(Pago p) {
        p.setId(countIds++);
        pagos.add(p);
        return p;
    }

    @Override
    public List<Pago> listaPagoDAO() {
        return pagos;
    }

    @Override
    public List<Pago> pagoPorPrestamoDAO(int prestamoId) {
        List<Pago> listPresPago = new ArrayList<>();
        for (Pago p : pagos) {
            if (p.getPrestamoId() == prestamoId) {
                listPresPago.add(p);
            }
        }
        return listPresPago;
    }
}
