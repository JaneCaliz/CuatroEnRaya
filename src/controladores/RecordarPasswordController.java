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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                controlador.initPlayer(player);
                

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                stage.setScene(scene);
                stage.show();

                Stage myStage = (Stage) this.recuperar.getScene().getWindow();
                myStage.close();
                
//                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/Codigo.fxml"));
//                
//                Parent root2 = loader2.load();
//
//                Scene scene2 = new Scene(root2);
//                Stage stage2 = new Stage();
//
//                stage2.setScene(scene2);
//                stage2.show();   
            }
        } catch (Connect4DAOException | IOException ex) {
            Logger.getLogger(RecordarPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        Stage myStage = (Stage) this.cancelar.getScene().getWindow();
        myStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
}
