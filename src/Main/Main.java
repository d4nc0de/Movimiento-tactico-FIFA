package Main;

import Campo.Grafo;
import Campo.Nodo;
import Jugadores.Jugador;
//import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //FlatDarkLaf.install();    
        final String ruta = "C:/Users/lostw/OneDrive/Documentos/GitHub/Movimiento-tactico-FIFA/FIFA/assets/CSV.csv";
        Grafo g = new Grafo();
       boolean w = false;
        Scanner sc = new Scanner(System.in);
        ArrayList<Jugador> equipo1 = new ArrayList(); //Barca
        ArrayList<Jugador> equipo2 = new ArrayList(); //Madrid

        Jugador p1 = new Jugador("Ter Stegen", 20, 85, 50, true);  // Portero
        Jugador p2 = new Jugador("Koundé", 60, 80, 85, false);      // Defensa
        Jugador p3 = new Jugador("Christensen", 50, 85, 75, false); // Defensa
        Jugador p4 = new Jugador("Balde", 70, 80, 90, false);       // Defensa
        Jugador p5 = new Jugador("Araujo", 55, 82, 78, false);      // Defensa
        Jugador p6 = new Jugador("Gavi", 75, 88, 85, false);        // Mediocampista
        Jugador p7 = new Jugador("Frenkie de Jong", 70, 90, 80, false);  // Mediocampista
        Jugador p8 = new Jugador("Pedri", 65, 90, 83, false);       // Mediocampista
        Jugador p9 = new Jugador("Lewandowski", 92, 78, 75, false); // Delantero
        Jugador p10 = new Jugador("Lamine Yamal", 85, 80, 82, false); // Delantero
        Jugador p11 = new Jugador("Raphina", 80, 75, 88, false);  // Delantero

        equipo1.add(p1);
        equipo1.add(p2);
        equipo1.add(p3);
        equipo1.add(p4);
        equipo1.add(p5);
        equipo1.add(p6);
        equipo1.add(p7);
        equipo1.add(p8);
        equipo1.add(p9);
        equipo1.add(p10);
        equipo1.add(p11);

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
        g.estrategias(estrategia, equipo1);
        System.out.println("Selecciona la estrategia del rival: \n"
                + "1. Posecion (4-3-3) \n"
                + "2. ContraGolpe[Remate] (5-3-2)\n "
                + "3. Presion [Velocidad] (4-4-2).\n"
                + "4. Predeterminada");
        estrategia = sc.nextInt();
        g.estrategias(estrategia, equipo2);
        
        while(w==false){
            System.out.println("Inicio de partido");
            
        }
    }
    

}
