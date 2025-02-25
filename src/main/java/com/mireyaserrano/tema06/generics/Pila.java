package com.mireyaserrano.tema06.generics;

public class Pila<T> {
    private static final int INITIAL_SIZE = 10;

    private static final float GROW_FACTOR = 2f;

    private T[] data;

    private int size;


    public Pila() {
        this(INITIAL_SIZE);
    }

    public Pila(int size) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size];
        data = temp;
        this.size = 0;
    }

    public void push(T e) {
        if (isFull()) {
            expand();
        }
        data[size] = e;
        size++;
    }

    private void expand() {
        T[] aux = (T[]) new Object[Math.round(data.length * GROW_FACTOR)];
        for (int i = 0; i < data.length; i++) {
            aux[i] = data[i];
        }
        data = aux;
    }

    public T pop() {
        T e = null;
        if (!isEmpty()) {
            e = data[size - 1];
            size--;
        }
        return e;
    }

    public T top() {
        T e = null;
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

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
    }

    public String clon(){
        T[] aux = (T[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            aux[i] = data[i];
        }
        return aux.toString();
    }

    public String peek(int n){
        T[] aux = (T[]) new Object[n];
        int contador = 0;
        for (int i = n; i < n; i++){
            aux[contador] = data[i];
            contador++;
        }
        return aux.toString();
    }

    public int search(T element){
        if (isEmpty()){ return -1; }
        for (int i = 0; i < size; i++){
            if (element == data[i]){
                return size - i;
            }
        }
        return -1;
    }

    public void reverse(){
        T[] aux = (T[]) new Object[size];
        int j = size - 1;
        for (int i = 0; i < size; i++) {
            aux[i] = data[j];
            j--;
        }
        data = aux;
    }
}
