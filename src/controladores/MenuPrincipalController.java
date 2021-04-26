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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Player;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button jugarIA;
    @FXML
    private ImageView profile;
    @FXML
    private Button jugarF;

    private Player player1;
    
    private WindowEvent cerrar;
    @FXML
    private Menu logout;
    @FXML
    private MenuItem play1;
    @FXML
    private MenuItem play2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        profile.setImage(player1.getAvatar());
    }    
    
    public void initPlayer(Player p){
        this.player1 = p;
    }
    
    @FXML
    private void jugarIA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            TableroController controlador = loader.getController();
            controlador.initializeIA(true);
            controlador.initializeP1(player1);
            controlador.colocarFichaIA();
            
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.jugarIA.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void jugarF(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            IniciarSesionController controlador = loader.getController();
            controlador.initit2Player(true);
            controlador.inititPlayer1(player1);
            
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.jugarIA.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void profile(MouseEvent event) {
        
    }

    @FXML
    private void play1(ActionEvent event) {
    }

    @FXML
    private void play2(ActionEvent event) {
    }
    
}
