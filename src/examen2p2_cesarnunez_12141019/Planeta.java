/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2p2_cesarnunez_12141019;

import java.io.Serializable;

/**
 *
 * @author cesar
 */
public class Planeta implements Serializable{
    
    private String nombre;
    private double tamanio, peso, coordenadaX, coordenadaY;

    public Planeta(double tamanio, double peso, String nombre, double coordenadaX, double coordenadaY) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.peso = peso;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public Planeta() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
