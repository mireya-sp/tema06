package com.mireyaserrano.tema06.pilasycolas;

import java.util.Random;

public class Ejercicio1 {

    public static void main(String[] args) {
        Random random = new Random();
        DynamicArray dynamicArray = new DynamicArray(10);
        for (int i = 0; i < 10; i++) {
            dynamicArray.add(random.nextInt(1, 100) * 10);
        }
        System.out.println(dynamicArray);

        dynamicArray.add(50.3);
        System.out.println("\nAñade el num 50,3: " + dynamicArray);

        dynamicArray.add(5, 10);
        System.out.println("\nAñade en la posición 5 un 10: " + dynamicArray);

        dynamicArray.remove(7);
        System.out.println("\nQuita el de la posición 7: " + dynamicArray);

        dynamicArray.remove(50.3);
        System.out.println("\nQuita el 50,3: " + dynamicArray);

        System.out.println("\nPilla el de la posición 3: " + dynamicArray.get(3));

        dynamicArray.set(7, 13);
        System.out.println("\nset el 13 en la posición 7: " + dynamicArray);

        System.out.println("\npilla el tamaño: " + dynamicArray.size());

    }

}
