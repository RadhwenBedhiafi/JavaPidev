/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataBase.dbconnectioninfo;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.club;
import services.crudclub;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class ClubDController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML private TableView<club> club;
    @FXML private TableColumn<club, Integer> id;
    @FXML private TableColumn<club, String> nomclub;
    @FXML private TableColumn<club, String> description;
    @FXML private TableColumn<club, String> horraire;
    @FXML private TableColumn<club, Integer> tarif;
    @FXML private TableColumn<club, String> image;
    @FXML private TableColumn<club, Integer> capacite;
    @FXML private TextField nomclubx;
    @FXML private TextArea descriptionx;
    @FXML private ComboBox<String> horrairex;
    private ObservableList<String> list = FXCollections.observableArrayList("matin","soir","nuit");
    @FXML private TextField tarifx;
    @FXML private TextField imagex;
    @FXML private TextField capacitex;
   public ObservableList<club> data = FXCollections.observableArrayList();
   @FXML
    private AnchorPane rootPane;
   @FXML
    private TextField search;
   @FXML
    private Button refresh;
    
    club Aem;
   
   

   
   
   public void updatedeleteclub() {
       try{
           Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateDel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Update&Delete Club");
         stage.show();
        } catch(Exception e){
        e.printStackTrace();
        }
       
   }
    
   public void vider(){
    nomclubx.clear();
    descriptionx.clear();
    horrairex.setValue(null);
    imagex.clear();
    tarifx.clear();
    capacitex.clear();
    }
   public void ajoutclub(ActionEvent event) throws IOException{
        String nomclub = nomclubx.getText();
        String description = descriptionx.getText();
        String image = imagex.getText();
        club clb = new club();
        clb.setNomclub(nomclub);
        clb.setDescription(description);
        clb.setHorraire(horrairex.getValue());
        clb.setTarif(Integer.parseInt(tarifx.getText()));
        clb.setImage(image);
        clb.setCapacite(Integer.parseInt(capacitex.getText()));
        
        int status = crudclub.save(clb);
        if (status>0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club bien ajouter! ");
        alert.showAndWait();
       
        
        
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non ajouter! ");
        alert.showAndWait();
        }
       
        
        
    }
   @FXML
    private void refresh() {
       data.clear();
       try{
   Connection ds=dbconnectioninfo.getInstance().getCnx();
   String sql ="select * from club";
   PreparedStatement stat = ds.prepareStatement(sql);
   ResultSet rs = stat.executeQuery();
   while(rs.next()){
   data.add(new club(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7)));
    club.setItems(data);
    chercher();
   }
  
   }catch (Exception e2){
   e2.printStackTrace();
   }
    }
   @FXML
    public void chercher() {
        FilteredList<club> filteredData = new FilteredList<>(data, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (aff.getNomclub().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<club> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(club.comparatorProperty());
        club.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horrairex.setItems(list);
  crudclub cs = new crudclub();
        data = cs.afficherclub();
   id.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
   nomclub.setCellValueFactory(new PropertyValueFactory<club, String>("nomclub"));
   description.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
   horraire.setCellValueFactory(new PropertyValueFactory<club, String>("horraire"));
   tarif.setCellValueFactory(new PropertyValueFactory<club, Integer>("tarif"));
   image.setCellValueFactory(new PropertyValueFactory<club, String>("image"));
   capacite.setCellValueFactory(new PropertyValueFactory<club, Integer>("capacite"));
   club.setItems(data);
    chercher();
      
   
    }    
    
   
    
}
