package com.mireyaserrano.tema06.Ejercicio3;

import com.mireyaserrano.tema06.Escaner;

public class Ejercicio3 {

    private static void menuConsultas(){
        System.out.println("***************\n** CONSULTAS **\n***************\n1. Por grupo\n2. Por edad\n3. Por nia\n4. Por apellidos\n--------------------\n0. Volver al menú principal");
    }

    private static void bajaAlumno(String mensaje){
        System.out.println(mensaje);
        int nia = Integer.parseInt(Escaner.lector.nextLine());
    }

    public static void main(String[] args){
        System.out.println("*********************\n** GESTIÓN ALUMNOS **\n*********************\n1. Nuevo alumno\n2. Baja de alumno\n3. Consultas\n------------------------------\n0. Salir\n");
        int menu = Escaner.solicitarInt("Seleccione una opcion:", 0, 3);
        switch(menu){
            case 0 -> System.out.println("...");
            case 1 -> System.out.println("");
            case 2 -> bajaAlumno("Introduce el NIA");
            case 3 -> menuConsultas();
            default -> System.out.println("como has llegado aquí");

        }

    }
}
