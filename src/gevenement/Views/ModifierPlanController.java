/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Views;

import gevenement.Entities.Plan;
import gevenement.Service.PlanService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ModifierPlanController implements Initializable {

    @FXML
    private DatePicker datedebut;
    @FXML
    private Button confirmer;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField titre;
    @FXML
    private Button r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GestionPlanController gpc = new GestionPlanController();
        Plan pln= gpc.getP();
        titre.setText(pln.getTitre());
         r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionPlan.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(ModifierPlanController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
         confirmer.setOnAction(e->{
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
         Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                  
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Error");
                 
         Date dd=Date.valueOf(datedebut.getValue());
         Date df=Date.valueOf(datefin.getValue());
         String t= titre.getText();
         String ch="";
         Boolean ok =true;
         if(ok==true){
             
             Plan plan = new Plan(dd, df, t);
             plan.setId(pln.getId());
             PlanService ps = new PlanService();
             try {
                 ps.updatePlan(plan);
                 ch+="Modification effectué avec success!\n";
                alert1.setContentText(ch);
                alert1.show();
             } catch (SQLException ex) {
                 Logger.getLogger(AjoutPlanController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else {
             alert.setContentText(ch);
                alert.show();
         }
        });
          
        
        
    
    }    
    
}
