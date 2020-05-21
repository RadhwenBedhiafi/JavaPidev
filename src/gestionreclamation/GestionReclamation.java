/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation;

import entities.Mailing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javax.mail.MessagingException;
/**
 *
 * @author COMPUTER
 */
public class GestionReclamation extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/homeReclamation.fxml"));
        
        Scene scene = new Scene(root);
        Image logo = new Image("/Images/elite.png");
        stage.getIcons().add(logo);
        stage.setTitle("Elite Kids");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       // Connection ds=DataSource.getInstance().getCnx();
       launch(args);
      // Mailing.send("najibaamri23@gmail.com", "hey", "najiba", "najibaamri23@gmail.com", "GUESSWHATISITT");
       
    }

   
    
}
