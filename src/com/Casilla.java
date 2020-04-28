package com;

import com.util.ArregloDinamico;

import java.util.Objects;

/**
 * <h1> Clase Casilla </h1>
 * Es la clase principal que compone el proyecto, con base en esta clase se podran asignar valores y en un
 * futuro laberintos compuestos de casillas, es de suma importancia pues con estos objetos se modelara
 *  el comportamiento de cada lugar disponible o no en el juego principal.
 * @author Ariel Merino, Armando Aquino
 * @version 1.2
 */
public class Casilla {

    /**
     * Valor de la posicion de dicha casilla en el eje de las x
     */
    private int posicionX;

    /**
     * Valor de la posicion de dicha casilla en el eje de las x
     */
    private int posicionY;

    /**
     * Valor que responde a la pregunta ¿la casilla ha sido visitada en un recorrido?
     */
    private boolean estado;

    /**
     * Sirve para dar adyacencia a las paredes de la casilla
     */
    private boolean[] paredes;

    /**
     * Sirve para dar adyacencia a las paredes de la casilla
     */
    private boolean[] vecinos;

    /**
     * Referencia a la casilla que se encentra a la izquierda
     */
    private Casilla vecinoIzquierdo;

    /**
     * Referencia a la casilla que se encentra a la derecha
     */
    private Casilla vecinoDerecho;

    /**
     * Referencia a la casilla que se encentra hacia abajo
     */
    private Casilla vecinoAbajo;

    /**
     * Referencia a la casilla que se encentra hacia arriba
     */
    private Casilla vecinoArriba;

    /**
     * Valor que permite daber la reerencia al objeto que se empleo para la solucion en BFS
     */
    private Casilla quienMeEncolo;

    private int orden;

    /**
     * En este constrcutor se ponen los valores minimos para crear una casilla.
     * @param posicionX coordenada en x de la casilla
     * @param posicionY coordenada en y de la casilla
     */
    public Casilla(int posicionX, int posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        paredes = new boolean[4];
        vecinos = new boolean[4];
        for (int i = 0; i < 4; i++) {
            paredes[i] = true;
            vecinos[i] = true;
        }
    }

    /**
     * Indica que esta pared esta libre y si lo esta es que se puede pasar de esa casilla a la otra,
     * es decir tiene la propiedad de ser "recorrible" en dicha direccion
     * @param direccion arriba, abajo, derecha o izquierda por convecion emplearemos
     *                  [1] = arriba
     *                  [2] = derecha
     *                  [3] = abajo
     *                  [4] = izquierda
     */
    public void visitarVecino(int direccion){
        vecinos[direccion] = false;
        paredes[direccion] = false;
        switch (direccion){
            case 0:
                vecinoArriba.estado = true;
                vecinoArriba.vecinos[2] = false;
                vecinoArriba.paredes[2] = false;
                break;
            case 1:
                vecinoDerecho.estado = true;
                vecinoDerecho.paredes[3] = false;
                vecinoDerecho.vecinos[3] = false;
                break;
            case 2:
                vecinoAbajo.estado = true;
                vecinoAbajo.paredes[0] = false;
                vecinoAbajo.vecinos[0] = false;
                break;
            case 3:
                vecinoIzquierdo.estado = true;
                vecinoIzquierdo.paredes[1] = false;
                vecinoIzquierdo.vecinos[1] = false;
                break;
        }
    }

    /**
     * Permite acceder al estado de una casilla
     * Es decir si esta ha sido visitada o no
     * @return valor de la visita
     */
    public boolean getEstado(){
        return estado;
    }

    /**
     * Permite acceder al arreglo de paredes
     * @return paredes de una casilla
     */
    public boolean[] getParedes(){
        return paredes;
    }

    @Override
    public String toString() {
        switch (orden){
            case 1:
                return " I ";
            case -1:
                return  " F ";
            case 0:
                return "   ";
            default:
                return " X ";
        }
    }

    /**
     * Permite modificar el estado de una casilla
     * @param estado Valor a modificar
     */
    public void setEstado(boolean estado){
        this.estado = estado;
    }

    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoIzquierdo vecino que sera asignado
     */
    public void setVecinoIzquierdo(Casilla becinoIzquierdo) {
        this.vecinoIzquierdo = becinoIzquierdo;
    }

    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoDerecho vecino que sera asignado
     */
    public void setVecinoDerecho(Casilla becinoDerecho) {
        this.vecinoDerecho = becinoDerecho;
    }
    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoAbajo vecino que sera asignado
     */
    public void setVecinoAbajo(Casilla becinoAbajo) {
        this.vecinoAbajo = becinoAbajo;
    }

    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoArriba vecino que sera asignado
     */
    public void setVecinoArriba(Casilla becinoArriba) {
        this.vecinoArriba = becinoArriba;
    }

