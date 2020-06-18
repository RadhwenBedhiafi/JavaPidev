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
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GestionPlanController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button r;
    @FXML
    private Button afficher;
    @FXML
    private TableView<Plan> table;
    @FXML
    private TableColumn<Plan, Date> datedebut;
    @FXML
    private TableColumn<Plan, Date> datefin;
    @FXML
    private TableColumn<Plan, String> titre;
        private static Plan pl;
        


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
PlanService ps= new PlanService(); 
        ArrayList<Plan> pln= new ArrayList<Plan>();
        try {
            pln = (ArrayList<Plan>) ps.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Plan> obs = FXCollections.observableArrayList(pln);
        table.setItems(obs);
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        modifier.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            pl = table.getSelectionModel().getSelectedItem();
            if (!(pl == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierPlan.fxml"));
                    modifier.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionPlanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("AcceuilGE.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(ModifierPlanController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
        supprimer.setOnAction(e -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)
            pl = table.getSelectionModel().getSelectedItem();
                if(pl != null)
                {
                pl = table.getSelectionModel().getSelectedItem();
                    try {
                        ps.deletePlan(pl);
                         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Suppression effectuée avec succès!");
                           alert1.show();

                    } catch (SQLException ex) {
                        Logger.getLogger(GestionPlanController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    PlanService ps6= new PlanService(); 
        ArrayList<Plan> pln6= new ArrayList<Plan>();
        try {
            pln6 = (ArrayList<Plan>) ps6.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Plan> obs6 = FXCollections.observableArrayList(pln6);
        table.setItems(obs6);
         datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                
                }

        });
       ajouter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("AjoutPlan.fxml"));
                ajouter.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjoutPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
 afficher.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
             PlanService ps5= new PlanService(); 
        ArrayList<Plan> pln5= new ArrayList<Plan>();
        try {
            pln5 = (ArrayList<Plan>) ps5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Plan> obs5 = FXCollections.observableArrayList(pln5);
        table.setItems(obs5);
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        });    }
    
 public Plan getP() {
        return pl;   
}
 
    }