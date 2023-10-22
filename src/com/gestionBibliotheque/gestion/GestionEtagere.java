/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Etagere;
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
public class GestionEtagere {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Etagere e){
        try {
            String req = "INSERT INTO etagere VALUES(?,?)";
            ps = con.open().prepareStatement(req);
            ps.setString(1, e.getIdEtagere());
            ps.setString(2, e.getLibelleEtagere());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (HeadlessException | SQLException error) {
            System.err.println("Insertion error : "+error.toString());
        }
    }
    
    public List<Etagere> list(){
        List<Etagere> ls = new ArrayList<>();
        String req = "SELECT * FROM etagere";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Etagere(rs.getString("idEtagere"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Etagere> comboEtagere(){
        List<Etagere> ls = new ArrayList<>();
        String req = "SELECT * FROM etagere";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Etagere(rs.getString("idEtagere"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Etagere> search(String search){
        List<Etagere> ls = new ArrayList<>();
        String req = "SELECT * FROM etagere WHERE idEtagere like '%"+search+"%' or libelleEtagere like '%"+search+"%'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Etagere(rs.getString("idEtagere"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Etagere> getEtagereById(String idEtagere){
        List<Etagere> ls = new ArrayList<>();
        String req = "SELECT * FROM etagere WHERE idEtagere = '"+idEtagere+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Etagere(rs.getString("idEtagere"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public String getEtagere(String idEtagere){
        String etagere = new String();
        String req = "SELECT libelleEtagere from etagere WHERE idEtagere = '"+idEtagere+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                etagere = rs.getString("libelleEtagere");
            }
        } catch (SQLException e) {
            System.err.println("Error get etagere : "+e);
        }
        return etagere;
    }
    
    public int idEtagere(){
        int idEtagere = 0;
        String req = "SELECT COUNT(*) AS id FROM etagere";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                idEtagere = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.err.println("Error get id etagere : "+error);
        }
        return idEtagere;
    }
    
    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM etagere WHERE idEtagere = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, "eta-"+id);
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
        int nombre = idEtagere()+ 1;
        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }
    
    public void update(Etagere e, String idEtagere){
        try {
            String req = "UPDATE etagere SET idEtagere = ? , libelleEtagere = ? WHERE idEtagere = ?";
            ps = con.open().prepareStatement(req);
            ps.setString(1, e.getIdEtagere());
            ps.setString(2, e.getLibelleEtagere());
            ps.setString(3, idEtagere);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification réussie!");
        } catch (SQLException error) {
            System.err.println("Modification error : "+error.toString());
        }
    }
    
    public void delete(String idEtagere){
        try {
            String req = "DELETE FROM etagere WHERE idEtagere = ? ";
            ps = con.open().prepareStatement(req);
            ps.setString(1, idEtagere);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (Exception e) {
        }
    }
}
