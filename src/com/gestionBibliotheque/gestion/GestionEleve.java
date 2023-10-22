/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Eleve;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author solofonirina
 */
public class GestionEleve{
    Connexion con = new Connexion();
    
    public void insert(Eleve e) throws SQLException{
        String req = "INSERT INTO eleve VALUES(?,?)";
        try(PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, e.getNumeroMatricule());
            ps.setString(2, e.getNom());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (SQLException error) {
            System.err.println("Insertion error : "+error.toString()); 
            throw error;
        }
    }
    
    public void insertExcel(List<Eleve> ls) throws SQLException{
        String req = "INSERT INTO eleve VALUES(?,?) ON DUPLICATE KEY UPDATE numeroMatricule=VALUES(numeroMatricule), nom=VALUES(nom)";
        
            
            try(PreparedStatement ps = con.open().prepareStatement(req);) {
                for (Eleve e : ls) {
                    System.out.println(e.getNumeroMatricule()+" "+e.getNom());
                    ps.setString(1, e.getNumeroMatricule());
                    ps.setString(2, e.getNom());
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException error) {
                System.err.println("Insertion error : "+error.toString()); 
                throw error;
            }
        
    }
    
    public List<Eleve> list(){
        List<Eleve> ls = new ArrayList<>();
        String req = "SELECT * FROM eleve";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                ls.add(new Eleve(rs.getString("numeroMatricule"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Eleve> search(String search){
        List<Eleve> ls = new ArrayList<>();
        String req = "SELECT * FROM eleve WHERE numeroMatricule like '%"+search+"%' or nom like '%"+search+"%'";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {                
                ls.add(new Eleve(rs.getString("numeroMatricule"), rs.getString("nom")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<Eleve> getEleveByNumeroMatricule(String numeroMatricule){
        List<Eleve> ls = new ArrayList<>();
        String req = "SELECT * FROM eleve WHERE numeroMatricule = '"+numeroMatricule+"'";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                ls.add(new Eleve(rs.getString("numeroMatricule"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public String getNomEleveByNumeroMatricule(String numeroMatricule){
        String nomEleve = new String();
        String req = "SELECT * FROM eleve WHERE numeroMatricule = '"+numeroMatricule+"'";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                nomEleve = rs.getString("nom");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return nomEleve;
    }
    
    public void update(Eleve e, String numeroMatricule){
        String req = "UPDATE eleve SET numeroMatricule = ? , nom = ? WHERE numeroMatricule = ?";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req)) {
            ps.setString(1, e.getNumeroMatricule());
            ps.setString(2, e.getNom());
            ps.setString(3, numeroMatricule);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification réussie!");
        } catch (SQLException error) {
            System.err.println("Modification error : "+error.toString());
        }
    }
    
    public void delete(String numeroMatricule){
        String req = "DELETE FROM eleve WHERE numeroMatricule = ? ";
        try(
            PreparedStatement ps = (PreparedStatement) con.open().prepareStatement(req)) {
            ps.setString(1, numeroMatricule);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (HeadlessException | SQLException e) {
        }
    }
}