package com.mireyaserrano.tema06.pilasycolas.ejercicio04;

import java.util.Scanner;

public class Ejercicio04 {
    public static void main(String[] args) {
        // Vamos a leer el archivo a parsear desde la entrada estándar
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        // Mientras haya datos en la entrada estándar, los leemos línea a línea
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();

        HtmlParser htmlParser = new HtmlParser(sb.toString());
        if (htmlParser.parse()) {
            System.out.println("El documento está bien formado");
        } else {
            System.err.println(htmlParser.getErrorMessage());
        }

    }
}
