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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button afficher;
    @FXML
    private Button supprimer;
    
    private static Enfant en;
    @FXML
    private TableColumn<?, ?> classe;
    @FXML
    private Button retour;
    

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
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }

        });
        modifier.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            en = table.getSelectionModel().getSelectedItem();
            if (!(en == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierEnfant.fxml"));
                    modifier.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ajouter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("AjouterEnfant.fxml"));
                ajouter.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
 afficher.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
            en = table.getSelectionModel().getSelectedItem();
            if (!(en== null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("GestionEnfant.fxml"));
                    ajouter.getScene().setRoot(root);

                } catch (IOException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
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
    public Enfant getE() {
        return en;
    }
}

