/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private int marcadorb = 0;
    private boolean ganador;
    private int turno = 0;
    private Grafo equipoA;
    private Grafo equipoB;

    /*private final int[][] equipoa = new int [][] ;
    private final int[][] equipob = new int [][] ;
    private final int[][] cestrategia = new int [][] ;
     */
    // Método para armar grafos para cada equipo por separado
    public Grafo armarGrafoP(ArrayList<Jugador> equipo, boolean Equipo) {
        Grafo grafo = new Grafo(equipo.size());  // Grafo con tamaño dinámico.
        for (Jugador jugador : equipo) {
            Nodo nodo = new Nodo(jugador);
            grafo.addNodo(nodo);
        }

        int[][] estrategia = grafo.matrizEstrategiasP(1, equipo);
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
        if (Equipo) {
            equipoA = grafo;
            return equipoA;
        } else {
            equipoB = grafo;
            return equipoB;
        }
    }

public Grafo unioneGrafo(Grafo equipoA, Grafo equipoB) {
    int n = equipoA.getListaAdyacencia().size();  // Tamaño del equipo (11).
    Grafo grafoCombinado = new Grafo(n * 2);  // Grafo combinado (22 nodos).

    // Copiar los nodos de los equipos A y B al grafo combinado.
    for (int i = 0; i < n; i++) {
        grafoCombinado.addNodo(equipoA.getListaAdyacencia().get(i));
        grafoCombinado.addNodo(equipoB.getListaAdyacencia().get(i));
    }

    // Crear la matriz combinada 22x22.
    int[][] campo = new int[n * 2][n * 2];

    // Copiar las matrices de adyacencia de A y B en la matriz combinada.
    int[][] equipoa = equipoA.getMatrizAdyacencia();
    int[][] equipob = equipoB.getMatrizAdyacencia();

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            campo[i][j] = equipoa[i][j];               // Equipo A (0-10).
            campo[i + n][j + n] = equipob[i][j];       // Equipo B (11-21).
        }
    }

    // Conectar delanteros del equipo A (8-10) con defensores del equipo B (11-15).
    for (int i = 8; i <= 10; i++) {
        for (int j = 11; j <= 15; j++) {
            campo[i][j] = campo[j][i] = 1;
            grafoCombinado.conectarNodo(
                grafoCombinado.getListaAdyacencia().get(i),
                grafoCombinado.getListaAdyacencia().get(j)
            );
        }
    }

    // Conectar delanteros del equipo B (19-21) con defensores del equipo A (0-4).
    for (int i = 19; i <= 21; i++) {
        for (int j = 0; j <= 4; j++) {
            campo[i][j] = campo[j][i] = 1;
            grafoCombinado.conectarNodo(
                grafoCombinado.getListaAdyacencia().get(i),
                grafoCombinado.getListaAdyacencia().get(j)
            );
        }
    }

    // Conectar mediocampistas entre equipos (5-7 del A con 16-18 del B).
    for (int i = 5; i <= 7; i++) {
        for (int j = 16; j <= 18; j++) {
            campo[i][j] = campo[j][i] = 1;
            grafoCombinado.conectarNodo(
                grafoCombinado.getListaAdyacencia().get(i),
                grafoCombinado.getListaAdyacencia().get(j)
            );
        }
    }

    // Conectar porteros con sus defensores.
    for (int i = 1; i <= 4; i++) {
        campo[0][i] = campo[i][0] = 1;
        grafoCombinado.conectarNodo(
            grafoCombinado.getListaAdyacencia().get(0),
            grafoCombinado.getListaAdyacencia().get(i)
        );
    }

    for (int i = 12; i <= 15; i++) {
        campo[11][i] = campo[i][11] = 1;
        grafoCombinado.conectarNodo(
            grafoCombinado.getListaAdyacencia().get(11),
            grafoCombinado.getListaAdyacencia().get(i)
        );
    }

    return grafoCombinado;
}


    public void iniciop(ArrayList<Jugador> equipoA, ArrayList<Jugador> equipoB, int[][] matrizPartido) {//Inicia el partido
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        ArrayList<Jugador> equipoInicial = tlr.nextBoolean() ? equipoA : equipoB;

        int rnum = tlr.nextInt(8, 11);  // Seleccionar un delantero aleatorio.

        Jugador jugadorInicial = equipoInicial.get(rnum);
        jugadorInicial.setBalon(true);
        System.out.println("Saca el equipo " + (equipoInicial == equipoA ? "A" : "B") + ": " + jugadorInicial.getNombre());

        // Iniciar la jugada desde el jugador seleccionado.
        cercaniaj(jugadorInicial, equipoInicial, matrizPartido);
    }

    public void cercaniaj(Jugador jugadorActual, ArrayList<Jugador> equipo, int[][] matrizAdyacencia) {
        if (!jugadorActual.isBalon()) {
            System.out.println(jugadorActual.getNombre() + " no tiene el balón.");
            return;
        }

        System.out.println(jugadorActual.getNombre() + " tiene el balón.");

        // Buscar el primer jugador cercano disponible para el pase.
        int indiceActual = equipo.indexOf(jugadorActual);
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[indiceActual][i] == 1 && i != indiceActual) {
                Jugador jugadorCercano = equipo.get(i);
                if (!jugadorCercano.isPortero()) {
                    pase(jugadorActual, jugadorCercano, equipo, matrizAdyacencia);
                    return;
                }
            } else {
                System.out.println("No hay jugadores disponibles para el pase.");
            }
        }

    }

    public void pase(Jugador desde, Jugador hacia, ArrayList<Jugador> equipo, int[][] matrizAdyacencia) {
        System.out.println(desde.getNombre() + " pasa el balón a " + hacia.getNombre());
        desde.setBalon(false);
        hacia.setBalon(true);
        if (esDelantero(hacia, equipo)) {
            disparo(hacia);
        } else {
            cercaniaj(hacia, equipo, matrizAdyacencia);  // Continuar la jugada recursivamente.
        }
    }

    public void disparo(Jugador delantero) {
        System.out.println(delantero.getNombre() + " intenta un disparo a portería...");

        // Simulación sencilla: si el remate es mayor a 70, se considera un gol.
        if (delantero.getRemate() > 70) {
            System.out.println("¡Gol de " + delantero.getNombre() + "!");
        } else {
            System.out.println("El disparo de " + delantero.getNombre() + " fue bloqueado.");
        }
    }

    public boolean esDelantero(Jugador jugador, ArrayList<Jugador> equipo) {
        // Consideramos como delanteros a los jugadores con índice entre 8 y 10.
        int index = equipo.indexOf(jugador);
        return index >= 8 && index <= 10;
    }

}
