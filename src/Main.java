public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        Casilla casilla = new Casilla(0,1,false);
        System.out.println(casilla);
        LaberintoCola laberintoCola = new LaberintoCola(6,6);
        String[][] arreglo = new String[5][5];
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length; j++) {
                arreglo[i][j] = "X";
            }
        }
        laberintoCola.printStringGrid();



    }
}
