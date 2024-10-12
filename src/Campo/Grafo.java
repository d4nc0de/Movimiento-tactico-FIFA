/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
import java.util.ArrayList;

/**
 *
 * @author lostw
 */
public class Grafo {

    private ArrayList<Nodo> listaAdyacencia;
    private int[][] matrizAdyacencia;

    public Grafo() {
        this.listaAdyacencia = new ArrayList();
    }

    public void addNodo(Jugador j) {
        Nodo nodo = new Nodo(j);
        this.listaAdyacencia.add(nodo);
    }

    public void conectarNodo(Nodo v1, Nodo v2) {
        if (this.matrizAdyacencia == null) {
            this.matrizAdyacencia = new int[listaAdyacencia.size()][listaAdyacencia.size()];
        }
        v1.agregarAdyacentes(v2);
        v2.agregarAdyacentes(v1);
        this.matrizAdyacencia[v1.getGrado()][v2.getGrado()] = 1;
        this.matrizAdyacencia[v2.getGrado()][v1.getGrado()] = 1;

    }

    public void imprimir() {
        //Lista
        System.out.println("Lista de adyancencia");
        for (Nodo v : listaAdyacencia) {
            System.out.println(" | " + v + "|");
        }
        System.out.println("");

        //Matriz
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                System.out.print(" | " + matrizAdyacencia[i][j] + "|");
            }
            System.out.println("");
        }
    }

    public void estrategias(int e, ArrayList<Jugador> equipo) {
        // Limpiar lista y matriz de adyacencia para una nueva estrategia
        listaAdyacencia.clear();
        matrizAdyacencia = new int[equipo.size()][equipo.size()];

        // Variables para separar jugadores por posición
        ArrayList<Jugador> defensas = new ArrayList<>();
        ArrayList<Jugador> mediocampistas = new ArrayList<>();
        ArrayList<Jugador> delanteros = new ArrayList<>();
        Jugador portero = null;

        // Clasificar jugadores por posición
        for (Jugador j : equipo) {
            if (j.isPortero()) {
                portero = j;
            } else if (j.getRemate() < 75) {
                defensas.add(j);
            } else if (j.getPosecion() >= 75 && j.getRemate() <= 85) {
                mediocampistas.add(j);
            } else {
                delanteros.add(j);
            }
        }

        // Organizar jugadores según la estrategia elegida
        switch (e) {
            case 1:  // Estrategia Posesión (4-3-3)
                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 3, 3);
                break;
            case 2:  // Estrategia ContraGolpe (5-3-2)
                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 5, 3, 2);
                break;
            case 3:  // Estrategia Presión (4-4-2)
                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 4, 2);
                break;
            default: // Estrategia Predeterminada (4-3-3)
                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 3, 3);
                break;
        }

        imprimir();  // Mostrar la lista y la matriz de adyacencia
    }

    public void organizarEstrategia(Jugador portero, ArrayList<Jugador> defensas,
            ArrayList<Jugador> mediocampistas, ArrayList<Jugador> delanteros,
            int numDefensas, int numMediocampistas, int numDelanteros) {

        // Añadir portero
        Nodo nodoPortero = new Nodo(portero);
        addNodo(portero);  // Añade el portero al grafo

        // Añadir defensas y conectar con el portero
        for (int i = 0; i < numDefensas && i < defensas.size(); i++) {
            Nodo nodoDefensa = new Nodo(defensas.get(i));
            addNodo(defensas.get(i));  // Añade defensa al grafo
            conectarNodo(nodoPortero, nodoDefensa);  // Conecta portero con defensa
        }

        // Añadir mediocampistas y conectar con defensas
        for (int i = 0; i < numMediocampistas && i < mediocampistas.size(); i++) {
            Nodo nodoMedio = new Nodo(mediocampistas.get(i));
            addNodo(mediocampistas.get(i));  // Añade mediocampista al grafo
            for (int j = 1; j <= numDefensas && j < listaAdyacencia.size(); j++) {
                Nodo defensa = listaAdyacencia.get(j);
                conectarNodo(defensa, nodoMedio);  // Conecta cada defensa con el mediocampista
            }
        }

        // Añadir delanteros y conectar con mediocampistas
        for (int i = 0; i < numDelanteros && i < delanteros.size(); i++) {
            Nodo nodoDelantero = new Nodo(delanteros.get(i));
            addNodo(delanteros.get(i));  // Añade delantero al grafo
            for (int j = numDefensas + 1; j <= (numDefensas + numMediocampistas) && j < listaAdyacencia.size(); j++) {
                Nodo medio = listaAdyacencia.get(j);
                conectarNodo(medio, nodoDelantero);  // Conecta cada mediocampista con el delantero
            }
        }
    }

    //Combinacion equipos 
    public void combinarEquipos(ArrayList<Jugador> equipo1, ArrayList<Jugador> equipo2) {
        // Clasificar jugadores por posición
        ArrayList<Jugador> defensas1 = new ArrayList<>();
        ArrayList<Jugador> mediocampistas1 = new ArrayList<>();
        ArrayList<Jugador> delanteros1 = new ArrayList<>();
        Jugador portero1 = null;

        ArrayList<Jugador> defensas2 = new ArrayList<>();
        ArrayList<Jugador> mediocampistas2 = new ArrayList<>();
        ArrayList<Jugador> delanteros2 = new ArrayList<>();
        Jugador portero2 = null;

        clasificarJugadores(equipo1, defensas1, mediocampistas1, delanteros1);
        clasificarJugadores(equipo2, defensas2, mediocampistas2, delanteros2);

        // Conectar delanteros de equipo 1 con defensas de equipo 2
        conectarPosiciones(delanteros1, defensas2);

        // Conectar delanteros de equipo 2 con defensas de equipo 1
        conectarPosiciones(delanteros2, defensas1);

        // Conectar mediocampistas entre ambos equipos
        conectarPosiciones(mediocampistas1, mediocampistas2);

        // Conectar porteros con sus defensas y los delanteros del equipo contrario
        if (portero1 != null) {
            conectarPortero(portero1, defensas1, delanteros2);
        }
        if (portero2 != null) {
            conectarPortero(portero2, defensas2, delanteros1);
        }

        System.out.println("Equipos combinados:");
        imprimir();
    }

