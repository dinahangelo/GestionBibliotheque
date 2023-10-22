/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Type;
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
public class GestionType {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Type t){
        try {
            String req = "INSERT INTO type (idType, libelleType) VALUES (?,?)";
            System.out.println(t.getLibelleType());
            ps = con.open().prepareStatement(req);
            ps.setString(1, t.getIdType());
            ps.setString(2, t.getLibelleType());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (HeadlessException | SQLException error) {
            System.err.println("Insertion error : "+error.toString());
        }
    }
    
    public List<Type> list(){
        List<Type> ls = new ArrayList<>();
        String req = "SELECT * FROM type";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Type(rs.getString("idType"), rs.getString("libelleType")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Type> search(String search){
        List<Type> ls = new ArrayList<>();
        String req = "SELECT * FROM type WHERE idType like '%"+search+"%' or libelleType like '%"+search+"%'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Type(rs.getString("idType"), rs.getString("libelleType")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public int idType(){
        int idType = 0;
        String req = "SELECT COUNT(*) AS id FROM type";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                idType = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.err.println("Error get id type : "+error);
        }
        return idType;
    }
    
    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM type WHERE idType = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, "tp-"+id);
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
        int nombre = idType()+ 1;
        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }
    
    public String getIdTypeByLibelle(String libelleType){
        String idType = new String();
        String req = "SELECT idType FROM type WHERE libelleType = '"+libelleType+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                idType = rs.getString("idType");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return idType;
    }
    
    public String getTypeById(String idType){
        String libelle = new String();
        String req = "SELECT * FROM type WHERE idType = '"+idType+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                libelle = rs.getString("libelleType");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return libelle;
    }
    
    public void update(Type t, String idType){
        try {
            String req = "UPDATE type SET libelleType = ? WHERE idType = ?";
            ps = con.open().prepareStatement(req);
            ps.setString(1, t.getLibelleType());
            ps.setString(2, idType);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification réussie!");
        } catch (SQLException error) {
            System.err.println("Modification error : "+error.toString());
        }
    }
    
    public void delete(String idType){
        try {
            String req = "DELETE FROM type WHERE idType = ? ";
            ps = con.open().prepareStatement(req);
            ps.setString(1, idType);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (Exception e) {
        }
    }
}
