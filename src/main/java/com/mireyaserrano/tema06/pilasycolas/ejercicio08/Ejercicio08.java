package com.mireyaserrano.tema06.pilasycolas.ejercicio08;

import java.util.Random;

public class Ejercicio08 {
    public static void main(String[] args) {
        Random random = new Random();
        GenericDynamicArray<Double> genericDynamicArray = new GenericDynamicArray<>(10);
        for (int i = 0; i < 10; i++) {
            genericDynamicArray.add(random.nextDouble() * 10);
        }
        System.out.println(genericDynamicArray);
        for (int i = 0; i < genericDynamicArray.size(); i++) {
            System.out.printf("genericDynamicArray[%d] = %.2f\n", i, genericDynamicArray.get(i));
        }
        int pos = random.nextInt(genericDynamicArray.size());
        double value = random.nextDouble() * 10;
        System.out.printf("Add(%d, %.2f)\n", pos, value);
        genericDynamicArray.add(pos, value);
        System.out.println(genericDynamicArray);
    }
}
