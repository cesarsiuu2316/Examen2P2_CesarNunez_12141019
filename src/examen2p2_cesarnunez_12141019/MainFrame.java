/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2p2_cesarnunez_12141019;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author cesar
 */
public class MainFrame extends javax.swing.JFrame implements Runnable{

    private ArrayList<Planeta> publicos = new ArrayList();
    private ArrayList<Cientifico> cientificos = new ArrayList();
    private Planeta p1;
    private Planeta p2;
    private Planeta planetaSeleccionado;
    private Thread thread = new Thread(this);
    private boolean pausar = false;
    private boolean vive = true;
    private Random rd = new Random();
    private Double t = 0.0;
            
    public MainFrame() {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        cargarCientificos();
        planetasPublicos();        
        actualizarCbCientificos();          
    }
    
    @Override
    public void run() {
        try{            
            while(vive){
                while(pausar == false){
                    if(pb_planetas.getValue() < calcularDistancia()){
                        pb_planetas.setValue(pb_planetas.getValue() + 1);
                    }else{
                        pausar = true;                         
                        crearPlaneta();
                        calcularValores();
                    } 
                    t += 0.005;
                    Thread.sleep(5);
                }
            }            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void calcularValores(){
        String v = "";
        double dist = calcularDistancia();
        v += "Tiempo: " + t + " s\n";
        Double velocidad = dist / t;
        v += "Distancia: " + dist + "\nVelocidad: " + velocidad + "\n";
        Double masa = p1.getPeso() + p2.getPeso();
        Double energiaColision = (1.0/2.0) * masa * (Math.pow(velocidad, 2));
        v += "Energía de colisión: " + energiaColision + "\n";
        Double r = rd.nextDouble();
        Double energiaMaxima = energiaColision + (r * ((2.0 * energiaColision) - energiaColision));
        v += "Energía máxima: " + energiaMaxima + "\n\n";  
        JOptionPane.showMessageDialog(null, v);
    }
        
    private void crearPlaneta(){
        double tamanio, peso, cx, cy;
        peso = (p1.getPeso() + p2.getPeso()) / 2;
        tamanio = (p1.getTamanio() + p2.getTamanio()) / 2;
        cx = (p1.getCoordenadaX() + p2.getCoordenadaX()) / 2;
        cy = (p1.getCoordenadaY() + p2.getCoordenadaY()) / 2;     
        DefaultComboBoxModel m = (DefaultComboBoxModel) cb_cientificos.getModel();
        
        if(p1 instanceof Terrestre){
            int x = 1 + rd.nextInt(100);
            if(x <= 25){
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo planeta: ");
                ((Cientifico) m.getSelectedItem()).getDescubiertos().add(new Terrestre(tamanio, peso, nombre, cx, cy));
                actualizarTreePlanetas();
                guardarCientificos();
            }
        }else{
            int x = 1 + rd.nextInt(100);
            if(x <= 20){
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo planeta: ");
                ((Cientifico) m.getSelectedItem()).getDescubiertos().add(new Gaseoso(tamanio, peso, nombre, cx, cy));
                actualizarTreePlanetas();
                guardarCientificos();
            }
        }
        
        l_planeta1.setText("");
        l_planeta2.setText("");
                
    }
    
    private void planetasPublicos(){
        publicos.add(new Terrestre(5000,13000,"Mercurio",400,300));
        publicos.add(new Terrestre(100000,15000,"Venus",640,260));
        publicos.add(new Terrestre(140000,17000,"Tierra",760,570));
        publicos.add(new Terrestre(90000,12000,"Marte",360,360));
        publicos.add(new Gaseoso(400000,40000,"Jupiter",340,310));
        publicos.add(new Gaseoso(300000,30000,"Saturno",560,450));
        publicos.add(new Gaseoso(200000,20000,"Urano",670,690));
        publicos.add(new Gaseoso(200000,20000,"Neptuno",840,900));
    }
    
    private void cargarCientificos(){
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream("./cientificos.sci"));
            Cientifico c;
            while((c = (Cientifico) os.readObject()) != null){
                cientificos.add(c);
            }            
        } catch (IOException ex) {
            // System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    private void guardarCientificos(){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./cientificos.sci"));
            for (Cientifico c : cientificos) {
                os.writeObject(c);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void actualizarCbCientificos(){
        DefaultComboBoxModel cb = (DefaultComboBoxModel) cb_cientificos.getModel();
        cb.removeAllElements();
        for (Cientifico c : cientificos) {
            cb.addElement(c);
        }
    }
    
    private void actualizarTreePlanetas(){
        try{
            if(cb_publicos.isSelected()){
                DefaultTreeModel m = (DefaultTreeModel) t_planetas.getModel();
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("Planetas públicos");
                for (Planeta p : publicos) {
                    root.add(new DefaultMutableTreeNode(p));
                }
                m.setRoot(root);
            }else{
                DefaultTreeModel m = (DefaultTreeModel) t_planetas.getModel();
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("Planetas descubiertos");
                Cientifico c = (Cientifico) cb_cientificos.getSelectedItem();
                for (Planeta d : c.getDescubiertos()) {
                    root.add(new DefaultMutableTreeNode(d));
                }   
                m.setRoot(root);
            }
        }catch(Exception e){
            
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ppm_menu = new javax.swing.JPopupMenu();
        mi_p1 = new javax.swing.JMenuItem();
        mi_p2 = new javax.swing.JMenuItem();
        pb_bono = new javax.swing.JProgressBar();
        pb_planetas = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_planetas = new javax.swing.JTree();
        cb_publicos = new javax.swing.JCheckBox();
        l_planeta1 = new javax.swing.JLabel();
        l_planeta2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_cientificos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tf_nombreC = new javax.swing.JTextField();
        b_colisionar = new javax.swing.JButton();
        aniadirCientifico = new javax.swing.JButton();
        l_bg = new javax.swing.JLabel();

        mi_p1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mi_p1.setText("Planeta 1");
        mi_p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_p1ActionPerformed(evt);
            }
        });
        ppm_menu.add(mi_p1);

        mi_p2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mi_p2.setText("Planeta 2");
        mi_p2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_p2ActionPerformed(evt);
            }
        });
        ppm_menu.add(mi_p2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(pb_bono, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 133, 820, 85));
        getContentPane().add(pb_planetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 30, 820, 85));

        t_planetas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Planetas");
        t_planetas.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        t_planetas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_planetasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_planetas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 236, 316, 370));

        cb_publicos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cb_publicos.setText("Públicos");
        cb_publicos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_publicosItemStateChanged(evt);
            }
        });
        getContentPane().add(cb_publicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 616, -1, -1));

        l_planeta1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        l_planeta1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(l_planeta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 236, 240, 50));

        l_planeta2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        l_planeta2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(l_planeta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 304, 240, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Científicos");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 381, 186, 36));

        cb_cientificos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cb_cientificos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cientificosItemStateChanged(evt);
            }
        });
        getContentPane().add(cb_cientificos, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 423, 244, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 485, 186, 36));

        tf_nombreC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getContentPane().add(tf_nombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, 250, 50));

        b_colisionar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_colisionar.setText("Colisionar");
        b_colisionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_colisionarMouseClicked(evt);
            }
        });
        getContentPane().add(b_colisionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 230, 110));

        aniadirCientifico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        aniadirCientifico.setText("Añadir Científico");
        aniadirCientifico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirCientificoMouseClicked(evt);
            }
        });
        getContentPane().add(aniadirCientifico, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, 250, 40));

        l_bg.setBackground(new java.awt.Color(204, 204, 204));
        l_bg.setOpaque(true);
        getContentPane().add(l_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aniadirCientificoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirCientificoMouseClicked
        String nombre = tf_nombreC.getText();
        Cientifico c = new Cientifico(nombre);
        cientificos.add(c);
        actualizarCbCientificos();
        guardarCientificos();
        JOptionPane.showMessageDialog(null, "El científico se añadió con éxito!");
        tf_nombreC.setText("");
    }//GEN-LAST:event_aniadirCientificoMouseClicked

    private void cb_cientificosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cientificosItemStateChanged
        actualizarTreePlanetas();
    }//GEN-LAST:event_cb_cientificosItemStateChanged

    private void cb_publicosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_publicosItemStateChanged
        actualizarTreePlanetas();
    }//GEN-LAST:event_cb_publicosItemStateChanged

    private void mi_p2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_p2ActionPerformed
        p2 = (Planeta) planetaSeleccionado;
        l_planeta2.setText(p2.toString());
    }//GEN-LAST:event_mi_p2ActionPerformed

    private void mi_p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_p1ActionPerformed
        p1 = (Planeta) planetaSeleccionado;
        l_planeta1.setText(p1.toString());
    }//GEN-LAST:event_mi_p1ActionPerformed

    private void t_planetasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_planetasMouseClicked
        if(evt.isMetaDown()){
            try{
                int row = t_planetas.getClosestRowForLocation(evt.getX(), evt.getY());
                t_planetas.setSelectionRow(row);
                Object o = t_planetas.getSelectionPath().getLastPathComponent();
                DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) o;
                planetaSeleccionado = (Planeta) nodoSeleccionado.getUserObject();                
            }catch(Exception e){

            }               
            ppm_menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }        
    }//GEN-LAST:event_t_planetasMouseClicked

    private void b_colisionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_colisionarMouseClicked
        try{
            if(l_planeta1.getText().equals("") || l_planeta2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Aún no selecciona los planetas que colisionarán!");
            }else{
                thread = new Thread(this);
                pb_planetas.setMaximum(calcularDistancia());
                pb_planetas.setValue(0);
                pausar = false;
                thread.start();
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Seleccione los planetas que colisionarán!");
        }
    }//GEN-LAST:event_b_colisionarMouseClicked

    private int calcularDistancia(){
        double d;
        double x1 = p1.getCoordenadaX();
        double x2 = p2.getCoordenadaX();        
        double y1 = p1.getCoordenadaY();
        double y2 = p2.getCoordenadaY();
        
        double expX = Math.pow(x2-x1, 2);
        double expY = Math.pow(y2-y1, 2);
        d = Math.sqrt(expX + expY);
        int y = (int) Math.round(d);
        return y;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aniadirCientifico;
    private javax.swing.JButton b_colisionar;
    private javax.swing.JComboBox<String> cb_cientificos;
    private javax.swing.JCheckBox cb_publicos;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_bg;
    private javax.swing.JLabel l_planeta1;
    private javax.swing.JLabel l_planeta2;
    private javax.swing.JMenuItem mi_p1;
    private javax.swing.JMenuItem mi_p2;
    private javax.swing.JProgressBar pb_bono;
    private javax.swing.JProgressBar pb_planetas;
    private javax.swing.JPopupMenu ppm_menu;
    private javax.swing.JTree t_planetas;
    private javax.swing.JTextField tf_nombreC;
    // End of variables declaration//GEN-END:variables
}
