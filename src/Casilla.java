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
    private int posicionX;
    /**
     * Valor de la posicion de dicha casilla en el eje de las y
     */
    private int posicionY;
    /**
     * Valor que responde a la pregunta ¿la casilla ha sido visitada en un recorrido?
     */
    private boolean estado;
    /**
     * Srive para dar adyacencia a las paredes de la casilla
     */
    private boolean[] paredes;

    private Casilla[] vecinos;

    /**
     * En este constrcutor se ponen los valores minimos para crear una casilla.
     * @param posicionX La posicion es escencial, pues  gira en torno a conocer su posicion.
     * @param posicionY La posicion es escencial, pues  gira en torno a conocer su posicion.
     */
    public Casilla(int posicionX, int posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        paredes = new boolean[4];
        vecinos = new Casilla[4];
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
    public void liberaPared(int direccion){
        paredes[direccion] = true;
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
    public void vecinoVisitado(int direccion){
        vecinos[direccion].estado = true;
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
     * Nos dice si alguna de las casillas adyacentes ya ha sido visitada o no
     * @return
     */
    public boolean hayVecinosSinVisitar(){
        for (int i = 0; i < vecinos.length; i++) {
            if (vecinos[i].estado)
                return true;
        }
        return false;
    }

    /**
     * Nos permite acceder a los vecinos que ya han sido visitados
     * @return vecinos adyacentes visitados
     */
    public Casilla[] getVecinos(){
        return vecinos;
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
        return estado? "X": " ";
    }
}