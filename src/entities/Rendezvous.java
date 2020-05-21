/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import javafx.scene.control.Button;

/**
 *
 * @author COMPUTER
 */
public class Rendezvous {
    
    int id, idR;
    String date;
    String lieu;
    String username;
    String email;
    String heure;
    Button edit;
    

    public Rendezvous(int id, int idR, String date,String heure, String lieu, String username, String email, Button edit) {
        this.id = id;
        this.idR = idR;
        this.date = date;
        this.lieu = lieu;
        this.username = username;
        this.email = email;
        this.edit = edit;
        this.heure = heure;
    }

    public Rendezvous(int idR, String date, String lieu,String heure) {
        this.idR = idR;
        this.date = date;
        this.lieu = lieu;
        this.heure = heure;
    }

    public Rendezvous(String date, String lieu, String heure) {
        this.date = date;
        this.lieu = lieu;
        this.heure = heure;
    }
    

   

    
    public Rendezvous(int idR, String lieu) {
        this.idR = idR;
        this.lieu = lieu;
    }

    public Rendezvous(int idR) {
        this.idR = idR;
    }
    
    
    
    public Rendezvous() {
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", idR=" + idR + ", date=" + date + ", lieu=" + lieu + ", username=" + username + ", email=" + email + '}';
    }
    
    

   

   

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Rendezvous other = (Rendezvous) obj;
        if (this.idR == other.idR) {
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    
    
    
    
}
