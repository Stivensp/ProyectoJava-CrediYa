/*
package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.model.Pago;


public class PagoService {
    //Atributos
    private final List<Pago> pagos = new ArrayList<>();
    private int countIds = 1;

    //Registrar pago
    public Pago registrarPago(int prestamoId, LocalDate fechaPago, double monto){
        Pago p = new Pago(
            countIds++,
            prestamoId,
            fechaPago,
            monto
        );
        pagos.add(p);
        return p;
    }
    //Lista de todos los pagos
    public List<Pago> listaPagos(){
        return pagos;
    }
    //Ver pagos por prestamo
    public List<Pago> pagoPorPrestamo(int prestamoId){
        List<Pago> listPresPago = new ArrayList<>();

        for(Pago p : pagos){
            if(p.getPrestamoId() == prestamoId){
                listPresPago.add(p);
            }
        }
        return listPresPago;
    }
}
*/