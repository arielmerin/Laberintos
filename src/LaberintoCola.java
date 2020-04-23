import java.io.*;
import java.util.Random;

/**
 * Programa que muestra la salida mas rapida de un laberinto (si la hay).
 * Utiliza una cola,
 * @author ALG
 * @version 1a. ed.
 */
public class LaberintoCola {
    private int largo;              //Dimensiones del laberinto
    private int ancho;
    private Casilla inicio, fin;      //Casilla de entrada y de salida del laberinto
    private Casilla [][] casillas;       // Laberinto
    private int [][] camino;        // Matriz de marcado de celdas

    public LaberintoCola(int largo, int ancho){
        this.ancho = ancho;
        this.largo = largo;
        casillas = new Casilla[largo][ancho];
        for (int i = 0; i < casillas.length ; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                Random random = new Random();
                casillas[i][j] = new Casilla(1,1);
            }
        }

    }


    public void printStringGrid(){
        System.out.println();
        for (int i = 0; i < casillas.length; i++){
            for (int j = 0; j < casillas[0].length; j++){
                System.out.print(casillas[i][j].getEstado()? " ─": "  ");
            }
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < casillas[0].length; j++){
                    System.out.print(casillas[i][j]);
                System.out.print("|");
            }
            System.out.format("%n");
        }
        for (int j = 0; j < casillas[0].length; j++){
            System.out.print(" ─");
        }

    }

}
