/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataBase.DataSource;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class BarChartdestinataireController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChartD;
    @FXML
    private Button tableRec;
    @FXML
    private Button statParent;
    @FXML
    private Button pdf;
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
        loadChart();
    } 
    
     private void loadChart() {
        Connection connexion ;
        connexion=DataSource.getInstance().getCnx();
        String req ="select destinataire, count(*) from reclamation group by destinataire  ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
        PreparedStatement ps;
        try {
            ps = connexion.prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while(rs.next())
            {
                
                series.getData().add(new XYChart.Data<>(rs.getString("destinataire"),rs.getInt(2)));
                series.setName("nombre de reclamations");
                
                
            }
            barChartD.getData().add(series);
            barChartD.setAnimated(true);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BarChartReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showReclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/reclamation.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showStat(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/barChartReclamation.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showPdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
         ReclamationService r = new ReclamationService();
            r.pdf();
            Notifications n = Notifications.create()
                                            .title("Succés")
                                            .text("le tableau de reclamations est enregistré en PDF")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showInformation();
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
