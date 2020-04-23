package com.util;

import java.util.Scanner;

/**
 * <h1>Utilidades</h1>
 * En esta clase se tiene la funcionalidad de contar con metodos auxiliares a la clase UIMenu pues  para el ingreso
 * de datos y la validacion de los mismos ha sido necesario implementar esta clase
 * @author Ariel Merino Peña, Armando Aquino Chapa
 * @version 1
 */
public class Utilidades {
    /**
     * Este método sirve para controlar que en las entradas de enteros
     * lo único que se pueda ingresar sean justo sólo valores numéricos y nada de cadenas
     *
     * @param msg mensaje de instrucciones al usuario o indicaciones
     * @param error mensaje de error al detectar que la entrada no es un valor nummérico
     *
     * @return entero que validó y ahora puede ser utilizado
     */
    public static int getInt(String msg, String error){
        int entero = 0;
        Scanner scan = new Scanner(System.in);
        String librearBuffer;
        boolean conti = true;
        do{
            System.out.println(msg);
            if(scan.hasNextInt())
            {
                entero = scan.nextInt();
                if (entero >= 0){
                    conti = false;
                }
            }else{
                librearBuffer = scan.next();
                System.out.println(error);
            }
        }while(conti);
        return entero;
    }
}