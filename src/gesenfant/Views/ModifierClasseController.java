/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;

import gesenfant.Entities.Classe;
import gesenfant.Entities.Enfant;
import gesenfant.Service.ClasseService;
import gesenfant.Service.EnfantService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class ModifierClasseController implements Initializable {

    @FXML
    private TextField taillemax;
    @FXML
    private TextField libelle;
    @FXML
    private TextField bloc;
    @FXML
    private Button retour;
    @FXML
    private Button r;
    @FXML
    private Button v;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    GestionClasseController gcc = new GestionClasseController();
        Classe cla= gcc.getC();
        bloc.setText(cla.getBloc());
        libelle.setText(cla.getLibelle());
        taillemax.setText(String.valueOf(cla.getTaillemax()));
        System.out.println("bla"+cla.getId());
         retour.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionClasse.fxml"));
             retour.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(ModifierClasseController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
         v.setOnAction(e->{
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
         Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                  
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Error");
                 
         String b= bloc.getText();
         String l=libelle.getText();
         String t= taillemax.getText();
         String ch="";
         Boolean ok =true;
         int pr = -1;
         int test=Integer.parseInt(t);
         if(!b.matches("^[A-Za-z0-9]+$+")){
             ch+="Vous devez entrer un bloc valide!";
             ok=false;
         }
         if(!l.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer une libelle valide!";
             ok=false;
         }
         if(!t.matches("\\d+")||t.length()==0||test==0)
         {
             ch += "Vous devez entrer une taille valide!\n";
                ok=false;
         }
         else pr = Integer.parseInt(t);
         if(ok==true){
             
             Classe classe = new Classe(b, l, pr);
             classe.setId(cla.getId());
             ClasseService cs = new ClasseService();
             try {
                 cs.updateClasse(classe);
                 ch+="Modification effectué avec success!\n";
                alert1.setContentText(ch);
                alert1.show();
             } catch (SQLException ex) {
                 Logger.getLogger(AjouterClasseController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else {
             alert.setContentText(ch);
                alert.show();
         }
        });
        r.setOnAction(e->{
          bloc.setText("");
          libelle.setText("");
          taillemax.setText("");
        });  
        }
        
    
}
