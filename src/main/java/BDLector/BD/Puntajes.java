package BDLector.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static List<Apostador> getApostadores(Connection connection) throws Exception {
        String query = "SELECT pronosticos.idRonda, pronosticos.idPartido, ronda.Equipo1, ronda.Goles_Equipo1, ronda.Goles_Equipo2, ronda.Equipo2, pronosticos.idApostador, pronosticos.idApuesta,\r\n"
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
        		+ "       END AS aciertos\r\n"
        		+ "FROM pronosticos\r\n"
        		+ "JOIN ronda ON pronosticos.idPartido = ronda.idPartido AND pronosticos.idRonda = ronda.idRonda;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Apostador> apostadoresList = new ArrayList<>();
        Set<String> apostadoresIdSet = new HashSet<>();

        while (resultSet.next()) {
            String idapostador = resultSet.getString("idapostador");
            int aciertos = resultSet.getInt("aciertos");

            if (!apostadoresIdSet.contains(idapostador)) {
                Apostador apostador = new Apostador(idapostador, "", "", aciertos);
                apostadoresList.add(apostador);
                apostadoresIdSet.add(idapostador);
            }
        }

        statement.close();
        return apostadoresList;
    } 
} 
	


