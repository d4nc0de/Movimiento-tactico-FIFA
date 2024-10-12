package Main;

import Campo.Grafo;
import Campo.Nodo;
import Jugadores.Jugador;
//import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.install();    
        final String ruta = "C:/Users/lostw/OneDrive/Documentos/GitHub/Movimiento-tactico-FIFA/FIFA/assets/CSV.csv";
        Grafo g= new Grafo();
        Jugador p1 = new Jugador();
        p1.setNombre("Messi");
        Jugador p2 = new Jugador();
        p2.setNombre("CR7");
        Jugador p3 = new Jugador();
        p3.setNombre("Kaka");
        ArrayList<Jugador> e1= new ArrayList();
        e1.add(p1);
        e1.add(p2);
        e1.add(p3);
        Nodo e = new Nodo(e1);
        System.out.println(e.toString());
    }
}
