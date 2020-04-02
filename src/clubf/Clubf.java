/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubf;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author benour
 */
public class Clubf extends Application {

    @Override
    public void start(Stage stage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Club");
         stage.show();
        } catch(Exception e){
        e.printStackTrace();
        }
      
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
                //Connection ds=DataSource.getInstance().getCnx();
                

    }
    
}
