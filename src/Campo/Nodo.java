/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
import java.util.ArrayList;

public class Nodo {

    private Jugador jugador;
    private int grado;
    private ArrayList<Nodo> adyacentes;
    private static int nNodos = 0;

    public Nodo(Jugador j) {
        this.jugador = j; 
        this.grado = nNodos++;
        this.adyacentes = new ArrayList<>();
    }

    public static int getnNodos() {
        return nNodos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public ArrayList<Nodo> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(ArrayList<Nodo> adyacentes) {
        this.adyacentes = adyacentes;
    }

    public void agregarAdyacentes(Nodo v) {
        this.adyacentes.add(v);
    }
}
