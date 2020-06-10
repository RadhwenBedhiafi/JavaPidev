/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gesenfant.Entities.Enfant;
import gesenfant.Service.ClasseService;
import gesenfant.Service.EnfantService;
import gesenfant.Util.DataBase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
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
public class ListeClasseController implements Initializable {

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
    private TableColumn<Enfant, String> smedical;
    @FXML
    private ComboBox<String> classeinp;
    @FXML
    private Button rechercherbtn;
    @FXML
    private Button gnrtpdf;
    private static ArrayList<Enfant> enf11;
        private static Enfant en;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connexion = DataBase.getInstance().getConnexion();
         try{
            String sql="select libelle from classe";
            Statement ste = connexion.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            classeinp.getItems().addAll(rs.getString("libelle")); 
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
    rechercherbtn.setOnAction(e-> {
        
                if (classeinp.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("") ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please , Search for something!");
                    alert.show();}
                
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    String r=classeinp.getSelectionModel().getSelectedItem().toString();
                    enf2 = (ArrayList<Enfant>) es2.getByClasse(r);
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
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                                enf11= (ArrayList<Enfant>) enf2;


            
        });  
                    

    retour.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GestionEnfant.fxml"));
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ListeClasseController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
   
    
}
}




