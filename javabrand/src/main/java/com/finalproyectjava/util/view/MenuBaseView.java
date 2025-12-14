package com.finalproyectjava.util.view;

import java.util.Scanner;

public abstract class MenuBaseView {
    //Atributos
    protected final Scanner consola = new Scanner(System.in); 

    public abstract void play();

    public void limpiarConsola(){
        for(int i = 0; i<50; i++){
            System.out.println();
        }
    }

}
