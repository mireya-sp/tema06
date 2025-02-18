package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import com.mireyaserrano.tema06.Escaner;

import java.time.LocalDateTime;

public class Main {

    private static Hospital hospital;

    private static ColaConsulta colaConsulta;

    public static void main(String[] args){
        System.out.println("=== HOSPITAL ===\n1. Añadir paciente\n2. Atender siguiente paciente\n3. Mostrar estado de las colas\n4. Salir");
        int opcion = Escaner.solicitarInt("Introduce una opcion", 1, 4);
        do {
            hospital.mostrarEstado();
            switch (opcion) {
                //case 1 -> ;
                //case 2 -> ;
                case 3 -> System.out.println();
                case 4 -> System.out.println("Saliendo...");
            }

        }while (opcion != 4);
    }

    public static void opcion1(){
        System.out.println("En qué cola quieres ingresar el paciente?");
        int cola = Integer.parseInt(Escaner.lector.nextLine()) - 1;
        System.out.println("Introduce el nombre del paciente: ");
        String nombre = Escaner.lector.nextLine();
        System.out.println("Introduce el SIP del paciene: ");
        String sip =  Escaner.lector.nextLine();
        Paciente newPaciente = new Paciente(nombre, sip, LocalDateTime.now());

        //colaConsulta.anyadirPaciente();
    }


}
