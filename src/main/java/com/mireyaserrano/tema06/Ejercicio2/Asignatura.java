package com.mireyaserrano.tema06.Ejercicio2;

import java.util.Objects;

public class Asignatura {

    private final int codigo;
    private final String nombre;
    private final int curso;

    public Asignatura(int codigo, String nombre, int curso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Asignatura that)) return false;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Asignatura: " + codigo + ". Código: " + codigo + ". Curso: " + curso;
    }

}
