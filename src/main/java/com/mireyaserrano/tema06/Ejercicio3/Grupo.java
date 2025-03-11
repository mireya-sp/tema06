package com.mireyaserrano.tema06.Ejercicio3;

public class Grupo {
    private final int codigo;
    private final String nombre;

    public Grupo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Grupo grupo)) return false;

        return codigo == grupo.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
