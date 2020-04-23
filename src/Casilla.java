public class Casilla {
    //    Atributos

    private int posicionX;
    private int posicionY;
    private boolean estado;
    private boolean[] paredes;


    public Casilla(int posicionX, int posicionY, boolean estado){
        this.estado = estado;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        paredes = new boolean[4];
    }




}