package com.mireyaserrano.tema06.Ejercicio2;

public class Ejercicio2 {

    public static void main(String args[]){
        Asignatura asignatura1 = new Asignatura("Programación", 1017, "curso 1");
        Asignatura asignatura2 = new Asignatura("Programación", 1017, "curso 1");
        System.out.println(asignatura1);
        System.out.println(asignatura2);
        System.out.println(asignatura1.equals(asignatura2));
    }

}
