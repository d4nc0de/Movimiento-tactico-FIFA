/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
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
    public void armarGrafoA(ArrayList<Jugador> equipo) {
        equipoA = new Grafo();
        for (int i = 0; i < equipo.size(); i++) {
            Nodo nodo = new Nodo(equipo.get(i));
            equipoA.addNodo(nodo);
        }

        ArrayList<Nodo> lista;
        lista = equipoA.getListaAdyacencia();
        int[][] estrategia;
        estrategia = equipoA.matrizEstrategiasP(1, equipo);

        // Recorremos la matriz para conectar nodos
        for (int i = 0; i < estrategia.length; i++) {
            for (int j = 0; j < estrategia[i].length; j++) {
                if (estrategia[i][j] == 1) {
                    equipoA.conectarNodo(lista.get(i), lista.get(j));
                    System.out.println("Se ha conectado el nodo del jugador " + lista.get(i).getJugador().getNombre() + " con el jugador " + lista.get(j).getJugador().getNombre());
                }
            }
        }
        equipoA.imprimir();
    }
    
    public void armarGrafoB(ArrayList<Jugador> equipo) {
        equipoB = new Grafo();
        for (int i = 0; i < equipo.size(); i++) {
            Nodo nodo = new Nodo(equipo.get(i));
            equipoB.addNodo(nodo);
        }

        ArrayList<Nodo> lista;
        lista = equipoB.getListaAdyacencia();
        int[][] estrategia;
        estrategia = equipoB.matrizEstrategiasP(1, equipo);

        // Recorremos la matriz para conectar nodos
        for (int i = 0; i < estrategia.length; i++) {
            for (int j = 0; j < estrategia[i].length; j++) {
                if (estrategia[i][j] == 1) {
                    equipoB.conectarNodo(lista.get(i), lista.get(j));
                    System.out.println("Se ha conectado el nodo del jugador " + lista.get(i).getJugador().getNombre() + " con el jugador " + lista.get(j).getJugador().getNombre());
                }
            }
        }
        equipoB.imprimir();
    }

    public void iniciop(ArrayList<Jugador> equipoA, ArrayList<Jugador> equipoB, int[][] matrizPartido) {
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
