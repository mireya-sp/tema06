package com.mireyaserrano.tema06.pilasycolas;

public class Pila {

    private static final int INITIAL_SIZE = 10;

    private static final float GROW_FACTOR = 2f;

    private static final double ERROR = Double.NEGATIVE_INFINITY;

    private double[] data;

    private int size;


    public Pila() {
        this(INITIAL_SIZE);
    }

    public Pila(int size) {
        data = new double[size];
        this.size = 0;
    }

    public void push(double e) {
        if (isFull()) {
            expand();
        }
        data[size] = e;
        size++;
    }

    private void expand() {
        double[] aux = new double[Math.round(data.length * GROW_FACTOR)];
        for (int i = 0; i < data.length; i++) {
            aux[i] = data[i];
        }
        data = aux;
    }

    public double pop() {
        double e = ERROR;
        if (!isEmpty()) {
            e = data[size - 1];
            size--;
        }
        return e;
    }

    public double top() {
        double e = ERROR;
        if (!isEmpty()) {
            e = data[size - 1];
        }
        return e;
    }

    private boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

}
