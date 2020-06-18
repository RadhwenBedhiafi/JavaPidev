/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Evenement {
    private int id;
    private String sujet;
    private Date date;
    private String lieu;
    private String description;
    private String titre;

    public Evenement(int id, String sujet, Date date, String lieu, String description, String titre) {
        this.id = id;
        this.sujet = sujet;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.titre = titre;
    }

    public Evenement(String sujet, Date date, String lieu, String description, String titre) {
        this.sujet = sujet;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.titre = titre;
    }

  
    public Evenement() {
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.sujet);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.lieu);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.titre);
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", sujet=" + sujet + ", date=" + date + ", lieu=" + lieu + ", description=" + description + ", titre=" + titre + '}';
    }
    
    
}
