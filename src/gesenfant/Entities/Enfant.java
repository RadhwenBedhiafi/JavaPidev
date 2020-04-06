/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Entities;

import java.util.Objects;

/**
 *
 * @author elbaz
 */
public class Enfant {
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private int age;
    private String nationalite;
    private String smedical;
    private String classe;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Enfant(String nom, String prenom, String sexe, int age, String nationalite, String smedical, String classe, int idUser) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.classe = classe;
        this.idUser = idUser;
    }

    public Enfant(String nom, String prenom, String sexe, int age, String nationalite, String smedical, int idUser) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.idUser = idUser;
    }

    public Enfant(int id, String nom, String prenom, String sexe, int age, String nationalite, String smedical, int idUser) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.idUser = idUser;
    }

    public Enfant(int id, String nom, String prenom, String sexe, int age, String nationalite, String smedical, String classe, int idUser) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.classe = classe;
        this.idUser = idUser;
    }

    public Enfant(String n, String p, boolean s, int pr, String nat, String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public Enfant(String nom, String prenom, String sexe, int age, String nationalite, String smedical, String classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.classe = classe;
    }

    public Enfant(int id, String nom, String prenom, String sexe, int age, String nationalite, String smedical, String classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getSmedical() {
        return smedical;
    }

    public void setSmedical(String smedical) {
        this.smedical = smedical;
    }

    public Enfant(int id, String nom, String prenom, String sexe, int age, String nationalite, String smedical) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
    }

    public Enfant() {
    }

    public Enfant(String nom, String prenom, String sexe, int age, String nationalite, String smedical) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.nationalite = nationalite;
        this.smedical = smedical;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.prenom);
        hash = 23 * hash + Objects.hashCode(this.sexe);
        hash = 23 * hash + this.age;
        hash = 23 * hash + Objects.hashCode(this.nationalite);
        hash = 23 * hash + Objects.hashCode(this.smedical);
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
        final Enfant other = (Enfant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.nationalite, other.nationalite)) {
            return false;
        }
        if (!Objects.equals(this.smedical, other.smedical)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", age=" + age + ", nationalite=" + nationalite + ", smedical=" + smedical + '}';
    }
    
}
