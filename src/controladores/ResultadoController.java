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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

public class ResultadoController implements Initializable {

    @FXML
    private Text resultado;
    @FXML
    private Text point;
    @FXML
    private Button salir;
    @FXML
    private Button replay;
    
    private String res;
    
    private Player player1, player2;
    
    private boolean IA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void initPlayer(Player p){
        this.player1 = p;
    }
    
    public void initPlayer2(Player p){
        this.player2 = p;
    }
    
    public void initRes (String s){
        try {
            this.res = s;
            if(null != res)switch (res) {
                case "Empate":
                    resultado.setText("¡Empate!");
                    point.setText("Puntuación: 0pts");
                    break;
                case "IA":
                    resultado.setText("¡Perdedor!");
                    point.setText("Puntuación: 0pts");
                    break;
                case "P1 IA":
                    resultado.setText("¡Has ganado!");
                    point.setText("Puntuación: 5pts");
                    player1.plusPoints(5);
                    break;
                case "P1":
                    resultado.setText("¡" + player1.getNickName() + " ha ganado!");
                    point.setText("Puntuación: 50pts");
                    player1.plusPoints(50);
                    break;
                case "P2":
                    resultado.setText("¡" + player2.getNickName() + " ha ganado!");
                    point.setText("Puntuación: 50pts");
                    player2.plusPoints(50);
                    break;
                default:
                    break;
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(ResultadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

            Parent root = loader.load();

            MenuPrincipalController controlador = loader.getController();
            controlador.initPlayer(player1);
            if(player2 != null)
                controlador.initPlayer2(player2);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            
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

        TableroController controlador = loader.getController();
        controlador.initializeP1(player1);
        if (player2 != null)
            controlador.initializeP2(player2);
        controlador.initializeIA(IA);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        

        stage.setScene(scene);
        stage.show();

        // stage.setOnCloseRequest(e -> controlador.closeWindow());

        Stage myStage = (Stage) this.replay.getScene().getWindow();
        myStage.close();
    }

    void initIA(boolean IA) {
        this.IA = IA;
    }
    
}
