package com.mireyaserrano.tema06.Ejercicio6;

import com.mireyaserrano.tema06.Escaner;

import java.util.GregorianCalendar;

public class Ejercicio6 {
    public static void main(String[] args){
        int opcion = 0;
        do{
            System.out.println("*************************\n** GESTIÓN DE BICICLETAS **\n*************************\n1.- Añadir bicicleta …\n2.- Vender bicicleta ...\n3.- Consultar bicicleta …\n4.- Mostrar stock\n------------------------------------\n0.- Salir");
            opcion = Integer.parseInt(Escaner.lector.nextLine());
            /*switch (opcion){
                case 0 -> ;
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 4 -> ;
                default -> System.err.println("Opción no válida, introduce un número del 0 al 4");
            }*/
        }while (opcion != 0);
    }



    public void addB(){
        System.out.println("Introduce la referencia: ");
        String ref = Escaner.lector.nextLine();
        System.out.println("Introduce la marca: ");
        String marca = Escaner.lector.nextLine();
        System.out.println("Introduce el modelo: ");
        String modelo = Escaner.lector.nextLine();
        System.out.println("Introduce el peso en kg: ");
        float peso = Float.parseFloat(Escaner.lector.nextLine());
        System.out.println("Introduce el tamaño de ruedas en pulgadas: ");
        float tamanyo = Float.parseFloat(Escaner.lector.nextLine());
        System.out.println("Tiene motor: (s/n)");
        boolean motor = (Escaner.lector.nextLine() == "s");
        System.out.println("Introduce la fecha de fabricación: ");
        String fecha = Escaner.lector.nextLine();
        System.out.println("Introduce el precio: ");
        float precio = Float.parseFloat(Escaner.lector.nextLine());

    }
}





