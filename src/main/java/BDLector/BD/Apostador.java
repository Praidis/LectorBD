package BDLector.BD;

public class Apostador {
	 private String idapostador;
	    private String idapuesta;
	    private String idresultado;
	    private int aciertos;

	    public Apostador(String idapostador, String idapuesta, String idresultado, int aciertos) {
	        this.idapostador = idapostador;
	        this.idapuesta = idapuesta;
	        this.idresultado = idresultado;
	        this.aciertos = aciertos;
	    }
	    
	    public String getidapostador() {
	        return idapostador;
	    }

	    public String getidapuesta() {
	        return idapuesta;
	    }
	    public String getidresultado() {
	        return idresultado;
	    }
	    
	    public int getAciertos() {
	        return aciertos;
	    }
	    public void setAciertos(int aciertos) {
	        this.aciertos = aciertos;
	    }

	    public String toString() {
	        return idapostador + " " + idapuesta;
	    }


}
