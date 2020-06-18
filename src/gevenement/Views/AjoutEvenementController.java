/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Views;

import gevenement.Entities.Evenement;
import gevenement.Service.EvenementService;
import gevenement.Service.PlanService;
import gevenement.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private ComboBox<String> titre;
    @FXML
    private TextField description;
    @FXML
    private TextField lieu;
    @FXML
    private TextField sujet;
    @FXML
    private DatePicker date;
    @FXML
    private Button confirmer;
    @FXML
    private Button r;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
       Connection connexion = DataBase.getInstance().getConnexion();
         try{
            String sql="select titre from plan_event";
            Statement ste = connexion.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            titre.getItems().addAll(rs.getString("titre")); 
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(PlanService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        confirmer.setOnAction(e->{
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
        Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Success");
                
                 
         String s= sujet.getText();
         Date d=Date.valueOf(date.getValue());
         String l= lieu.getText();
         String des= description.getText();
         String t = titre.getSelectionModel().getSelectedItem().toString();
         String ch="";
         Boolean ok =true;
         if(ok==true && !s.equals("") && !l.equals("") && !des.equals("")){
             Evenement evenement = new Evenement(s, d, l,des,t);
             EvenementService es = new EvenementService();
             try {
                es.ajouterEvenement(evenement);
                ch+="Ajout effectué avec success!\n";
                alert1.setContentText(ch);
                alert1.show();
             
        es.sendmail();
             } catch (SQLException ex) {
                 Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else {
             ch="Veuillez remplir tout les champs !!!";
             alert.setContentText(ch);
             alert.show();
         }
        });
        r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionPlan.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(ModifierPlanController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
       
   }
    

 
    
}
