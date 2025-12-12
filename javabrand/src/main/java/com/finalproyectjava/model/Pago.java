package com.finalproyectjava.model;

public class Pago {
    //Atributos
    private int id;
    private int prestamo_id;
    private String fecha_pago;
    private double monto;
    //Constructor
    public Pago (int id, int prestamo_id, String fecha_pago, double monto){
        this.id = id;
        this.prestamo_id = prestamo_id;
        this.fecha_pago = fecha_pago;
        this.monto = monto;
    }
    //Setters
    public void setId (int id){
        this.id = id;
    }
    public void setprestamo_id(int prestamo_id){
        this.prestamo_id = prestamo_id;
    }
    public void setfecha_pago (String fecha_pago){
        this.fecha_pago = fecha_pago;
    }
    public void setmonto(double monto){
        this.monto = monto;
    }
    //Getters
    public int getId (int id){
        return this.id = id;
    }
    public int getprestamo_id(int prestamo_id){
       return this.prestamo_id = prestamo_id;
    }
    public String getfecha_pago (String fecha_pago){
        return this.fecha_pago = fecha_pago;
    }
    public double getmonto(double monto){
       return this.monto = monto;
    }

    //ToString
    @Override

    public String toString(){
        return 
        " id " + id +
        " prestamo id " + prestamo_id +
        " fecha pago " + fecha_pago +
        " monto " + monto
        ;
    }

}
