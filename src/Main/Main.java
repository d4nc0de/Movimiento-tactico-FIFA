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
        String ruta1 = "C:\\Users\\pc\\Documents\\GitHub\\Movimiento-tactico-FIFA\\assets\\equipo1.csv";
        String ruta2 = "C:\\Users\\pc\\Documents\\GitHub\\Movimiento-tactico-FIFA\\assets\\equipo2.csv";

        // Inicializar las instancias para leer los equipos desde los CSV.
        CSV tabla1 = new CSV();
        CSV tabla2 = new CSV();

        // Cargar los equipos en listas de jugadores.
        ArrayList<Jugador> equipo1 = tabla1.aggEquipo(ruta1);
        ArrayList<Jugador> equipo2 = tabla2.aggEquipo(ruta2);

        // Crear el campo donde se armarán los grafos.
        Campo campo = new Campo();

        // Construir los grafos de cada equipo por separado.
        Grafo grafoEquipoA = campo.armarGrafoP(equipo1,true);
        Grafo grafoEquipoB = campo.armarGrafoP(equipo2,false);

        // Combinar ambos grafos en uno solo.
        Grafo grafoCombinado = campo.unioneGrafo(grafoEquipoA, grafoEquipoB);

        // Imprimir el grafo combinado para verificar.
        System.out.println("Grafo combinado:");
        grafoCombinado.imprimir();
        
        //campo.iniciop(equipo1, equipo2, grafoCombinado.getMatrizAdyacencia());
    


        
        
        
        /*
        boolean w = false;
        Scanner sc = new Scanner(System.in);

        Jugador p12 = new Jugador("Kepa", 20, 85, 55, true);         // Portero
        Jugador p13 = new Jugador("Carvajal", 65, 78, 82, false);    // Defensa
        Jugador p14 = new Jugador("Alaba", 60, 80, 75, false);       // Defensa
        Jugador p15 = new Jugador("Rüdiger", 50, 83, 78, false);     // Defensa
        Jugador p16 = new Jugador("Fran García", 70, 76, 90, false); // Defensa
        Jugador p17 = new Jugador("Tchouaméni", 70, 85, 80, false);  // Mediocampista
        Jugador p18 = new Jugador("Camavinga", 75, 80, 85, false);   // Mediocampista
        Jugador p19 = new Jugador("Modric", 60, 88, 72, false);      // Mediocampista
        Jugador p20 = new Jugador("Vinícius Jr.", 85, 75, 95, false); // Delantero
        Jugador p21 = new Jugador("Rodrygo", 80, 77, 90, false);    // Delantero
        Jugador p22 = new Jugador("Joselu", 85, 70, 75, false);     // Delantero

        equipo2.add(p12);
        equipo2.add(p13);
        equipo2.add(p14);
        equipo2.add(p15);
        equipo2.add(p16);
        equipo2.add(p17);
        equipo2.add(p18);
        equipo2.add(p19);
        equipo2.add(p20);
        equipo2.add(p21);
        equipo2.add(p22);

        //Seleccion de la estrategia para formar el grafo    
        int estrategia = 0;
        System.out.println("Selecciona tu estrategia: \n"
                + "1.  Posecion (4-3-3) \n"
                + "2. ContraGolpe[Remate] (5-3-2)\n "
                + "3. Presion [Velocidad] (4-4-2).\n"
                + "4. Predeterminada");
        estrategia = sc.nextInt();
        int[][] barca = new int[equipo1.size()][equipo1.size()];
        
        barca = g.matrizEstrategiasP(estrategia, equipo1);
/*
        System.out.println("Selecciona la estrategia del rival: \n"
                + "1. Posecion (4-3-3) \n"
                + "2. ContraGolpe[Remate] (5-3-2)\n "
                + "3. Presion [Velocidad] (4-4-2).\n"
                + "4. Predeterminada");
        estrategia = sc.nextInt();
        int[][] madrid = new int[equipo2.size()][equipo2.size()];
        
        madrid = g.matrizEstrategiasP(estrategia, equipo2);
        System.out.println("------------");
        
        int n  = equipo1.size();
        int[][] partido = new int[n*2][n*2];
        g.unioneP(barca, madrid); //Crea la matriz de abyacencia con ambos equipos
        */

    }
}
