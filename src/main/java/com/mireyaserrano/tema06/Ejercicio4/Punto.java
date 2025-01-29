package com.mireyaserrano.tema06.Ejercicio4;

public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Punto(){
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distancia(Punto otroPunto){
        double distx = this.x - otroPunto.getX();
        double disty = this.y - otroPunto.getY();
        return Math.sqrt(distx * distx + disty * disty);
    }

    @Override
    public String toString() {
        return "punto{" + "x=" + x + ", y=" + y + '}';
    }
}
