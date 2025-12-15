package com.finalproyectjava.model;

//Atributos diferentes de clientes y empleados
//rol - empleado
//salario - empleado
//telefono - clientes

public class Persona {
    //Atributos
    protected int id;
    protected String nombre;
    protected String documento;
    protected String correo;

    //Constructor
    public Persona (int id, String nombre, String documento, String correo){
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.correo =correo;
    }
    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDocumento(String documento){
        this.documento = documento;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    //Getters
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDocumento(){
        return documento;
    }
    public String getCorreo(){
        return correo;
    }

    //toString
    @Override
    public String toString(){
        return 
        "id " + id +
        " nombre " + nombre +
        " documento " + documento +
        " correo " + correo 
        ;
    }
}
