package BDLector.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Reader {
	

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectointegrador";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "20302758Abc*";

    Ronda ronda = new Ronda();
    Puntos puntos = new Puntos();
    int contadorParidosJugados = 0;
    int tondaAnt= 0;
    ArrayList<Integer> partidosJugadosEnRonda = new ArrayList<>();
    ArrayList<Integer> puntosValores = puntos.readPuntos();
	int caciertos = 0;
    int puntoAcierto = puntosValores.get(0);
	int puntoRonda = puntosValores.get(1);
    public ArrayList<Apostador> readApostador() {
    ArrayList<Apostador> Apostador = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT pronosticos.idRonda, pronosticos.idPartido, ronda.Equipo1, ronda.Goles_Equipo1, ronda.Goles_Equipo2, ronda.Equipo2, pronosticos.idApostador, pronosticos.idApuesta,\r\n"
            		+ "       CASE \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 THEN ronda.Equipo1 \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 THEN ronda.Equipo2\r\n"
            		+ "           ELSE 'Empate'\r\n"
            		+ "       END AS idesultado,\r\n"
            		+ "       CASE \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo1 THEN 1 \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo2 THEN 1\r\n"
            		+ "           WHEN ronda.Goles_Equipo1 = ronda.Goles_Equipo2 AND pronosticos.idApuesta = 'Empate' THEN 1\r\n"
            		+ "           ELSE 0\r\n"
            		+ "       END AS aciertos,\r\n"
            		+ "       (SELECT COUNT(*) FROM ronda r2 WHERE r2.idronda = pronosticos.idRonda) AS cantidad_partidos\r\n"
            		+ "FROM pronosticos\r\n"
            		+ "JOIN ronda ON pronosticos.idPartido = ronda.idPartido AND pronosticos.idRonda = ronda.idronda;");
            if (resultSet.next()) {
            	 do {
                     String idApostador = resultSet.getString("idapostador");
                     int aciertos = resultSet.getInt("aciertos");
                     boolean existeApostador = false;
                     for (Apostador apostador : Apostador) {
                         if (apostador.getidapostador().equals(idApostador)) {
                             apostador.setAciertos(apostador.getAciertoss() + aciertos*puntoAcierto);
                             existeApostador = true;
                             break;
                         }
                     }
                     if (!existeApostador) {
                         Apostador apostador = new Apostador(idApostador, aciertos*puntoAcierto);
                         Apostador.add(apostador);
                     }
                    
                } while (resultSet.next());

            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading the MySQL JDBC Driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return Apostador;
    }

}
