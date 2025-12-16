package com.finalproyectjava.model;

public class Empleado extends Persona {
    //Atributos
    private String rol;
    private Double salario;

    //Constructor
    public Empleado (int id, String nombre, String documento, String rol, String correo, Double salario){
        super(id, nombre, documento, correo);
        this.rol = rol;
        this.salario = salario;
    }
    //Setters
    public void setRol(String rol){
        this.rol = rol;
    }
    public void setSalario(Double salario){
        this.salario = salario;
    }
    //Getters
    public String getRol(){
        return rol;
    }
    public Double getSalario(){
        return salario;
    }
    
    //toString
    @Override
    public String toString() {
        return super.toString() +
            ", Rol: " + rol +
            ", Salario: " + salario;
    }
}
