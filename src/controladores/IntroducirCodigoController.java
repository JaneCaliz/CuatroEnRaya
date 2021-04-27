/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static controladores.CodigoController.valor;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;


public class IntroducirCodigoController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private Button cancelar;
    @FXML
    private Button obtener;
    @FXML
    private Text text;
    
    public Player p;
    @FXML
    private Text pass;
    
    Stage stage2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/Codigo.fxml"));
            
            Parent root2 = loader2.load();
            
            Scene scene2 = new Scene(root2);
            stage2 = new Stage();
            stage2.setAlwaysOnTop(true);
                            
            stage2.setScene(scene2);   
            stage2.show();
        } catch (IOException ex) {
            Logger.getLogger(IntroducirCodigoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void initPlayer(Player p){
        this.p = p;
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
       
        Stage myStage = (Stage) this.cancelar.getScene().getWindow();
        myStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setMaximized(myStage.isMaximized());
            
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void obtener(ActionEvent event) throws IOException {
        if(valor().equals(code.getText())){
            text.setText("");
            stage2.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Contraseña");
            alert.setHeaderText("Tu contraseña es:");
            alert.setContentText(p.getPassword());
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){

                Stage myStage = (Stage) this.cancelar.getScene().getWindow();
                myStage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                stage.setMaximized(myStage.isMaximized());
            
                stage.setScene(scene);
                stage.show();
            }

        }
        else{
            text.setStyle("-fx-font: 15 SansSerif");
            text.setText("Código incorrecto");
        }
    }
}
