/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Livre;
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
public class GestionLivre {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Livre l){
        try {
            String req = "INSERT INTO livre VALUES (?,?,?,?,?,?,?)";
            ps = con.open().prepareStatement(req);
            ps.setString(1, l.getCodeLivre());
            ps.setString(2, l.getLibelleLivre());
            ps.setString(3, l.getAuteurLivre());
            ps.setString(4, l.getNombrePage());
            ps.setInt(5, l.getNombreLivre());
            ps.setString(6, l.getGroupe());
            ps.setString(7, l.getType());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (HeadlessException | SQLException error) {
            System.err.println("Insertion error : "+error.toString());
        }
    }
    
    public List<Livre> list(){
        List<Livre> ls = new ArrayList<>();
        String req = "SELECT livre.codeLivre, livre.libelleLivre, livre.auteurLivre, livre.nombrePage, livre.nombreLivre,groupe.libelleGroupe, type.libelleType, etagere.libelleEtagere FROM livre JOIN groupe ON livre.idGroupe = groupe.idGroupe JOIN type ON livre.idType = type.idType JOIN etagere ON groupe.idEtagere = etagere.idEtagere;";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Livre(rs.getString("codeLivre"), rs.getString("libelleLivre"), rs.getString("auteurLivre"), rs.getString("nombrePage"), rs.getInt("nombreLivre"),rs.getString("libelleGroupe"),rs.getString("libelleType"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Livre> listBySelected(String selectedVariable, String selectedValue){
        List<Livre> ls = new ArrayList<>();
        String req = "SELECT livre.codeLivre, livre.libelleLivre, livre.auteurLivre, livre.nombrePage, livre.nombreLivre,groupe.libelleGroupe, type.libelleType, etagere.libelleEtagere FROM livre JOIN groupe ON livre.idGroupe = groupe.idGroupe JOIN type ON livre.idType = type.idType JOIN etagere ON groupe.idEtagere = etagere.idEtagere WHERE "+selectedVariable+" = '"+selectedValue+"';";

        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Livre(rs.getString("codeLivre"), rs.getString("libelleLivre"), rs.getString("auteurLivre"), rs.getString("nombrePage"), rs.getInt("nombreLivre"),rs.getString("libelleGroupe"),rs.getString("libelleType"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Livre> search(String search){
        List<Livre> ls = new ArrayList<>();
        String req = "SELECT livre.codeLivre, livre.libelleLivre, livre.auteurLivre, livre.nombrePage, livre.nombreLivre, groupe.libelleGroupe, type.libelleType, etagere.libelleEtagere FROM livre JOIN groupe ON livre.idGroupe = groupe.idGroupe JOIN type ON livre.idType = type.idType JOIN etagere ON groupe.idEtagere = etagere.idEtagere WHERE livre.codeLivre like '%"+search+"%' or livre.libelleLivre like '%"+search+"%' or livre.auteurLivre like '%"+search+"%' or livre.nombrePage like '%"+search+"%' or groupe.libelleGroupe like '%"+search+"%' or type.libelleType like '%"+search+"%' or etagere.libelleEtagere like '%"+search+"%' ";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Livre(rs.getString("codeLivre"), rs.getString("libelleLivre"), rs.getString("auteurLivre"), rs.getString("nombrePage"), rs.getInt("nombreLivre"),rs.getString("libelleGroupe"),rs.getString("libelleType"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Livre> listBySelectedSearch(String search){
        List<Livre> ls = new ArrayList<>();
        String req = "SELECT livre.codeLivre, livre.libelleLivre, livre.auteurLivre, livre.nombrePage, livre.nombreLivre, groupe.libelleGroupe, type.libelleType, etagere.libelleEtagere FROM livre JOIN groupe ON livre.idGroupe = groupe.idGroupe JOIN type ON livre.idType = type.idType JOIN etagere ON groupe.idEtagere = etagere.idEtagere WHERE "+search+"; ";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Livre(rs.getString("codeLivre"), rs.getString("libelleLivre"), rs.getString("auteurLivre"), rs.getString("nombrePage"),  rs.getInt("nombreLivre"), rs.getString("libelleGroupe"),rs.getString("libelleType"), rs.getString("libelleEtagere")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public List<Livre> getLivreById(String codeLivre){
        List<Livre> ls = new ArrayList<>();
        String req = "SELECT * FROM livre WHERE codeLivre = '"+codeLivre+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new Livre(rs.getString("codeLivre"), rs.getString("libelleLivre"), rs.getString("auteurLivre"), rs.getString("nombrePage"), rs.getInt("nombreLivre"), rs.getString("idGroupe"),rs.getString("idType")));
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return ls;
    }
    
    public String getLibelleLivreById(String codeLivre){
        String libelleLivre = new String();
        String req = "SELECT * FROM livre WHERE codeLivre = '"+codeLivre+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                libelleLivre = rs.getString("libelleLivre");
            }
        } catch (SQLException e) {
            System.err.println("Err : "+e);
        }
        return libelleLivre;
    }
    
    public int codeLivre(){
        int codeLivre = 0;
        String req = "SELECT COUNT(*) AS id FROM livre";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                codeLivre = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.err.println("Error get id livre : "+error);
        }
        return codeLivre;
    }
    
    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM livre WHERE codeLivre = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setString(1, "liv-"+id);
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
        int nombre = codeLivre()+ 1;
        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }
    
    public int getNombreLivreExistant(String codeLivre){
        int nbLivre = 0;
        String req = "SELECT nombreLivre FROM livre WHERE codeLivre = '"+codeLivre+"'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                nbLivre = rs.getInt("nombreLivre");
            }
        } catch (SQLException e) {
        }
        return nbLivre;
    }
    
    public void update(Livre l, String codeLivre){
//        System.out.println(l.getLibelleLivre()+" "+l.getAuteurLivre()+" "+l.getNombrePage()+" "+l.getGroupe()+" "+l.getType()+" "+codeLivre);
        try {
            String req = "UPDATE livre SET libelleLivre = ?, auteurLivre = ?, nombrePage = ?, nombreLivre = ?, idGroupe = ?, idType = ? WHERE codeLivre = ?";
            ps = con.open().prepareStatement(req);
            ps.setString(1, l.getLibelleLivre());
            ps.setString(2, l.getAuteurLivre());
            ps.setString(3, l.getNombrePage());
            ps.setInt(4, l.getNombreLivre());
            ps.setString(5, l.getGroupe());
            ps.setString(6, l.getType());
            ps.setString(7, codeLivre);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modificatuion réussie!");
        } catch (HeadlessException | SQLException error) {
            System.err.println("Modification error : "+error.toString());
        }
    }
    
    public void delete(String codeLivre){
        try {
            String req = "DELETE FROM livre WHERE codeLivre = ? ";
            ps = con.open().prepareStatement(req);
            ps.setString(1, codeLivre);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie!");
        } catch (Exception e) {
        }
    }
}
