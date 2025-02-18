package com.mireyaserrano.tema06.generics;

import java.util.Arrays;

public class DynamicArray<T> {
    private final static int DEFAULT_CAPACITY = 10;

    private final static float GROW_FACTOR = 2f;

    public T[] data;

    public int size;

    public DynamicArray() { this(DEFAULT_CAPACITY); }

    public DynamicArray(int capacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity];
        data = temp;
        size = 0;
    }

    public T get(int index) {
        if (index >= size || index < 0) return null;
        return data[index];
    }

    public boolean add(T value) {
        if (isFull()) expand();
        data[size] = value;
        size++;
        return true;
    }

    private void moveToRight(int index){
        for (int i = size; i > index; i--){
            data[i] = data[i-1];
        }
        size++;
    }

    public boolean add(int index, T value){
        if (index >= size || index < 0) return false;
        if (isFull()) expand();
        moveToRight(index);
        data[index] = value;
        return true;
    }

    private void moveToLeft(int index){
        for (int i = index; i < size; i++){
            data[i] = data[i+1];
        }
        size--;
    }

    public T remove(int index){
        if (index >= size || index < 0) return null;
        T valor = data[index];
        moveToLeft(index);
        return valor;
    }

    public boolean remove(T value){
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                moveToLeft(i);
                return true;
            }
        }
        return false;
    }

    public boolean set(int index, T value){
        if (index >= size || index < 0) return false;
        data[index] = value;
        return true;
    }

    private void expand() {
        T[] copy = (T[]) new Object[Math.round(data.length * GROW_FACTOR)];
        for (int i = 0; i < size; i++){
            copy[i] = data[i];
        }
        data = copy;
    }

    public int size() { return size; }

    private boolean isFull() { return size == data.length; }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicArray that = (DynamicArray)o ;

        if (size != that.size) return false;

        for (int i = 0; i < size; i++) {
            if (data[i] != that.data[i]) return false;
        }
        return true;
    }

    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++){ sb.append(data[i]).append(" "); }
        sb.append("]");
        return sb.toString();
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
    }

    public String clon(){
        T[] clone =  (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            clone[i] = data[i];
        }
        return clone.toString();
    }

    public int indexOf(T element){
        for (int i = 0; i < size; i++){
            if (data[i] == element){
                return i;
            }
        }
        return -1;
    }

    public void trimToSize(){
        if (size < data.length){
            T[] aux = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                aux[i] = data[i];
            }
            data = aux;
        }
    }

    public boolean swap(int index1, int index2){
        if ( (index1 >= 0 && index1 < size) || (index2 >= 0 && index2 < size) ){
            T aux = data[index1];
            data[index1] = data[index2];
            data[index2] = aux;
            return true;
        }
        return false;
    }
}
