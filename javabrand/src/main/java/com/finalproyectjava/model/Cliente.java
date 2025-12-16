package com.finalproyectjava.model;

public class Cliente extends Persona {
    //Atibutos
    private String telefono;

    //Constructor
    public Cliente (int id, String nombre, String documento, String correo, String telefono){
        super(id, nombre, documento, correo);
        this.telefono = telefono;
    }

    //Setters
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    //Getters
    public String getTelefono(){
        return telefono;
    }
    
    //toString
    @Override
    public String toString(){
        return super.toString() +
        " telefono " + telefono;
    }
}
