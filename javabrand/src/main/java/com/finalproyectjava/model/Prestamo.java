package com.finalproyectjava.model;

import java.time.LocalDate;

public class Prestamo {
    //Atributos
    private int id;
    private int clienteId;
    private int empleadoId;
    private double monto;
    private double interes;
    private int cuotas;
    private LocalDate fechaInicio;
    private EstadoPrestamo estado;
    private double valorCuota;

    //Enum
    public enum EstadoPrestamo {
        PENDIENTE,
        PAGADO
    }
    //Constructor
    public Prestamo (int id, int clienteId, int empleadoId, double monto, double interes, int cuotas, LocalDate fechaInicio, EstadoPrestamo estado, Double valorCuota){
        
        this.id = id;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.valorCuota = valorCuota;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setClienteId (int clienteId ){
        this.clienteId  = clienteId ;
    }
    public void setEmpleadoId(int empleadoId){
        this.empleadoId = empleadoId;
    }
    public void setMonto(double monto){
        this.monto = monto;
    }
    public void setInteres(double interes){
        this.interes = interes;
    }
    public void setCuotas(int cuotas){
        this.cuotas = cuotas;
    }
    public void setFechaInicio(LocalDate fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    public void setEstado(EstadoPrestamo estado){
        this.estado = estado;
    }
    public void setValorCuota(Double valorCuota){
        this.valorCuota = valorCuota;
    }

    //Getters
    public int getId(){
        return id;
    }
    public int getClienteId (){
        return clienteId ;
    }
    public int getEmpleadoId(){
        return empleadoId;
    }
    public double getMonto(){
        return monto;
    }
    public double getInteres(){
        return interes;
    }
    public int getCuotas(){
        return cuotas;
    }
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }
    public EstadoPrestamo getEstado(){
        return estado;
    }
    public Double getValorCuota(){
        return valorCuota;
    }
    //toString
    @Override
    public String toString(){
        return 
        "id " + id +
        " Cliente id "+ clienteId +
        " Empleado id " + empleadoId +
        " Monto " + monto +
        " Interes " + interes +
        " Cuotas " + cuotas +
        " Fecha inicio " + fechaInicio +
        " Estado " + estado
        ;
    }
    
}
