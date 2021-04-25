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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;


public class ConfirmarController implements Initializable {

    @FXML
    private Button cancelar;
    @FXML
    private Button cerrar;
    @FXML
    private Text warning;
    
    private Player player2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        if(RegistrarP2){
//        texto.setText(texto + player2.getNickName());
//
    }    
    public void initializeP2(Player p){
        this.player2 = p ;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage myStage = (Stage) this.cerrar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void cerrar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.cerrar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(ConfirmarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
