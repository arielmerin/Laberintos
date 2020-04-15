package com.mx.edd;

public class Punto {
    //    Atributos
    private int x;      // Coordenada x
    private int y;      // Coordenada y

    /**
     * Asigna valor a la coordenada x del punto.
     * @param nuevaX - nuevo valor para la coordenada x.
     */
    public void asignarX(int nuevaX) {
        x = nuevaX;
    }
    /**
     * Asigna valor a la coordenada y del punto.
     * @param nuevaY - nuevo valor para la coordenada y.
     */
    public void asignarY(int nuevaY) {
        y = nuevaY;
    }
    /**
     * Regresa la coordenada x del punto.
     * @return int - la coordenada x del punto.
     */
    public int obtenerX () {
        return x;
    }
    /**
     * Regresa la coordenada y del punto.
     * @return int - la coordenada y del punto.
     */
    public int obtenerY () {
        return y;
    }
    /**
     * Permite desplazar un punto.
     * @param deltaX - desplazamiento en el eje de las Xs.
     * @param deltaY - desplazamiento en el eje de las Ys.
     */
    public void desplazar (int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }
    /**
     *  Determina la distancia entre dos puntos usando la fórmula
     *  ((x2-x1)^2 + (y2-y1)^2)^1/2
     *  @param p - punto respecto al que se quiere determinar la distancia.
     *  @return double - distancia entre los dos puntos.
     */
    public double distancia (Punto p) {
        return Math.sqrt((double)((x-p.obtenerX())*(x-p.obtenerX()) +
                (y-p.obtenerY())*(y-p.obtenerY())));
    }
    /**
     * Determina si tres puntos estan alineados verificando que se cumpla la
     * igualdad: (y-y1)*(x2-x1) = (y2-y1)*(x -x1)
     * @param p1, p2 - puntos que junto con el que se llama al método
     * se verificara si estan alineados.
     * @return true si estan alineados, false en otro caso.
     */
    public boolean estanAlineados (Punto p1, Punto p2) {
        return (y - p1.y)*(p2.x - p1.x) == (p2.y - p1.y)*(x -p1.x);
    }
    /**
     * Constructor de un punto, crea el punto (0,0)
     */
    public Punto () {
        x = y = 0;
    }
    /**
     * Constructor de un punto a partir de dos números enteros que
     * representan las coordenadas del nuevo punto.
     * @param x - coordenada x del nuevo punto.
     * @param y - coordenada y del nuevo punto.
     */
    public Punto (int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructor de un punto a partir de otro punto.
     * @param p - punto a partir del cual se creara el nuevo.
     */
    public Punto (Punto p) {
        x = p.x;
        y = p.y;
    }
    /**
     * Determina si dos puntos son iguales
     * @param p - punto contra el cual se va a comparar
     * @return boolean - true si son iguales y false en otro caso
     */
    public boolean equals (Object p) {
        Punto pto = (Punto)p;
        return x == pto.obtenerX() && y == pto.obtenerY();
    }
    /**
     * Método para convertir un Punto a cadena de caracteres
     * @return String -- el punto en formato de cadena
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}