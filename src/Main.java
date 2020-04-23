
/**
 * <h1> Clase Main </h1>
 * Clase principal desde la cual se efectuan las llamadas a clases secundarias en la ejecuacion del programa.
 * @author Ariel Merino, Armando Aquino
 * @version 1.0
 */
public class Main {
    /**
     * Metodo principal desde el cual se manda a llamar a las funciones escenciales para la ejecucion del programa
     * @param args argumentos que se emplearan al correr el metodo desde la terminal
     */
    public static void main(String[] args) {

        LaberintoCola laberintoCola = new LaberintoCola(10,10);

        laberintoCola.printStringGrid();
        laberintoCola.crearLaberinto();
        laberintoCola.printStringGrid();


    }
}
