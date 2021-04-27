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
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button jugarIA;
    @FXML
    private ImageView profile;
    @FXML
    private Button jugarF;    
    @FXML
    private MenuItem play1;
    @FXML
    private MenuItem play2;
    @FXML
    private MenuButton button;
    
    private Player player1, player2;
    @FXML
    private Text points;
    @FXML
    private Button trophy;
    @FXML
    private GridPane barra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        points.minHeight(25);
        points.maxHeight(30);
        points.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(60), 20).asString(), ";","-fx-base: rgb(100,100,",50,");"));
    }    
    
    public void initPlayer(Player p){
        this.player1 = p;
        points.setText(player1.getPoints() + "");
        profile.setImage(player1.getAvatar());
        play1.setText(player1.getNickName());
        play2.disableProperty().setValue(Boolean.TRUE);
        play2.visibleProperty().setValue(Boolean.FALSE);
    }
    
    public void initPlayer2(Player p){
        this.player2 = p;
        play2.setText(player2.getNickName());
        play2.disableProperty().setValue(Boolean.FALSE);
        play2.visibleProperty().setValue(Boolean.TRUE);
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
    private void play1(ActionEvent event) {
        
    }

    @FXML
    private void play2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            MenuPrincipalController controlador = loader.getController();
            controlador.initPlayer(player1);
            
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.jugarF.getScene().getWindow();
            myStage.close();
            
            player2 = null;
            play1.disableProperty().setValue(Boolean.TRUE);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
