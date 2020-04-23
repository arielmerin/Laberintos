package com;

import com.util.ArregloDinamico;
import com.util.Cola;
import com.util.Pila;

import java.util.Random;

/**
 * Programa que muestra un laberinto y la solucion a este, asegurando que siempre habra una
 * @author Ariel Merino, Armando Aquino
 * @version 1
 */
public class Laberinto {
    /**
     * Dimension largo que tendra el arreglo bidimensional del que constara el laberinto
     */
    private int largo;
    /**
     * Dimension ancho que tendra el arreglo bidimensional del que constara el laberinto
     */
    private int ancho;
    /**
     * Casillas de inicio y final que necesitara para encontrar una salida
     */
    private Casilla inicio, fin;
    /**
     * Arreglo bidimensional compuesto por casillas que representa el tablero del laberinto
     */
    public Casilla [][] casillas;
    /**
     * Es la trayectoria que marca el camino para resolver dicho laberinto
     */
    private int [][] camino;

    /**
     * <h1> Constructor escencial </h1>
     * para la consolidacion de adyacencias y realciones entre casillas del laberinto
     * se encarga de hacer que en un principio todas las casillas sean adyacentes y
     * @param largo dimension del arreglo
     * @param ancho segunda dimension del arreglo
     */
    public Laberinto(int largo, int ancho){
        this.ancho = ancho;
        this.largo = largo;
        casillas = new Casilla[largo][ancho];
        /**
         * R3ellena el laberinto de casillas con las coordenadas que le tocan
         */
        for (int i = 0; i < casillas.length ; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                Casilla casilla = new Casilla(i,j);
                casillas[i][j] = casilla;
            }
        }
        /**
         * Le dice a la primera fila que su vecino de abajo es [1][j]
         */
        for (int j = 0; j < casillas[0].length; j++) {
            casillas[0][j].setVecinoAbajo(casillas[1][j]);
            /**
             * Con esto estoy diciendo que "ya visisto el vecino de arriba
             */
            casillas[0][j].configuraleElVecino(0);
        }
        /**
         * Con esto le digo a la ultima fila que su vecino de arriba es el que tienen arriba jeje
         */
        for (int j = 0; j < ancho; j++) {
            casillas[largo-1][j].setVecinoArriba(casillas[largo-2][j]);
            /**
             * Con esto decimos que ya visito al vecino de abajo
             */
            casillas[largo-1][j].configuraleElVecino(2);
        }
        for (int i = 0; i < largo; i++) {
            /**
             * Con esto le digo que su vecino es el de la derecha a la primera columna
             */
            casillas[i][0].setVecinoDerecho(casillas[i][1]);
            /**
             * Con esto digo que ya visito el vecino de la izquierda
             */
            casillas[i][0].configuraleElVecino(3);
        }
        for (int i = 0; i < largo; i++) {
            /**
             * Con esto digo que su vecino es el interior
             */
            casillas[i][ancho-1].setVecinoIzquierdo(casillas[i][ancho-2]);
            /**
             * Con esto digo que ya vio a su vecino el de la derecha
             */
            casillas[i][ancho-1].configuraleElVecino(1);
        }
        /**
         * Para acabar de hacer todas la flechas horizontales
         */
        for (int i = 0; i < largo; i++) {
            for (int j = 1; j < ancho -1; j++) {
                casillas[i][j].setVecinoIzquierdo(casillas[i][j-1]);
                casillas[i][j].setVecinoDerecho(casillas[i][j+1]);
            }
        }
        for (int i = 1; i < largo - 1; i++) {
            for (int j = 0; j < ancho; j++) {
                casillas[i][j].setVecinoArriba(casillas[i-1][j]);
                casillas[i][j].setVecinoAbajo(casillas[i+1][j]);
            }
        }

    }

    /**
     * <h1>Impresion de un laberinto</h1>
     * Este metodo se encarga de darle vida a las adyacencias y formatos de las casillas
     * itera sobre cada una de las casillas para recopilar sus datos y si alguna de ellas son
     * vecinas entonces pone una linea en la terminal.
     */
    public void printStringGrid(){
        System.out.println();
        for (int j = 0; j < casillas[0].length ; j++) {
            boolean[] primeraLinea = casillas[0][j].getParedes();
            if (primeraLinea[0]){
                System.out.print("  ─ ");
            }else {
                System.out.print("    ");
            }
        }
        System.out.println();
        for (int i = 0; i <casillas.length ; i++) {
            if (casillas[i][0].tieneParedIzquierda()){
                System.out.print("|");
            }else {
                System.out.print(" ");
            }
            for (int j = 0; j < casillas[i].length; j++) {
                boolean[] paredDer = casillas[i][j].getParedes();
                System.out.print(casillas[i][j]);
                if (paredDer[1]){
                    System.out.print("|");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            for (int j = 0; j < casillas[i].length; j++) {
                boolean[] paredAbajo = casillas[i][j].getParedes();
                if (paredAbajo[2]){
                    System.out.print("  ─ ");
                }else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }

    /**
     * <h1>Creacion de un laberinto</h1>
     * En este metodo se toma en cuenta una cuadricula aleatoria del arreglo bidimensional para que apartir de esta
     * eleccion se tengan caminos adyacentes entre si y se descubran algunas casillas, dando lugar a un laberinto
     * que siempre tendra solucion para cualesquiera dos puntos
     */
    public void crearLaberinto() {
        Random ran = new Random();
        Pila<Casilla> pila = new Pila();
        int aleatorio = ran.nextInt(ancho);
        pila.push(casillas[1][aleatorio]);
        int i = 0;
        casillas[1][aleatorio].setEstado(true);
        while (!pila.esVacio() ) {
            Casilla enCuestion = pila.peek();
            enCuestion.setEstado(true);
            if (enCuestion.vecinosDisponibles().getElementos() != 0) {
                i = ran.nextInt(4);
                i = i % enCuestion.vecinosDisponibles().getElementos();
                int k = enCuestion.vecinosDisponibles().busca(i);
                if (enCuestion.hayVecino(k)) {
                    if (!enCuestion.hayVecinoOcupado(k)) {
                        Casilla siguiente = enCuestion.dameVecino(k);
                        enCuestion.visitarVecino(k);
                        pila.push(siguiente);
                    }
                }
            } else {
                pila.pop();
            }
        }
    }

    public void hallarSolucion(Casilla inicio, Casilla fin){
        Cola<Casilla> cola = new Cola<>();
        Cola<Casilla> fueronEncolados = new Cola<>();

        cola.queue(inicio);
        fueronEncolados.queue(inicio);
        Casilla posicion = cola.peek();
        while (!posicion.equals(fin) ){
            posicion = cola.peek();
            posicion.setEstado(false);
            boolean[] paredes = posicion.getParedes();
            for (int i = 0; i < 4; i++) {

                if (!paredes[i] && posicion.dameVecino(i).getEstado()){
                    Casilla nuevo = posicion.dameVecino(i);
                    if (nuevo.getEstado()){
                        if (!fueronEncolados.contiene(nuevo))
                        cola.queue(nuevo);
                        fueronEncolados.queue(nuevo);
                    }
                    nuevo.setQuienMeEncolo(posicion);
                    nuevo.setEstado(false);
                }
            }

            if (posicion.vecinosDisponiblesMod().getElementos() == 0){
                cola.dequeue();
            }
        }

        int i = 0;
        while (posicion != null){
            posicion.setOrden(++i);
            posicion = posicion.getQuienMeEncolo();
        }

    }

}