    /**
     * Nos indica si la casilla tiene una pared en la parte izquerda, para poder dibujarla
     * @return Si cuenta con dicha pared, regresa verdadero, falso en otro caso.
     */
    public boolean tieneParedIzquierda(){
        return paredes[3];
    }


    /**
     * Responde a la presgunta, ¿esta casilla tiene un vecino a su lado x?
     * @param posicion el lado por el cual se pregunta si tiene una posicion
     * @return De ser cierto devuelve verdadero en otro caso, falso.
     */
    public boolean hayVecino(int posicion){
         switch (posicion){
            case 0:
                return vecinoArriba != null;
            case 1:
                return vecinoDerecho != null;
             case 2:
                return vecinoAbajo != null;
             case 3:
                 return vecinoIzquierdo != null;
            default:
                return false;
        }
    }

    /**
     * Significa que en elgun momento el vecino (siempre adyacente) que se esta consultadgo ya ha sido marcado o
     * si se prefiere "visitado"
     * @param pos posicion a la que es adyacente respecto a la casilla original
     * @return valor binario sobre si ha sido visitada dicha casilla o no
     */
    public boolean hayVecinoOcupado(int pos){
        switch (pos){
            case 0:
                return vecinoArriba.estado;
            case 1:
                return vecinoDerecho.estado;
            case 2:
                return vecinoAbajo.estado;
            case 3:
                return vecinoIzquierdo.estado;
            default:
                return false;
        }
    }

    /**
     * Se encarga de porporcionar una casilla dada una posicion respecto a esta
     * @param posicion valor entre 0 y 3 que indica la adyacencia con la casilla originar
     * @return la casilla adyacente
     */
    public Casilla dameVecino(int posicion){
        switch (posicion){
            case 0:
                return vecinoArriba;
            case 1:
                return vecinoDerecho;
            case 2:
                return vecinoAbajo;
            case 3:
                return vecinoIzquierdo;
            default:
                return null;
        }
    }

    /**
     * Modifica directamente el valor de una adyacencia entre vecinos, permitiendo cambiar el valor para las
     * casillas del contorno
     * @param pos posicion donde se cambiara la adyacencia
     */
    public void configuraleElVecino(int pos){
        vecinos[pos] = false;
    }

    /**
     * Devuelve un arreglo con las convenciones que establecimos sobre las adyacencias
     *  y su posicion i.e. 0,1,2,3
     * @return arreglo de vecinos sin ser visitados
     */
    public ArregloDinamico<Integer> vecinosDisponibles(){
        ArregloDinamico<Integer> disponibles = new ArregloDinamico<>();
        for (int i = 0; i < 4; i++) {
            if (vecinos[i]){
                if (i == 0 && !vecinoArriba.estado){
                    disponibles.agrega(i);
                }
                if (i == 1 && !vecinoDerecho.estado){
                    disponibles.agrega(i);
                }
                if (i == 2 && !vecinoAbajo.estado){
                    disponibles.agrega(i);
                }
                if (i == 3 && !vecinoIzquierdo.estado){
                    disponibles.agrega(i);
                }
            }
        }
        return disponibles;
    }

    /**
     * Hace la diferencia bajo el criterio de disponibilidad constrastandolo con el de tener paredes libres para
     * mosrar cuales son las casillas que tienen libre el paso
     * @return casillas adyacentes disponibles
     */
    public ArregloDinamico<Integer> vecinosDisponiblesMod(){
        ArregloDinamico<Integer> disponibles = new ArregloDinamico<>();
        for (int i = 0; i < 4; i++) {
            if (vecinos[i] && !paredes[i]){
                disponibles.agrega(i);
            }
        }
        return disponibles;
    }


    /**
     * Permite modificar el valor de la casilla que ha encolado (metido en la estructura de datos) a nuestr variable en
     * cuestion
     * @param quienMeEncolo Referencia a la casilla que se evaluo antes en el proceso de solucion
     */
    public void setQuienMeEncolo(Casilla quienMeEncolo) {
        this.quienMeEncolo = quienMeEncolo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Casilla casilla = (Casilla) o;
        return posicionX == casilla.posicionX &&
                posicionY == casilla.posicionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicionX, posicionY);
    }

    /**
     * Sirve para acceder al valor del objeto que en el proceso de solucion al laberinto ha encolado a dicha casilla
     * @return Casilla anterior en la trayectoria
     */
    public Casilla getQuienMeEncolo() {
        return quienMeEncolo;
    }

    /**
     * Permite modificar el orden en el que se dio la trayectoria para conectar la solucion, es util si se llegase a requerir
     * @param orden valor en el que se fue agregando
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
}