package BDLector.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.sql.*;

public class Reader {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectointegrador";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "20302758Abc*";
    public List<Puntajes> readData() {
        List<Puntajes> puntajesList = new ArrayList<>();
        
        //Set<String> addedApostadores = new HashSet<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT pronosticos.idRonda, pronosticos.idPartido, ronda.Equipo1, ronda.Goles_Equipo1, ronda.Goles_Equipo2, ronda.Equipo2, pronosticos.idApostador, pronosticos.idApuesta,\r\n"
            		+ "       CASE \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 THEN ronda.Equipo1 \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 THEN ronda.Equipo2\r\n"
            		+ "           ELSE 'Empate'\r\n"
            		+ "       END AS idresultado,\r\n"
            		+ "       CASE \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo1 THEN 1 \r\n"
            		+ "           WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo2 THEN 1\r\n"
            		+ "           WHEN ronda.Goles_Equipo1 = ronda.Goles_Equipo2 AND pronosticos.idApuesta = 'Empate' THEN 1\r\n"
            		+ "           ELSE 0\r\n"
            		+ "       END AS aciertos\r\n"
            		+ "FROM pronosticos\r\n"
            		+ "JOIN ronda ON pronosticos.idPartido = ronda.idPartido AND pronosticos.idRonda = ronda.idRonda;");
            while (resultSet.next()) {
            	 boolean found = false;
                 for (Puntajes puntajes : puntajesList) {
                     if (puntajes.getApostador().getidapostador().equals(resultSet.getString("idapostador"))) {
                         found = true;
                         int aciertos = puntajes.getApostador().getAciertos();
                         if (resultSet.getString("idresultado").equals(resultSet.getString("idapuesta"))) {
                             aciertos++;
                         }
                         puntajes.getApostador().setAciertos(aciertos);
                         break;
                     }
                 }
                 if (!found) {
                     Apostador Apostador = new Apostador(resultSet.getString("idapostador"),resultSet.getString("idapuesta"),resultSet.getString("idresultado"),resultSet.getInt("aciertos"));
                     Pronostico pronostico = new Pronostico(resultSet.getInt("idronda"),resultSet.getInt("idpartido"),resultSet.getString("idapostador"),resultSet.getString("idapuesta"));
                     Resultados Resultado = new Resultados(resultSet.getString("idresultado"),resultSet.getString("idapuesta"));
                     Puntajes puntajes = new Puntajes(Resultado, pronostico,Apostador);
                     puntajesList.add(puntajes);
                 }
             }
             statement.close();
             connection.close();
         } catch (Exception e) {
             System.out.println("Error en la conexión con la base de datos.");
             e.printStackTrace();
         }
         return puntajesList;
     }
	
	

}
