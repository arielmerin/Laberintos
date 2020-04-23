package com.UIMenu;

import com.Laberinto;

import static com.util.Utilidades.getInt;

/**
 * <h1>UI Menu</h1>
 * Esta clase provee de todas las interaccione graficas que el usuario experimentara a traves de la terminal durante la
 * ejecucion del programa, lo refente a mostrar y manejar las opciones que el ejecutador use
 * @author Armando Aquino Chapa, Ariel Merino Peña
 * @version 1
 */
public class UIMenu {


    /**
     * Metodo inicial donde se le pide al usuario la seleccion de opciones para ofrecerle que se trabaje con la informacion
     * del sistema o que se lea un nuevo archivo, tambien se presentan las opciones de ver las materias a las que esta
     * inscrito un alumno y los alumnos que estan inscritos en una materia, solo se puede salir de este metodo una vez que
     * el usuario lo pidio de manera explicita
     */
    public static void principal(){
        System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
        System.out.println("BIENIDX A los laberitos");
        boolean primeraPregunta = true;
        do {
            System.out.println("[2] Para ingresar valores del laberinto");
            System.out.println("[5] Para salir del programa");
            int respuesta = getInt("Seleccione una opcion: ", "Error, ingrese una opcion valida");
            switch (respuesta){
                case 2:
                    int n = getInt("Ingrese el valor del largo, debe ser mayor que 1 ", "Error, ingrese un valor valido");
                    int m = getInt("Ingrese el valor del ancho, debe ser mayor que 1 ", "Error, ingrese un valor valido");
                    Laberinto laberinto = new Laberinto(n,m);
                    System.out.format("Laberinto de dimension (%d,%d) sin solucion",n,m);
                    laberinto.crearLaberinto();
                    laberinto.printStringGrid();
                    System.out.format("Laberinto de dimension (%d,%d) con solucion\n\n",n,m);
                    /**
                     * Mando's code
                     */
                    break;
                case 5:
                    System.out.println("Hasta luego ");
                    primeraPregunta = false;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while (primeraPregunta);

    }
}
