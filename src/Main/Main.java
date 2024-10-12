package Main;

import Archivo.CSV;
import Campo.Grafo;
import Campo.Nodo;
import Jugadores.Jugador;
//import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FlatDarkLaf.install();
        String ruta = "C:\\Users\\lostw\\OneDrive\\Documentos\\GitHub\\Movimiento-tactico-FIFA\\assets\\CSV.csv";
        CSV tabla1 = new CSV();
        tabla1.leerArchivo(ruta);

    }
}
