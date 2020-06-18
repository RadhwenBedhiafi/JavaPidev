/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
//import java.sql.Date;
/**
 *
 * 
 */
public class Event {
    private int id ; 
    private String sujet ; 
    private String date ;
    private String lieu ; 
    private String image ; 
    private String description ;
    private String titre ;
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
     public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", sujet=" + sujet + ", date=" + date + ", lieu=" + lieu + ", image=" + image + ", description=" + description + ", titre=" + titre +  '}';
    }

    
}
