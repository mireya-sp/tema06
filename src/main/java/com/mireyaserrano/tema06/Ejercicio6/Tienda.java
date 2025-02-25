package com.mireyaserrano.tema06.Ejercicio6;

import java.util.GregorianCalendar;

public class Tienda {
    private Bicicleta[] inventario;
    private int size = 100;

    public Tienda(Bicicleta[] inventario, int size) {
        this.inventario = inventario;
        this.size = size;
    }

    public Bicicleta[] getInventario() {
        return inventario;
    }


}
