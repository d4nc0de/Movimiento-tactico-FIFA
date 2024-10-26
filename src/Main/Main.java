package Main;

import Campo.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Crear el campo donde se armarán los grafos.
        Campo campo = new Campo();
        campo.initGame();
    }
}
