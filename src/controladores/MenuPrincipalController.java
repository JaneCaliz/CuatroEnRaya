/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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

            Stage myStage = (Stage) this.jugarIA.getScene().getWindow();

            TableroController controlador = loader.getController();
            controlador.initializeIA(true);
            controlador.initializeP1(player1);
            stage.setMaximized(myStage.isMaximized());
            
            stage.setScene(scene);
            stage.show();

            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void jugarF(ActionEvent event) {
        try{
            if(player2 == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                IniciarSesionController controlador = loader.getController();
                controlador.initit2Player(true);
                controlador.inititPlayer1(player1);
                stage.setMaximized(myStage.isMaximized());


                stage.setScene(scene);
                stage.show();

                myStage.close();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                TableroController controlador = loader.getController();
                controlador.initializeP1(player1);
                controlador.initializeP2(player2);
                stage.setMaximized(myStage.isMaximized());


                stage.setScene(scene);
                stage.show();

                myStage.close();
            }
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
            
            Stage myStage = (Stage) this.jugarF.getScene().getWindow();
            stage.setMaximized(myStage.isMaximized());

            stage.setScene(scene);
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void play1(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText("Cerrar sesión");
            alert.setContentText("¿Está seguro de querer cerrar la sesión de " + player1.getNickName() + "?"); alert.getDialogPane().getStylesheets()
                   .add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if(player2 != null && result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initPlayer(player2);
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player2 = null;
                play1.disableProperty().setValue(Boolean.TRUE);
            }
            else if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player1 = null;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void play2(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText("Cerrar sesión");
            alert.setContentText("¿Está seguro de querer cerrar la sesión de " + player2.getNickName() + "?");    
            alert.getDialogPane().getStylesheets()
                   .add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initPlayer(player1);
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player2 = null;
                play1.disableProperty().setValue(Boolean.TRUE);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
