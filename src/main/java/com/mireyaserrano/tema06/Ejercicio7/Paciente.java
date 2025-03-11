package com.mireyaserrano.tema06.Ejercicio7;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Paciente {
    public enum Sexo {
        M, V
    }
    private final String sip;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final Sexo sexo;
    private final LocalDate fechaNacimiento;

    public Paciente(String sip, String nombre, String apellido1, String apellido2, Sexo sexo, LocalDate fechaNacimiento) {
        this.sip = sip;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSip() {
        return sip;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        Period p = Period.between(fechaNacimiento, hoy);
        return p.getYears();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Paciente paciente)) return false;

        return sip.equals(paciente.sip);
    }

    @Override
    public int hashCode() {
        return sip.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%10s", sip) + "\t" +
                String.format("%-12s", nombre ) +
                String.format("%-12s", apellido1) +
                String.format("%-12s", apellido2) +
                String.format("%-4s", sexo) +
                String.format("%4d", getEdad());

    }
}
