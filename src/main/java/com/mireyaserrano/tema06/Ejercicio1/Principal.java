package com.mireyaserrano.tema06.Ejercicio1;



public class Principal {

    public static void main(String[] args){
        Coche c1 = new Coche();
        Coche c2 = new Coche("Seat Ibiza", "Azul", true, "0123 LVD", Coche.TipoCoche.UTILITARIO, 2025, Coche.ModalidadSeguro.TERCEROS);
        System.out.println(c1);
        System.out.println(c2);
        c1.setModelo("Peugeot 208");
        c1.setAnyo(2023);
        System.out.println(c1);
        c2.setColor("Verde");
        System.out.println(c2);

        // Probamos la clase CocheInmutable
        CocheInmutable c3 = new CocheInmutable();
        CocheInmutable c4 = new CocheInmutable();
        System.out.println(c3);
        System.out.println(c4);
    }

}
