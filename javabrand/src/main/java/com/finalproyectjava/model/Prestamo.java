package com.finalproyectjava.model;

public class Prestamo {
    //Atributos
    private int id;
    private int cliente_id;
    private int empleado_id;
    private double monto;
    private double interes;
    private int cuotas;
    private String fecha_inicio;
    private String estado;

    //Constructor
    public Prestamo (int id, int cliente_id, int empleado_id, double monto, double interes, int cuotas, String fecha_inicio, String estado){
        
        this.id = id;
        this.cliente_id = cliente_id;
        this.empleado_id = empleado_id;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;

    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setCliente_id (int cliente_id ){
        this.cliente_id  = cliente_id ;
    }
    public void setEmpleado_id(int empleado_id){
        this.empleado_id = empleado_id;
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
    public void setFecha_inicio(String fecha_inicio){
        this.fecha_inicio = fecha_inicio;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }

    //Getters
    public int getId(int id){
        return this.id = id;
    }
    public int getCliente_id (int cliente_id ){
        return this.cliente_id  = cliente_id ;
    }
    public int getEmpleado_id(int empleado_id){
        return this.empleado_id = empleado_id;
    }
    public double getMonto(double monto){
        return this.monto = monto;
    }
    public double getInteres(double interes){
        return this.interes = interes;
    }
    public int getCuotas(int cuotas){
        return this.cuotas = cuotas;
    }
    public String getFecha_inicio(String fecha_inicio){
        return this.fecha_inicio = fecha_inicio;
    }
    public String getEstado(String estado){
        return this.estado = estado;
    }

    //toString
    @Override
    public String toString(){
        return 
        "id " + id +
        " Cliente id "+ cliente_id +
        " Empleado id " + empleado_id +
        " Monto " + monto +
        " Interes " + interes +
        " Cuotas " + cuotas +
        " Fecha inicio " + fecha_inicio +
        " Estado " + estado
        ;
    }
    
}
