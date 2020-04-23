public class Main {
    public static void main(String[] args) {

        LaberintoCola laberintoCola = new LaberintoCola(3,3);

        laberintoCola.printStringGrid();
        laberintoCola.crearLaberinto();

        //laberintoCola.casillas[1][0].vecinoVisitado(0);
        //laberintoCola.casillas[1][0].setEstado(true);
        //laberintoCola.casillas[1][0].vecinoVisitado(1);
        //laberintoCola.casillas[1][0].vecinoVisitado(0);
        //laberintoCola.casillas[1][0].vecinoVisitado(2);
        laberintoCola.casillas[2][2].setEstado(true);
        laberintoCola.casillas[2][2].visitarVecino(0);
        laberintoCola.casillas[2][2].visitarVecino(3);
        //System.out.println(laberintoCola.casillas[2][2].hayVecinosSinVisitar());
        //System.out.println(laberintoCola.casillas[4][2].vecinosDisponibles());

        //System.out.println(laberintoCola.casillas[1][0].hayVecino(1));
        //System.out.println(laberintoCola.casillas[1][0].hayVecino(0));
        //System.out.println(laberintoCola.casillas[1][0].hayVecinosSinVisitar());
        //laberintoCola.casillas[1][1].setEstado(true);
        //laberintoCola.casillas[1][1].vecinoVisitado(1);
        laberintoCola.printStringGrid();


    }
}
