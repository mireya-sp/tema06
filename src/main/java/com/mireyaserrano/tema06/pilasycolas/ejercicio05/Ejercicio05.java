package com.mireyaserrano.tema06.pilasycolas.ejercicio05;

public class Ejercicio05 {
    public static void main(String[] args) {
        ExpresionAritmetica exp = new ExpresionAritmetica("12 3 5 + 2 * -");
        System.out.println(exp);
        System.out.println(exp.evaluate());
    }
}
