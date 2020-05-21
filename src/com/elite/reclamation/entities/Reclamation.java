/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.entities;

import java.util.Date;



/**
 *
 * @author COMPUTER
 */
public class Reclamation {
    int id;
    String destinataire,description;
    int idUser;
    String username;
    String email;
    Date date;
    int etat;

    public Reclamation(int id, String destinataire, String description, int idUser, String username, String email, Date date, int etat) {
        this.id = id;
        this.destinataire = destinataire;
        this.description = description;
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation(String destinataire, String description) {
        this.destinataire = destinataire;
        this.description = description;
    }

    public Reclamation() {
    }

    public Reclamation(String destinataire, String description, int etat) {
        this.destinataire = destinataire;
        this.description = description;
        this.etat = etat;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", destinataire=" + destinataire + ", description=" + description + ", idUser=" + idUser + ", username=" + username + ", email=" + email + ", date=" + date + ", etat=" + etat + '}';
    }
    
    
    
    
    
}
