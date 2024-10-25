/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author lostw
 */
public class Grafo {

    private ArrayList<Nodo> listaAdyacencia;
    private int[][] matrizAdyacencia;
    private boolean id;

    // Constructor que define el tamaño de la matriz en función del equipo
    public Grafo(int tamaño) {
        this.listaAdyacencia = new ArrayList<>();
        this.matrizAdyacencia = new int[tamaño][tamaño];
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
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
            case 1:
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
            case 2:
                
                break;
            case 3:
                
                break;
               
        }
        return posesion;
    }

    public void imprimirP(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " || ");
            }
            System.out.println();
        }
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

}
