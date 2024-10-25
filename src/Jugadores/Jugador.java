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
    public boolean balon;

    public Jugador(String nombre, int velocidad, int remate, int posecion) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.remate = remate;
        this.posecion = posecion;
        this.balon = false;
    }


    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setBalon(boolean balon) {
        this.balon = balon;
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


    public boolean isBalon() {
        return balon;
    }

    public String toString() {
    return "Nombre: "+getNombre()+"\nVelocidad: "+getVelocidad()+"\nPosesion: "+getPosecion()+"\nRemate: "+getRemate()+"\n";
    }
    
    
    
}
