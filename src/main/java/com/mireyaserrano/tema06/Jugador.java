package com.mireyaserrano.tema06;

public class Jugador {
    private String nombre;
    private float portero;
    private float defensa;
    private float pase;
    private float regate;
    private float tiro;
    private float estadoForma;

    public Jugador(String nombre, float portero, float defensa, float pase, float regate, float tiro, float estadoForma) {
        this.nombre = nombre;
        this.portero = portero;
        this.defensa = defensa;
        this.pase = pase;
        this.regate = regate;
        this.tiro = tiro;
        this.estadoForma = estadoForma;
    }

    public Jugador(String nombre) {
        this(nombre, 50, 50, 50, 50, 50, 50);
    }

    Jugador futbolista1 = new Jugador("Aitana Bonmatí", 32, 82, 87, 80, 82, 95);
    Jugador futbolista2 = new Jugador("Pablo Gavi", 32, 82, 87, 80, 82, 95);
    Jugador futbolista3 = new Jugador("Ana García");
    Jugador futbolista4 = new Jugador("Rubén Prieto");

    public void crearFutbolista() {
        Jugador futbolista1 = new Jugador("Alexia Putellas", 29, 81, 80, 83, 84, 90);
    }

}
