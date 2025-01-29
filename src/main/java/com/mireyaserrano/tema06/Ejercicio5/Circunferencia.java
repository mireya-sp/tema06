package com.mireyaserrano.tema06.Ejercicio5;

public class Circunferencia {
    private Punto centro;
    private double radio;

    public Circunferencia(Punto centro, double radio) {
        this.centro = centro;
        this.radio = radio;
    }

    public Circunferencia(double x, double y, double radio){
        this.centro = new Punto(x,y);
        this.radio = radio;
    }
    public Circunferencia(){
        this(new Punto(), 1.0);
    }
    public Punto getCentro() {
        return centro;
    }
    public void setCentro(Punto centro) {
        this.centro = centro;
    }
    public double getRadio() {
        return radio;
    }
    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double distancia(Punto punto){
        return this.centro.distancia(punto);
    }

    public double area(){
        return Math.PI * Math.pow(radio,2);
    }
    public double perimetro(){
        return 2 * Math.PI * radio;
    }

    @Override
    public String toString() {
        return "Circunferencia{" + "centro=" + centro + ", radio=" + radio + '}';
    }
}
