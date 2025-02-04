package com.mireyaserrano.tema06.Ejercicio3;

public class Alumno {
    private int nia;
    private String nombre;
    private String apellidos;
    private String fechaNac;
    private String grupo;
    private int numTlf;

    public Alumno(int nia, String nombre, String apellidos, String fechaNac, String grupo, int numTlf) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.grupo = grupo;
        this.numTlf = numTlf;
    }

    public int getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getNumTlf() {
        return numTlf;
    }

    public void setNumTlf(int numTlf) {
        this.numTlf = numTlf;
    }
}
