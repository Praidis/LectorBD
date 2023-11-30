package BDLector.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Puntos {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectointegrador";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "20302758Abc*";

    public ArrayList<Integer> readPuntos() {
        ArrayList<Integer> puntos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT idpuntosG, idpuntosP FROM Puntos");
            while (resultSet.next()) {
                int puntoAcierto = resultSet.getInt("idpuntosG");
                int puntoRonda = resultSet.getInt("idpuntosP");
                puntos.add(puntoAcierto);
                puntos.add(puntoRonda);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntos;
    }
    
    public String toString() {
        ArrayList<Integer> puntos = readPuntos();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < puntos.size(); i += 2) {
            builder.append("Punto de Acierto: ").append(puntos.get(i)).append(" | Punto de Ronda: ").append(puntos.get(i + 1)).append("\n");
        }
        return builder.toString();
    }
    
    public int getPuntoAcierto(int i) {
        ArrayList<Integer> puntos = readPuntos();
        return puntos.get(i);
    }

    public int getPuntoRonda(int i) {
        ArrayList<Integer> puntos = readPuntos();
        return puntos.get(i + 1);
    }

}