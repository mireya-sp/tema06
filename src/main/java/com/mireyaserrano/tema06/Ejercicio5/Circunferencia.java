package com.mireyaserrano.tema06.Ejercicio5;

import com.mireyaserrano.tema06.Ejercicio4.Punto;

public class Circunferencia {
    private Punto centro;
    private double radio;

    public Circunferencia(Punto centro, double radio) {
        this.centro = centro;
        this.radio = radio;
    }

    public Circunferencia(double x, double y, double radio) {
        centro = new Punto(x,y);
        this.radio = radio;
    }

    public Circunferencia() {
        centro = new Punto(0,0);
        radio = 1;
    }

    public Punto getCentro() {
        return centro;
    }

    public void setCentro(Punto centro) {
        // Copia o asignación
        // IMPORTANTE: tener claro las diferencias
        // En este caso creamos una copia del Punto utilizando el constructor de copia
        this.centro = new Punto(centro);

        // Si quisiéramos utilizar el mismo Punto utilizaríamos asignación que copia el puntero
        // es decir, copia la dirección de memoria donde está el objeto Punto, por tanto el Punto es el mismo.
        // this.centro = centro;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double calcularDistancia(Punto p) {
        return centro.calcularDistancia(p);
    }

    public double calcularDistancia(Circunferencia c) {
        return centro.calcularDistancia(c.centro);
    }

    public double calcularArea() {
        return Math.PI * Math.pow(radio,2);
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    public void visualizarCirculo() {
        System.out.print("Círculo de radio " + radio + " cm situado en ");
        if (centro.getX() == 0 && centro.getY() == 0) {
            System.out.println("el origen de coordenadas");
        } else {
            System.out.println("el punto " + centro.toString());
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Circunferencia that)) return false;

        return Double.compare(radio, that.radio) == 0 && centro.equals(that.centro);
    }

    @Override
    public int hashCode() {
        int result = centro.hashCode();
        result = 31 * result + Double.hashCode(radio);
        return result;
    }

    @Override
    public String toString() {
        String result = "Circunferencia de radio " + radio + " cm situada en ";
        if (centro.getX() == 0 && centro.getY() == 0) {
            result += "el origen de coordenadas";
        } else {
            result += "el punto " + centro.toString();
        }
        return result;
    }
}
