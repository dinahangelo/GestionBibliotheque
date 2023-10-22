/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.front;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Emprunter;
import com.gestionBibliotheque.gestion.GestionEmprunt;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author solofonirina
 */
public class Menu extends javax.swing.JFrame {
    Connexion con = new Connexion();
    GestionEmprunt gem = new GestionEmprunt();
    JDialog notification = new JDialog();
    ScheduledExecutorService time = Executors.newScheduledThreadPool(1);
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        dateHeure();
        slogan();
        setLocationRelativeTo(null);     
        textLycee.setVisible(false);
        livre.setVisible(false);
        logoKmm.setVisible(false);   

        time.schedule(()->{
            alert();
        }, 6, TimeUnit.SECONDS);
        time.schedule(()->{
            effetFade(textLycee, 150);
            effetFade(livre, 100);
            effetFade(logoKmm, 50);
        }, 1, TimeUnit.SECONDS);
    }
    
    public void effetFade(JLabel label, int timeOut){
        ImageIcon currentIcon = (ImageIcon) label.getIcon();
        Image image = currentIcon.getImage();

        Timer timer = new Timer(timeOut, new ActionListener() {
            private float alpha = 0.0f;
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                alpha += 0.05f;
                if(alpha >= 1.0f){
                    ((Timer) ae.getSource()).stop();
                }else{
                    Image newImage = createImageWithAlpha(image, alpha);
                    ImageIcon newIcon = new ImageIcon(newImage);
                    label.setIcon(newIcon);
                    label.setVisible(true);
                }
            }
        });
        
        timer.start();
    }
    
    private static Image createImageWithAlpha(Image image, float alpha){
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        
        BufferedImage bufferdImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferdImage.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        
        return bufferdImage;
    }
    public void dateHeure(){
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    LocalTime tm = LocalTime.now();
                    labH.setText("Heure : "+tm.getHour()+":"+tm.getMinute()+":"+tm.getSecond());
                    LocalDate da = LocalDate.now();
                    switch(da.getMonthValue()){
                        case 1 : labD.setText("Date : "+da.getDayOfMonth()+" Janv "+da.getYear()); break;
                        case 2 : labD.setText("Date : "+da.getDayOfMonth()+" Fev "+da.getYear()); break;
                        case 3 : labD.setText("Date : "+da.getDayOfMonth()+" Mars "+da.getYear()); break;
                        case 4 : labD.setText("Date : "+da.getDayOfMonth()+" Avr "+da.getYear()); break;
                        case 5 : labD.setText("Date : "+da.getDayOfMonth()+" Mey "+da.getYear()); break;
                        case 6 : labD.setText("Date : "+da.getDayOfMonth()+" Juin "+da.getYear()); break;
                        case 7 : labD.setText("Date : "+da.getDayOfMonth()+" Juil "+da.getYear()); break;
                        case 8 : labD.setText("Date : "+da.getDayOfMonth()+" Août "+da.getYear()); break;
                        case 9 : labD.setText("Date : "+da.getDayOfMonth()+" Sept "+da.getYear()); break;
                        case 10 : labD.setText("Date : "+da.getDayOfMonth()+" Oct "+da.getYear()); break;
                        case 11 : labD.setText("Date : "+da.getDayOfMonth()+" Nov "+da.getYear()); break;
                        case 12 : labD.setText("Date : "+da.getDayOfMonth()+" Dec "+da.getYear()); break;
                    }
                    try{
                        sleep(1000);
                        
                         
                    }catch(InterruptedException ex){
                        Logger.getLogger(Calendar.class.getName()).log(Level .SEVERE,null,ex);
                    }
                }
            }
        };
        clock.start();
    }
    
    public void slogan(){
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    Calendar Cal = new GregorianCalendar();
                    int minute = Cal.get(Calendar.SECOND);
                    if((minute<15)|| ((minute>30)&&(minute<45))){
                        lb1.setText("Olom-banona");
                        lb2.setText("no tarigetra !");
                    }else{
                        lb1.setText("Mijoroa");
                        lb2.setText("Mamirata !");
                    }
                    try{
                        sleep(1000);  
                    }catch(InterruptedException ex){
                        Logger.getLogger(Calendar.class.getName()).log(Level .SEVERE,null,ex);
                    }
                }
            }
        };
        clock.start();
    }
    
    public void alert(){
        if(!gem.listEnRetard().isEmpty()){
            notification.setSize(800, 600);
            JTable table = new JTable();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Classe");
            model.addColumn("Emprunteur");
            model.addColumn("Livre");
            model.addColumn("Nombre");
            model.addColumn("Fin");
            model.addColumn("Année scolaire");
            for(Emprunter e : gem.listEnRetard()){
                Object[] line = {e.getClasse(), e.getCodeEmprunteur(), e.getCodeLivre(), e.getNombreLivre(), e.getFinEmprunt(), e.getAnneeScolaire()};
                model.addRow(line);
            }
            table.setModel(model);
            
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(30);
            table.getColumnModel().getColumn(4).setPreferredWidth(80);
            table.getColumnModel().getColumn(5).setPreferredWidth(80);

            JScrollPane scrollPane = new JScrollPane(table);
            notification.setTitle("Liste des élèves qui n'ont pas retourné des livres empruntés");
            notification.add(scrollPane);
            notification.setAlwaysOnTop(true);
            notification.setVisible(true);
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        textLycee = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        logoKmm = new javax.swing.JLabel();
        labD = new javax.swing.JLabel();
        labH = new javax.swing.JLabel();
        livre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenuItem14.setText("jMenuItem14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1150, 590));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 590));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textLycee.setFont(new java.awt.Font("Times New Roman", 0, 100)); // NOI18N
        textLycee.setForeground(new java.awt.Color(14, 99, 254));
        textLycee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Texte KATOLIKA.png"))); // NOI18N
        textLycee.setPreferredSize(new java.awt.Dimension(500, 350));
        textLycee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textLyceeMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textLyceeMouseEntered(evt);
            }
        });
        jPanel1.add(textLycee, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 697, 570));

        lb1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lb1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lb1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel1.add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 661, 225, 25));

        lb2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jPanel1.add(lb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 698, 174, 29));

        logoKmm.setBackground(new java.awt.Color(14, 99, 254));
        logoKmm.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        logoKmm.setForeground(new java.awt.Color(14, 99, 254));
        logoKmm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Logo KMM FInale etoile22.png"))); // NOI18N
        jPanel1.add(logoKmm, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        labD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labD.setForeground(new java.awt.Color(102, 102, 102));
        labD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(labD, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 178, 14));

        labH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        labH.setForeground(new java.awt.Color(102, 102, 102));
        labH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(labH, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 159, 17));

        livre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/BOOKY.png"))); // NOI18N
        livre.setText("jLabel2");
        jPanel1.add(livre, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 329, 265, 199));

        jMenu1.setText("Fichier");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Contacts_20px_1.png"))); // NOI18N
        jMenuItem2.setText("Utilisateur");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Exit_20px_1.png"))); // NOI18N
        jMenuItem3.setText("Deconnexion");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Bases");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Book_20px.png"))); // NOI18N
        jMenuItem5.setText("Livres");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Generic Book File Type_20px.png"))); // NOI18N
        jMenuItem4.setText("Type du livre");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);
        jMenu2.add(jSeparator1);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Book Stack_20px.png"))); // NOI18N
        jMenuItem6.setText("Groupes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Book Shelf_20px.png"))); // NOI18N
        jMenuItem7.setText("Etagères");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
        jMenu2.add(jSeparator2);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/User Groups_20px.png"))); // NOI18N
        jMenuItem8.setText("Elèves");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/School Director_20px.png"))); // NOI18N
        jMenuItem9.setText("Professeurs");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Saisie");

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Dossier_20px.png"))); // NOI18N
        jMenuItem11.setText("Emprunter");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Etats");

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Dossier_20px.png"))); // NOI18N
        jMenuItem12.setText("Liste par statut d'emprunt");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Dossier_20px.png"))); // NOI18N
        jMenuItem16.setText("Liste par cycle d'emprunt");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);
        jMenu6.add(jSeparator3);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Bar Chart_20px_2.png"))); // NOI18N
        jMenuItem15.setText("Histogramme de participation par classe");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Pie Chart_20px_1.png"))); // NOI18N
        jMenuItem13.setText("Diagramme de participation par cycle");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/Pie Chart_20px_1.png"))); // NOI18N
        jMenuItem17.setText("Diagramme du type de livre le plus emprunté");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("?");

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gestionBibliotheque/image/About_20px_1.png"))); // NOI18N
        jMenuItem10.setText("A propos...");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new ListeLivre(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new ListeType(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        new ListeGroupe(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        new ListeEtagere(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        new ListeEleve(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        new ListeProfesseur(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        new ListeEmprunt(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        dispose();
        new Authentification().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new ModifierUser(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:\
        new Liste(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        JButton printButton = new JButton("Imprimer");
        printButton.setPreferredSize(new Dimension(150, 30));

        // Créer un nouveau JPanel pour centrer le bouton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printButton);
        
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        gem.getNombreEmprunterCycle().forEach((donnees) -> {
            dataset.setValue(donnees[0], Integer.parseInt(donnees[1]));
        });

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Diagramme de participation pour chaque cycle", 
            dataset, 
            true, 
            true, 
            false
        );

        ChartPanel chartPanel = new ChartPanel(pieChart);
        javax.swing.JFrame frame = new javax.swing.JFrame();

        // Création d'un nouveau JPanel pour contenir le bouton et le ChartPanel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);  // Ajouter le JPanel du bouton en haut
        panel.add(chartPanel, BorderLayout.CENTER);  // Ajouter le ChartPanel au centre
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputStream is = getClass().getResourceAsStream("/com/gestionBibliotheque/front/diagrammeCycle.jrxml");
                if (is == null) {
                    Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, "Le fichier diagrammeCycle.jrxml n'a pas été trouvé dans le JAR.");
                    return;
                }

                Connection connection = null;
                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(is);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    // Ajoutez vos paramètres si vous en avez
                    connection = con.open();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    JRViewer viewer = new JRViewer(jasperPrint);
                    JDialog dialog = new JDialog((Frame)null, "Outil d'impression", true);
                    dialog.add(viewer);
                    dialog.setSize(800, 600);
                    dialog.setVisible(true);

                    // Si vous voulez exporter immédiatement en PDF
//                    JasperExportManager.exportReportToPdfFile(jasperPrint, "fichier.pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException err) {
                            Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, err);
                        }
                    }
                }
            }

        });
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void updateChart(JFrame frame, String selectedYear) {
        GestionEmprunt gem = new GestionEmprunt();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Récupérez les données pour l'année scolaire sélectionnée (vous devrez ajuster cette méthode en conséquence)
        gem.getNombreEmprunterClasse(selectedYear).forEach((donnees) -> {
            dataset.addValue(Integer.parseInt(donnees[1]), "Classe", donnees[0]);
        });

        // Créez le diagramme
        JFreeChart barChart = ChartFactory.createBarChart(
                "Histogramme de participation par classe", 
                "", 
                "Nombre de participant", 
                dataset, 
                PlotOrientation.VERTICAL, 
                true, 
                true, 
                false);

        // Affichez le diagramme dans un JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);

        // Supprimez l'ancien ChartPanel s'il existe
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof ChartPanel) {
                frame.getContentPane().remove(comp);
            }
        }

        frame.add(chartPanel, BorderLayout.CENTER);
        frame.revalidate();  // Assurez-vous que le JFrame est mis à jour
        frame.repaint();     // Redessinez le JFrame
    }

    
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:

        // Étape 1 : Créer le JLabel
        JLabel yearLabel = new JLabel("Année scolaire : ");

        // Étape 2 : Créer et ajouter un JComboBox
        JComboBox<String> yearComboBox = new JComboBox<>();
        yearComboBox.addItem("Choisir");
        gem.getAnneeScolaire().forEach((anneeScolaire) -> {
            yearComboBox.addItem(anneeScolaire);
        });

        JButton printButton = new JButton("Imprimer");
        JPanel comboPanel = new JPanel();
        comboPanel.add(yearLabel);
        comboPanel.add(yearComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(printButton);

        // Créez le JFrame
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(comboPanel, BorderLayout.NORTH);  // Ajoutez le JComboBox au nord
        frame.add(buttonPanel, BorderLayout.SOUTH);  // Ajoutez le bouton au sud

        // Créez le diagramme et ajoutez-le au centre
        updateChart(frame, (String) yearComboBox.getSelectedItem());

        // Étape 2 : Ajouter un ActionListener au JComboBox
        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) yearComboBox.getSelectedItem();

                // Mettez à jour le diagramme avec les données de l'année scolaire sélectionnée
                updateChart(frame, selectedYear);
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) yearComboBox.getSelectedItem();
                if (!"Choisir".equals(selectedYear)) {
                    InputStream is = getClass().getResourceAsStream("/com/gestionBibliotheque/front/histogrammeClasse.jrxml");
                    if (is == null) {
                        Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, "Le fichier histogrammeClasse.jrxml n'a pas été trouvé dans le JAR.");
                        return;
                    }

                    Connection connection = null;
                    try {
                        JasperReport jasperReport = JasperCompileManager.compileReport(is);
                        Map<String, Object> parameters = new HashMap<String, Object>();
                        parameters.put("ANNEE_PARAM", selectedYear); // Passez l'année sélectionnée en tant que paramètre

                        connection = con.open();
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                        JRViewer viewer = new JRViewer(jasperPrint);
                        JDialog dialog = new JDialog((Frame) null, "Outil d'impression", true);

                        dialog.add(viewer);
                        dialog.setSize(800, 600);
                        dialog.setVisible(true);

                        // Si vous voulez exporter immédiatement en PDF
//                        JasperExportManager.exportReportToPdfFile(jasperPrint, "fichier.pdf");
                    } catch (JRException ex) {
                        Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (connection != null) {
                            try {
                                connection.close();
                            } catch (SQLException err) {
                                Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, err);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner une année valide!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        new ListeCycle(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        JButton printButton = new JButton("Imprimer");
        printButton.setPreferredSize(new Dimension(150, 30));

        // Créer un nouveau JPanel pour centrer le bouton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printButton);

        DefaultPieDataset dataset = new DefaultPieDataset();
        gem.getNombreTypeEmprunter().forEach((donnees) -> {
            dataset.setValue(donnees[0], Integer.parseInt(donnees[1]));
        });

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Diagramme de livre empruntés", 
            dataset, 
            true, 
            true, 
            false
        );

        ChartPanel chartPanel = new ChartPanel(pieChart);

        // Création d'un nouveau JPanel pour contenir le bouton et le ChartPanel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);  // Ajouter le JPanel du bouton en haut
        panel.add(chartPanel, BorderLayout.CENTER);  // Ajouter le ChartPanel au centre

        javax.swing.JFrame frame = new javax.swing.JFrame();
//        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(panel);  // Ajouter le JPanel principal au JFrame
        frame.pack();
        frame.setVisible(true);
        
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputStream is = getClass().getResourceAsStream("/com/gestionBibliotheque/front/diagrammeType.jrxml");
                if (is == null) {
                    Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, "Le fichier diagrammeType.jrxml n'a pas été trouvé dans le JAR.");
                    return;
                }

                Connection connection = null;
                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(is);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    // Ajoutez vos paramètres si vous en avez
                    connection = con.open();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    JRViewer viewer = new JRViewer(jasperPrint);
                    JDialog dialog = new JDialog((Frame)null, "Outil d'impression", true);
                    dialog.add(viewer);
                    dialog.setSize(800, 600);
                    dialog.setVisible(true);

                    // Si vous voulez exporter immédiatement en PDF
//                    JasperExportManager.exportReportToPdfFile(jasperPrint, "fichier.pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException err) {
                            Logger.getLogger(Liste.class.getName()).log(Level.SEVERE, null, err);
                        }
                    }
                }
            }

        });

    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        new About(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void lb1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lb1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lb1InputMethodTextChanged

    private void textLyceeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textLyceeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_textLyceeMouseEntered

    private void textLyceeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textLyceeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_textLyceeMouseExited

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel labD;
    private javax.swing.JLabel labH;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel livre;
    private javax.swing.JLabel logoKmm;
    private javax.swing.JLabel textLycee;
    // End of variables declaration//GEN-END:variables
}
