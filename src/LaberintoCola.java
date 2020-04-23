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
    public Casilla [][] casillas;       // Laberinto
    private int [][] camino;        // Matriz de marcado de celdas

    public LaberintoCola(int largo, int ancho){
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
            casillas[0][j].setBecinoAbajo(casillas[1][j]);
            /**
             * Con esto estoy diciendo que "ya visisto el vecino de arriba
             */
            casillas[0][j].configuraleElVecino(0);

        }

        /**
         * Con esto le digo a la ultima fila que su vecino de arriba es el que tienen arriba jeje
         */
        for (int j = 0; j < ancho; j++) {
            casillas[largo-1][j].setBecinoArriba(casillas[largo-2][j]);
            /**
             * Con esto decimos que ya visito al vecino de abajo
             */
            casillas[largo-1][j].configuraleElVecino(2);
        }

        for (int i = 0; i < largo; i++) {
            /**
             * Con esto le digo que su vecino es el de la derecha a la primera columna
             */
            casillas[i][0].setBecinoDerecho(casillas[i][1]);
            /**
             * Con esto digo que ya visito el vecino de la izquierda
             */
            casillas[i][0].configuraleElVecino(3);
        }

        for (int i = 0; i < largo; i++) {
            /**
             * Con esto digo que su vecino es el interior
             */
            casillas[i][ancho-1].setBecinoIzquierdo(casillas[i][largo-2]);
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
                casillas[i][j].setBecinoIzquierdo(casillas[i][j-1]);
                casillas[i][j].setBecinoDerecho(casillas[i][j+1]);
            }
        }

        for (int i = 1; i < largo - 1; i++) {
            for (int j = 0; j < ancho; j++) {
                casillas[i][j].setBecinoArriba(casillas[i-1][j]);
                casillas[i][j].setBecinoAbajo(casillas[i+1][j]);
            }
        }

    }


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



    public void crearLaberinto() {
        /**
         * Objetos necesarios
         */
        Random ran = new Random();
        Pila<Casilla> pila = new Pila();

        int aleatorio = ran.nextInt(ancho);

        System.out.println("Esta es la casilla donde empezara (" + 1 + ", " + aleatorio + ")");

        pila.push(casillas[1][aleatorio]);
        int rec = 0;
        int i = 0;
        casillas[1][aleatorio].setEstado(true);

        while (!pila.esVacio() ) {
            printStringGrid();
            Casilla enCuestion = pila.peek();
            System.out.println("Estos son los elementos en la pila " + pila);
            System.out.print("Esta es su posicion: ");
            enCuestion.imprimePosicion();
            enCuestion.setEstado(true);
            System.out.println("Las posiciones que tiene disponibles" +enCuestion.vecinosDisponibles());
            if (enCuestion.vecinosDisponibles().getElementos() != 0){
                i = ran.nextInt(4);
                i = i % enCuestion.vecinosDisponibles().getElementos();
                int k = enCuestion.vecinosDisponibles().busca(i);
                System.out.println("La posicion en que se intentea " +k);

                if (enCuestion.hayVecino(k)){
                    if (!enCuestion.hayVecinoOcupado(k)){
                        Casilla siguiente = enCuestion.dameVecino(k);
                        enCuestion.visitarVecino(k);
                        System.out.println("Esta metiendo un elemento a la pila");
                        pila.push(siguiente);
                    }
                }
            }else{
                System.out.println("Entro al caso en el que ya no hay vecinos disponibles");
                pila.pop();
                System.out.println("El tamaño de la pila" +pila.getTamanio());
            }




        }
    }
}
