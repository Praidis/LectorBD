package BDLector.BD;
import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;

public class App {

    public static void main(String[] args) {
        Reader reader = new Reader();
        Puntos puntos = new Puntos();
        Cantidadpartidos veces = new Cantidadpartidos();

        // Muestra los puntos por acierto y ronda completa
        ArrayList<Integer> puntosArray = puntos.readPuntos();
        for (int i = 0; i < puntosArray.size(); i += 2) {
            System.out.println("Puntos por Acierto: " + puntos.getPuntoAcierto(i) + " | Puntos por Ronda completa: " + puntos.getPuntoRonda(i));
        }

        // Muestra los apostadores modificando sus aciertos
       ArrayList<Apostador> apostadores = reader.readApostador();
        ArrayList<Apostador2> cantidadpartidos = veces.cantidadpartidos();
        for (Apostador2 apostador : cantidadpartidos) {
            for (Apostador apost : apostadores) {
                if (apostador.getIdapostador().equals(apost.getidapostador())) {
                    apost.setAciertos(apost.getAciertos(0) + apostador.getVecesaparece()*puntos.getPuntoRonda(0));
                }else {
                	apost.setAciertos(apost.getAciertos(0));	
                }
            }
        }
        for (Apostador apostador : apostadores) {
            System.out.println(apostador.getidapostador()+"="+apostador.getAciertos(0));
        }

    }
}
