/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grafo {

    private ArrayList<Nodo> listaAdyacencia;
    private int[][] matrizAdyacencia;

    // Constructor que define el tamaño de la matriz en función del equipo
    public Grafo(int tamaño) {
        this.listaAdyacencia = new ArrayList<>();
        this.matrizAdyacencia = new int[tamaño][tamaño];
    }

    public void addNodo(Nodo nodo) {
        listaAdyacencia.add(nodo);
    }

    public void conectarNodo(Nodo v1, Nodo v2) {
        int indice1 = listaAdyacencia.indexOf(v1);
        int indice2 = listaAdyacencia.indexOf(v2);

        if (indice1 != -1 && indice2 != -1) {
            matrizAdyacencia[indice1][indice2] = 1;
            matrizAdyacencia[indice2][indice1] = 1;
            v1.agregarAdyacentes(v2);
            v2.agregarAdyacentes(v1);
        }
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public void imprimir() {
        System.out.println("Lista de adyacencia:");
        for (Nodo nodo : listaAdyacencia) {
            System.out.println(" | " + nodo.getJugador().toString() + " |");
        }

        System.out.println("\nMatriz de adyacencia:");
        for (int[] fila : matrizAdyacencia) {
            for (int valor : fila) {
                System.out.print(" | " + valor + " |");
            }
            System.out.println();
        }
    }

    public ArrayList<Nodo> getListaAdyacencia() {
        return listaAdyacencia;
    }

    public int[][] matrizEstrategiasP(int e, ArrayList<Jugador> equipo) {
        int[][] posesion = new int[equipo.size()][equipo.size()];  // Crear matriz adecuada

        switch (e) {
            case 1://4-3-3
                // Estrategia para conectar nodos según las posiciones
                for (int i = 1; i <= 4; i++) {
                    posesion[0][i] = 1;
                    posesion[i][0] = 1;
                }
                for (int i = 1; i <= 4; i++) {
                    for (int j = i + 1; j <= 4; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 5; j <= 7; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 5; i <= 7; i++) {
                    for (int j = i + 1; j <= 7; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 8; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 8; i <= 10; i++) {
                    for (int j = i + 1; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                //imprimirP(posesion);
                break;
            case 2://4-4-2
                for (int i = 1; i <= 4; i++) {// Portero
                    posesion[0][i] = 1;
                    posesion[i][0] = 1;
                }
                for (int i = 1; i <= 4; i++) {//Abyacencia defensas con defensas
                    for (int j = i + 1; j <= 4; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 5; j <= 7; j++) {//Abyacencia defensas y  entre medio campista
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 5; i <= 8; i++) {//Medio campo
                    for (int j = i + 1; j <= 8; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 9; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 9; i <= 10; i++) {
                    for (int j = i + 1; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                break;
            case 3: // Formacion 3-4-3
                for (int i = 1; i <= 3; i++) {
                    posesion[0][i] = 1;
                    posesion[i][0] = 1;
                }
                for (int i = 1; i <= 3; i++) {
                    for (int j = i + 1; j <= 3; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 4; j <= 7; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 4; i <= 7; i++) {
                    for (int j = i + 1; j <= 8; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                    for (int j = 8; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                for (int i = 8; i <= 10; i++) {
                    for (int j = i + 1; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }
                break;

        }
        return posesion;
    }

    public static void caminos(int estrategia, Grafo grafo, ArrayList<Jugador> equipo, int nodoInicio) {
        int V = grafo.getListaAdyacencia().size(); //Numero de Nodos
        int[] distancias = new int[V]; //Tam vector
        Arrays.fill(distancias, Integer.MAX_VALUE); // Inicializamos todas las distancias a infinito.
        distancias[nodoInicio] = 0; // La distancia del nodo inicial es 0.

        PriorityQueue<Integer> colaPrioridad = new PriorityQueue<>((a, b) -> distancias[a] - distancias[b]); //Cola de prioridad
        colaPrioridad.add(nodoInicio);

        while (!colaPrioridad.isEmpty()) {
            int u = colaPrioridad.poll(); // Extraer el nodo con menor distancia.
            Nodo nodoActual = grafo.getListaAdyacencia().get(u);

            for (Nodo adyacente : nodoActual.getAdyacentes()) {
                int v = grafo.getListaAdyacencia().indexOf(adyacente);
                Jugador jugadorU = nodoActual.getJugador();
                Jugador jugadorV = adyacente.getJugador();

                // Calcular el peso dinámico basado en la estrategia y los atributos.
                int peso = calcularPeso(estrategia, jugadorU, jugadorV);

                // Relajación de arista.
                if (distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    colaPrioridad.add(v);
                }
            }
        }

        // Imprimir distancias mínimas desde el nodo de inicio.
        for (int i = 0; i < V; i++) {
            System.out.println("Distancia mínima desde " + nodoInicio + " hasta " + i + ": " + distancias[i]);
        }
    }

// Método para calcular el peso entre dos nodos basándose en la estrategia.
    private static int calcularPeso(int estrategia, Jugador jugador1, Jugador jugador2) {
        int peso = 0;
        switch (estrategia) {
            case 1: // Estrategia 4-3-3
                peso = Math.abs(jugador1.getVelocidad() - jugador2.getVelocidad());
                break;
            case 2: // Estrategia 4-4-2
                peso = Math.abs(jugador1.getPosecion() - jugador2.getPosecion()) + 2;
                break;
            case 3: // Estrategia 3-4-3
                peso = Math.abs(jugador1.getRemate() - jugador2.getRemate()) + 3;
                break;

        }
        return peso;
    }

    public void imprimirP(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " || ");
            }
            System.out.println();
        }
    }

}
