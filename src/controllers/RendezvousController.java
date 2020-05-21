/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataBase.DataSource;
import static controllers.ReclamationController.oc;
import entities.Mailing;
import entities.Reclamation;
import entities.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;
import services.ReclamationService;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class RendezvousController implements Initializable {

    @FXML
    private TableView<Rendezvous> tablerv;
    @FXML
    private TableColumn<Rendezvous, Integer> id_col;
    @FXML
    private TableColumn<Rendezvous, Integer> idR_col;
    @FXML
    private TableColumn<Rendezvous, String> date_col;
    @FXML
    private TableColumn<Rendezvous, String> lieu_col;
    
    public static  ObservableList<Rendezvous> oc = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> typeRecherche;
    
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "date", "lieu", "username", "email","heure");
    @FXML
    private TextField RechercheTextField;
    @FXML
    private TableColumn<Rendezvous, String> email_col;
    @FXML
    private TableColumn<Rendezvous, String> username_col;
    @FXML
    private TableColumn<Rendezvous,Button> edit_col;
    @FXML
    private TableColumn<Rendezvous, String> heure_col;
    @FXML
    private Button menu;
    @FXML
    private AnchorPane parentContainer;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idR_col.setVisible(false);
        id_col.setVisible(false);
        RendezvousService cs = new RendezvousService();
            oc = cs.afficherRendezvous();
              typeRecherche.setItems(listeTypeRecherche);
            typeRecherche.setValue("Tout");
             TableColumn actionCol = new TableColumn("Action");
            tablerv.getColumns().add(actionCol);
            actionCol.setCellValueFactory(new PropertyValueFactory<>("button"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            idR_col.setCellValueFactory(new PropertyValueFactory<>("idR"));
            date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
            edit_col.setCellValueFactory(new PropertyValueFactory<>("edit"));
            heure_col.setCellValueFactory(new PropertyValueFactory<>("heure"));
            
            tablerv.setItems(oc);
            editableCols();
            
                  
            
            //actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
Callback<TableColumn<Rendezvous, String>, TableCell<Rendezvous, String>> cellFactory
                = //
                new Callback<TableColumn<Rendezvous, String>, TableCell<Rendezvous, String>>() {
            @Override
            public TableCell call(final TableColumn<Rendezvous, String> param) {
                final TableCell<Rendezvous, String> cell = new TableCell<Rendezvous, String>() {

                    final Button btn = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {

//                                Reclamation person = getTableView().getItems().get(getIndex());
////                                System.out.println(person.getId()
//                                        + "   " + person.getNom());
                                if (getTableView().getItems().get(getIndex()) == null) {
                                    Notifications n = Notifications.create()
                                            .title("Erreur")
                                            .text("Choix invalide")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showWarning();
                                } else {
                                    //List<Reclamation> listReclamation = TableViewReclamation.getSelectionModel().getSelectedItems();

                                    Rendezvous rendezvousSelectione = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer ce rendezvous");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        new RendezvousService().supprimerRendezvous(rendezvousSelectione.getId());
                                        
                                       // Mailing.send(rendezvousSelectione.getEmail(), "<b> Reclamation Refusée </b>", "Monsieur " 
                                              //  + rendezvousSelectione.getUsername() +"\n Nous sommes désolés, votre reclamatation a été refusée", "najibaamri23@gmail.com", "GUESSWHATISITT");
//                                        System.out.println("Reclamation supprimée" + reclamationSelectione.getId());
                                        
                                    }
                                }
                                
                                list();
                                             
                                

                            });
                             
//                            btn.getStyleClass().removeAll("addBobOk, focus"); 
//                            btn.getStyleClass().add("addBobOk");
//                             btn.setStyle("-fx-background-color: #00ff00");
                            setGraphic(btn);
                            setText(null);
//                            //  System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);


    }    

    @FXML
    private void list() {
         ArrayList arrayList = null;
        RendezvousService produitService = new RendezvousService();
        

     

            //arrayList = (ArrayList) produitService.afficherReclamation();
        arrayList = (ArrayList) produitService.rechercheRendezvous(typeRecherche.getValue().toString(), RechercheTextField.getText());
        
 
        ObservableList oc = FXCollections.observableArrayList(arrayList);
        tablerv.setItems(oc);
    }
    
     public void refreshTable () 
    {
        
            oc.clear();
            Connection connexion;
                   connexion=DataSource.getInstance().getCnx();
            String query = "select * from rendezvous";
            PreparedStatement pst;
        try {
            pst = connexion.prepareStatement(query);
               ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                  Rendezvous e=new Rendezvous();   
               e.setId(rs.getInt(1));
                    e.setIdR(rs.getInt(2));
                    e.setDate(rs.getString(3));
                    e.setHeure(rs.getString(4));
                    e.setLieu(rs.getString(5));                   
                    e.setUsername(rs.getString(6));
                    e.setEmail(rs.getString(7));
                    Button edit = new Button("edit");
                    
                    e.setEdit(edit);
                      edit.setOnAction(s -> {
            
                if (e.getEdit() == edit  ) {
                    System.out.println("id: "+e.getId());
                    Integer a = e.getId();
                    System.out.println("idR: "+e.getIdR());
                    String b = e.getLieu();
                    System.out.println("Lieu: "+e.getLieu());
                    String date = e.getDate();
                    String heure = e.getHeure();
                    System.out.println("date: "+e.getDate());
                    System.out.println("date: "+e.getDate());
                    System.out.println("username: "+e.getUsername());
                    System.out.println("email: "+e.getEmail());
                    edit.setDisable(false);
                    
                    String reqe = "UPDATE rendezvous set lieu =?, date=?, heure? where id = ?  ";
                    try {
                        PreparedStatement pstm = connexion.prepareStatement(reqe);
                            pstm.setString(1, b);
                            pstm.setString(2, date);
                            pstm.setString(3, heure);
                            pstm.setInt(4, a);
                            pstm.executeUpdate();
                             
        Mailing.send(e.getEmail(), "Rendezvous Modifié", "Monsieur/ Madame: "+ e.getUsername()+"\n On vous informe que votre rendezvous a été changé et "
        + "ça sera \n le: "+e.getDate()+ " à: " +e.getHeure()+"\n Lieu: "+ e.getLieu() +"\n nous sommes désolé pour ce changement \n Cordialement," , "elitekids560@gmail.com", "ELITEJARDIN560");
Notifications h = Notifications.create()
                                            .title("Rendezvous")
                                            .text("Rendezvous Modifié avec succés")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(3));
                             h.showInformation();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    } 
                      
        
        });
                        
                oc.add(e);
                tablerv.setItems(oc);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
     
         private void  editableCols()
 {
    
     
     lieu_col.setCellFactory(TextFieldTableCell.forTableColumn());
     
     lieu_col.setOnEditCommit(e->{
         
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setLieu(e.getNewValue());
         
       
     });
     
      heure_col.setCellFactory(TextFieldTableCell.forTableColumn());
     heure_col.setOnEditCommit(e->{
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setHeure(e.getNewValue());
     });
     
     date_col.setCellFactory(TextFieldTableCell.forTableColumn());
     date_col.setOnEditCommit(e->{
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());
     });
     
 tablerv.setEditable(true);

     
     
 
 }

    @FXML
    private void menu(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/homeReclamation.fxml"));
        
        Scene scene = menu.getScene();
        root.translateXProperty().set(-scene.getWidth());
        
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline.play();
    }
    
}
