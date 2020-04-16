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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private TableColumn<Classe, String> bloc;
    @FXML
    private TableColumn<Classe, String> libelle;
    @FXML
    private TableColumn<Classe, Integer> tmax;
    @FXML
    private Button retour;
    @FXML
    private TextField rechercher;
    @FXML
    private Button rechercherbtn;
    @FXML
    private RadioButton iden;
    @FXML
    private ToggleGroup tri;
    @FXML
    private RadioButton ag;
    @FXML
    private RadioButton iden1;
    @FXML
    private RadioButton ag1;

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
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Suppression effectuée avec succès!");
                           alert1.show();
                           
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                ClasseService cs5= new ClasseService(); 
        ArrayList<Classe> cla5= new ArrayList<Classe>();
        try {
            cla5 = (ArrayList<Classe>) cs5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Classe> obs5 = FXCollections.observableArrayList(cla5);
        table.setItems(obs5);
        bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
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
          ClasseService cs5= new ClasseService(); 
        ArrayList<Classe> cla5= new ArrayList<Classe>();
        try {
            cla5 = (ArrayList<Classe>) cs5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Classe> obs5 = FXCollections.observableArrayList(cla5);
        table.setItems(obs5);
        bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
        rechercher.setText("");
        
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
        rechercherbtn.setOnAction(e-> {
                if (rechercher.getText().equalsIgnoreCase("") ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please , Search for something!");
                    alert.show();}
                
                ClasseService cs1= new ClasseService();
                ArrayList<Classe> cla1= new ArrayList<Classe>();
                try {
                    String r=rechercher.getText();
                    int i=Integer.parseInt(r);
                    cla1 = (ArrayList<Classe>) cs1.getById(i);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Classe> obs1 = FXCollections.observableArrayList(cla1);
                table.setItems(obs1);
                bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
                libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
                
        });  
        iden.setOnAction(e-> {
               
                 ClasseService cs2= new ClasseService();
                ArrayList<Classe> cla2= new ArrayList<Classe>();
                try {
                   
                   
                    cla2 = (ArrayList<Classe>) cs2.readAllIDA();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Classe> obs2 = FXCollections.observableArrayList(cla2);
                table.setItems(obs2);
                bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
                libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
            
        });  
        iden1.setOnAction(e-> {
               
                 ClasseService cs2= new ClasseService();
                ArrayList<Classe> cla2= new ArrayList<Classe>();
                try {
                   
                   
                    cla2 = (ArrayList<Classe>) cs2.readAllIDD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Classe> obs2 = FXCollections.observableArrayList(cla2);
                table.setItems(obs2);
                bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
                libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
            
        });  ag.setOnAction(e-> {
               
                 ClasseService cs2= new ClasseService();
                ArrayList<Classe> cla2= new ArrayList<Classe>();
                try {
                   
                   
                    cla2 = (ArrayList<Classe>) cs2.readAllA();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Classe> obs2 = FXCollections.observableArrayList(cla2);
                table.setItems(obs2);
                bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
                libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
            
        });  ag1.setOnAction(e-> {
               
                ClasseService cs2= new ClasseService();
                ArrayList<Classe> cla2= new ArrayList<Classe>();
                try {
                   
                   
                    cla2 = (ArrayList<Classe>) cs2.readAllD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Classe> obs2 = FXCollections.observableArrayList(cla2);
                table.setItems(obs2);
                bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
                libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tmax.setCellValueFactory(new PropertyValueFactory<>("taillemax"));
    });
    } 
    public Classe getC() {
        return cl;
    }
}

