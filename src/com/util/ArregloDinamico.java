package com.util;

/**
 *
 * @author Luis Manuel Martínez Dámaso
 * @version 1.2
 */

import java.util.Iterator;

public class ArregloDinamico<T> implements Iterable<T> {

    private T[] arreglo;
    private int elementos;

    public int getElementos() {
        return elementos;
    }

    /**
     * No importa el nombre que se le ponga a la clase, solo es para fines didácticos
     * @param <T> Tipo sobre el cual va a iterar
     */
    private class Iterador<T> implements Iterator<T> {

        private int siguiente;

        public Iterador() {
            this.siguiente = 0;
        }

        @Override
        public boolean hasNext() {
            return elementos > siguiente;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T aux = (T) arreglo[siguiente];
                siguiente++;
                return aux;
            }else {
                return null;
            }
        }

        @Override
        public void remove() {
            /**
             * Nuncahay que implementar este método, no tiene sentido en la encapsulacion
             */
            Iterator.super.remove();
        }
    }

    /**
     * Constructor por omisión
     */
    public ArregloDinamico() {
        /**
         * Crear un arreglo de este tamaño
         */
        this(1);
    }

    /**
     * Constructor que recibe el tamaño con el cual queremos inicializar el
     * arreglo dinámico.
     *
     * @param n numero de elementos iniciales de un arreglo dinamico
     */
    public ArregloDinamico(int n) {
        this.arreglo = (T[])new Object[n];
        elementos = 0;
        /**
         * Crear un arreglo de este tamaño
         */
    }

    /**
     * Método para insertar un elemento al final del arreglo dinamico. Si el
     * arreglo no tiene espacio, crecer el arreglo al doble de tamaño.
     *
     * @param elem elemento a agregar
     */
    public void agrega(T elem) {
        if (elementos == arreglo.length-1){
            T[] a = (T[])new Object[elementos+2];
            for (int i = 0; i < arreglo.length ; i++) {
                a[i] = arreglo[i];
            }
            this.arreglo = a;
        }
        this.arreglo[elementos] = elem;
        elementos++;
    }

    /**
     * Método para acceder al elemento n-esimo del arreglo dinámico. El método
     * debe devolver el elemento buscado. Si no existe elemento n-esimo,
     * devolver null.
     *
     * @param n el enesimo elemento a buscar
     * @return resultado de la extraccion
     */
    public T busca(int n) {
        if (n < elementos && arreglo[n] != null){
            return arreglo[n];
        }else {
            return null;
        }
    }

    /**
     * Método para eliminar al elemento n-esimo del arreglo dinámico, no debe
     * haber espacios sin elementos. El método debe devolver el elemento
     * eliminado. Si no existe elemento n-esimo, devolver null.
     *
     * @param n el valor a eliminar
     * @return lo que contenia el lugar eliminado
     */
    public T elimina(int n) {
        T result = arreglo[n];
        T[] aux = (T[])new Object[elementos];
        for (int i = 0; i < n; i++){
            aux[i] = arreglo[i];
        }
        for (int i = n +1; i < elementos; i++){
            aux[i-1] = arreglo[i];
        }
        arreglo = aux;
        elementos--;
        return result;
    }

    /**
     * Método para saber si un elemento esta en el arreglo dinámico, devuelve
     * true si esta en el arreglo, false en otro caso.
     *
     * @param elem elemento que se buscara
     * @return resultado de la busqueda, verdadero en caso de haberlo encontrado
     */
    public boolean contiene(T elem) {
        for (int i = 0; i < elementos; i++){
            if (busca(i).equals(elem)){
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param <T> Tipo del cual se encargara de ordenar nuestro metodo
     * @param array Arreglo sobre el cual se efectuara el algoritmo de ordenamiento.
     */
    public static <T extends Comparable<T>> void quickSort(ArregloDinamico<T> array) {
        quickSort(array.arreglo, 0, array.elementos-1);
    }

    public static <T extends  Comparable<T>> void quickSort( T[] arreglo, int izq, int der){
        T pivote = arreglo[izq];
        int i=izq;
        int j=der;
        T aux;

        while(i<j){
            while( pivote.compareTo(arreglo[i]) >= 0 && i<j) i++;
            while(arreglo[j].compareTo(pivote) > 0) j--;
            if (i<j) {
                aux= arreglo[i];
                arreglo[i]=arreglo[j];
                arreglo[j]=aux;
            }
        }
        arreglo[izq]=arreglo[j];
        arreglo[j]=pivote;
        if(izq<j-1){
            quickSort(arreglo,izq,j-1);
        }
        if(j+1 <der){
            quickSort(arreglo,j+1,der);
        }
    }

    public static <T extends Comparable<T>>  void selectionSort(T[] arreglo){
        for (int i = 1; i < arreglo.length ; i++) {
            T elem = arreglo[i];
            int j = i-1;
            while (j>=0 && arreglo[j].compareTo(elem) > 0){
                arreglo[j+1] = arreglo[j];
                j--;
            }
            arreglo[j+1] = elem;
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterador();
    }


    @Override
    public String toString() {
        StringBuilder std = new StringBuilder();
        Iterador it = new Iterador();
        while (it.hasNext()){
            std.append(it.next() + ", ");
        }
        String result = "";
        if (std.length() > 1){
            result = std.substring(0,std.length() - 2);
        }
        return "[" + result + "]";
    }
}
