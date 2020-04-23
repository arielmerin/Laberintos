/**
 * <h1> Clase Casilla </h1>
 * Es la clase principal que compone el proyecto, con base en esta clase se podran asignar valores y en un
 * futuro laberintos compuestos de casillas, es de suma importancia pues con estos objetos se modelara
 *  el comportamiento de cada lugar disponible o no en el juego principal.
 * @author Ariel Merino Armando Aquino
 * @version 1.2
 */
public class Casilla {
    //    Atributos
    /**
     * Valor de la posicion de dicha casilla en el eje de las x
     */

    /**
     * Valor que responde a la pregunta ¿la casilla ha sido visitada en un recorrido?
     */
    private boolean estado;

    private int posicionX;
    private int posicionY;

    /**
     * Srive para dar adyacencia a las paredes de la casilla
     */
    private boolean[] paredes;

    private boolean[] vecinos;

    private Casilla becinoIzquierdo;

    private Casilla becinoDerecho;

    private Casilla becinoAbajo;

    private Casilla becinoArriba;

    /**
     * En este constrcutor se ponen los valores minimos para crear una casilla.
     */
    public Casilla(int posicionX, int posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        paredes = new boolean[4];
        for (int i = 0; i < 4; i++) {
            paredes[i] = true;
        }
        vecinos = new boolean[4];

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
        vecinos[direccion] = true;
        paredes[direccion] = false;
        switch (direccion){
            case 0:
                becinoArriba.estado = true;
                becinoArriba.vecinos[2] = true;
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


    public boolean hayAdyacenciasDisponibles(){
        if (becinoIzquierdo != null){
            if (!becinoIzquierdo.estado){
                return true;
            }
        }
        if (becinoAbajo != null){
            if (!becinoAbajo.estado){
                return true;
            }

        }
        if (becinoDerecho != null){
            if (!becinoDerecho.estado){
                return true;
            }

        }
        if (becinoArriba != null){
            if (!becinoArriba.estado){
                return true;
            }

        }
        return false;
    }
    /**
     * Nos dice si alguna de las casillas adyacentes ya ha sido visitada o no
     * @return
     */
    public boolean hayVecinosSinVisitar(){
        for (int i = 0; i < vecinos.length; i++) {
            if (!vecinos[i])
                return true;
        }
        return false;
    }



    /**
     * Nos permite acceder a los vecinos que ya han sido visitados
     * @return vecinos adyacentes visitados
     */
    public boolean yaVisitoAlBeci(int posicion){
        return vecinos[posicion];
    }

    /**
     *
     * @return
     */
    public boolean[] getParedes(){
        return paredes;
    }

    @Override
    public String toString() {
        return estado? " X ": "   ";
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void setBecinoIzquierdo(Casilla becinoIzquierdo) {
        this.becinoIzquierdo = becinoIzquierdo;
    }

    public void setBecinoDerecho(Casilla becinoDerecho) {
        this.becinoDerecho = becinoDerecho;
    }

    public void setBecinoAbajo(Casilla becinoAbajo) {
        this.becinoAbajo = becinoAbajo;
    }

    public void setBecinoArriba(Casilla becinoArriba) {
        this.becinoArriba = becinoArriba;
    }

    public boolean tieneParedIzquierda(){
        return paredes[3];
    }


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

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }


    public void imprimePosicion(){
        System.out.println("(" + posicionX + ", " + posicionY + ")");
    }

    public void configuraleElVecino(int pos){
        vecinos[pos] = true;
    }


    public ArregloDinamico<Integer> vecinosDisponibles(){
        ArregloDinamico<Integer> disponibles = new ArregloDinamico<>();
        for (int i = 0; i < 4; i++) {
            if (!vecinos[i]){
                disponibles.agrega(i);
            }
        }
        return disponibles;
    }
}