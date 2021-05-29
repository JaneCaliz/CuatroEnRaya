/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuatroenraya;

import DBAccess.Connect4DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Connect4;

public class CuatroEnRaya extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(620);
        
        Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setTitle("Conecta4");
        
        stage.show();
    }

    public static void main(String[] args) {
        try {
            Connect4 bd = Connect4.getSingletonConnect4();
            bd.removeAllData();
            bd.createDemoData(15, 10, 15);
            launch(args);                      
        } catch (Connect4DAOException ex) {
            Logger.getLogger(CuatroEnRaya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
