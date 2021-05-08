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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

public class PerfilController implements Initializable {

    @FXML
    private Button volver;
    @FXML
    private ImageView avatar;
    @FXML
    private Text points;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private Button modificar;
    
    Player player1, player2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volver(ActionEvent event) {
        cerrar();
    }
    
    public void initPlayer(Player p){
        if(player1 != null)
            player1 = p;
        else
            player2 = p;
        
        avatar.setImage(p.getAvatar());
        usuario.setText(p.getNickName());
        password.setText(p.getPassword());
        email.setText(p.getEmail());
        age.setText(p.getBirthdate().toString());
        points.setText(p.getPoints() + "pts");
    }
    
    public void initOtro(Player p, int i){
        if(i == 1)
            player1 = p;
        else
            player2 = p;
    }
    
    public void cerrar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.volver.getScene().getWindow();
            
            MenuPrincipalController controlador = loader.getController();
            controlador.initscene();
            controlador.initPlayer(player1);
            if(player2 != null)
                controlador.initPlayer2(player2);
            
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
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
    }
    
}
