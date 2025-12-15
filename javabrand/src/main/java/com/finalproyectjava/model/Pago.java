package com.finalproyectjava.model;

import java.time.LocalDate;

public class Pago {
    //Atributos
    private int id;
    private int prestamoId;
    private LocalDate fechaPago;
    private double monto;
    //Constructor
    public Pago (int id, int prestamoId, LocalDate fechaPago, double monto){
        this.id = id;
        this.prestamoId = prestamoId;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }
    //Setters
    public void setId (int id){
        this.id = id;
    }
    public void setprestamoId(int prestamoId){
        this.prestamoId = prestamoId;
    }
    public void setfechaPago (LocalDate fechaPago){
        this.fechaPago = fechaPago;
    }
    public void setmonto(double monto){
        this.monto = monto;
    }
    //Getters
    public int getId (){
        return id;
    }
    public int getprestamoId(){
       return  prestamoId;
    }
    public LocalDate getfechaPago (){
        return fechaPago;
    }
    public double getmonto(){
       return monto;
    }

    //ToString
    @Override

    public String toString(){
        return 
        " id " + id +
        " prestamo id " + prestamoId +
        " fecha pago " + fechaPago +
        " monto " + monto
        ;
    }

}
