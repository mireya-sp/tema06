package com.mireyaserrano.tema06.Ejercicio1;

public class Coche {
    public enum TipoCoche{
        MINI, UTILITARIO, FAMILIAR, DEPORTIVO
    }
    public enum ModalidadSeguro{
        TERCEROS, TODO_RIESGO
    }
    private String modelo;
    private String color;
    private boolean metal;
    private String matricula;
    private TipoCoche tipo;
    private int anoFabricacion;
    private ModalidadSeguro seguro;

    private static int numero = 0;
    private static char letra1 = 'A';
    private static char letra2 = 'A';
    private static char letra3 = 'A';

    public Coche(String modelo, String color, boolean metal, TipoCoche tipo, int anoFabricacion, ModalidadSeguro seguro) {
        this.modelo = modelo;
        this.color = color;
        this.metal = metal;
        this.matricula = generarMatricula();
        this.tipo = tipo;
        this.anoFabricacion = anoFabricacion;
        this.seguro = seguro;
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

    public boolean isMetal() {
        return metal;
    }

    public void setMetal(boolean metal) {
        this.metal = metal;
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

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public ModalidadSeguro getSeguro() {
        return seguro;
    }

    public void setSeguro(ModalidadSeguro seguro) {
        this.seguro = seguro;
    }

    public static String generarMatricula(){
        String matricula = String.format("%04d%c%c%c",numero,letra3,letra2,letra1);
        numero++;
        if(numero > 9999){
            numero = 0;
            letra1++;
            if(letra1 > 'Z'){
                letra1 = 'A';
                letra2++;
                if(letra2 > 'Z'){
                    letra2 = 'A';
                }
            }
        }
        return matricula;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "Modelo='" + modelo + '\'' +
                ", Color='" + color + '\'' +
                ", Metalizado=" + metal +
                ", Matricula='" + matricula + '\'' +
                ", Tipo de coche=" + tipo +
                ", Año fabricacion=" + anoFabricacion +
                ", Tipo de seguro=" + seguro +
                '}';
    }

}
