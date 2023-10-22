/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.classe;

/**
 *
 * @author solofonirina
 */
public class Type {
    private String idType;
    private String libelleType;

    public Type(String libelleType) {
        this.libelleType = libelleType;
    }

    public Type(String idType, String libelleType) {
        this.idType = idType;
        this.libelleType = libelleType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getLibelleType() {
        return libelleType;
    }

    public void setLibelleType(String libelleType) {
        this.libelleType = libelleType;
    }

}
