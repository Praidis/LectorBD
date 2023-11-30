package BDLector.BD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JButton button = new JButton("Calcular puntos de los Apostadores");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí va el código que ya tienes en el método main
                Reader reader = new Reader();
                Puntos puntos = new Puntos();
                Cantidadpartidos veces = new Cantidadpartidos();

                ArrayList<Integer> puntosArray = puntos.readPuntos();
                ArrayList<Apostador> apostadores = reader.readApostador();
                ArrayList<Apostador2> cantidadpartidos = veces.cantidadpartidos();

                for (int i = 0; i < puntosArray.size(); i += 2) {
                    textArea.append("Puntos por Acierto: " + puntos.getPuntoAcierto(i) + " | Puntos por Ronda completa: " + puntos.getPuntoRonda(i) + "\n");
                }

                for (Apostador2 apostador : cantidadpartidos) {
                    for (Apostador apost : apostadores) {
                        if (apostador.getIdapostador().equals(apost.getidapostador())) {
                            apost.setAciertos(apost.getAciertos(0) + apostador.getVecesaparece() * puntos.getPuntoRonda(0));
                        } else {
                            apost.setAciertos(apost.getAciertos(0));
                        }
                    }
                }

                for (Apostador apostador : apostadores) {
                    textArea.append(apostador.getidapostador() + "=" + apostador.getAciertos(0) + "\n");
                }
            }
        });

        frame.getContentPane().add(textArea, BorderLayout.CENTER);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
