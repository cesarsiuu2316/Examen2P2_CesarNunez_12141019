/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2p2_cesarnunez_12141019;

import java.io.Serializable;
import java.util.ArrayList;


public class Cientifico implements Serializable{
    
    private String nombre = "";
    private ArrayList<Planeta> descubiertos = new ArrayList();

    public Cientifico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Planeta> getDescubiertos() {
        return descubiertos;
    }

    public void setDescubiertos(ArrayList<Planeta> descubiertos) {
        this.descubiertos = descubiertos;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
