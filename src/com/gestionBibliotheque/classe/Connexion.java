/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.classe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author solofonirina
 */
public class Connexion {
    public Connection connexion;
    public Connexion(){}
    
    public Connection open(){
        try {
            connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionBibliotheque", "root", "");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connexion;
    }
    
    public Connection close(){
        try{
            connexion.close();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return connexion;
    }
}
