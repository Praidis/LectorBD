package BDLector.BD;

public class Puntajes {

    private Resultados Resultado;
    private Pronostico pronostico;
    private Apostador Apostador;

    public Puntajes(Resultados Resultado, Pronostico pronostico, Apostador Apostador) {
        this.Resultado = Resultado;
        this.pronostico = pronostico;
        this.Apostador = Apostador;
    }

    public Resultados getResultado() {
        return Resultado;
    }

    public Pronostico getPronostico() {
        return pronostico;
    }

    public Apostador getApostador() {
        return Apostador;
    }

   public String toString() {
        return "" +Apostador;
    }
} 
	


