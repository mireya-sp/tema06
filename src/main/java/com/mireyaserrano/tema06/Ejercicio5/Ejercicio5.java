package com.mireyaserrano.tema06.Ejercicio5;

public class Ejercicio5 {
    public static void main(String[] args) {
        Circunferencia c1 = new Circunferencia(new Punto(3.2,4.0), 2.5);
        Circunferencia c2 = new Circunferencia(0.0, 0.0, 2.5);
        Circunferencia c3 = new Circunferencia();

        System.out.println(c1);
        System.out.println("Perimetro: "+ c1.perimetro() +" cm");
        System.out.println("Area: "+ c1.area() +" cm2");
        System.out.println();

        System.out.println(c2);
        System.out.println("Perimetro: "+ c2.perimetro() +" cm");
        System.out.println("Area: "+ c2.area() +" cm2");
        System.out.println();

        System.out.println(c3);
        System.out.println("Perimetro: "+ c3.perimetro() +" cm");
        System.out.println("Area: "+ c3.area() +" cm2");
        System.out.println();

        double distanciaCentro = c1.getCentro().distancia(c2.getCentro());
        System.out.println("La distancia entre los centros es de "+ distanciaCentro + " cm");
    }
}
