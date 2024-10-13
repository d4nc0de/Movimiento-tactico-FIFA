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

    public Grafo() {
        this.listaAdyacencia = new ArrayList();
    }

    public void addNodo(Nodo nodo) {
        this.listaAdyacencia.add(nodo);
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }
    
    public void conectarNodo(Nodo v1, Nodo v2) {
        if (this.matrizAdyacencia == null) {
            this.matrizAdyacencia = new int[listaAdyacencia.size()*2][listaAdyacencia.size()*2];
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
            System.out.println(" | " + v.getJugador().toString() + "|");
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

    public ArrayList<Nodo> getListaAdyacencia() {
        return listaAdyacencia;
    }

    public int[][] matrizEstrategiasP(int e, ArrayList<Jugador> equipo) {
        int[][] posesion = new int[equipo.size()][equipo.size()];  // Matriz de adyacencia 11x11.

        switch (e) {
            case 1:
                // Conectar portero (0) con defensas (1-4).
                for (int i = 1; i <= 4; i++) {
                    posesion[0][i] = 1;
                    posesion[i][0] = 1;  // Conexión bidireccional.
                }

                // Conectar defensas (1-4) entre ellos y con mediocampistas (5-7).
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

                // Conectar mediocampistas (5-7) entre ellos y con delanteros (8-10).
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

                // Conectar delanteros (8-10) entre ellos.
                for (int i = 8; i <= 10; i++) {
                    for (int j = i + 1; j <= 10; j++) {
                        posesion[i][j] = 1;
                        posesion[j][i] = 1;
                    }
                }

                //imprimirP(posesion);  // Imprimir la matriz para verificar.
                break;

            default:
                System.out.println("Estrategia no reconocida.");
        }
        return posesion;
    }

    public void imprimirP(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " || ");
            }
            System.out.println();
        }
    }

    public int[][] unioneP(int[][] equipoa, int[][] equipob) {
        int n = equipoa.length;  // Tamaño de las matrices de los equipos (11).
        int[][] campo = new int[n * 2][n * 2];  // Matriz combinada 22x22.

        // Copiar equipo A (0-10) y equipo B (11-21) en la matriz "campo".
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                campo[i][j] = equipoa[i][j];                 // Equipo A (0-10).
                campo[i + n][j + n] = equipob[i][j];         // Equipo B (11-21).
            }
        }

        // Conectar delanteros del equipo A (8-10) con defensores y portero del equipo B (11-15).
        for (int i = 8; i <= 10; i++) {
            for (int j = 11; j <= 15; j++) {
                campo[i][j] = 1;
                campo[j][i] = 1;  // Conexión bidireccional.
            }
        }

        // Conectar delanteros del equipo B (19-21) con defensores y portero del equipo A (0-4).
        for (int i = 19; i <= 21; i++) {
            for (int j = 0; j <= 4; j++) {
                campo[i][j] = 1;
                campo[j][i] = 1;  // Conexión bidireccional.
            }
        }

        // Conectar mediocampistas entre equipos (5-7 del A con 16-18 del B).
        for (int i = 5; i <= 7; i++) {
            for (int j = 16; j <= 18; j++) {
                campo[i][j] = 1;
                campo[j][i] = 1;  // Conexión bidireccional.
            }
        }

        // Conectar portero de cada equipo con sus defensores.
        for (int i = 1; i <= 4; i++) {
            campo[0][i] = 1;  // Portero del equipo A (0) con sus defensores (1-4).
            campo[i][0] = 1;
        }
        for (int i = 12; i <= 15; i++) {
            campo[11][i] = 1;  // Portero del equipo B (11) con sus defensores (12-15).
            campo[i][11] = 1;
        }

        imprimirP(campo);  // Imprimir la matriz para verificar.
        return campo;
    }

