/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

import Jugadores.Jugador;
import java.util.ArrayList;

public class Nodo {

    private ArrayList<Jugador> equipo;
    private int grado;
    private ArrayList<Nodo> adyacentes;
    private static int nNodos = 0;

    
    public Nodo(ArrayList equipo) {
        this.equipo = equipo;
        this.grado = nNodos++;
        this.adyacentes = new ArrayList<>();
    }
    
    public static int getnNodos() {
        return nNodos;
    }

    public static void setnNodos(int anNodos) {
        nNodos = anNodos;
    }

    public ArrayList<Jugador> getEquipo() {
        return equipo;
    }

    public void setEquipo(ArrayList<Jugador> equipo) {
        this.equipo = equipo;
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

    public String toString() {
        String res = "";
        for (int i = 0; i < equipo.size(); i++) {
            res = res + "Jugador: " + equipo.get(i).getNombre() + "\n";
        }
        return res;
    }
}
