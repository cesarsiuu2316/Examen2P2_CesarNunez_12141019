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
public class Terrestre extends Planeta implements Serializable {

    public Terrestre(int tamanio, int peso, String nombre, int coordenadaX, int coordenadaY) {
        super(tamanio, peso, nombre, coordenadaX, coordenadaY);
    }
    
    public Terrestre() {
    }
    
}
