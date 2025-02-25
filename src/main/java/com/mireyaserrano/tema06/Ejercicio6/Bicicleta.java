package com.mireyaserrano.tema06.Ejercicio6;

import java.util.GregorianCalendar;

public class Bicicleta {
    private final String referencia;
    private final String marca;
    private final String modelo;
    private final float peso;
    private final float tamanyo;
    private final boolean motor;
    private final GregorianCalendar fechaFabricacion;
    private final float precio;
    private int stock;

    public int getStock() {
        return stock;
    }

    public Bicicleta(String referencia, String marca, String modelo, float peso, float tamanyo, boolean motor,
                     GregorianCalendar fechaFabricacion, float precio, int stock) {
        this.referencia = referencia;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.tamanyo = tamanyo;
        this.motor = motor;
        this.fechaFabricacion = fechaFabricacion;
        this.precio = precio;
        this.stock = stock;
    }

}
