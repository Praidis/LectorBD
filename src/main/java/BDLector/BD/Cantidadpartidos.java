package BDLector.BD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
//import java.util.LinkedHashMap;
import java.util.*;


public class Cantidadpartidos {
	
	
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectointegrador";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "20302758Abc*";

    public ArrayList<Apostador2> cantidadpartidos() {
        ArrayList<Apostador2> Cantidadpartidos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT idapostador, veces_aparece \r\n"
                                    + "FROM (\r\n"
                                    + "       SELECT idapostador, idronda, SUM(aciertos) AS total_aciertos, COUNT(*) OVER (PARTITION BY idapostador) AS veces_aparece\r\n"
                                    + "       FROM (\r\n"
                                    + "              SELECT pronosticos.idRonda, pronosticos.idPartido, ronda.Equipo1, ronda.Goles_Equipo1, ronda.Goles_Equipo2, ronda.Equipo2, pronosticos.idApostador, pronosticos.idApuesta,\r\n"
                                    + "              CASE \r\n"
                                    + "                 WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 THEN ronda.Equipo1 \r\n"
                                    + "                 WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 THEN ronda.Equipo2\r\n"
                                    + "                 ELSE 'Empate'\r\n"
                                    + "              END AS idesultado,\r\n"
                                    + "              CASE \r\n"
                                    + "                 WHEN ronda.Goles_Equipo1 > ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo1 THEN 1 \r\n"
                                    + "                 WHEN ronda.Goles_Equipo1 < ronda.Goles_Equipo2 AND pronosticos.idApuesta = ronda.Equipo2 THEN 1\r\n"
                                    + "                 WHEN ronda.Goles_Equipo1 = ronda.Goles_Equipo2 AND pronosticos.idApuesta = 'Empate' THEN 1\r\n"
                                    + "                 ELSE 0\r\n"
                                    + "              END AS aciertos\r\n"
                                    + "              FROM pronosticos\r\n"
                                    + "              JOIN ronda ON pronosticos.idPartido = ronda.idPartido AND pronosticos.idRonda = ronda.idronda \r\n"
                                    + "            ) AS resultado_pronosticos\r\n"
                                    + "       GROUP BY idapostador, idronda\r\n"
                                    + "       HAVING SUM(aciertos) = (SELECT COUNT(*) FROM ronda WHERE idronda = resultado_pronosticos.idRonda)\r\n"
                                    + "     ) AS filtrado\r\n"
                                    + "GROUP BY idapostador\r\n"
                                    + "ORDER BY veces_aparece DESC;");
            if (resultSet.next()) {
           	 do {
                 String apostador = resultSet.getString("idapostador");
                 int vecesaparece = resultSet.getInt("veces_aparece");
                 Cantidadpartidos.add(new Apostador2(apostador, vecesaparece));
                            	 }
            while (resultSet.next());

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Cantidadpartidos;
  
}
}

