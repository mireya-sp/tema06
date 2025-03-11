package com.mireyaserrano.tema06.Ejercicio1;

import java.time.LocalDate;

public class Coche {
    public enum TipoCoche {MINI, UTILITARIO, FAMILIAR, DEPORTIVO}
    public enum ModalidadSeguro {TERCEROS, TODORIESGO}
    private String matricula;
    private String modelo;
    private String color;
    private boolean metalizada;
    private TipoCoche tipo;
    private int anyo;
    private ModalidadSeguro seguro;

    public Coche(String modelo, String color, boolean metalizada, String matricula, TipoCoche tipo, int anyo, ModalidadSeguro seguro) {
        this.modelo = modelo;
        this.color = color;
        this.metalizada = metalizada;
        this.matricula = matricula;
        this.tipo = tipo;
        this.anyo = anyo;
        this.seguro = seguro;
    }

    public Coche() {
        this("Unknown", "Blanco", false, "Unknown", TipoCoche.UTILITARIO,
                LocalDate.now().getYear(), ModalidadSeguro.TERCEROS);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isMetalizada() {
        return metalizada;
    }

    public void setMetalizada(boolean metalizada) {
        this.metalizada = metalizada;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public TipoCoche getTipo() {
        return tipo;
    }

    public void setTipo(TipoCoche tipo) {
        this.tipo = tipo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public ModalidadSeguro getSeguro() {
        return seguro;
    }

    public void setSeguro(ModalidadSeguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Coche coche)) return false;

        return matricula.equals(coche.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    @Override
    public String toString() {
        return "Coche{" +
                "modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", metalizada=" + metalizada +
                ", matricula='" + matricula + '\'' +
                ", tipo=" + tipo +
                ", anyo=" + anyo +
                ", seguro=" + seguro +
                '}';
    }

}
