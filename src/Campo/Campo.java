/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campo;

/**
 *
 * @author tuori
 */
public class Campo {
    
    private int marcador;
    private int tiempo;
    private boolean ganador;
    private int turno;
    private Grafo equipoA;
    private Grafo equipoB;
    private  int estrategia ;

    public Campo(int marcador, int tiempo, boolean ganador, int turno, Grafo equipoA, Grafo equipoB, int estrategia) {
        this.marcador = marcador;
        this.tiempo = tiempo;
        this.ganador = ganador;
        this.turno = turno;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.estrategia = estrategia;
    }


    
    
    

}
