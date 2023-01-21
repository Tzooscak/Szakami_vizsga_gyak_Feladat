/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.szakma_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author hobo3
 */
public class versenyzok extends javax.swing.JFrame {

    /**
     * Creates new form versenyzok
     */
    public versenyzok() {
        initComponents();
        Tablatolt(tabla);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexForJob = 2;
        sortKeys.add(new RowSorter.SortKey(columnIndexForJob, SortOrder.DESCENDING));
 
        int columnIndexForName = 3;
        sortKeys.add(new RowSorter.SortKey(columnIndexForName, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "neve", "szakma", "orszag", "pont"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton1.setText("Törlés");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga_szakma", "root", "");
            Statement smt = con.createStatement();
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            int sstam = tabla.getSelectedRow();
            System.out.println(sstam);
            int ertek = Integer.parseInt(String.valueOf(tabla.getValueAt(sstam, 0)));
            smt.executeUpdate("DELETE FROM versenyzo WHERE versenyzo.id = '" + ertek + "'");
            Tablatorol(tabla);
            Tablatolt(tabla);
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void Tablatorol(JTable JTable) {
        DefaultTableModel model = (DefaultTableModel) JTable.getModel();
        int sorszam = model.getRowCount();
        for (int i = 0; i < sorszam; i++) {
            model.removeRow(0);
        }
    }
    
    public static void Tablatolt(JTable JTable) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga_szakma", "root", "");
            Statement smt = con.createStatement();
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();
            ResultSet rs = smt.executeQuery("SELECT versenyzo.id,`nev`,szakma.szakmaNev,orszag.orszagNev,`pont` FROM `versenyzo`\n" +
                                            "INNER JOIN szakma ON szakmaId = szakma.id\n" +
                                            "INNER JOIN orszag ON orszag.id = orszagId");
            String[] mezon = {"id", "nev", "szakmaNev", "orszagNev", "pont"};
            String[] rekord = new String[5];
            while (rs.next()) {
                for (int i = 0; i < mezon.length; i++) {
                    rekord[i] = rs.getString(mezon[i]);
                }
                model.addRow(rekord);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(versenyzok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(versenyzok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(versenyzok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(versenyzok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new versenyzok().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
