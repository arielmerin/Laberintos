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
        System.out.println("Este programa genera (con la opcion 2) laberintos de 15 x 15 mostrando la solucion, \ntambien" +
                " puedes asignar el tamaño que gustes (mayor estricto a 1 en ambos valores) y las casillas de inicio y fin");

        boolean primeraPregunta = true;
        do {
            System.out.println("[1] Para ingresar valores del laberinto");
            System.out.println("[2] Generar automaticamente el laberinto");
            System.out.println("[0] Salir del programa");
            int respuesta = getInt("Seleccione una opcion: ", "Error, ingrese una opcion valida");
            switch (respuesta){
                case 1:
                    int n, m;
                    do{
                        n = getInt("Ingrese el valor del ancho (horizontal), debe ser mayor que 1 ", "Error, ingrese un valor valido");
                        m = getInt("Ingrese el valor del largo (vertical), debe ser mayor que 1 ", "Error, ingrese un valor valido");
                    }while (n <= 1 || m <= 1);
                    Laberinto laberinto = new Laberinto(m,n);
                    System.out.format("Laberinto de dimension (%d,%d) sin solucion",n,m);
                    laberinto.crearLaberinto();
                    laberinto.printStringGrid();
                    System.out.format("Laberinto de dimension (%d,%d) con solucion\n\n",n,m);
                    System.out.format("Para proporcionar las coordenadas recuerde que la primera casilla es (0,0), " +
                            "\nes decir la primera posicion es 0. \tAdemas la maxima posicion es (%d,%d)\n", n-1,m-1);
                    System.out.println("No es valido porporcionar la misma pareja ordenada de inicio que de final, si usted hace eso el programa volvera a pedirle sus datos");

                    int iniY = 0, iniX = 0, veces = 0, times = 0;
                    int finalY =0, finalX = 0;
                    do {
                        System.out.println("======================");
                        do {
                            if (veces > 0){
                                System.out.format("Teclee nuevamente los valores para la coordenada de salida pues (%d,%d) no es valida \n",
                                        iniX, iniY);
                            }
                            iniX = getInt("Ingrese la coordenada en x del inicio","Error, ingrese un valor valido");
                            iniY = getInt("Ingrese la coordenada en y del inicio","Error, ingrese un valor valido");
                            veces++;
                        }while (iniY >= m || iniX >= n);
                        do {
                            if (times > 0){
                                System.out.format("Teclee nuevamente los valores para la coordenada de salida pues (%d,%d) no es valida \n",
                                        finalX, finalY);
                            }
                            finalX = getInt("Ingrese la coordenada en x del final","Error, ingrese un valor valido");
                            finalY = getInt("Ingrese la coordenada en y del final","Error, ingrese un valor valido");
                            times++;
                        }while (finalY >= m || finalX >= n);
                        System.out.println("======================");
                    }while (iniY == finalY && iniX == finalX );

                    laberinto.hallarSolucion(laberinto.casillas[iniY][iniX],laberinto.casillas[finalY][finalX]);
                    laberinto.printStringGrid();
                    break;
                case 2:
                    Laberinto laberinto1 = new Laberinto(15,15);
                    laberinto1.crearLaberinto();
                    System.out.format("El laberinto con dimension (%d,%d), sin solucion es este",15,15);
                    laberinto1.printStringGrid();
                    laberinto1.hallarSolucion(laberinto1.casillas[0][0],laberinto1.casillas[14][14]);
                    System.out.println(" La solucion es  la siguiente");
                    laberinto1.printStringGrid();
                    break;
                case 0:
                    System.out.println("Hasta luego ");
                    primeraPregunta = false;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while (primeraPregunta);

    }
}
