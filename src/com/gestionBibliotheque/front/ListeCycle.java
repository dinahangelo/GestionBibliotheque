/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.front;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.CustomTableCellRenderer;
import com.gestionBibliotheque.classe.Emprunter;
import com.gestionBibliotheque.gestion.GestionEleve;
import com.gestionBibliotheque.gestion.GestionEmprunt;
import com.gestionBibliotheque.gestion.GestionLivre;
import com.gestionBibliotheque.gestion.GestionProfesseur;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author solofonirina
 */
public class ListeCycle extends javax.swing.JDialog {
    Connexion con = new Connexion();
    GestionEleve ge = new GestionEleve();
    GestionEmprunt gem = new GestionEmprunt();
    GestionLivre gl = new GestionLivre();
    GestionProfesseur gp = new GestionProfesseur();
    public static int id;
    /**
     * Creates new form ListeEmprunt
     */
    public ListeCycle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setEnRetard();
        listEmprunt();
        customiser();
        setLocationRelativeTo(null);
    }
    
    public void setEnRetard(){
        for(Emprunter e : gem.getRetard(Date.valueOf(LocalDate.now().toString()))){
            gem.setStatutEnRetard(e.getId());
        }
    }
    
    public void customiser(){
        TableColumn column = tableEmprunt.getColumnModel().getColumn(7);
        column.setCellRenderer(new CustomTableCellRenderer());
    }
    
    public final void listEmprunt(){
        DefaultTableModel table = new DefaultTableModel();
        TableColumnModel colonne = tableEmprunt.getColumnModel();
        table.addColumn("Classe");
        table.addColumn("Emprunteur");
        table.addColumn("Livre");
        table.addColumn("Nombre");
        table.addColumn("Début");
        table.addColumn("Fin");
        table.addColumn("Année scolaire");
        table.addColumn("Statut");
        tableEmprunt.setModel(table);
        colonne.getColumn(0).setPreferredWidth(5);
        colonne.getColumn(1).setPreferredWidth(250);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(20);
        colonne.getColumn(3).setPreferredWidth(40);
        colonne.getColumn(4).setPreferredWidth(40);
        colonne.getColumn(5).setPreferredWidth(70);
        colonne.getColumn(7).setPreferredWidth(30);
        for(Emprunter e : gem.list()){
            Object[] line = {e.getClasse(), e.getCodeEmprunteur(), e.getCodeLivre(), e.getNombreLivre(),  e.getDebutEmprunt(), e.getFinEmprunt(), e.getAnneeScolaire(), e.getStatut()};
            table.addRow(line);
        }
    }
    
    public final void listParCycle(String req){
        DefaultTableModel table = new DefaultTableModel();
        TableColumnModel colonne = tableEmprunt.getColumnModel();
        table.addColumn("Classe");
        table.addColumn("Emprunteur");
        table.addColumn("Livre");
        table.addColumn("Nombre");
        table.addColumn("Début");
        table.addColumn("Fin");
        table.addColumn("Année scolaire");
        table.addColumn("Statut");
        tableEmprunt.setModel(table);
        colonne.getColumn(0).setPreferredWidth(5);
        colonne.getColumn(1).setPreferredWidth(250);
        colonne.getColumn(2).setPreferredWidth(150);
        colonne.getColumn(6).setPreferredWidth(20);
        colonne.getColumn(3).setPreferredWidth(40);
        colonne.getColumn(4).setPreferredWidth(40);
        colonne.getColumn(5).setPreferredWidth(70);
        colonne.getColumn(7).setPreferredWidth(30);
        for(Emprunter e : gem.listParCycle(req)){
            Object[] line = {e.getClasse(), e.getCodeEmprunteur(), e.getCodeLivre(), e.getNombreLivre(),  e.getDebutEmprunt(), e.getFinEmprunt(), e.getAnneeScolaire(), e.getStatut()};
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
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmprunt = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jPanel2.setBackground(new java.awt.Color(14, 99, 254));

        jLabel9.setBackground(new java.awt.Color(254, 254, 254));
        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("Liste emprunt");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choisir...", "Premier cycle", "Second cycle", "Secondaire" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(130, 24));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Listé par :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 518, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))))
        );

        tableEmprunt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableEmprunt.setToolTipText("");
        jScrollPane1.setViewportView(tableEmprunt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        setEnRetard();
        listEmprunt();
        TableColumn column = tableEmprunt.getColumnModel().getColumn(7);
        column.setCellRenderer(new CustomTableCellRenderer());
        customiser();
    }//GEN-LAST:event_formWindowActivated

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        String liste = jComboBox1.getSelectedItem().toString();
        switch (liste) {
            case "Premier cycle":
                String reqPermierCycle ="SELECT \n" +
                                        "    emprunter.id, \n" +
                                        "    IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, \n" +
                                        "    livre.libelleLivre, \n" +
                                        "    dateEmprunt, \n" +
                                        "    finEmprunt, \n" +
                                        "    classe, \n" +
                                        "    anneeScolaire, \n" +
                                        "    emprunter.nombreLivre, \n" +
                                        "    statut \n" +
                                        "FROM \n" +
                                        "    emprunter \n" +
                                        "LEFT JOIN \n" +
                                        "    professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur \n" +
                                        "LEFT JOIN \n" +
                                        "    eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule \n" +
                                        "JOIN \n" +
                                        "    livre ON emprunter.codeLivre = livre.codeLivre \n" +
                                        "WHERE \n" +
                                        "    classe LIKE '12%' OR classe LIKE '11%' OR classe LIKE '10%' OR classe LIKE '9%' OR classe LIKE '8%' OR classe LIKE '7%'";
                listParCycle(reqPermierCycle);
                break;
            case "Second cycle":
                String reqSecondCycle = "SELECT \n" +
                                        "    emprunter.id, \n" +
                                        "    IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, \n" +
                                        "    livre.libelleLivre, \n" +
                                        "    dateEmprunt, \n" +
                                        "    finEmprunt, \n" +
                                        "    classe, \n" +
                                        "    anneeScolaire, \n" +
                                        "    emprunter.nombreLivre, \n" +
                                        "    statut \n" +
                                        "FROM \n" +
                                        "    emprunter \n" +
                                        "LEFT JOIN \n" +
                                        "    professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur \n" +
                                        "LEFT JOIN \n" +
                                        "    eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule \n" +
                                        "JOIN \n" +
                                        "    livre ON emprunter.codeLivre = livre.codeLivre \n" +
                                        "WHERE \n" +
                                        "    classe LIKE '6%' OR classe LIKE '5%' OR classe LIKE '4%' OR classe LIKE '3%'";
                listParCycle(reqSecondCycle);
                break;
            case "Secondaire":
                String reqSecondaire = "SELECT \n" +
                                        "    emprunter.id, \n" +
                                        "    IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, \n" +
                                        "    livre.libelleLivre, \n" +
                                        "    dateEmprunt, \n" +
                                        "    finEmprunt, \n" +
                                        "    classe, \n" +
                                        "    anneeScolaire, \n" +
                                        "    emprunter.nombreLivre, \n" +
                                        "    statut \n" +
                                        "FROM \n" +
                                        "    emprunter \n" +
                                        "LEFT JOIN \n" +
                                        "    professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur \n" +
                                        "LEFT JOIN \n" +
                                        "    eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule \n" +
                                        "JOIN \n" +
                                        "    livre ON emprunter.codeLivre = livre.codeLivre \n" +
                                        "WHERE \n" +
                                        "    classe LIKE '2%' OR classe LIKE '1L%' OR classe LIKE '1S%' OR classe LIKE 'T%'";
                listParCycle(reqSecondaire);
                break;

            default:
                listEmprunt();
                break;
        }
        customiser();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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
            java.util.logging.Logger.getLogger(ListeCycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListeCycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListeCycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListeCycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListeCycle dialog = new ListeCycle(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEmprunt;
    // End of variables declaration//GEN-END:variables
}
