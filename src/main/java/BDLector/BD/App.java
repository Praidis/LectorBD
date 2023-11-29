package BDLector.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class App {
	
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/projectointegrador";
        String user = "root";
        String password = "20302758Abc*";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            List<Apostador> apostadoresList = Puntajes.getApostadores(connection);

            for (Apostador apostador : apostadoresList) {
                System.out.println("Id apostador: " + apostador.getidapostador() + ", Aciertos: " + apostador.getAciertos());
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error en la conexión con la base de datos.");
            e.printStackTrace();
        }
    }
}


