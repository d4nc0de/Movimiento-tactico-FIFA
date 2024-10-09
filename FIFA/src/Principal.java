import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatDarkLaf;

public class Principal {
    public static void main(String[] args) {
        FlatDarkLaf.install();
        JOptionPane.showMessageDialog(null, "hola");
        final String ruta = "C:/Users/lostw/OneDrive/Documentos/GitHub/Movimiento-tactico-FIFA/FIFA/assets/CSV.csv";
    }
}