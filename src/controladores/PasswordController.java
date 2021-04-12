/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import controladores.RecordarPasswordController;
import javafx.fxml.FXMLLoader;
import model.Player;


public class PasswordController implements Initializable {

    @FXML
    private Text password;
    @FXML
    private Button cerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordarPassword.fxml"));
            Connect4 DB = Connect4.getSingletonConnect4();
            RecordarPasswordController user = (RecordarPasswordController) loader.getController();
            Player p = DB.getPlayer(user.usuario.getText());
            password.setText(p.getPassword());
        } 
        catch (Connect4DAOException ex) {
            Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void cerrar(ActionEvent event) {
        Stage myStage = (Stage) this.cerrar.getScene().getWindow();
        myStage.close();
    }
    
}
