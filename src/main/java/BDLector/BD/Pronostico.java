package BDLector.BD;

public class Pronostico {
	
	 private int idronda;
	 private int idpartido;
	 private String idapostador;
	 private String idapuesta;
	 
	 Pronostico(int idronda, int idpartido, String idapostador,String idapuesta)
	 {
	 this.idronda = idronda;
	 this.idpartido = idpartido;
	 this.idapostador = idapostador;
	 this.idapuesta = idapuesta;
     }	 
	 public String getidapostador() {
		    return idapostador;
		}

		public String getidapuesta() {
		    return idapuesta;
		}
		
	 public String toString() {
		return idronda +" " +idpartido+" "+ idapostador+" "+ idapuesta;
	 }
}
