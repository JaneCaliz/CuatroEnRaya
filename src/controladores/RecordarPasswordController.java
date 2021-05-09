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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

public class RecordarPasswordController implements Initializable {

    @FXML
    public TextField usuario;
    @FXML
    private TextField correo;
    @FXML
    private Button recuperar;
    @FXML
    private Text error;
    @FXML
    private Button cancelar;
    boolean modo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void initMode(boolean b){
        modo = b;
    }
    
    @FXML
    private void recuperar(ActionEvent event) {
        try {
            Connect4 base = Connect4.getSingletonConnect4();
            if(!base.exitsNickName(usuario.getText())){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("Usuario no existente");
            }
            else if(!correo.getText().equals(base.getPlayer(usuario.getText()).getEmail())){
                error.setStyle("-fx-font: 15 SansSerif");
                error.setText("El usuario y el correo no coinciden");
            }
            else{
                error.setText("");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IntroducirCodigo.fxml"));
            
                Parent root = loader.load();
                
                Connect4 DB = Connect4.getSingletonConnect4();
                
                Player player = DB.getPlayer(usuario.getText());
                
                IntroducirCodigoController controlador = loader.getController();
                controlador.initMode(modo);
                controlador.initPlayer(player);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.recuperar.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(200);
                stage.setMinWidth(360);

                stage.setScene(scene);
                stage.show();
                
                controlador.codigo();
                
                stage.setOnCloseRequest(e -> {
                try {
                    controlador.cerrar();
                } catch (IOException ex) {
                    Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                });

                myStage.close();
            }
        } catch (Connect4DAOException | IOException ex) {
            Logger.getLogger(RecordarPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        cerrar();
    }
    
    public void cerrar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
        Parent root = loader.load();

        IniciarSesionController controlador = loader.getController();
        controlador.initMode(modo);
        controlador.initit2Player(false);

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        Stage myStage = (Stage) this.cancelar.getScene().getWindow();
        stage.setMaximized(myStage.isMaximized());
        stage.setMinHeight(325);
        stage.setMinWidth(385);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> controlador.closeWindow());

        myStage.close();
        
//        Stage myStage = (Stage) this.cancelar.getScene().getWindow();
//        myStage.close();
//        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
//        IniciarSesionController controlador = loader.getController();
//        Parent root = loader.load();
//
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        
//        stage.setMaximized(myStage.isMaximized());
//        stage.setMinHeight(325);
//        stage.setMinWidth(385);
//
//        stage.setScene(scene);
//        stage.show();
//        
//        stage.setOnCloseRequest(e -> {
//            controlador.closeWindow();
//        });
    }
}