//
//    public void estrategias(int e, ArrayList<Jugador> equipo) {
//        // Limpiar lista y matriz de adyacencia para una nueva estrategia
//        listaAdyacencia.clear();
//        matrizAdyacencia = new int[equipo.size()][equipo.size()];
//
//        // Variables para separar jugadores por posición
//        ArrayList<Jugador> defensas = new ArrayList<>();
//        ArrayList<Jugador> mediocampistas = new ArrayList<>();
//        ArrayList<Jugador> delanteros = new ArrayList<>();
//        Jugador portero = null;
//
//        // Clasificar jugadores por posición
//        for (Jugador j : equipo) {
//            if (j.isPortero()) {
//                portero = j;
//            } else if (j.getRemate() < 75) {
//                defensas.add(j);
//            } else if (j.getPosecion() >= 75 && j.getRemate() <= 85) {
//                mediocampistas.add(j);
//            } else {
//                delanteros.add(j);
//            }
//        }
//
//        // Organizar jugadores según la estrategia elegida
//        switch (e) {
//            case 1:  // Estrategia Posesión (4-3-3)
//                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 3, 3);
//                break;
//            case 2:  // Estrategia ContraGolpe (5-3-2)
//                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 5, 3, 2);
//                break;
//            case 3:  // Estrategia Presión (4-4-2)
//                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 4, 2);
//                break;
//            default: // Estrategia Predeterminada (4-3-3)
//                organizarEstrategia(portero, defensas, mediocampistas, delanteros, 4, 3, 3);
//                break;
//        }
//
//        imprimir();  // Mostrar la lista y la matriz de adyacencia
//    }
//
//    public void organizarEstrategia(Jugador portero, ArrayList<Jugador> defensas,
//            ArrayList<Jugador> mediocampistas, ArrayList<Jugador> delanteros,
//            int numDefensas, int numMediocampistas, int numDelanteros) {
//
//        // Añadir portero
//        Nodo nodoPortero = new Nodo(portero);
//        addNodo(portero);  // Añade el portero al grafo
//
//        // Añadir defensas y conectar con el portero
//        for (int i = 0; i < numDefensas && i < defensas.size(); i++) {
//            Nodo nodoDefensa = new Nodo(defensas.get(i));
//            addNodo(defensas.get(i));  // Añade defensa al grafo
//            conectarNodo(nodoPortero, nodoDefensa);  // Conecta portero con defensa
//        }
//
//        // Añadir mediocampistas y conectar con defensas
//        for (int i = 0; i < numMediocampistas && i < mediocampistas.size(); i++) {
//            Nodo nodoMedio = new Nodo(mediocampistas.get(i));
//            addNodo(mediocampistas.get(i));  // Añade mediocampista al grafo
//            for (int j = 1; j <= numDefensas && j < listaAdyacencia.size(); j++) {
//                Nodo defensa = listaAdyacencia.get(j);
//                conectarNodo(defensa, nodoMedio);  // Conecta cada defensa con el mediocampista
//            }
//        }
//
//        // Añadir delanteros y conectar con mediocampistas
//        for (int i = 0; i < numDelanteros && i < delanteros.size(); i++) {
//            Nodo nodoDelantero = new Nodo(delanteros.get(i));
//            addNodo(delanteros.get(i));  // Añade delantero al grafo
//            for (int j = numDefensas + 1; j <= (numDefensas + numMediocampistas) && j < listaAdyacencia.size(); j++) {
//                Nodo medio = listaAdyacencia.get(j);
//                conectarNodo(medio, nodoDelantero);  // Conecta cada mediocampista con el delantero
//            }
//        }
//    }
//
//    //Combinacion equipos 
//    public void combinarEquipos(ArrayList<Jugador> equipo1, ArrayList<Jugador> equipo2) {
//        // Clasificar jugadores por posición
//        ArrayList<Jugador> defensas1 = new ArrayList<>();
//        ArrayList<Jugador> mediocampistas1 = new ArrayList<>();
//        ArrayList<Jugador> delanteros1 = new ArrayList<>();
//        Jugador portero1 = null;
//
//        ArrayList<Jugador> defensas2 = new ArrayList<>();
//        ArrayList<Jugador> mediocampistas2 = new ArrayList<>();
//        ArrayList<Jugador> delanteros2 = new ArrayList<>();
//        Jugador portero2 = null;
//
//        clasificarJugadores(equipo1, defensas1, mediocampistas1, delanteros1);
//        clasificarJugadores(equipo2, defensas2, mediocampistas2, delanteros2);
//
//        // Conectar delanteros de equipo 1 con defensas de equipo 2
//        conectarPosiciones(delanteros1, defensas2);
//
//        // Conectar delanteros de equipo 2 con defensas de equipo 1
//        conectarPosiciones(delanteros2, defensas1);
//
//        // Conectar mediocampistas entre ambos equipos
//        conectarPosiciones(mediocampistas1, mediocampistas2);
//
//        // Conectar porteros con sus defensas y los delanteros del equipo contrario
//        if (portero1 != null) {
//            conectarPortero(portero1, defensas1, delanteros2);
//        }
//        if (portero2 != null) {
//            conectarPortero(portero2, defensas2, delanteros1);
//        }
//
//        System.out.println("Equipos combinados:");
//        imprimir();
//    }
//
//// Método para clasificar jugadores por posición
//    private void clasificarJugadores(ArrayList<Jugador> equipo,
//            ArrayList<Jugador> defensas,
//            ArrayList<Jugador> mediocampistas,
//            ArrayList<Jugador> delanteros) {
//        for (Jugador j : equipo) {
//            if (j.isPortero()) {
//                continue;
//            }
//            if (j.getRemate() < 75) {
//                defensas.add(j);
//            } else if (j.getRemate() >= 75 && j.getRemate() <= 85) {
//                mediocampistas.add(j);
//            } else {
//                delanteros.add(j);
//            }
//        }
//    }
//
//// Método para conectar posiciones entre dos listas de jugadores
//    private void conectarPosiciones(ArrayList<Jugador> listaA, ArrayList<Jugador> listaB) {
//        for (Jugador j1 : listaA) {
//            Nodo nodo1 = new Nodo(j1);
//            addNodo(j1);
//            for (Jugador j2 : listaB) {
//                Nodo nodo2 = new Nodo(j2);
//                addNodo(j2);
//                conectarNodo(nodo1, nodo2);
//            }
//        }
//    }
//
//// Método para conectar un portero con sus defensas y delanteros rivales
//    private void conectarPortero(Jugador portero, ArrayList<Jugador> defensas, ArrayList<Jugador> delanteros) {
//        Nodo nodoPortero = new Nodo(portero);
//        addNodo(portero);
//        for (Jugador defensa : defensas) {
//            conectarNodo(nodoPortero, new Nodo(defensa));
//        }
//        for (Jugador delantero : delanteros) {
//            conectarNodo(nodoPortero, new Nodo(delantero));
//        }
//    }
}
