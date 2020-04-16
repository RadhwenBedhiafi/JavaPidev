/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;

import gesenfant.Entities.Enfant;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class EtatMedicalController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TableView<Enfant> table;
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
    private TableColumn<Enfant, String> classe;
    @FXML
    private RadioButton bien;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton malade;
    @FXML
    private TableColumn<Enfant,String> smedical;

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
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
    bien.setOnAction(e-> {
               
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    enf2 = (ArrayList<Enfant>) es2.readAllEtatB();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs2 = FXCollections.observableArrayList(enf2);
                table.setItems(obs2);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
    malade.setOnAction(e-> {
               
                EnfantService es3= new EnfantService();
                ArrayList<Enfant> enf3= new ArrayList<Enfant>();
                try {
                    enf3 = (ArrayList<Enfant>) es3.readAllEtatM();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs3 = FXCollections.observableArrayList(enf3);
                table.setItems(obs3);
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
                root = FXMLLoader.load(getClass().getResource("GestionEnfant.fxml"));
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EtatMedicalController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
    }   
}
