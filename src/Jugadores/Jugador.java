/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private int defensa;
    private boolean portero;
    public boolean balon;

    public Jugador(String nombre, int velocidad, int remate, int posecion,boolean portero) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.remate = remate;
        this.posecion = posecion;
        this.defensa = (this.remate + this.posecion)/ 2;
        this.portero = portero;
        this.balon = false;
    }

    public boolean isPortero() {
        return portero;
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

    public int getDefensa() {
        return defensa;
    }

    public boolean isBalon() {
        return balon;
    }
    
    
    
}
