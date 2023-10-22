/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.front;

import com.gestionBibliotheque.classe.Etagere;
import com.gestionBibliotheque.classe.Groupe;
import com.gestionBibliotheque.classe.Livre;
import com.gestionBibliotheque.gestion.GestionEtagere;
import com.gestionBibliotheque.gestion.GestionGroupe;
import com.gestionBibliotheque.gestion.GestionLivre;
import com.gestionBibliotheque.gestion.GestionType;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author solofonirina
 */
public class ListeLivre extends javax.swing.JDialog {
    GestionLivre gl = new GestionLivre();
    GestionEtagere get = new GestionEtagere();
    GestionGroupe gg = new GestionGroupe();
    GestionType gt = new GestionType();
    public static String codeLivre;
    /**
     * Creates new form ListeLivre
     */
    public ListeLivre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        combo.setVisible(false);
        txtChild.setVisible(false);
        setLocationRelativeTo(null);
    }
    
    public void listeLivre(){
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Code livre");
        table.addColumn("Libelle");
        table.addColumn("Auteur");
        table.addColumn("Pages");
        table.addColumn("Nombre");
        table.addColumn("Type");
        table.addColumn("Groupe");
        table.addColumn("Etagère");
        tableLivre.setModel(table);
        TableColumnModel colonne = tableLivre.getColumnModel();
        colonne.getColumn(0).setPreferredWidth(70);
        colonne.getColumn(1).setPreferredWidth(200);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(3).setPreferredWidth(50);
        colonne.getColumn(4).setPreferredWidth(50);
        colonne.getColumn(5).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(150);
        for(Livre l : gl.list()){
            Object[] line = {l.getCodeLivre(), l.getLibelleLivre(), l.getAuteurLivre(), l.getNombrePage(), l.getNombreLivre(), l.getType(), l.getGroupe(), l.getEtagere()};
            table.addRow(line);
        }
    }
    
    public void listeLivreBySelected(String selectedVariable, String selectedValue){
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Code livre");
        table.addColumn("Libelle");
        table.addColumn("Auteur");
        table.addColumn("Pages");
        table.addColumn("Nombre");
        table.addColumn("Type");
        table.addColumn("Groupe");
        table.addColumn("Etagère");
        tableLivre.setModel(table);
        TableColumnModel colonne = tableLivre.getColumnModel();
        colonne.getColumn(0).setPreferredWidth(70);
        colonne.getColumn(1).setPreferredWidth(200);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(3).setPreferredWidth(50);
        colonne.getColumn(4).setPreferredWidth(50);
        colonne.getColumn(5).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(150);
        for(Livre l : gl.listBySelected(selectedVariable, selectedValue)){
            Object[] line = {l.getCodeLivre(), l.getLibelleLivre(), l.getAuteurLivre(), l.getNombrePage(), l.getNombreLivre(),l.getType(), l.getGroupe(), l.getEtagere()};
            table.addRow(line);
        }
    }
    
    public void listBySelectedSearch(String search){
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Code livre");
        table.addColumn("Libelle");
        table.addColumn("Auteur");
        table.addColumn("Pages");
        table.addColumn("Nombre");
        table.addColumn("Type");
        table.addColumn("Groupe");
        table.addColumn("Etagère");
        tableLivre.setModel(table);
        TableColumnModel colonne = tableLivre.getColumnModel();
        colonne.getColumn(0).setPreferredWidth(70);
        colonne.getColumn(1).setPreferredWidth(200);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(3).setPreferredWidth(50);
        colonne.getColumn(4).setPreferredWidth(50);
        colonne.getColumn(5).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(150);
        for(Livre l : gl.listBySelectedSearch(search)){
            Object[] line = {l.getCodeLivre(), l.getLibelleLivre(), l.getAuteurLivre(), l.getNombrePage(), l.getNombreLivre(), l.getType(), l.getGroupe(), l.getEtagere()};
            table.addRow(line);
        }
    }
    
    public void search(){
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Code livre");
        table.addColumn("Libelle");
        table.addColumn("Auteur");
        table.addColumn("Pages");
        table.addColumn("Nombre");
        table.addColumn("Type");
        table.addColumn("Groupe");
        table.addColumn("Etagère");
        tableLivre.setModel(table);
        TableColumnModel colonne = tableLivre.getColumnModel();
        colonne.getColumn(0).setPreferredWidth(70);
        colonne.getColumn(1).setPreferredWidth(200);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(3).setPreferredWidth(50);
        colonne.getColumn(4).setPreferredWidth(50);
        colonne.getColumn(5).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(150);
        for(Livre l : gl.search(txtSearch.getText())){
            Object[] line = {l.getCodeLivre(), l.getLibelleLivre(), l.getAuteurLivre(), l.getNombrePage(), l.getNombreLivre(), l.getType(), l.getGroupe(), l.getEtagere()};
            table.addRow(line);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comboListerPar = new javax.swing.JComboBox<>();
        combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtChild = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLivre = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jPanel2.setBackground(new java.awt.Color(14, 99, 254));
        jPanel2.setPreferredSize(new java.awt.Dimension(413, 76));

        comboListerPar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aucun(e)", "Etagère", "Groupe", "Type" }));
        comboListerPar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboListerParItemStateChanged(evt);
            }
        });

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Livre(s)");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Search_20px_1.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(14, 99, 254));
        txtSearch.setBorder(null);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(254, 254, 254));

        jLabel3.setBackground(new java.awt.Color(254, 254, 254));
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Lister par :");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtChild.setBackground(new java.awt.Color(254, 254, 254));
        txtChild.setForeground(new java.awt.Color(254, 254, 254));
        txtChild.setText("jLabel4");
        txtChild.setToolTipText("");
        txtChild.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtChild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboListerPar, 0, 200, Short.MAX_VALUE)
                    .addComponent(combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboListerPar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtChild)))))
                .addContainerGap())
        );

        tableLivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(tableLivre);

        jButton1.setBackground(new java.awt.Color(14, 99, 254));
        jButton1.setForeground(new java.awt.Color(254, 254, 254));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/new.png"))); // NOI18N
        jButton1.setText("Nouveau");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(14, 99, 254));
        jButton2.setForeground(new java.awt.Color(254, 254, 254));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/edit.png"))); // NOI18N
        jButton2.setText("Modifier");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(14, 99, 254));
        jButton3.setForeground(new java.awt.Color(254, 254, 254));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/delete.png"))); // NOI18N
        jButton3.setText("Supprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(37, 37, 37)
                .addComponent(jButton2)
                .addGap(34, 34, 34)
                .addComponent(jButton3)
                .addGap(328, 328, 328))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String search = "";
        switch(comboListerPar.getSelectedItem().toString()){
            case "Aucun(e)":
                search();
                break;
            
            case "Etagère" :
                search = "livre.codeLivre like '%"+txtSearch.getText()+"%' or livre.libelleLivre like '%"+txtSearch.getText()+"%' or livre.auteurLivre like '%"+txtSearch.getText()+"%' or livre.nombrePage like '%"+txtSearch.getText()+"%' or groupe.libelleGroupe like '%"+txtSearch.getText()+"%' or type.libelleType like '%"+txtSearch.getText()+"%' and etagere.libelleEtagere = '"+combo.getSelectedItem().toString()+"'";
                listBySelectedSearch(search);
                break;
            
            case "Groupe" :  
                search = "livre.codeLivre like '%"+txtSearch.getText()+"%' or livre.libelleLivre like '%"+txtSearch.getText()+"%' or livre.auteurLivre like '%"+txtSearch.getText()+"%' or livre.nombrePage like '%"+txtSearch.getText()+"%' or type.libelleType like '%"+txtSearch.getText()+"%' or etagere.libelleEtagere like '%"+txtSearch.getText()+"%' and groupe.libelleGroupe = '"+combo.getSelectedItem().toString()+"'";
                listBySelectedSearch(search);
                break;
            
            case "Type" :  
                search = "livre.codeLivre like '%"+txtSearch.getText()+"%' or livre.libelleLivre like '%"+txtSearch.getText()+"%' or livre.auteurLivre like '%"+txtSearch.getText()+"%' or livre.nombrePage like '%"+txtSearch.getText()+"%' or groupe.libelleGroupe like '%"+txtSearch.getText()+"%' or etagere.libelleEtagere like '%"+txtSearch.getText()+"%' and type.libelleType = '"+combo.getSelectedItem().toString()+"'";
                listBySelectedSearch(search);
                break;
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        listeLivre();
    }//GEN-LAST:event_formWindowActivated

    private void comboListerParItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboListerParItemStateChanged
        // TODO add your handling code here:
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        switch(comboListerPar.getSelectedItem().toString()){
            case "Aucun(e)": 
                combo.setVisible(false);
                txtChild.setVisible(false);
                listeLivre();
                break;
            
            case "Etagère" : 
                txtChild.setText("Etagère :");
                for(Etagere e : get.list()){
                    comboBoxModel.addElement(e.getLibelleEtagere());
                    combo.setModel(comboBoxModel);
                }
                combo.setVisible(true);
                txtChild.setVisible(true);
                String idEtagere = combo.getSelectedItem().toString();
                listeLivreBySelected("etagere.idEtagere", "eta-"+idEtagere.substring(idEtagere.length()-1, idEtagere.length()));
                break;
            
            case "Groupe" : 
                txtChild.setText("Groupe :");
                for(Groupe g : gg.list()){
                    comboBoxModel.addElement(g.getLibelleGroupe());
                    combo.setModel(comboBoxModel);
                }
                combo.setVisible(true);
                txtChild.setVisible(true);
                String libelleGroupe = combo.getSelectedItem().toString();
                listeLivreBySelected("livre.idGroupe", gg.getIdGroupeByLibelle(libelleGroupe));
                break;
            
            case "Type" : 
                txtChild.setText("Type :");
                for(com.gestionBibliotheque.classe.Type t : gt.list()){
                    comboBoxModel.addElement(t.getLibelleType());
                    combo.setModel(comboBoxModel);
                }
                combo.setVisible(true);
                txtChild.setVisible(true);
                String libelleType = combo.getSelectedItem().toString();
                listeLivreBySelected("livre.idType", gt.getIdTypeByLibelle(libelleType));
                break;
        }
    }//GEN-LAST:event_comboListerParItemStateChanged

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged
        // TODO add your handling code here:
        switch(comboListerPar.getSelectedItem().toString()){            
            case "Etagère" : 
                String idEtagere = combo.getSelectedItem().toString();
                listeLivreBySelected("etagere.idEtagere", "eta-"+idEtagere.substring(idEtagere.length()-1, idEtagere.length()));
                break;
            
            case "Groupe" : 
                String libelleGroupe = combo.getSelectedItem().toString();
                listeLivreBySelected("livre.idGroupe", gg.getIdGroupeByLibelle(libelleGroupe));
                break;
            
            case "Type" : 
                String libelleType = combo.getSelectedItem().toString();
                listeLivreBySelected("livre.idType", gt.getIdTypeByLibelle(libelleType));
                break;
        }
    }//GEN-LAST:event_comboItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new NouveauLivre(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int n = tableLivre.getSelectedRow();
        String codeLivre = (String) tableLivre.getValueAt(n, 0);
        String titre = (String) tableLivre.getValueAt(n, 1);
        if(JOptionPane.showConfirmDialog(null, "Vous êtes en train de supprimer "+titre+" de la liste. Voulez-vous continuer? ") == JOptionPane.YES_NO_OPTION){
            try {
                gl.delete(codeLivre);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int n = tableLivre.getSelectedRow();
        codeLivre = (String) tableLivre.getValueAt(n, 0);
        new ModifierLivre(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListeLivre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListeLivre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListeLivre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListeLivre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListeLivre dialog = new ListeLivre(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JComboBox<String> comboListerPar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableLivre;
    private javax.swing.JLabel txtChild;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
