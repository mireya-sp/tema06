package com.mireyaserrano.tema06.Ejercicio6;

import com.mireyaserrano.tema06.utils.Ansi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bicicleta {
    private final String referencia;
    private final String marca;
    private final String modelo;
    private final float peso;
    private final float tamanyo;
    private final boolean motor;
    private final LocalDate fechaFabricacion;
    private final float precio;
    private int stock;

    public Bicicleta(String referencia, String marca, String modelo, float peso, float tamanyo, boolean motor,
                     LocalDate fechaFabricacion, float precio, int stock) {
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

    public String getReferencia() {
        return referencia;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public float getPeso() {
        return peso;
    }

    public float getTamanyo() {
        return tamanyo;
    }

    public boolean isMotor() {
        return motor;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public boolean vender(int cantidad) {
        if (stock >= cantidad) {
            stock = stock - cantidad;
            return true;
        }
        return false;
    }

    public void comprar(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Bicicleta bicicleta)) return false;

        return referencia.equals(bicicleta.referencia) && marca.equals(bicicleta.marca);
    }

    @Override
    public int hashCode() {
        int result = referencia.hashCode();
        result = 31 * result + marca.hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Ansi.Color fg;
        if (stock <= 0) {
            fg = Ansi.Color.RED;
        } else if (stock < 3) {
            fg = Ansi.Color.YELLOW;
        } else if (stock < 6) {
            fg = Ansi.Color.GREEN;
        } else {
            fg = Ansi.Color.BLUE;
        }
        return String.format("%-10s",referencia) +
                String.format("%-14s",marca) +
                String.format("%-12s",modelo) + "\t" +
                String.format("%8s",String.format("%.2f",peso) + " Kg") +
                String.format("%9s",String.format("%.1f",tamanyo) + "\"") +
                (motor ? String.format("%6s", "Sí") : String.format("%6s", "No")) +
                String.format("%14s", fechaFabricacion.format(dateTimeFormatter)) +
                String.format("%14s",String.format("%.2f", precio) + " €") + "\t" +
                "(" + Ansi.format(String.valueOf(stock), false, fg, Ansi.Color.NONE) + " unidades disponibles)";
    }
}
