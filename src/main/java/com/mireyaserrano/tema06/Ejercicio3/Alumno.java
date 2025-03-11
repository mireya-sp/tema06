package com.mireyaserrano.tema06.Ejercicio3;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Alumno {

    private final String nia;
    private final String nombre;
    private final String apellidos;
    private final LocalDate fechaNacimiento;
    private final Grupo grupo;
    private final String telefono;

    public Alumno(String nia, String nombre, String apellidos, LocalDate fechaNacimiento, Grupo grupo, String telefono) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = grupo;
        this.telefono = telefono;
    }

    /**
     * Constructor de copia
     * Crea un copia del alumno recibido como parámetro
     * @param alumno Alumno a copiar
     */
    public Alumno(Alumno alumno) {
        this(alumno.getNia(), alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento(),
                alumno.getGrupo(), alumno.getTelefono());
    }

    public String getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        Period p = Period.between(fechaNacimiento, hoy);
        return p.getYears();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Alumno alumno)) return false;

        return nia.equals(alumno.nia);
    }

    @Override
    public int hashCode() {
        return nia.hashCode();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return  String.format("%9s",nia) +
                String.format("%16s",String.format("%-12s",nombre)) +
                String.format("%-25s",apellidos) +
                fechaNacimiento.format(dateTimeFormatter) + " (" +getEdad()+" años)" +
                String.format("%10s",grupo.getNombre()) +
                String.format("%12s",telefono);
    }

}
