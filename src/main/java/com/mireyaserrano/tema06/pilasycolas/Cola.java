package com.mireyaserrano.tema06.pilasycolas;

public class Cola {
    /** Capacidad inicial por defecto */
    private static final int INITIAL_CAPACITY = 10;
    /** Valor con el que reconocemos una condición de error */
    private final DynamicArray data;

    /**
     * Crea una cola con la capacidad inicial por defecto
     */
    public Cola() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Crea una cola con una capacidad inicial
     * @param initialCapacity Capacidad inicial de la
     */
    public Cola(int initialCapacity) {
        data = new DynamicArray(initialCapacity);
    }

    /**
     * Obtiene el número de elementos que están en la cola
     * @return Cantidad de elementos en la cola
     */
    public int size() {
        return data.size();
    }

    /**
     * Determina si la cola está vacía
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /**
     * Añade un elemento al final de la cola
     * @param value Elemento a añadir
     * @return true
     */
    public boolean add(double value) {
        return data.add(value);
    }

    /**
     * Extrae (eliminando) el primer elemento de la cola
     * @return Elemento extraído
     */
    public double remove() {
        if (isEmpty())
            return Double.POSITIVE_INFINITY;
        return data.remove(0);
    }

    /**
     * Consulta (sin eliminar) el primer elemento de la cola
     * @return Primer elemento de la cola
     */
    public double peek() {
        if (isEmpty())
            return Double.POSITIVE_INFINITY;
        return data.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cola cola = (Cola) o;

        return data.equals(cola.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return "Cola " + data;
    }
}
