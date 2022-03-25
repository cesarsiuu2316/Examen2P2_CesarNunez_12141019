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
public class Gaseoso extends Planeta implements Serializable{

    public Gaseoso(double tamanio, double peso, String nombre, double coordenadaX, double coordenadaY) {
        super(tamanio, peso, nombre, coordenadaX, coordenadaY);
    }

    public Gaseoso() {
    }

    @Override
    public String toString() {
        return super.getNombre();
    }
    
    

}
