package com.mireyaserrano.tema06.pilasycolas.Ejercicio01;

import java.util.Random;

public class Ejercicio01 {

    public static void main(String[] args){
        Random random = new Random();
        DynamicArray dynamicArray = new DynamicArray(10);
        for (int i = 0; i < 10; i++) {
            dynamicArray.add(random.nextDouble() * 10);
        }
        System.out.println(dynamicArray);
        for (int i = 0; i < dynamicArray.size(); i++) {
            System.out.printf("dynamicArray[%d] = %.2f\n", i, dynamicArray.get(i));
        }
        System.out.println();

        int pos = random.nextInt(dynamicArray.size());
        double value = random.nextDouble() * 10;
        System.out.printf("add(%d, %.2f)\n", pos, value);
        dynamicArray.add(pos, value);
        System.out.println(dynamicArray);
        System.out.println();

        value = random.nextDouble() * 10;
        dynamicArray.add(value);
        System.out.printf("add(%.2f)\n", value);
        System.out.println(dynamicArray);
        System.out.println();

        boolean eliminado = dynamicArray.remove(value);
        System.out.printf("remove(%.2f) ", value);
        if (eliminado) {
            System.out.printf("se ha eliminado el elemento %.2f correctamente\n", value);
        } else {
            System.out.printf("no se ha podido eliminar el elemento %.2f\n", value);
        }
        System.out.println(dynamicArray);
        System.out.println();

        double valorEliminado = dynamicArray.remove(pos);
        System.out.printf("remove(%d) se ha eliminado el valor %.2f\n", pos, valorEliminado);
        System.out.println(dynamicArray);
        System.out.println();

        System.out.printf("get(%d) obtiene el elemento %.2f\n", pos, dynamicArray.get(pos));
        System.out.println(dynamicArray);
        System.out.println();

        value = 1.11;
        System.out.printf("set(%d, %.2f) ", pos, value);
        if (dynamicArray.set(pos, value)) {
            System.out.print("elemento modificado correctamente\n");
        } else {
            System.out.print("no se ha podido modificar el elemento\n");

        }
        System.out.println(dynamicArray);
        System.out.println();
    }
}
