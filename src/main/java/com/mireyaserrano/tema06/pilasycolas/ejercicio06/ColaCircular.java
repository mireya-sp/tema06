package com.mireyaserrano.tema06.pilasycolas.ejercicio06;

public class ColaCircular {
    /** Como aún no hemos visto las Exception de momento utilizamos el menos infinito para detectar errores */
    private static final double ERROR = Double.NEGATIVE_INFINITY;
    /* Capacidad por defecto de la cola */
    private static final int INITIAL_CAPACITY = 10;
    /* Factor de crecimiento */
    private static final float GROW_FACTOR = 2f;
    /* Array donde se almacenan los elementos de la cola */
    private double[] data;
    /* Índice que controla cual es el primer elemento de la cola */
    private int first;
    /* Índice que controla cuál es el último elemento de la cola */
    private int last;

    public ColaCircular() {
        this(INITIAL_CAPACITY);
    }

    public ColaCircular(int initialCapacity) {
        data = new double[initialCapacity];
        first = -1;
        last = -1;
    }

    public boolean isEmpty() {
        return first == -1;
    }

    /**
     * Determina si la cola está llena
     * @return True si la cola está llena, False en caso contrario
     */
    public boolean isFull() {
        return (last == data.length - 1 && first == 0) || (first == last + 1);
    }

    /**
     * Añade un elemento al final de la Cola
     * @param e Elemento a añadir
     */
    public void add(double e) {
        // Actualizamos el índice first si se trata de la primera inserción
        if (isEmpty()) {
            first = 0;
        } else if (isFull()) { // Si la cola está llena
            // Ampliamos el tamaño
            expand();
        }
        last = (last + 1) % data.length;
        // Actualizamos el índice ultimo según corresponda
      /*
      if (last == data.length-1 && first != 0) {
         last = 0;
      } else {
         last++;
      }
       */
        // Añadimos el elemento
        data[last] = e;
    }

    /**
     * Amplía el tamaño de la cola en un factor de multiplicación
     * y copia todos los elementos al principio del nuevo array
     * actualizando los índices primero y último
     */
    private void expand() {
        double[] aux = new double[Math.round(data.length * GROW_FACTOR)];
        int i = first;
        int j = 0;
        // Recorremos el array original de forma circular
        do {
            aux[j] = data[i];
            i++;
            j++;
            // Al llegar al final
            if (i == data.length && first > last) {
                i = 0; // Reiniciar en cero (dar la vuelta)
            }
        } while (i != last + 1);
        // Actualizamos índices
        first = 0;
        last = j - 1;
        // Asignamos el nuevo array
        data = aux;
    }


    /**
     * Extrae el siguiente elemento de la Cola (el primero)
     * @return Elemento que ocupa la primera posición
     */
    public double remove() {
        double e;
        if (isEmpty()) {
            // Cola vacía
            e = ERROR;
        } else {
            e = data[first];
            if (first == last) {
                first = -1;
                last = -1;
            } else {
                first = (first + 1) % data.length;
            /*
            first++;
            if (first == data.length) {
               first = 0;
            }
             */
            }
        }
        return e;
    }

    /**
     * Representación del contenido de la Cola mediante un String
     * @return String con la representación de la Cola
     */
    @Override
    public String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()) {
            i = first;
            do {
                sb.append(String.format("%.2f",data[i])).append(" ");
                i++;
                if (i == data.length && first > last) {
                    i = 0; // Reiniciar en cero (dar la vuelta)
                }
            } while (i != last + 1);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Método útil para ver como van evolucionando los índices
     * @return representación de la cola como una cadena de caracteres
     */
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (double value : data)  {
            sb.append(String.format("%.2f", value)).append(" ");
        }
        sb.append("]");
        return "DEBUG INFO: first = " + first + ", " + "last = " + last + "\n" + sb;
    }
}
