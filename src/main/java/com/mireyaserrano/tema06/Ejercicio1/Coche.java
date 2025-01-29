package com.mireyaserrano.tema06.Ejercicio1;

public class Coche {
    private enum TipoCoche{
        MINI, UTILITARIO, FAMILIAR, DEPORTIVO
    }
    private enum ModalidadSeguro{
        TERCEROS, TODO_RIESGO
    }
    private String modelo;
    private String color;
    private boolean metal;
    private String matricula;
    private TipoCoche tipo;
    private int anoFabricacion;
    private ModalidadSeguro seguro;

    public Coche(String modelo, String color, boolean metal, String matricula, TipoCoche tipo, int anoFabricacion, ModalidadSeguro seguro) {
        this.modelo = modelo;
        this.color = color;
        this.metal = metal;
        this.matricula = matricula;
        this.tipo = tipo;
        this.anoFabricacion = anoFabricacion;
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", metal=" + metal +
                ", matricula='" + matricula + '\'' +
                ", tipo=" + tipo +
                ", anoFabricacion=" + anoFabricacion +
                ", seguro=" + seguro +
                '}';
    }

    public Coche(String modelo){
        this("MiCarro", "Rosa", false, "00001AAA", TipoCoche.MINI, 1999, ModalidadSeguro.TODO_RIESGO);
    }
}
