/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Connexion;
import com.gestionBibliotheque.classe.Utilisateur;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author solofonirina
 */
public class GestionUtilisateur {
    public PreparedStatement ps;
    public ResultSet rs;
    Connexion con = new Connexion();
    
    public boolean login(Utilisateur u){
        int compte = 0;
        boolean response = false;
        String req = "SELECT count(*) as compte FROM utilisateur WHERE user = ? AND password = ?";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, u.getUser());
            ps.setString(2, u.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {                
                compte = rs.getInt("compte");
            }
        } catch (SQLException e) {
            System.err.println("Error login : "+e);
        }
        if(compte == 0){
            response = false;
        }else{
            response = true;
        }
        return response;
    }
    
    public void update(Utilisateur u, String motDePasseActuel){
        String req = "UPDATE utilisateur SET password = ? WHERE user = ? AND password = ?";
        try {
            ps = con.open().prepareStatement(req);
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getUser());
            ps.setString(3, motDePasseActuel);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modification mot de passe réussie!");
        } catch (HeadlessException | SQLException e) {
            System.err.println("Modification mot de passe error : "+e);
        }
    }
}
