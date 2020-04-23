package com;

import com.util.ArregloDinamico;

/**
 * <h1> Clase com.Casilla </h1>
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
    private Casilla becinoIzquierdo;

    /**
     * Referencia a la casilla que se encentra a la derecha
     */
    private Casilla becinoDerecho;

    /**
     * Referencia a la casilla que se encentra hacia abajo
     */
    private Casilla becinoAbajo;

    /**
     * Referencia a la casilla que se encentra hacia arriba
     */
    private Casilla becinoArriba;

    /**
     * En este constrcutor se ponen los valores minimos para crear una casilla.
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
                becinoArriba.estado = true;
                becinoArriba.vecinos[2] = false;
                becinoArriba.paredes[2] = false;
                break;
            case 1:
                becinoDerecho.estado = true;
                becinoDerecho.paredes[3] = false;
                becinoDerecho.vecinos[3] = false;
                break;
            case 2:
                becinoAbajo.estado = true;
                becinoAbajo.paredes[0] = false;
                becinoAbajo.vecinos[0] = false;
                break;
            case 3:
                becinoIzquierdo.estado = true;
                becinoIzquierdo.paredes[1] = false;
                becinoIzquierdo.vecinos[1] = false;
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
        return !estado? " X ": "   ";
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
    public void setBecinoIzquierdo(Casilla becinoIzquierdo) {
        this.becinoIzquierdo = becinoIzquierdo;
    }

    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoDerecho vecino que sera asignado
     */
    public void setBecinoDerecho(Casilla becinoDerecho) {
        this.becinoDerecho = becinoDerecho;
    }
    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoAbajo vecino que sera asignado
     */
    public void setBecinoAbajo(Casilla becinoAbajo) {
        this.becinoAbajo = becinoAbajo;
    }

    /**
     * Brinda la capacidad de asignar un vecino a cierto lado de la casilla
     * @param becinoArriba vecino que sera asignado
     */
    public void setBecinoArriba(Casilla becinoArriba) {
        this.becinoArriba = becinoArriba;
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
                return becinoArriba != null;
            case 1:
                return becinoDerecho != null;
             case 2:
                return becinoAbajo != null;
             case 3:
                 return becinoIzquierdo != null;
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
                return becinoArriba.estado;
            case 1:
                return becinoDerecho.estado;
            case 2:
                return becinoAbajo.estado;
            case 3:
                return becinoIzquierdo.estado;
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
                return becinoArriba;
            case 1:
                return becinoDerecho;
            case 2:
                return becinoAbajo;
            case 3:
                return becinoIzquierdo;
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
                if (i == 0 && !becinoArriba.estado){
                    disponibles.agrega(i);
                }
                if (i == 1 && !becinoDerecho.estado){
                    disponibles.agrega(i);
                }
                if (i == 2 && !becinoAbajo.estado){
                    disponibles.agrega(i);
                }
                if (i == 3 && !becinoIzquierdo.estado){
                    disponibles.agrega(i);
                }
            }
        }
        return disponibles;
    }
}