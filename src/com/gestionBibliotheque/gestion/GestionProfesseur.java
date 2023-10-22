/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Professeur;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author solofonirina
 */
public class GestionProfesseur {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Professeur p){
        String req = "INSERT INTO professeur VALUES (?,?)";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, p.getCodeProfesseur());
            ps.setString(2, p.getNomProfesseur());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (HeadlessException | SQLException err) {
            System.err.println("Insertion error : "+err.toString());
        }
    }
    
    public List<Professeur> list(){
        List<Professeur> ls = new ArrayList<>();
        String req = "SELECT * FROM professeur";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                ls.add(new Professeur(rs.getString("codeProfesseur"), rs.getString("nomProfesseur")));
            }
        } catch (SQLException err) {
            System.err.println("List error : "+err);
        }
        return ls;
    }
    
    public List<Professeur> search(String search){
        List<Professeur> ls = new ArrayList<>();
        String req = "SELECT * FROM professeur WHERE codeProfesseur like '%"+search+"%' or nomProfesseur like '%"+search+"%'";
        try {
            ps = con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                ls.add(new Professeur(rs.getString("codeProfesseur"), rs.getString("nomProfesseur")));
            }
        } catch (SQLException err) {
            System.err.println("Search error : "+err);
        }
        return ls;
    }
    
    public int nombreProfesseur(){
        int nombreProfesseur = 0;
        String req = "SELECT COUNT(*) AS nombreProfesseur FROM professeur";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                nombreProfesseur = rs.getInt("nombreProfesseur");
            }
        } catch (SQLException error) {
            System.err.println("Error get id groupe : "+error);
        }
        return nombreProfesseur;
    }
    
    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM professeur WHERE codeProfesseur = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, "prof-"+id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Données pour id: " + id);
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmprunt.class.getName()).log(Level.SEVERE, "SQL Error for id: " + id, ex);
        }
        return bool;
    }

    public int newId(){
        int nombre = nombreProfesseur()+ 1;
        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }
    
    public List<Professeur> getProfesseurByCodeProfesseur(String codeProfesseur){
        List<Professeur> ls = new ArrayList<>();
        String req = "SELECT * FROM professeur WHERE codeProfesseur = '"+codeProfesseur+"'";
        try {
            ps = con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                ls.add(new Professeur(rs.getString("codeProfesseur"), rs.getString("nomProfesseur")));
            }
        } catch (SQLException err) {
            System.err.println("Search error : "+err);
        }
        return ls;        
    }
    
    public String getNomProfesseurByCodeProfesseur(String codeProfesseur){
        String nomProfesseur = new String();
        String req = "SELECT * FROM professeur WHERE codeProfesseur = '"+codeProfesseur+"'";
        try {
            ps = con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                nomProfesseur = rs.getString("nomProfesseur");
            }
        } catch (SQLException err) {
            System.err.println("Search error : "+err);
        }
        return nomProfesseur;        
    }
    
    public void update(Professeur p, String codeProfesseur){
        String req = "UPDATE professeur SET codeProfesseur = ? , nomProfesseur = ? WHERE codeProfesseur = ?";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, p.getCodeProfesseur());
            ps.setString(2, p.getNomProfesseur());
            ps.setString(3, codeProfesseur);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification réussie!");
        } catch (HeadlessException | SQLException err) {
            System.err.println("Update error : "+err);
        }
    }
    
    public void delete(String codeProfesseur){
        String req = "DELETE FROM professeur WHERE codeProfesseur = ?";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, codeProfesseur);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (HeadlessException | SQLException e) {
        }
    }
}