package BDLector.BD;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Reader reader = new Reader();
        //ArrayList<Integer> puntos = reader.readPuntos();
        ArrayList<Apostador> apostadores = reader.readApostador();
        for (Apostador apostador : apostadores) {
            System.out.println(apostador);
        }
    }
}

