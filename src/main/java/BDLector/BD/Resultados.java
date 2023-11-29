package BDLector.BD;

public class Resultados {
	private String idresultado;
    private String idapuesta;
    
    Resultados(String idresultado, String idapuesta){
        
        this.idresultado= idresultado;
         this.idapuesta = idapuesta;
}
    public String getidresultado() {
        return idresultado;
    }

    public String getidapuesta() {
        return idapuesta;
    }
    
    public String toString() {
        return " "+ idapuesta;
     }
	
}
