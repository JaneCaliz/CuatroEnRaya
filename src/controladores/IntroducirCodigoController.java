/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static controladores.CodigoController.valor;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void obtener(ActionEvent event) throws IOException {
        if(valor().equals(code.getText())){
            text.setText("");
            pass.setStyle("-fx-font: 20 SansSerif");
            pass.setFill(Color.BLUE);
            pass.setText("Tu contraseña es: " + p.getPassword());
                      
        }
        else{
            text.setStyle("-fx-font: 15 SansSerif");
            text.setText("Código incorrecto");
        }
    }

    @FXML
    private void borrar(KeyEvent event) {
        text.setText("");
        text.setStyle("-fx-font: 1 SansSerif");
    }
}
