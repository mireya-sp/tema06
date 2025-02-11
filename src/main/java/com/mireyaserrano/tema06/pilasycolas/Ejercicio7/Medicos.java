package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

public class Medicos {
    private String nombre;
    private int num_colegiado;

    public Medicos(String nombre, int num_colegiado) {
        this.nombre = nombre;
        this.num_colegiado = num_colegiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_colegiado() {
        return num_colegiado;
    }

    public void setNum_colegiado(int num_colegiado) {
        this.num_colegiado = num_colegiado;
    }
}
