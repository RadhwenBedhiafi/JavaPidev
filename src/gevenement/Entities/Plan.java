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
public class Plan {
    
   private int id;
   private Date datedebut;
   private Date datefin;
   private String titre;

    public Plan(int id, Date datedebut, Date datefin, String titre) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.titre = titre;
    }

    public Plan(Date datedebut, Date datefin, String titre) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.titre = titre;
    }

    public Plan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.datedebut);
        hash = 97 * hash + Objects.hashCode(this.datefin);
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
        final Plan other = (Plan) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.datedebut, other.datedebut)) {
            return false;
        }
        if (!Objects.equals(this.datefin, other.datefin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", titre=" + titre + '}';
    }
    
}
