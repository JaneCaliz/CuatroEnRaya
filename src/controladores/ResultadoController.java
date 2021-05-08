package controladores;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import model.Connect4;
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
    private Player player1, player2, initp2;
    private boolean IA, bool;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void initPlayer(Player p){
        this.player1 = p;
    }
    
    public void initPlayer2(Player p){
        this.player2 = p;
    }
     
    public void initP2(Player p){
        this.initp2 = p;
    }
    
    public void initMax(Boolean b){
        bool = b;
    }
    
    public void initRes (String s){
        try {
            Connect4 BD = Connect4.getSingletonConnect4();
            this.res = s;
            if(null != res)switch (res) {
                case "Empate":
                    resultado.setText("¡Empate!");
                    point.setText("");
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
                    BD.regiterRound(LocalDateTime.now(), player1, player2);
                    break;
                case "P2":
                    resultado.setText("¡" + player2.getNickName() + " ha ganado!");
                    point.setText("Puntuación: 50pts");
                    player2.plusPoints(50);
                    BD.regiterRound(LocalDateTime.now(), player2, player1);
                    break;
                default:
                    break;
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(ResultadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double width(){
        return resultado.getLayoutBounds().getWidth();
    }

    @FXML
    private void salir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

            Parent root = loader.load();

            MenuPrincipalController controlador = loader.getController();
            controlador.initscene();
            controlador.initPlayer(player1);
            if(player2 != null)
                controlador.initPlayer2(player2);
                
            if(initp2 != null)
                controlador.initPlayer2(initp2);
          
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.salir.getScene().getWindow();
            stage.setMaximized(bool);
            stage.setMinHeight(520);
            stage.setMinWidth(460);

            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> {
                controlador.closeWindow();
                e.consume();
            });
            
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
        
        if(initp2 != null)
            controlador.initP2(initp2);


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        Stage myStage = (Stage) this.replay.getScene().getWindow();
        stage.setMaximized(bool);
        stage.setMinHeight(396);
        stage.setMinWidth(504);

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controlador.salir();
                e.consume();
            } catch (IOException ex) {
                Logger.getLogger(ResultadoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        myStage.close();
    }

    void initIA(boolean IA) {
        this.IA = IA;
    }
    
}
