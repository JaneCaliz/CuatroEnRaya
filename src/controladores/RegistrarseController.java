package controladores;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import model.Player;

public class RegistrarseController implements Initializable {

    @FXML
    private TextField usuario;
    @FXML
    private Text eusuario;
    @FXML
    private TextField email;
    @FXML
    private Text eemail;
    @FXML
    private Text epassword;
    @FXML
    private Text ecpassword;
    @FXML
    private DatePicker edad;
    @FXML
    private Text eage;
    @FXML
    private ImageView avatar;
    @FXML
    private Button avatar1;
    @FXML
    private Button avatar4;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatard;
    @FXML
    private Button avatar3;
    @FXML
    private Button upload;
    @FXML
    private Button cancelar;
    @FXML
    private Button registrarse;
    @FXML
    private ImageView iavatar1;
    @FXML
    private ImageView iavatar4;
    @FXML
    private ImageView iavatar2;
    @FXML
    private ImageView iavatard;
    @FXML
    private ImageView iavatar3;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void avatar1(ActionEvent event) {
        avatar.setImage(iavatar1.getImage());
    }

    @FXML
    private void avatar2(ActionEvent event) {
        avatar.setImage(iavatar2.getImage());
    }
    
    @FXML
    private void avatar3(ActionEvent event) {
        avatar.setImage(iavatar3.getImage());
    }
    
    @FXML
    private void avatar4(ActionEvent event) {
        avatar.setImage(iavatar4.getImage());
    }

    @FXML
    private void avatard(ActionEvent event) {
        avatar.setImage(iavatard.getImage());
    }

    @FXML
    private void upload(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrar();
    }
    
    public void cerrar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
            Parent root = loader.load();
            
            IniciarSesionController controlador = loader.getController();
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
        } catch (IOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void registrarse(ActionEvent event) {
        try {
            Connect4 BD = Connect4.getSingletonConnect4();
            if(!Player.checkNickName(usuario.getText()) || BD.exitsNickName(usuario.getText())){
                if(!Player.checkNickName(usuario.getText())){
                    eusuario.setText("Caracter inválido. Símbolos válidos: -, _ y alfanuméricos");
                }
                if(usuario.getText().length() < 6 || usuario.getText().length() > 15){
                    eusuario.setText("El usuario debe tener entre 6 a 15 caracteres");
                }
                if(BD.exitsNickName(usuario.getText())){
                    eusuario.setText("Usuario ya existente");
                }
            }
            else{
                eusuario.setText("");
            }
            if(!Player.checkEmail(email.getText())){
                eemail.setText("Por favor introduzca un correo existente");
            }
            else{
                eemail.setText("");
            }
            String pass = password.getText();
            if(!Player.checkPassword(password.getText())){
                if(!Player.checkPassword(password.getText())){
                    epassword.setText("La contraseña debe contener: una mayúscula, una minúscula, un dígito, un carácter espacial (!@#$%&*()-+=) y ningún espacio en blanco");
                }
                if(password.getText().length() < 8 || password.getText().length() > 20){
                    epassword.setText("La contraseña debe tener entre 6 a 15 caracteres");
                }
            }
            else{
                epassword.setText("");
            }
            if(!pass.equals(password.getText()) && Player.checkPassword(password.getText())){
                ecpassword.setText("Las contraseñas no coinciden");
            }
            else{
                ecpassword.setText("");
            }
            if(edad.getValue().toEpochDay() < LocalDate.now().minusYears(12).toEpochDay()){
                eage.setText("Debes ser mayor de 12 años para jugar");
            }
            else{
                eage.setText("");
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
