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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Button registrar;
    @FXML
    private Button arrow;
    @FXML
    private Pane idk;
    
    private Player p1, p2;
    private boolean RegistrarP2, modo;
    @FXML
    private HBox screen;
    @FXML
    private Text uerror;
    @FXML
    private Text perror;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar.setDefaultButton(true);
    }    

    public void initMode(boolean b){
        modo = b;
        
        if(modo){ 
            screen.getStylesheets().remove("/Img/lightmode.css");
            screen.getStylesheets().add("/Img/darkmode.css");
            usuario.getStylesheets().remove("/Img/lightmode.css");
            usuario.getStylesheets().add("/Img/darkmode.css");
            password.getStylesheets().remove("/Img/lightmode.css");
            password.getStylesheets().add("/Img/darkmode.css");
            recuerdame.getStylesheets().remove("/Img/lightmode.css");
            recuerdame.getStylesheets().add("/Img/darkmode.css");
            iniciar.getStylesheets().remove("/Img/lightmode.css");
            iniciar.getStylesheets().add("/Img/darkmode.css");
            registrar.getStylesheets().remove("/Img/lightmode.css");
            registrar.getStylesheets().add("/Img/darkmode.css");
            title.setStyle("-fx-fill: #ffffff;");
        }
        else{
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
            usuario.getStylesheets().remove("/Img/darkmode.css");
            usuario.getStylesheets().add("/Img/lightmode.css");
            password.getStylesheets().remove("/Img/darkmode.css");
            password.getStylesheets().add("/Img/lightmode.css");
            recuerdame.getStylesheets().remove("/Img/darkmode.css");
            recuerdame.getStylesheets().add("/Img/lightmode.css");
            iniciar.getStylesheets().remove("/Img/darkmode.css");
            iniciar.getStylesheets().add("/Img/lightmode.css");
            registrar.getStylesheets().remove("/Img/darkmode.css");
            registrar.getStylesheets().add("/Img/lightmode.css");
            title.setStyle("-fx-fill: #000000;");
        }
    }
    
    public void closeWindow() {
         try {
            if(RegistrarP2){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                Stage myStage = (Stage) this.iniciar.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initMode(modo);
                controlador.initscene();
                controlador.initPlayer(p1);

                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(520);
                stage.setMinWidth(460);

                stage.setScene(scene);
                stage.show();
                
                stage.setOnCloseRequest(e -> {
                    controlador.closeWindow();
                    e.consume();
                });

                myStage.close();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
                
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.iniciar.getScene().getWindow();

                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(300);
                stage.setMinWidth(250);
                
                stage.setScene(scene);
                stage.show();

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
                uerror.setStyle("-fx-font: 15 Style");
                uerror.setText("Usuario repetido");
            }
            else if(usu.length() == 0){
                uerror.setStyle("-fx-font: 15 Style");
                uerror.setText("Usuario vacío");
            }
            else if(pass.length() == 0){
                perror.setStyle("-fx-font: 15 Style");
                perror.setText("Contraseña vacía");
            }
            else if(!RegistrarP2 && p1 == null && BD.exitsNickName(usuario.getText())){
                uerror.setStyle("-fx-font: 15 Style");
                uerror.setText("Usuario no existente");
            }
            else if(!RegistrarP2 && p1 == null && p2.checkCredentials(usuario.getText(), password.getText())){
                perror.setStyle("-fx-font: 15 Style");
                perror.setText("Contraseña incorrecta");
            }
            else if(RegistrarP2 && p2 == null && BD.exitsNickName(usuario.getText())){
                uerror.setStyle("-fx-font: 15 Style");
                uerror.setText("Usuario no existente");
            }
            else if(RegistrarP2 && p2 == null && p1.checkCredentials(usuario.getText(), password.getText())){
                perror.setStyle("-fx-font: 15 Style");
                perror.setText("Contraseña incorrecta");
            }
            else{
                if(RegistrarP2){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));

                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    
                    TableroController controlador = loader.getController();
                    controlador.initMode(modo);
                    controlador.initializeP2(p2);
                    controlador.initializeP1(p1);
                    controlador.initializeIA(false);
                    Stage myStage = (Stage) this.iniciar.getScene().getWindow();

                    stage.setMaximized(myStage.isMaximized());
                    stage.setMinHeight(396);
                    stage.setMinWidth(504);
                                        
                    stage.setScene(scene);
                    stage.show();
                    
                    stage.setOnCloseRequest(e -> {
                        try {
                            controlador.salir();
                            e.consume();
                        } catch (IOException ex) {
                            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    myStage.close();
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    
                    Stage myStage = (Stage) this.iniciar.getScene().getWindow();
                    stage.setMinHeight(520);
                    stage.setMinWidth(460);
                    
                    MenuPrincipalController controlador = loader.getController();
                    controlador.initMode(modo);
                    controlador.initscene();
                    controlador.initPlayer(p1);
                    stage.setMaximized(myStage.isMaximized());
                    
                    stage.setScene(scene);
                    stage.show();
                    
                    stage.setOnCloseRequest(e -> {
                        controlador.closeWindow();
                        e.consume();
                    });
                    
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
            
            RecordarPasswordController controlador = loader.getController();
            Stage myStage = (Stage) this.iniciar.getScene().getWindow();
            controlador.initMode(modo);
            controlador.initit2Player(RegistrarP2);
            if(p1 != null)
                controlador.initPlayer(p1);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(235);
            stage.setMinWidth(350);
            
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> {
                try {
                    controlador.cerrar();
                } catch (IOException ex) {
                    Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void inititPlayer1(Player p){
       this.p1 = p;
    }
    
    void initPlayer2(Player p2){
       this.p2 = p2; 
    }
    
    void initit2Player(boolean b) {
       RegistrarP2 = b;
    }
    
    public boolean getRegistrarP2(){
        return RegistrarP2;
    }

    @FXML
    private void registrar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Registrarse.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            RegistrarseController controlador = loader.getController();
            Stage myStage = (Stage) this.iniciar.getScene().getWindow();
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(235);
            stage.setMinWidth(350);
            
            controlador.initMode(modo);
            controlador.initit2Player(RegistrarP2);
            if(p1 != null)
                controlador.initPlayer(p1);
            
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrar());
            
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void arrow(ActionEvent event) {
        closeWindow();
    }
}
