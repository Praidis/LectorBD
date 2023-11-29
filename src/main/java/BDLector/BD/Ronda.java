package BDLector.BD;

public class Ronda {	
    private int idronda;
    private int idpartido;
    private String equipo1;
    private int golesEquipo1;
    private int golesEquipo2;
    private String equipo2;
    
    public Ronda(int idronda, int idpartido, String equipo1, int golesEquipo1, int golesEquipo2, String equipo2) {
        this.idronda = idronda;
        this.idpartido = idpartido;
        this.equipo1 = equipo1;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.equipo2 = equipo2;
    }
        
    public String toString() {
    return idronda + " " + idpartido + " " + equipo1 + " " + golesEquipo1 + " " + golesEquipo2 + " " + equipo2;
}
}
    
