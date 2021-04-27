/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    
    private Player p1, p2;
    
    private boolean RegistrarP2;
    
    @FXML
    private Button cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar.setDefaultButton(true);
    }    

    public void closeWindow() {
         try {
            if(RegistrarP2){
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
            
            if(RegistrarP2){
                p2 = BD.loginPlayer(usu,pass);
            }
            else{
                p1 = BD.loginPlayer(usu,pass);
            }
            
            if(RegistrarP2 && p2 == p1){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario repetido");
            }
            else if(usu.length() == 0 || pass.length() == 0){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario o contraseña vacío");
            }
            else if(!RegistrarP2 && p1 == null){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario o contraseña incorectos");
            }
//                System.out.println(BD.getConnect4DAO());
            else if(RegistrarP2 && p2 == null){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario o contraseña incorectos");
            }
            else{
                if(RegistrarP2){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));

                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    
                    TableroController controlador = loader.getController();
                    controlador.initializeP2(p2);
                    controlador.initializeP1(p1);
                    controlador.initializeIA(false);

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
                    
                    MenuPrincipalController controlador = loader.getController();
                    controlador.initPlayer(p1);

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
    private void cancelar(ActionEvent event) {
        closeWindow();
    }
    
    void inititPlayer1(Player p){
       this.p1 = p;
    }
    
    void initit2Player(boolean b) {
       if (b)
              title.setText("Iniciar sesión segundo jugador");
       RegistrarP2 = b;
    }
    
    public boolean getRegistrarP2(){
        return RegistrarP2;
    }
}
