/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultadoController implements Initializable {

    @FXML
    private Text resultado;
    @FXML
    private Text ganador;
    @FXML
    private Text point;
    @FXML
    private Button salir;
    @FXML
    private Button replay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(true){
            resultado.setText("¡Empate!");
            ganador.setText("P1 y P2");
            point.setText("Puntuación: 25pts");
        }
        //else if(contra maquina){
//          resultado.setText("¡Perdedor!");
//          point.setText("Puntuación: 0pts");
//      }
        else{
            resultado.setText("¡Ganador!");
            ganador.setText("P");
            point.setText("Puntuación: 50pts");
        }
    }    

    @FXML
    private void salir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            
            Parent root = loader.load();
            
            IniciarSesionController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeWindow());
            
            Stage myStage = (Stage) this.salir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(ResultadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void replay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));
            
        Parent root = loader.load();

        IniciarSesionController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> controlador.closeWindow());

        Stage myStage = (Stage) this.replay.getScene().getWindow();
        myStage.close();
    }
    
}
