package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import java.util.Arrays;

public class DynamicArray {
    private static final double ERROR = Double.NEGATIVE_INFINITY;
    private final static int DEFAULT_CAPACITY = 10;
    private final static float GROW_FACTOR = 2f;
    private double[] data;
    private int size;

    public DynamicArray() {this(DEFAULT_CAPACITY); }

    public DynamicArray(int capacity){
        data = new double[capacity];
        size = 0;
    }

    public double get(int index){
        if (index >= size || index<0)
            return ERROR;
        return data[index];
    }

    public boolean add(double value){
        if (isFull())
            expand();
        data[size]=value;
        size++;
        return true;
    }
    private void moveToRight(int index){
        for (int i = size; i > index ; i--) {
            data[i] = data[i-1];
        }
        size++;
    }

    public boolean add(int index, double value){
        if (index>= size || index < 0)
            return false;
        if (isFull())
            expand();
        moveToRight(index);
        data[index] = value;
        return true;
    }

    private void moveToLeft(int index){
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i+1];
        }
        size--;
    }

    public double remove(int index){
        if (index>= size || index < 0)
            return ERROR;
        double valor = data[index];
        moveToLeft(index);
        return valor;
    }

    public boolean remove(double value){
        for (int i = 0; i < size; i++) {
            if (data[i]==value){
                moveToLeft(i);
                return true;
            }
        }
        return false;
    }


    public boolean set(int index, double value){
        if (index >= size || index < 0)
            return false;
        data[index] = value;
        return true;
    }


    private void expand(){
        double[] copy = new double[Math.round(data.length*GROW_FACTOR)];
        for (int i = 0; i < size ; i++) {
            copy[i] = data[i];
        }
        data = copy;
    }

    public int size(){
        return size;
    }

    private boolean isFull(){
        return size== data.length;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        com.mireyaserrano.tema06.pilasycolas.DynamicArray that = (com.mireyaserrano.tema06.pilasycolas.DynamicArray) o;

        if (size != that.size) return false;

        for (int i = 0; i < size ; i++) {
            if (data[i] != that.data[i])
                return false;
        }
        return true;
    }


    @Override
    public int hashCode(){
        int result = Arrays.hashCode(data);
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
