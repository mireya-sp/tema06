package com.mireyaserrano.tema06.generics;

public class Cola<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static float GROW_FACTOR = 2f;
    private T[] data;
    private int size;

    public Cola(T[] data, int size) {
        this.data = data;
        this.size = size;
    }

    public Cola() { this(DEFAULT_CAPACITY); }

    public Cola(int capacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity];
        data = temp;
        size = 0;
    }

    public boolean add(T value){
        if (isFull()) expand();
        data[size] = value;
        size++;
        return true;
    }

    private boolean isFull(){ return size == data.length; }

    private void expand(){
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[Math.round(data.length * GROW_FACTOR)];
        for (int i = 0; i < size; i++){
            copy[i] = data[i];
        }
        data = copy;
    }

    public boolean isEmpty(){ return size == 0; }

    public T remove(){
        if (isEmpty()){ return null; }
        T valor = data[0];
        moveToLeft(0);
        return valor;
    }

    private void moveToLeft(int index){
        for (int i = index; i < size - 1; i--){
            data[i] = data[i+1];
        }
        size--;
    }

    public T peek() {
        return data[0];
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
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
    }

    public String clon(){
        T[] clone = (T[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            clone[i] = data[i];
        }
        return clone.toString();
    }

    public T peekLast(){
        if (isEmpty()){
            return null;
        }
        return data[size - 1];
    }

    public int search(T element){
        for (int i = 0; i < size - 1; i++){
            if (data[i] == element){
                return size - i;//Devuelve la posición empezando de 0, para que sea la posición 'natural' habria que poner + 1
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
