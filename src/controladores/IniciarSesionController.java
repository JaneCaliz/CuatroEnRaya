/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

public class IniciarSesionController implements Initializable {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink recuerdame;
    @FXML
    private Button iniciar;
    @FXML
    private Text title;
    @FXML
    private Text error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(MenuPrincipalController.getSegundo()){
            title.setText("Iniciar sesión segundo jugador");
        }
        
        // TODO
    }    

    public void closeWindow() {
         try {
            if(MenuPrincipalController.getSegundo()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

                Stage myStage = (Stage) this.iniciar.getScene().getWindow();
                myStage.close();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
                
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

                Stage myStage = (Stage) this.iniciar.getScene().getWindow();
                myStage.close();
            }
  
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void inicio(ActionEvent event) {

        
        try {
            Connect4 BD = Connect4.getSingletonConnect4();
            
            String usu = usuario.getText();
            String pass = password.getText();
            
            Player p = BD.loginPlayer(usu,pass);
            
            if(p == null){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario o contraseña incorectos");
                System.out.println(BD.getConnect4DAO());
            } else{
                if(MenuPrincipalController.getSegundo()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.show();

                    Stage myStage = (Stage) this.iniciar.getScene().getWindow();
                    myStage.close();
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.show();

                    Stage myStage = (Stage) this.iniciar.getScene().getWindow();
                    myStage.close();
                }
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   


    @FXML
    private void recordar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/RecordarPassword.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.iniciar.getScene().getWindow();
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void click(KeyEvent event) {
        error.setStyle("-fx-font: 1 SansSerif");
        error.setText("");
    }
    
}
