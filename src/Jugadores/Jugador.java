package Jugadores;

/**
 *
 * @author tuori
 */
public class Jugador {
    
    private String nombre;
    private int velocidad;
    private int remate ;
    private int posecion;

    public Jugador(String nombre, int velocidad, int remate, int posecion) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.remate = remate;
        this.posecion = posecion;
    }


    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public int getVelocidad() {
        return velocidad;
    }

    public int getRemate() {
        return remate;
    }

    public int getPosecion() {
        return posecion;
    }


    public String toString() {
    return "Nombre: "+getNombre()+"\nVelocidad: "+getVelocidad()+"\nPosesion: "+getPosecion()+"\nRemate: "+getRemate()+"\n";
    }
    
    
    
}
