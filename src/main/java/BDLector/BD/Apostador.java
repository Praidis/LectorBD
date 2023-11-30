package BDLector.BD;

public class Apostador {
	 private String idapostador;
     private int aciertos;

	    public Apostador(String idapostador, int aciertos) {
	        this.idapostador = idapostador;
	        this.aciertos = aciertos;
	    }
	    
	    public String getidapostador() {
	        return idapostador;
	    }

	    public int getAciertos(int ronda) {
	        return aciertos;
	    }
	    public int getAciertoss() {
	        return aciertos;
	    }
	    
	    public void setAciertos(int aciertos) {
	        this.aciertos = aciertos;
	    }

	    public String toString() {
	    return idapostador + " " + aciertos;
	    }


}
