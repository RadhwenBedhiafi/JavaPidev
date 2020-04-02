/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.club;
import model.demandeadhesion;

import services.cruddemande;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class DemandelistController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML private TableView<demandeadhesion> dem;
    @FXML private TableColumn<demandeadhesion, Integer> id;
    @FXML private TableColumn<demandeadhesion, String>  dureInsc;
    @FXML private TableColumn<demandeadhesion, String>  modePayment;
    @FXML private TableColumn<demandeadhesion, Integer>  etatDemande;
    @FXML private TableColumn<demandeadhesion, Integer>  numParent;
    @FXML private TableColumn<demandeadhesion, String> ancienClub;
    @FXML private TableColumn<demandeadhesion, String> objectif;
    @FXML private TableColumn<demandeadhesion, Integer> clubId;
    public ObservableList<demandeadhesion> data2 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cruddemande cs = new cruddemande();
        data2 = cs.afficherdemande();
   id.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("id"));
   dureInsc.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("dureInsc"));
   modePayment.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("modePayment"));
    etatDemande.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("etatDemande"));
    numParent.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("numParent"));
   ancienClub.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("ancienClub"));
   objectif.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("objectif"));
   clubId.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("clubId"));
   dem.setItems(data2);
   
        // TODO
    }    
    
}
