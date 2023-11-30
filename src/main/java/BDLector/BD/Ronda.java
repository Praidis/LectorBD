package BDLector.BD;

public class Ronda {	
    private int idronda;
    private int idpartido;
    private String idapostador;
    private int aciertos;
    private int numPartidos;
    
    // Constructor vacío
    public Ronda() {
    }

    // Constructor con parámetros
    public Ronda(int idronda, int idpartido, String idapostador, int aciertos,int numPartidos) {
        this.idronda = idronda;
        this.idpartido = idpartido;
        this.idapostador = idapostador;
        this.aciertos = aciertos;
        this.numPartidos = numPartidos;
    }
        
    public String toString() {
        return idronda + " " + idpartido + " " + idapostador + " " + aciertos + " "+ numPartidos;
    }
}
