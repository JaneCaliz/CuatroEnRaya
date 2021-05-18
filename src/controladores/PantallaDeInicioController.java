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
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class PantallaDeInicioController implements Initializable {

    @FXML
    private Button jugarButt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void jugar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
            Parent root = loader.load();
            
            IniciarSesionController controlador = loader.getController();
            controlador.initit2Player(false);
            controlador.initMode(false);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.jugarButt.getScene().getWindow();
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(325);
            stage.setMinWidth(385);
            
            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            myStage.getIcons().add(image);
            myStage.setTitle("Conecta4");
                        
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeWindow());
            
            myStage.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    } 
}
