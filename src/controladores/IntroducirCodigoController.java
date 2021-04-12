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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class IntroducirCodigoController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private Button cancelar;
    @FXML
    private Button obtener;
    @FXML
    private Text text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            Stage myStage = (Stage) this.obtener.getScene().getWindow();
            myStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Password.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        }
        else{
            text.setText("Código incorrecto");
        }
    }

    @FXML
    private void borrar(KeyEvent event) {
        text.setText("");
    }
}
