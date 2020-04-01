/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant;

import gesenfant.Entities.Classe;
import gesenfant.Entities.Enfant;
import gesenfant.Service.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author elbaz
 */
public class GesEnfant { 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Enfant e = new Enfant("Amine", "Bedhiaif","Homme",26,"Tunisien","Bien");
        EnfantService es = new EnfantService();
        es.ajouterEnfant(e);
        System.out.println(es.getAllEnfants());
    }
    
}
