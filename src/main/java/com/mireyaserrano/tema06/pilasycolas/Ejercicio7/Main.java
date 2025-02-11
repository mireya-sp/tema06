package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import com.mireyaserrano.tema06.Escaner;

public class Main {

    public static void main(String[] args){
        System.out.println("=== HOSPITAL ===\n1. Añadir paciente\n2. Atender siguiente paciente\n3. Mostrar estado de las colas\n4. Salir");
        int opcion = Escaner.solicitarInt("Introduce una opcion", 1, 4);

        switch (opcion){
            case 1 -> ;
            case 2 -> ;
            case 3 -> ;
            case 4 -> System.out.println("Saliendo...");
        }
    }

}
