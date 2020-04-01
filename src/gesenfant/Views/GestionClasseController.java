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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class GestionClasseController implements Initializable {

    @FXML
    private TableView<Classe> table;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;
    
    private static Classe cl;
    @FXML
    private TableColumn<Classe, Integer> id;
    @FXML
    private TableColumn<Classe, String> bloc;
    @FXML
    private TableColumn<Classe, String> libelle;
    @FXML
    private TableColumn<Classe, Integer> tmax;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ClasseService cs= new ClasseService(); 
        ArrayList<Classe> cla= new ArrayList<Classe>();
        try {
            cla = (ArrayList<Classe>) cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Classe> obs = FXCollections.observableArrayList(cla);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
        
        
        supprimer.setOnAction(e -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)
            cl = table.getSelectionModel().getSelectedItem();
                if(cl != null)
                {
                cl = table.getSelectionModel().getSelectedItem();
                    try {
                        cs.deleteClasse(cl);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }

        });
        modifier.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            cl = table.getSelectionModel().getSelectedItem();
            if (!(cl == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierClasse.fxml"));
                    modifier.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ajouter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("AjouterClasse.fxml"));
                ajouter.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterClasseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
 afficher.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
            cl = table.getSelectionModel().getSelectedItem();
            if (!(cl== null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("GestionClasse.fxml"));
                    ajouter.getScene().setRoot(root);

                } catch (IOException ex) {
                    Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        retour.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
             retour.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             }); 

    }    
    public Classe getC() {
        return cl;
    }
}
