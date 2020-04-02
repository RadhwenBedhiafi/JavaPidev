/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionr;

import java.util.Date;

/**
 *
 * @author COMPUTER
 */
class Reclamation {
    int id,etat;
    String description,destinataire;
    Date date;

    public Reclamation(int id, int etat, String description, String destinataire, Date date) {
        this.id = id;
        this.etat = etat;
        this.description = description;
        this.destinataire = destinataire;
        this.date = date;
    }

    public Reclamation(int etat, String description, String destinataire, Date date) {
        this.etat = etat;
        this.description = description;
        this.destinataire = destinataire;
        this.date = date;
    }

    public Reclamation(String description, String destinataire, Date date) {
        this.description = description;
        this.destinataire = destinataire;
        this.date = date;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", etat=" + etat + ", description=" + description + ", destinataire=" + destinataire + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