// Método para clasificar jugadores por posición
    private void clasificarJugadores(ArrayList<Jugador> equipo,
            ArrayList<Jugador> defensas,
            ArrayList<Jugador> mediocampistas,
            ArrayList<Jugador> delanteros) {
        for (Jugador j : equipo) {
            if (j.isPortero()) {
                continue;
            }
            if (j.getRemate() < 75) {
                defensas.add(j);
            } else if (j.getRemate() >= 75 && j.getRemate() <= 85) {
                mediocampistas.add(j);
            } else {
                delanteros.add(j);
            }
        }
    }

// Método para conectar posiciones entre dos listas de jugadores
    private void conectarPosiciones(ArrayList<Jugador> listaA, ArrayList<Jugador> listaB) {
        for (Jugador j1 : listaA) {
            Nodo nodo1 = new Nodo(j1);
            addNodo(j1);
            for (Jugador j2 : listaB) {
                Nodo nodo2 = new Nodo(j2);
                addNodo(j2);
                conectarNodo(nodo1, nodo2);
            }
        }
    }

// Método para conectar un portero con sus defensas y delanteros rivales
    private void conectarPortero(Jugador portero, ArrayList<Jugador> defensas, ArrayList<Jugador> delanteros) {
        Nodo nodoPortero = new Nodo(portero);
        addNodo(portero);
        for (Jugador defensa : defensas) {
            conectarNodo(nodoPortero, new Nodo(defensa));
        }
        for (Jugador delantero : delanteros) {
            conectarNodo(nodoPortero, new Nodo(delantero));
        }
    }

    public boolean peleaB() { //Lucha entre jugadores por el balor

    }

    public void cercaniaj() {// Que jugadores tiene cerca

    }

    public void pase() { // A que jugador le pasara la pelota

    }

    public void disparo() { // Tiro a puerta

    }

}
