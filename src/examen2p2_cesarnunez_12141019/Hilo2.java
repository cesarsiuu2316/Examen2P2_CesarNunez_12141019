/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2p2_cesarnunez_12141019;

import javax.swing.JProgressBar;

/**
 *
 * @author cesar
 */
public class Hilo2 implements Runnable{

    private JProgressBar pb;
    private double energiaMaxima;
    private boolean vive = true;
    private boolean pausar = false;
    private double energiaColision;

    public Hilo2(JProgressBar pb, double energiaMaxima, double energiaColision) {
        this.pb = pb;
        this.energiaMaxima = energiaMaxima;
        this.energiaColision = energiaColision;
        pb.setMaximum((int) energiaMaxima);
    }

    public JProgressBar getPb() {
        return pb;
    }

    public void setPb(JProgressBar pb) {
        this.pb = pb;
        pb.setMaximum((int) energiaMaxima);
    }

    public double getEnergiaMaxima() {
        return energiaMaxima;
    }

    public void setEnergiaMaxima(double energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
        pb.setMaximum((int) energiaMaxima);
    }

    public boolean isVive() {
        return vive;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public boolean isPausar() {
        return pausar;
    }

    public void setPausar(boolean pausar) {
        this.pausar = pausar;
    }

    public double getEnergiaColision() {
        return energiaColision;
    }

    public void setEnergiaColision(double energiaColision) {
        this.energiaColision = energiaColision;
    }
    
    @Override
    public void run() {
         try{            
            while(vive){
                while(pausar == false){
                    if(pb.getValue() < (int) energiaColision){
                        pb.setValue(pb.getValue() + 1);
                    }else{
                        pausar = true; 
                    } 
                    Thread.sleep(5);
                }
            }            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
