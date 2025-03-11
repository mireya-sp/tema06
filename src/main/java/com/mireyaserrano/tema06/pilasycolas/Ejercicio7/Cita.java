package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import java.util.GregorianCalendar;
import com.mireyaserrano.tema06.pilasycolas.Lib;

public class Cita {
    private final GregorianCalendar fecha;
    private final Paciente paciente;
    private String codigo;
    private static int nCitas;
    private static final char[] letras = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    private static int primeraLetra = Lib.aleatorio(0,letras.length-1);
    private static int segundaLetra = Lib.aleatorio(0, letras.length-1);

    public Cita(GregorianCalendar fecha, Paciente paciente) {
        this.fecha = fecha;
        this.paciente = paciente;

        nCitas++;
        codigo = obtenerNumero();
    }

    private String obtenerNumero() {
        int numero = nCitas % 10;
        if (numero == 0) {
            segundaLetra++;
            if (segundaLetra == letras.length-1) {
                segundaLetra = 0;
                primeraLetra++;
            }
            if (primeraLetra == letras.length-1) {
                primeraLetra = 0;
            }
        }
        codigo = ""+ letras[primeraLetra] + numero + letras[segundaLetra];
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getCodigo() {
        return codigo;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "paciente=" + paciente +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
