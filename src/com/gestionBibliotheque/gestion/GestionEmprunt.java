/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Emprunter;
import java.sql.Date;
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
public class GestionEmprunt {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public void insert(Emprunter e){
            String req = "INSERT INTO emprunter VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.open().prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.setString(2, e.getCodeEmprunteur());
            ps.setString(3, e.getCodeLivre());  
            ps.setDate(4, e.getDebutEmprunt());
            ps.setDate(5, e.getFinEmprunt());
            ps.setString(6, e.getClasse());
            ps.setString(7, e.getAnneeScolaire());
            ps.setInt(8, e.getNombreLivre());
            ps.setString(9, e.getStatut());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Insertion réussie!");
        } catch (SQLException err) {
            System.out.println("Error insertion : "+err);
        }
    }
    
    public List<Emprunter> list(){
        List<Emprunter> ls = new ArrayList<>();
        String req = "SELECT emprunter.id, IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, livre.libelleLivre, dateEmprunt, finEmprunt, classe, anneeScolaire, emprunter.nombreLivre, statut FROM emprunter LEFT JOIN professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur LEFT JOIN eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule JOIN livre ON emprunter.codeLivre = livre.codeLivre GROUP BY id ASC";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("nomEmprunteur"), rs.getString("libelleLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<String[]> getNombreEmprunterClasse(String anneeScolaire){
        List<String[]> ls = new ArrayList<>();
        String req = "SELECT \n" +
                    "    classe,\n" +
                    "    COUNT(*) as nombre\n" +
                    "FROM \n" +
                    "    `emprunter`\n" +
                    "WHERE \n" +
                    "    anneeScolaire = ?\n" +
                    "GROUP BY \n" +
                    "    classe\n" +
                    "ORDER BY \n" +
                    "    CASE \n" +
                    "        WHEN classe LIKE '12%' THEN 1\n" +
                    "        WHEN classe LIKE '11%' THEN 2\n" +
                    "        WHEN classe LIKE '10%' THEN 3\n" +
                    "        WHEN classe LIKE '9%' THEN 4\n" +
                    "        WHEN classe LIKE '8%' THEN 5\n" +
                    "        WHEN classe LIKE '7%' THEN 6\n" +
                    "        WHEN classe LIKE '6%' THEN 7\n" +
                    "        WHEN classe LIKE '5%' THEN 8\n" +
                    "        WHEN classe LIKE '4%' THEN 9\n" +
                    "        WHEN classe LIKE '3%' THEN 10\n" +
                    "        WHEN classe LIKE '2%' THEN 11\n" +
                    "        WHEN classe LIKE '1L%' THEN 12\n" +
                    "        WHEN classe LIKE '1S%' THEN 13\n" +
                    "        WHEN classe LIKE 'T%' THEN 14\n" +
                    "    END,\n" +
                    "    classe;";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            ps.setString(1, anneeScolaire);  // Set the anneeScolaire parameter
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] donnees = new String[2];
                donnees[0] = rs.getString("classe");
                donnees[1] = rs.getString("nombre");
                ls.add(donnees);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // It's a good practice to print or log the exception for debugging purposes
        }
        return ls;
    }
    
    public List<String[]> getNombreTypeEmprunter(){
        List<String[]> ls = new ArrayList<>();
        String req = "SELECT\n" +
                    "    t.libelleType AS type,\n" +
                    "    COUNT(*) AS nombre\n" +
                    "FROM\n" +
                    "    emprunter e\n" +
                    "INNER JOIN\n" +
                    "    livre l ON e.codeLivre = l.codeLivre\n" +
                    "INNER JOIN\n" +
                    "    type t ON l.idType = t.idType\n" +
                    "WHERE\n" +
                    "    e.codeEmprunteur NOT LIKE 'prof-%'  -- Exclure les emprunts des professeurs\n" +
                    "GROUP BY\n" +
                    "    t.libelleType\n" +
                    "ORDER BY\n" +
                    "    nombre DESC;";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] donnees = new String[2];
                donnees[0] = rs.getString("type");
                donnees[1] = rs.getString("nombre");
                ls.add(donnees);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // It's a good practice to print or log the exception for debugging purposes
        }
        return ls;
    }

    
    public List<String[]> getNombreEmprunterCycle(){
        List<String[]> ls = new ArrayList<>();
        String req = "SELECT CASE WHEN classe LIKE '12%' OR classe LIKE '11%' OR classe LIKE '10%' OR classe LIKE '9%' OR classe LIKE '8%' OR classe LIKE '7%' THEN 'Premier Cycle' WHEN classe LIKE '6%' OR classe LIKE '5%' OR classe LIKE '4%' OR classe LIKE '3%' THEN 'Second Cycle' WHEN classe LIKE '2%' OR classe LIKE '1L%' OR classe LIKE '1S%' OR classe LIKE 'T%' THEN 'Secondaire' END AS groupe, COUNT(*) as nombre FROM `emprunter` GROUP BY groupe";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] donnees = new String[2];
                donnees[0] = rs.getString("groupe");
                donnees[1] = rs.getString("nombre");
                ls.add(donnees);
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<String> getAnneeScolaire(){        
        List<String> ls = new ArrayList<>();
        String req = "SELECT anneeScolaire FROM `emprunter` GROUP BY anneeScolaire";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(rs.getString("anneeScolaire"));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<Emprunter> listByStatus(String status){
        List<Emprunter> ls = new ArrayList<>();
        String req = "SELECT emprunter.id, IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, livre.libelleLivre, dateEmprunt, finEmprunt, classe, anneeScolaire, emprunter.nombreLivre, statut FROM emprunter LEFT JOIN professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur LEFT JOIN eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule JOIN livre ON emprunter.codeLivre = livre.codeLivre WHERE statut ='"+status+"' GROUP BY id ASC";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("nomEmprunteur"), rs.getString("libelleLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<Emprunter> listEnRetard(){
        List<Emprunter> ls = new ArrayList<>();
        String req = "SELECT emprunter.id, IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, livre.libelleLivre, dateEmprunt, finEmprunt, classe, anneeScolaire, emprunter.nombreLivre, statut FROM emprunter LEFT JOIN professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur LEFT JOIN eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule JOIN livre ON emprunter.codeLivre = livre.codeLivre WHERE statut ='En retard' GROUP BY id ASC";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("nomEmprunteur"), rs.getString("libelleLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<Emprunter> listParCycle(String req) {
        List<Emprunter> ls = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("nomEmprunteur"), rs.getString("libelleLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    
    public List<Emprunter> search(String search){
        List<Emprunter> ls = new ArrayList<>();
        String req = "SELECT emprunter.id, IF(emprunter.codeEmprunteur LIKE '%-%', professeur.nomProfesseur, eleve.nom) as nomEmprunteur, livre.libelleLivre, dateEmprunt, finEmprunt, classe, anneeScolaire, emprunter.nombreLivre, statut FROM emprunter LEFT JOIN professeur ON emprunter.codeEmprunteur = professeur.codeProfesseur LEFT JOIN eleve ON emprunter.codeEmprunteur = eleve.numeroMatricule JOIN livre ON emprunter.codeLivre = livre.codeLivre WHERE id LIKE '%"+search+"%' OR professeur.nomProfesseur LIKE '%"+search+"%' OR eleve.nom LIKE '%"+search+"%' OR livre.libelleLivre LIKE '%"+search+"%' OR dateEmprunt LIKE '%"+search+"%' OR finEmprunt LIKE '%"+search+"%' OR classe LIKE '%"+search+"%' OR anneeScolaire LIKE '%"+search+"%' OR emprunter.nombreLivre LIKE '%"+search+"%' OR statut LIKE '%"+search+"%' GROUP BY id ASC";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("nomEmprunteur"), rs.getString("libelleLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public List<Emprunter> getEmpruntById(int id){
        List<Emprunter> ls = new ArrayList<>();
        String req = "SELECT * FROM emprunter where id = ?";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ls.add(new Emprunter(rs.getInt("id"), rs.getString("codeEmprunteur"), rs.getString("codeLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException e) {
        }
        return ls;
    }
    
    public int getNombreLivre(String codeLivre){
        int nombreLivre = 0;
        String req = "SELECT nombreLivre FROM emprunter WHERE codeLivre='"+codeLivre+"' AND (statut='En cours' OR statut='En retard')";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            rs = ps.executeQuery();
            while(rs.next()){
                nombreLivre += rs.getInt("nombreLivre");
            }
        } catch (SQLException e) {
        }
        return nombreLivre;
    }
    
    public void setStatuOk(int id){
        String req = "UPDATE emprunter SET statut ='Ok' WHERE id = ? ";
        try {
            ps = con.open().prepareStatement(req);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Livre bien reçu !");
        } catch (SQLException err) {
            System.out.println("Statut OK error : "+err);
        }
    }
    
    public void setStatutEnRetard(int id){
        String req = "UPDATE emprunter SET statut ='En retard' WHERE id = ? ";
        try {
            ps = con.open().prepareStatement(req);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException err) {
            System.out.println("Statut En retard error : "+err);
        }
    }
    
    public List<Emprunter> getRetard(Date dateNow){
        List<Emprunter> lsRetard = new ArrayList<>();
        String req = "SELECT * FROM emprunter WHERE finEmprunt<? and statut='En cours'";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            ps.setDate(1, dateNow);
            rs = ps.executeQuery();
            while(rs.next()){
                lsRetard.add(new Emprunter(rs.getInt("id"), rs.getString("codeEmprunteur"), rs.getString("codeLivre"), rs.getDate("dateEmprunt"), rs.getDate("finEmprunt"), rs.getString("classe"), rs.getString("anneeScolaire"), rs.getInt("nombreLivre"), rs.getString("statut")));
            }
        } catch (SQLException err) {
            System.err.println("Error get retard : "+err);
        }
        return lsRetard;
    }
    
    public int idEmprunter(){
        int id = 0;
        String req = "SELECT COUNT(*) AS id FROM emprunter";
        try (
            PreparedStatement ps = con.open().prepareStatement(req);
            ResultSet rs = ps.executeQuery()
        ) {
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.err.println("Error get id emprunter : "+error);
        }
        return id;
    }

    public boolean testId(int id){
        boolean bool = false;

        String req = "SELECT * FROM emprunter WHERE id = ?";

        try (PreparedStatement ps = con.open().prepareStatement(req)) {
            ps.setInt(1, id);
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
        int nombre = idEmprunter() + 1;

        while(testId(nombre)){
            nombre++;
        }

        return nombre;
    }

    
    public int testEmprunteur(String emprunteur){
        int response = 0;
        String req = "SELECT COUNT(*) AS response FROM emprunter WHERE codeEmprunteur=? AND (statut='En cours' OR statut='En retard') ";
        try {
            ps = (PreparedStatement) con.open().prepareStatement(req);
            ps.setString(1, emprunteur);
            rs = ps.executeQuery();
            while(rs.next()){
                response = rs.getInt("response");
            }
        } catch (Exception e) {
        }
        return response;
    }
    
    public void update(Emprunter e,int id){
        String req = "UPDATE emprunter SET codeEmprunteur = ?, codeLivre = ?, dateEmprunt = ?, finEmprunt = ?, classe = ?, anneeScolaire = ?, nombreLivre = ?, statut = ? WHERE id = ?";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, e.getCodeEmprunteur());
            ps.setString(2, e.getCodeLivre());  
            ps.setDate(3, e.getDebutEmprunt());
            ps.setDate(4, e.getFinEmprunt());
            ps.setString(5, e.getClasse());
            ps.setString(6, e.getAnneeScolaire());
            ps.setInt(7, e.getNombreLivre());
            ps.setString(8, e.getStatut());
            ps.setInt(9, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificatuion réussie!");
        } catch (SQLException err) {
            System.err.println("Update error : "+err);
        }
    }
    
    public void delete(int id){
        String req = "DELETE FROM emprunter WHERE id = ? ";
        try{ 
            ps = con.open().prepareStatement(req);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
        }
    }
}
