
package com.Marc.Kalamus.GUI;

import com.Marc.Kalamus.Persistencia.KalamusDB;
import java.util.ArrayList;


public class GUI extends javax.swing.JFrame {

    public GUI(KalamusDB kalamusDB) {
        initComponents();
        this.kalamusDB = kalamusDB;
    }
    private KalamusDB kalamusDB;
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        mostraPlanetas = new javax.swing.JButton();
        mostraEssers = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        afegirPlaneta = new javax.swing.JButton();
        afegirEsser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mostraPlanetas.setText("Planet List");
        mostraPlanetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostraPlanetasActionPerformed(evt);
            }
        });

        mostraEssers.setText("Being List");
        mostraEssers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostraEssersActionPerformed(evt);
            }
        });

        Exit.setText("Sortir");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        afegirPlaneta.setText("Planet Add");
        afegirPlaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afegirPlanetaActionPerformed(evt);
            }
        });

        afegirEsser.setText("Being Add");
        afegirEsser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afegirEsserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(Exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mostraPlanetas)
                            .addComponent(afegirPlaneta))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(afegirEsser)
                            .addComponent(mostraEssers))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostraPlanetas)
                    .addComponent(mostraEssers))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afegirPlaneta)
                    .addComponent(afegirEsser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(Exit)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostraPlanetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostraPlanetasActionPerformed
        Llista llistaGUI=new Llista();
        llistaGUI.setVisible(true);
        ArrayList <String> llistaPlanetas = kalamusDB.infoPlanetas();
          for(int i =0; i<llistaPlanetas.size(); i ++){
              llistaGUI.llista.append(llistaPlanetas.get(i)+"\n");
          }
    }//GEN-LAST:event_mostraPlanetasActionPerformed

    private void mostraEssersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostraEssersActionPerformed
        Llista llistaGUI=new Llista();
        llistaGUI.setVisible(true);
        ArrayList <String> llistaEsser = kalamusDB.infoEssers();
          for(int i =0; i<llistaEsser.size(); i ++){
              llistaGUI.llista.append(llistaEsser.get(i)+"\n");
          }
    }//GEN-LAST:event_mostraEssersActionPerformed

    private void afegirEsserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afegirEsserActionPerformed
        BeingAdd add= new BeingAdd(kalamusDB);
        add.setVisible(true);
        
        
    }//GEN-LAST:event_afegirEsserActionPerformed

    private void afegirPlanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afegirPlanetaActionPerformed
        PlanetAdd add= new PlanetAdd(kalamusDB);
        add.setVisible(true);
    }//GEN-LAST:event_afegirPlanetaActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton afegirEsser;
    private javax.swing.JButton afegirPlaneta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mostraEssers;
    private javax.swing.JButton mostraPlanetas;
    // End of variables declaration//GEN-END:variables
}
