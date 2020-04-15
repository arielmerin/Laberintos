package com.mx.edd;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Programa que muestra la salida mas rapida de un laberinto (si la hay).
 * Utiliza una cola,
 * @author ALG
 * @version 1a. ed.
 */
public class LaberintoCola {
    private int largo;              //Dimensiones del laberinto
    private int ancho;
    private Punto inicio, fin;      //Punto de entrada y de salida del laberinto
    private int [][] paredes;       // Laberinto
    private int [][] camino;        // Matriz de marcado de celdas

    /**
     * Constructor que crea un laberinto a partir de la matriz dada, el punto
     * de entrada y el de salida
     * @param laberinto -- matriz de enteros que representa el laberinto
     * @param entrada -- punto de entrada
     * @param salida -- punto de salida
     */
    public LaberintoCola (int[][] laberinto, Punto entrada, Punto salida) {
        largo = laberinto.length;
        ancho = laberinto[0].length;
        camino = new int[largo][ancho];
        for (int i = 0; i < ancho; i++)
            for (int j = 0; j < largo; j++) {
                camino[i][j] = 0;
                paredes[i][j] = laberinto[i][j];
            }

        inicio = new Punto(entrada);
        fin = new Punto(salida);
    }

    /**
     * Constructor que crea un laberinto a partir de un archivo
     * @param  -- Cadena con el nombre del archivo.
     */
    public LaberintoCola (int ancho, int largo, int iniciox, int inicioy, int finx, int finy) throws IOException {
        paredes = new int[ancho][largo];
        camino = new int[ancho][largo];
        inicio = new Punto (iniciox, inicioy);
        fin = new Punto (finx, finy);
        Random r = new Random();
        for (int i = 0; i < ancho; i++)
            for (int j = 0; j < largo; j++) {
                paredes[i][j] = r.nextInt(16);
                camino[i][j] = 0;
            }
    }

    /**
     * Metodo para encontrar la salida.
     * @return boolean -- Devuelve true si hay salida y false en otro caso.
     */
    public boolean encontrarSalida() {
        Punto punto = inicio;
        Cola cola = new Cola();
        boolean hayCamino = true;
        boolean encontreSalida = false;

        while(hayCamino && !encontreSalida) {  // Marcar los vecinos
            if (etiquetarVecinos(punto, cola))
                encontreSalida = true;
            if (cola.esVacio())
                hayCamino = false;            // No hay camino
            punto = (Punto)cola.dequeue();
            //cola.dequeue();
        }

        if (encontreSalida) {
            camino[inicio.obtenerX()][inicio.obtenerY()] = 0;
            System.out.println("Encontre la salida");
            Pila caminoCorto = marcaCamino();   //BUsca vecinos con trayectoria -1
            mostrar(caminoCorto);
            return true;
        } else {
            System.out.println("NO hay solucion");
            return false;
        }
    }

    /**
     * Metodo privado para marcar el camino recorrido para salir del laberinto
     * @return Pila -- pila en la que esta el camino de salida del laberinto
     */
    private Pila marcaCamino() {
        Pila ruta = new Pila();
        int distancia = camino[fin.obtenerX()][fin.obtenerY()];
        Punto punto = fin;
        int x = fin.obtenerX();
        int y = fin.obtenerY();
        boolean heAvanzado;

        ruta.push(fin);
        while(!punto.equals(inicio)) {
            heAvanzado = false;
            if ((paredes[x][y] & 1) == 0 && camino[x+1][y] == distancia -1 && !heAvanzado) {
                punto = new Punto(x+1, y);
                heAvanzado = true;
            }

            if (!heAvanzado &&(paredes[x][y] & 2) == 0 && camino[x][y+1] == (distancia-1)) {
                punto = new Punto(x,y+1);
                heAvanzado = true;
            }

            if (!heAvanzado && (paredes[x][y] & 4) == 0 && camino[x-1][y] == (distancia - 1)) {
                punto = new Punto(x-1,y);
                heAvanzado = true;
            }

            if (!heAvanzado && (paredes[x][y] & 8) == 0 && camino[x][y-1] == (distancia - 1)) {
                punto = new Punto(x, y-1);
                heAvanzado = true;
            }
            ruta.push(punto);
            distancia --;
            x = punto.obtenerX();
            y = punto.obtenerY();
        }
        return ruta;
    }

    /*
     * Metodo para etiquetar los vecinos.
     * @param Punto -- punto del cual se marcaran los vecinos
     * @param Cola -- cola que guardara los vecinos pendientes de marcado
     * @return boolean -- true si encuentra la salida y false en otro caso.
     */
    private boolean etiquetarVecinos(Punto p, Cola cola) {
        int x = p.obtenerX();
        int y = p.obtenerY();

        if ((paredes[x][y] & 1) == 0 && camino[x+1][y] == 0) {
            camino[x+1][y] = camino[x][y] + 1;
            if (fin.equals(new Punto(x+1, y)))
                return true;
            cola.agrega(new Punto(x+1, y));
        }
        if ((paredes[x][y] & 2) == 0 && camino[x][y+1] == 0) {
            camino[x][y+1]=  camino[x][y] + 1;
            if (fin.equals(new Punto(x, y+1)))
                return true;
            cola.agrega(new Punto(x, y+1));
        }
        if ((paredes[x][y] & 4) == 0 && camino[x-1][y] == 0) {
            camino[x-1][y] =  camino[x][y] + 1;
            if (fin.equals(new Punto(x-1, y)))
                return true;
            cola.agrega(new Punto(x-1,y));
        }
        if ((paredes[x][y] & 8) == 0 && camino[x][y-1] == 0) {
            camino[x][y-1] =  camino[x][y] + 1;
            if (fin.equals(new Punto(x, y-1)))
                return true;
            cola.agrega(new Punto(x, y-1));
        }
        return false;
    }

    /**
     * Metodo para mostrar la salida del laberinto
     * @param p- pila que contiene el camino de salida encontrado
     */
    public void mostrar(Pila p) {
        System.out.println("El laberinto original tiene:");
        for(int i = 0; i < largo; i++){
            for (int j = 0; j < ancho; j++)
                System.out.print(paredes[i][j]+ " ");
            System.out.println();
        }

        System.out.println("El camino es ");
        java.util.Iterator it = p.iterator();
        while(it.hasNext()){
            Punto pto = (Punto) it.next();
            System.out.print(pto+ " ");
        }
        System.out.println();
    }

    /**
     * Metodo para probar la salida mas corta del laberinto
     */
    public static void main (String [] pps) {
        try{
            LaberintoCola mundo = new LaberintoCola(16,56,1,1,1,2);
            mundo.encontrarSalida();
        }catch(IOException e) { }
    }
}
