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
import javafx.collections.ObservableList;
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
public class ModifierEvenementController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<String> titre;
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
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
        GestionEvenementController gec = new GestionEvenementController();
        Evenement env= gec.getE();
        sujet.setText(env.getSujet());
        lieu.setText(env.getLieu());
        description.setText(env.getDescription());
        titre.setValue(env.getTitre());
        PlanService ps = new PlanService();
        EvenementService es1 = new EvenementService();

        

        
        
         r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
         confirmer.setOnAction(e->{
             //Local(String nom, String adresse, float prix,float surface,int capacite)
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Error");
             String s= sujet.getText();
             Date d=Date.valueOf(date.getValue());
             String l= lieu.getText();
             String des= description.getText();
             String t = titre.getSelectionModel().getSelectedItem().toString();
             String ch="";
             Boolean ok =true;
             if(ok==true){
                 
                 Evenement evenement = new Evenement(s, d, l, des,t);
                 evenement.setId(env.getId());
                 EvenementService es = new EvenementService();
                 try {
                     es.updateEvenement(evenement);
                     ch+="Operation effectuée avec success!\n";
                     alert1.setContentText(ch);
                     alert1.show();
                 } catch (SQLException ex) {
                     Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             else {
                 alert.setContentText(ch);
                 alert.show();
             }
        });
      
    }    
    
}
