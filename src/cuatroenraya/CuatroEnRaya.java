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
import javafx.stage.Stage;
import model.Connect4;

public class CuatroEnRaya extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setMinHeight(300);
        stage.setMinWidth(250);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connect4 bd = Connect4.getSingletonConnect4();
            //bd.removeAllData();
            //bd.createDemoData(15, 10, 10);
            launch(args);                      
        } catch (Connect4DAOException ex) {
            Logger.getLogger(CuatroEnRaya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
