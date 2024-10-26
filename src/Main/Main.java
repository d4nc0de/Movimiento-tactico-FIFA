package Main;

import Archivo.CSV;
import Campo.*;
import Jugadores.Jugador;
//import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Rutas de los archivos CSV de cada equipo.
        String ruta1 = "C:\\Users\\tuori\\OneDrive\\Documentos\\GitHub\\Movimiento-tactico-FIFA_1\\assets\\equipo1.csv";
        String ruta2 = "C:\\Users\\tuori\\OneDrive\\Documentos\\GitHub\\Movimiento-tactico-FIFA_1\\assets\\equipo2.csv";

        // Inicializar las instancias para leer los equipos desde los CSV.
        CSV tabla1 = new CSV();
        CSV tabla2 = new CSV();

        // Cargar los equipos en listas de jugadores.
        ArrayList<Jugador> equipo1 = tabla1.aggEquipo(ruta1);
        ArrayList<Jugador> equipo2 = tabla2.aggEquipo(ruta2);

        // Crear el campo donde se armarán los grafos.
        Campo campo = new Campo();

        // Construir los grafos de cada equipo por separado.
        //campo.iniciop(equipo1, equipo2, grafoCombinado.getMatrizAdyacencia());     
        boolean w = false;
        Scanner sc = new Scanner(System.in);

        //Seleccion de la estrategia para formar el grafo    
        int estrategia = 1;

        Grafo grafoEquipoA = campo.armarGrafoP(estrategia, equipo1, true);
        Grafo grafoEquipoB = campo.armarGrafoP(estrategia, equipo2, false);
        // Calcular caminos mínimos desde el nodo 0 (Portero).
        Grafo.caminos(1, grafoEquipoA, equipo1, 0);
    }
}
