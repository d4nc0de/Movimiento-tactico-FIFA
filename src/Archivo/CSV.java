package Archivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CSV {

    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void leerArchivo(String ruta){
        try {
            lector = new BufferedReader(new FileReader(ruta));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",");
                imprimirLinea();
                System.out.println("");
            }
            lector.close();
            linea = null;
            partes = null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
    }

    public void imprimirLinea(){
        for (int i = 0; i < partes.length; i++) {
            System.out.print(partes[i]+" | ");
        }
    }
}
