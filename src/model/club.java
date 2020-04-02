
package model;


public class club {
    private int id;
    private String nomclub;
    private String description;
    private String horraire;
    private int tarif;
    private String image;
    private int capacite;
    
    
public club(){
    super();
}
public club(int id, String nomclub, String description, String horraire, int tarif,String image,int capacite){

super();
this.id = id;
this.nomclub = nomclub;
this.description = description;
this.horraire = horraire;
this.tarif = tarif;
this.image = image;
this.capacite = capacite;

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomclub() {
        return nomclub;
    }

    public void setNomclub(String nomclub) {
        this.nomclub = nomclub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHorraire() {
        return horraire;
    }

    public void setHorraire(String horraire) {
        this.horraire = horraire;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}



