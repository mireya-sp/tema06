package com.mireyaserrano.tema06.Ejercicio1;



public class Principal {

    public static void main(String[] args) {
        final int CANTIDAD_COCHES = 10001;
        Coche coche = null;
        for (int i = 0; i < CANTIDAD_COCHES ; i++) {
            coche = new Coche("Seat panda", "Rojo", false, Coche.TipoCoche.MINI,2000, Coche.ModalidadSeguro.TODO_RIESGO);
        }

        System.out.println(coche);
    }

}
