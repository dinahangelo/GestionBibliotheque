/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Groupe;
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
public class GestionGroupe {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Groupe p){
        try {
            String req = "INSERT INTO groupe VALUES (?,?,?,?)";
            ps = con.open().prepareStatement(req);
            ps.setString(1, p.getIdGroupe());
            ps.setString(2, p.getLibelleGroupe());
            ps.setString(3, p.getPfEtagere());
            ps.setString(4, p.getIdEtagere());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (HeadlessException | SQLException error) {
            System.err.println("Insertion error : "+error.toString());
        }
    }
    
    public List<Groupe> list(){
        List<Groupe> ls = new ArrayList<>();
        String req = "SELECT * FROM groupe";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Groupe(rs.getString("idGroupe"), rs.getString("libelleGroupe"), rs.getString("pfEtagere"), rs.getString("idEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Groupe> search(String search){
        List<Groupe> ls = new ArrayList<>();
        String req = "SELECT * FROM groupe WHERE idGroupe like '%"+search+"%' or libelleGroupe like '%"+search+"%' or pfEtagere like '%"+search+"%' or idEtagere like '%"+search+"%' ";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Groupe(rs.getString("idGroupe"), rs.getString("libelleGroupe"), rs.getString("pfEtagere"), rs.getString("idEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Groupe> getGroupeById(String idGroupe){
        List<Groupe> ls = new ArrayList<>();
        String req = "SELECT * FROM groupe WHERE idGroupe = '"+idGroupe+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Groupe(rs.getString("idGroupe"), rs.getString("libelleGroupe"), rs.getString("pfEtagere"), rs.getString("idEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public String getIdGroupeByLibelle(String libelleGroupe){
        String idGroupe = new String();
        String req = "SELECT idGroupe FROM groupe WHERE libelleGroupe = '"+libelleGroupe+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                idGroupe = rs.getString("idGroupe");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return idGroupe;
    }
    
    public String getLibelleGroupeByIdGroupe(String idGroupe){
        String libelleGroupe = new String();
        String req = "SELECT libelleGroupe FROM groupe WHERE idGroupe = '"+idGroupe+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                libelleGroupe = rs.getString("libelleGroupe");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return libelleGroupe;
    }
    
    public int idGroupe(){
        int idGroupe = 0;
        String req = "SELECT COUNT(*) AS id FROM groupe";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                idGroupe = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.err.println("Error get id groupe : "+error);
        }
        return idGroupe;
    }
    
    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM groupe WHERE idGroupe = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, "gp-"+id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
        int nombre = idGroupe()+ 1;
        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }
    
    public void update(Groupe g, String idGroupe){
        try {
            String req = "UPDATE groupe SET libelleGroupe = ? , pfEtagere = ?, idEtagere = ? WHERE idGroupe = ?";
            ps = con.open().prepareStatement(req);
            ps.setString(1, g.getLibelleGroupe());
            ps.setString(2, g.getPfEtagere());
            ps.setString(3, g.getIdEtagere());
            ps.setString(4, idGroupe);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification réussie!");
        } catch (SQLException error) {
            System.err.println("Modification error : "+error.toString());
        }
    }
    
    public void delete(String idGroupe){
        try {
            String req = "DELETE FROM groupe WHERE idGroupe = ? ";
            ps = con.open().prepareStatement(req);
            ps.setString(1, idGroupe);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (Exception e) {
        }
    }
}
