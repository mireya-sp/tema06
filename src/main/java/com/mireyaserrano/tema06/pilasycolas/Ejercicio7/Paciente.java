package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import java.time.LocalDateTime;

public class Paciente {
    private String nombre;
    private String sip;
    private LocalDateTime fecha_hora_llegada;


    public Paciente(String nombre, String sip, LocalDateTime fecha_hora_llegada) {
        this.nombre = nombre;
        this.sip = sip;
        this.fecha_hora_llegada = fecha_hora_llegada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public LocalDateTime getFecha_hora_llegada() {
        return fecha_hora_llegada;
    }

    public void setFecha_hora_llegada(LocalDateTime fecha_hora_llegada) {
        this.fecha_hora_llegada = fecha_hora_llegada;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", sip='" + sip + '\'' +
                ", fecha_hora_llegada=" + fecha_hora_llegada +
                '}';
    }
}
