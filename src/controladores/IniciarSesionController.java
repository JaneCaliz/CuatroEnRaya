/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ydavpacat
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox show;
    @FXML
    private Hyperlink recuerdame;
    @FXML
    private Button iniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void closeWindow() {
//         try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
//            
//            Parent root = loader.load();
//            
//            IniciarSesionController controlador = loader.getController();
//            
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            
//            stage.setOnCloseRequest(e -> controlador.closeWindow());
//            
//            Stage myStage = (Stage) this.jugarButt.getScene().getWindow();
//            myStage.close();
//            
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        
    }

    @FXML
    private void check(ActionEvent event) {
    }

    @FXML
    private void inicio(ActionEvent event) {
    }
    
}
