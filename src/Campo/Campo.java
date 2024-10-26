package Campo;

import Archivo.CSV;
import Jugadores.Jugador;
import Campo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 *
 * @author tuori
 */
public class Campo {

    private int marcadora = 0;
    private int intento = 0;
    private Grafo equipoA;
    ThreadLocalRandom tlr = ThreadLocalRandom.current();

    // Método para armar grafos para cada equipo por separado
    public Grafo armarGrafoP(int e, ArrayList<Jugador> equipo, boolean Equipo) {
        Grafo grafo = new Grafo(equipo.size());  // Grafo con tamaño dinámico.
        for (Jugador jugador : equipo) {
            Nodo nodo = new Nodo(jugador);
            grafo.addNodo(nodo);
        }

        int[][] estrategia = grafo.matrizEstrategiasP(e, equipo);
        ArrayList<Nodo> lista = grafo.getListaAdyacencia();

        // Conectar nodos según la estrategia
        for (int i = 0; i < estrategia.length; i++) {
            for (int j = 0; j < estrategia[i].length; j++) {
                if (estrategia[i][j] == 1) {
                    grafo.conectarNodo(lista.get(i), lista.get(j));
                    System.out.println("Se ha conectado "
                            + lista.get(i).getJugador().getNombre() + " con "
                            + lista.get(j).getJugador().getNombre());
                }
            }
        }
        grafo.imprimir();
        // Asignar el grafo correspondiente
        equipoA = grafo;
        return equipoA;

    }

    public void intentoGolazo(ArrayList<Nodo> caminoMinimo) {
        for (int i = 0; i < caminoMinimo.size(); i++) {
            Nodo nodo = caminoMinimo.get(i);
            Jugador jugador = nodo.getJugador();

            // Marcamos que el jugador actual tiene el balón
            jugador.setIsBalon(true);
            System.out.println(jugador.getNombre() + " tiene el balón");

            // Si es el último jugador en el camino, se intenta hacer el gol
            if (i == caminoMinimo.size() - 1) {
                int numeroRandom = tlr.nextInt(0, 101);
                JOptionPane.showMessageDialog(null, (jugador.getNombre() + " se acerca a la porteria y lanza una patada al balon y..."));
                if (numeroRandom <= jugador.getRemate()) {
                    marcadora++; // Incrementa el marcador si es gol
                    JOptionPane.showMessageDialog(null, jugador.getNombre() + " ha anotado un gol! Marcador: " + marcadora);
                } else {
                    JOptionPane.showMessageDialog(null, jugador.getNombre() + " ha fallado el gol!");
                }
            }

            // Limpiar la posesión del balón para el siguiente pase
            jugador.setIsBalon(false);
        }
    }

    public void initGame() throws IOException {

        // Rutas de los archivos CSV de cada equipo.
        String ruta1 = "C:\\Users\\lostw\\OneDrive\\Documentos\\GitHub\\Movimiento-tactico-FIFA\\assets\\equipo1.csv";
        String ruta2 = "C:\\Users\\lostw\\OneDrive\\Documentos\\GitHub\\Movimiento-tactico-FIFA\\assets\\equipo2.csv";

        // Inicializar las instancias para leer los equipos desde los CSV.
        CSV tabla1 = new CSV();
        CSV tabla2 = new CSV();

        // Cargar los equipos en listas de jugadores.
        ArrayList<Jugador> equipo1 = tabla1.aggEquipo(ruta1);
        //ArrayList<Jugador> equipo2 = tabla2.aggEquipo(ruta2);

        Grafo grafoEquipoA = armarGrafoP(1, equipo1, true);
        //Grafo grafoEquipoB = armarGrafoP(1, equipo2, false);
        // Obtener camino mínimo desde el portero hasta el último jugador (ejemplo)
        ArrayList<Nodo> caminoMinimo = Grafo.caminos(1, grafoEquipoA, equipo1, 0);
        // Simular el paso de la pelota y el intento de gol
        intentoGolazo(caminoMinimo);
        intento++;
        JOptionPane.showMessageDialog(null, "Gracias por usar nuestro simulador, usted va por el intento No: "+intento);

    }

}
