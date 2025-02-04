package com.mireyaserrano.tema06.pilasycolas;

import java.util.Random;

public class Ejercicio2 {

    public static void main(String[] args){
        final int TAMANYO_PILA = 5;
        Random random = new Random();
        Pila pila = new Pila();
        for (int i = 0; i <= TAMANYO_PILA; i++){
            pila.push(random.nextInt(0, 100));
            System.out.println(pila);
        }

        for (int i = 0; i < TAMANYO_PILA; i++){
            System.out.println(pila.top());
            pila.pop();
            System.out.println(pila);
        }
    }

}
