/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import gesenfant.Entities.Enfant;
import gesenfant.Service.EnfantService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class GestionEnfantController implements Initializable {
    
    @FXML
    private TableView<Enfant> table;
    @FXML
    private TableColumn<Enfant, Integer> id;
    @FXML
    private TableColumn<Enfant, String> nom;
    @FXML
    private TableColumn<Enfant, String> prenom;
    @FXML
    private TableColumn<Enfant, String> sexe;
    @FXML
    private TableColumn<Enfant, Integer> age;
    @FXML
    private TableColumn<Enfant, String> nationalite;
    @FXML
    private TableColumn<Enfant, String> smedical;
    @FXML
    private Button afficher;
    @FXML
    private Button supprimer;
    
    private static Enfant en;
    @FXML
    private TableColumn<Enfant, String> classe;
    @FXML
    private Button retour;
    @FXML
    private Button affecter;
    @FXML
    private TextField rechercher;
    @FXML
    private Button rechercherbtn;
    @FXML
    private RadioButton iden;
    @FXML
    private RadioButton ag;
    @FXML
    private RadioButton iden1;
    @FXML
    private RadioButton ag1;
    @FXML
    private ToggleGroup tri;
    @FXML
    private Button chercher;
    @FXML
    private Button etat;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EnfantService es= new EnfantService(); 
        ArrayList<Enfant> enf= new ArrayList<Enfant>();
        try {
            enf = (ArrayList<Enfant>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs = FXCollections.observableArrayList(enf);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        
        supprimer.setOnAction(e -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)
            en = table.getSelectionModel().getSelectedItem();
                if(en != null)
                {
                en = table.getSelectionModel().getSelectedItem();
                    try {
                        es.deleteEnfant(en);
                         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Suppression effectuée avec succès!");
                           alert1.show();

                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    EnfantService es6= new EnfantService(); 
        ArrayList<Enfant> enf6= new ArrayList<Enfant>();
        try {
            enf6 = (ArrayList<Enfant>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs6 = FXCollections.observableArrayList(enf6);
        table.setItems(obs6);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
                
                }

        });
        affecter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            en = table.getSelectionModel().getSelectedItem();
            if (!(en == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierEnfant.fxml"));
                    affecter.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
 afficher.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
             EnfantService es5= new EnfantService(); 
        ArrayList<Enfant> enf5= new ArrayList<Enfant>();
        try {
            enf5 = (ArrayList<Enfant>) es5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs5 = FXCollections.observableArrayList(enf5);
        table.setItems(obs5);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        rechercher.setText("");
        });
        
        
       rechercherbtn.setOnAction(e-> {
                if (rechercher.getText().equalsIgnoreCase("") ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please , Search for something!");
                    alert.show();}
                
                EnfantService es1= new EnfantService();
                ArrayList<Enfant> enf1= new ArrayList<Enfant>();
                try {
                    String r=rechercher.getText();
                    int i=Integer.parseInt(r);
                    enf1 = (ArrayList<Enfant>) es1.getById(i);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs1 = FXCollections.observableArrayList(enf1);
                table.setItems(obs1);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
       iden.setOnAction(e-> {
               
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    enf2 = (ArrayList<Enfant>) es2.readAllID();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs2 = FXCollections.observableArrayList(enf2);
                table.setItems(obs2);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
        iden1.setOnAction(e-> {
               
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    enf2 = (ArrayList<Enfant>) es2.readAllIDD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs2 = FXCollections.observableArrayList(enf2);
                table.setItems(obs2);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  ag.setOnAction(e-> {
               
                EnfantService es3= new EnfantService();
                ArrayList<Enfant> enf3= new ArrayList<Enfant>();
                try {
                    enf3 = (ArrayList<Enfant>) es3.readAllAge();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs3 = FXCollections.observableArrayList(enf3);
                table.setItems(obs3);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  ag1.setOnAction(e-> {
               
                EnfantService es4= new EnfantService();
                ArrayList<Enfant> enf4= new ArrayList<Enfant>();
                try {
                    enf4 = (ArrayList<Enfant>) es4.readAllAgeD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs4 = FXCollections.observableArrayList(enf4);
                table.setItems(obs4);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
       retour.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
       chercher.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("ListeClasse.fxml"));
                chercher.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
       etat.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("EtatMedical.fxml"));
                etat.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
  

    }    
    public Enfant getE() {
        return en;
    }
}

