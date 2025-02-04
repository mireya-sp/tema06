package com.mireyaserrano.tema06;

import java.util.Scanner;

public class Escaner {
    public final static Scanner lector = new Scanner(System.in);
    public static void dispose(){
        lector.close();
    }


    public static int solicitarInt(String mensaje, int min, int max) {
        int respuesta;
        do {
            //Solicitamos el 'int'
            System.out.println(mensaje);
            respuesta = Integer.parseInt(Escaner.lector.nextLine());
            //Validamos la respuesta
            if (respuesta<min||respuesta>max) {
                System.err.printf("El número introducido no puede ser inferior a %d, ni superior a %d.\n\n", min, max);
            }
        } while (respuesta<min||respuesta>max);
        return respuesta;
    }

}
