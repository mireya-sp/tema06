package com.mireyaserrano.tema06.Ejercicio1;

import java.time.LocalDate;

public class CocheInmutable {
    private static final StringBuilder ultimasLetrasMatricula = new StringBuilder("AAA".toUpperCase());
    private static int ultimoNumeroMatricula = 0;

    public enum TipoCoche {MINI, UTILITARIO, FAMILIAR, DEPORTIVO}
    public enum ModalidadSeguro {TERCEROS, TODORIESGO}
    private final String matricula;
    private final String modelo;
    private final String color;
    private final boolean metalizada;
    private final TipoCoche tipo;
    private final int anyo;
    private final ModalidadSeguro seguro;

    public CocheInmutable(String modelo, String color, boolean metalizada, TipoCoche tipo, int anyo, ModalidadSeguro seguro) {
        this.modelo = modelo;
        this.color = color;
        this.metalizada = metalizada;
        this.matricula = nuevaMatricula();
        this.tipo = tipo;
        this.anyo = anyo;
        this.seguro = seguro;
    }

    public CocheInmutable() {
        this("Unknown", "Blanco", false, TipoCoche.UTILITARIO,
                LocalDate.now().getYear(), ModalidadSeguro.TERCEROS);
    }

    private static String nuevaMatricula() {
        ultimoNumeroMatricula = (ultimoNumeroMatricula + 1) % 10000;
        boolean finalizado = false;
        if (ultimoNumeroMatricula == 0) { // Hay que asignar nueva letra
            int indiceFinal = ultimasLetrasMatricula.length() - 1;
            do {
                char letra = ultimasLetrasMatricula.charAt(indiceFinal);
                letra++;
                if (letra <= 'Z') {
                    ultimasLetrasMatricula.setCharAt(indiceFinal, letra);
                    finalizado = true;
                } else {
                    ultimasLetrasMatricula.setCharAt(indiceFinal, 'A');
                    indiceFinal--;
                    if (indiceFinal < 0) {
                        ultimasLetrasMatricula.append('A');
                        finalizado = true;
                    }
                }
            } while (!finalizado);
        }
        return String.format("%04d", ultimoNumeroMatricula) + " " + ultimasLetrasMatricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }


    public boolean isMetalizada() {
        return metalizada;
    }

    public String getMatricula() {
        return matricula;
    }

    public TipoCoche getTipo() {
        return tipo;
    }

    public int getAnyo() {
        return anyo;
    }

    public ModalidadSeguro getSeguro() {
        return seguro;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CocheInmutable coche)) return false;

        return matricula.equals(coche.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    @Override
    public String toString() {
        return "Coche{" +
                "modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", metalizada=" + metalizada +
                ", matricula='" + matricula + '\'' +
                ", tipo=" + tipo +
                ", anyo=" + anyo +
                ", seguro=" + seguro +
                '}';
    }
}
