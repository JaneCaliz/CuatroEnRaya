/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;


public class PasswordController implements Initializable {

    @FXML
    private Text password;
    @FXML
    private Button cerrar;
    
    public Player p;
    @FXML
    private Button mostrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void initPlayer(Player p){
        this.p = p;
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Stage myStage = (Stage) this.cerrar.getScene().getWindow();
        myStage.close();
    }

    @FXML
    private void mostarPass(ActionEvent event) {
        password.setText(p.getPassword());
    }
    
}