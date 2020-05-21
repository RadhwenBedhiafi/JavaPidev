/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataBase.DataSource;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import org.controlsfx.control.Notifications;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class BarChartReclamationController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChartR;
    @FXML
    private Button tableRec;
    @FXML
    private Button statDest;
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
        //for(Node n:barChartR.lookupAll(".default-color0.chart-bar")) {
          //  n.setStyle("-fx-bar-fill: green;");}
   
    }    

    
    private void loadChart() {
        Connection connexion ;
        connexion=DataSource.getInstance().getCnx();
        String req ="select username, count(*) from reclamation group by username  ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
        PreparedStatement ps;
        try {
            ps = connexion.prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while(rs.next())
            {
                
                series.getData().add(new XYChart.Data<>(rs.getString("username"),rs.getInt(2)));
                series.setName("nombre de reclamations");
                
                
                
                
            }
            barChartR.getData().add(series);
            barChartR.setAnimated(true);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BarChartReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void back(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/reclamation.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
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
    private void showStatDest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/barChartdestinataire.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showpdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
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
