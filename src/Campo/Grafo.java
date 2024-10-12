/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

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

    public void addNodo(Nodo v) {
        this.listaAdyacencia.add(v);
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

}
