package Archivo;

import Jugadores.Jugador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {

    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void imprimirLinea() {
        for (int i = 0; i < partes.length; i++) {
            System.out.print(partes[i] + " | ");
        }
    }

    public ArrayList aggEquipo(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Jugador> equipo = new ArrayList<>();
        lector = new BufferedReader(new FileReader(ruta));
        while ((linea = lector.readLine()) != null) {
            partes = linea.split(",");
            //imprimirLinea();
            Jugador jugador;
            
            jugador = new Jugador(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), false);
            equipo.add(jugador);

        }
//        for (int i = 0; i < equipo.size(); i++) {
//            System.out.println(equipo.get(i).toString());
//        }
        return equipo;
    }
}
