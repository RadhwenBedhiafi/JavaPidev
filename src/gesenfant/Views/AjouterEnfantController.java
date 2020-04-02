/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;
import gesenfant.Entities.Enfant;
import gesenfant.Service.ClasseService;
import gesenfant.Service.EnfantService;
import gesenfant.Util.DataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class AjouterEnfantController implements Initializable {
    

    @FXML
    private TextField nom;
    @FXML
    private TextField nationalite;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private TextField prenom;
    @FXML
    private TextField smedical;
    @FXML
    private Button v;
    @FXML
    private Button r;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane bg;
    @FXML
    public ComboBox<String>classe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connexion = DataBase.getInstance().getConnexion();
         try{
            String sql="select libelle from classe";
            Statement ste = connexion.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            classe.getItems().addAll(rs.getString("libelle")); 
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            ObservableList<String> sexeList= FXCollections.observableArrayList("Homme","Femme");
            sexe.setItems(sexeList);
            sexe.getSelectionModel().select("Sexe");
            GestionEnfantController gec = new GestionEnfantController();
            Enfant enf= gec.getE();
            nom.setText(enf.getNom());
            prenom.setText(enf.getPrenom());
            sexe.setValue(enf.getSexe());
            age.setText(String.valueOf(enf.getAge()));
            nationalite.setText(enf.getNationalite());
            smedical.setText(enf.getSmedical());
            classe.setValue(enf.getClasse());
          
            
            



retour.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionEnfant.fxml"));
             retour.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             }); 
        v.setOnAction(e->{
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
        Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Success");
                
                 
         String n= nom.getText();
         String p=prenom.getText();
         String a= age.getText();
         String s = sexe.getSelectionModel().getSelectedItem().toString();
         String nat= nationalite.getText();
         String m= smedical.getText();
         String c = classe.getSelectionModel().getSelectedItem().toString();

         String ch="";
         Boolean ok =true;
         int pr = -1;
         int test=Integer.parseInt(a);
         if(!n.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un nom valide!";
             ok=false;
         }
         if(!p.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un prenom valide!";
             ok=false;
         }
         if(!nat.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer une nationalité valide!";
             ok=false;
         }
         if(!m.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un status medical valide!";
             ok=false;
         }
         if(!a.matches("\\d+")||a.length()==0||test>5||test==0)
         {
             ch += "Vous devez entrer un age valide!\n";
                ok=false;
         }
         else pr = Integer.parseInt(a);
         if(ok==true){
             Enfant enfant = new Enfant(n, p, s,pr,nat,m,c );
             EnfantService es = new EnfantService();
             try {
                es.ajouterEnfant(enfant);
                ch+="Ajout effectué avec success!\n";
                alert1.setContentText(ch);
                alert1.show();
                 
             } catch (SQLException ex) {
                 Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else {
             alert.setContentText(ch);
             alert.show();
         }
        });
        r.setOnAction(e->{
          nom.setText("");
          prenom.setText("");
          age.setText("");
          nationalite.setText("");
          smedical.setText("");
        });  
    }           
   
    
}
