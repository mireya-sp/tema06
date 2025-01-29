package com.mireyaserrano.tema06.Ejercicio4;

public class Ejercicio4 {
    public static void main(String[] args) {
        Punto p1 = new Punto(2.0, 6.2);
        Punto p2 = new Punto(1.5, 3.8);
        Punto p3 = new Punto();

        System.out.println("P1: " +p1);
        System.out.println("P2: " +p2);
        System.out.println("P3: " +p3);

        System.out.println("Distancia entre P1 y P2: "+ p1.distancia(p2));
    }
}
