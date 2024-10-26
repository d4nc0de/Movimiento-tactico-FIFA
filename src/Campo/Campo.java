package Campo;

import Jugadores.Jugador;
import Campo.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author tuori
 */
public class Campo {

    private int marcadora = 0;
    private int turno = 0;
    private Grafo equipoA;
    ThreadLocalRandom tlr = ThreadLocalRandom.current();

    // Método para armar grafos para cada equipo por separado
    public Grafo armarGrafoP(int e ,ArrayList<Jugador> equipo, boolean Equipo) {
        Grafo grafo = new Grafo(equipo.size());  // Grafo con tamaño dinámico.
        for (Jugador jugador : equipo) {
            Nodo nodo = new Nodo(jugador);
            grafo.addNodo(nodo);
        }

        int[][] estrategia = grafo.matrizEstrategiasP(e , equipo);
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

}
