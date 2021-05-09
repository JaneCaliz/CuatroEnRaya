package controladores;

import DBAccess.Connect4DAOException;
import java.awt.AlphaComposite;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
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
    boolean modo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initMode(boolean b){
        modo = b;
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
    private void upload(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        file.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        
        file.setTitle("Subir avatar desde PC");
        Stage stage = (Stage) upload.getScene().getWindow();
        File image = file.showOpenDialog(stage);
        
        if(image != null){
            Image img = new Image(image.toURI().toString());
            avatar.setImage(img);
        }
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
            controlador.initMode(modo);
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
    private void registrarse(ActionEvent event) throws IOException {
        try {
            boolean usu = false, passw = false, cpass = false, correo = false, age = false;
            String pass = "";
            Connect4 BD = Connect4.getSingletonConnect4();
            if(usuario.getText().length() == 0){
                eusuario.setText("Campo obligatorio");
                usu = false;
            }
            else if(!Player.checkNickName(usuario.getText()) || BD.exitsNickName(usuario.getText())){
                if(!Player.checkNickName(usuario.getText())){
                    eusuario.setText("Caracter inválido. Símbolos válidos: -, _ y alfanuméricos");
                }
                if(usuario.getText().length() < 6 || usuario.getText().length() > 15){
                    eusuario.setText("El usuario debe tener entre 6 a 15 caracteres");
                }
                if(BD.exitsNickName(usuario.getText())){
                    eusuario.setText("Usuario ya existente");
                }
                usu = false;
            }
            else{
                eusuario.setText("");
                usu = true;
            }
            if(email.getText().length() == 0){
                eemail.setText("Campo obligatorio");
                correo = false;
            }
            else if(!Player.checkEmail(email.getText())){
                eemail.setText("Por favor introduzca un correo existente");
                correo = false;
            }
            else{
                eemail.setText("");
                correo = true;
            }
            if(password.getText().length() == 0){
                epassword.setText("Campo obligatorio");
                passw = false;
            }
            else if(!Player.checkPassword(password.getText())){
                if(!Player.checkPassword(password.getText())){
                    epassword.setText("La contraseña debe contener: una mayúscula, una minúscula, un dígito, un carácter especial (!@#$%&*()-+=) y ningún espacio en blanco");
                }
                if(password.getText().length() < 8 || password.getText().length() > 20){
                    epassword.setText("La contraseña debe tener entre 6 a 15 caracteres");
                }
                passw = false;
            }
            else{
                epassword.setText("");
                passw = true;
                pass = password.getText();
            }
            if(cpassword.getText().length() == 0){
                ecpassword.setText("Campo obligatorio");
                cpass = false;
            }
            else if(!pass.equals(password.getText()) && Player.checkPassword(password.getText())){
                ecpassword.setText("Las contraseñas no coinciden");
                cpass = false;
            }
            else{
                ecpassword.setText("");
                cpass = true;
            }
            if(edad.getValue() != null && edad.getValue().toEpochDay() > LocalDate.now().minusYears(12).toEpochDay()){
                eage.setText("Debes ser mayor de 12 años para jugar");
                age = false;
            }
            else if(edad.getValue() == null){
                eage.setText("Campo obligatorio");
                age = false;
            }
            else{
                eage.setText("");
                age = true;
            }
            
            if(usu && passw && cpass && correo && age){
                BD.registerPlayer(usuario.getText(), email.getText(), password.getText(), avatar.getImage(), edad.getValue(), 0);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario registrado");
                alert.setHeaderText(" Tu usuario fue registrado con éxito");  
                alert.setContentText("Ya puedes iniciar sesión con tu nuevo usuario");
                alert.initStyle(StageStyle.UNDECORATED);
                DialogPane dialogPane = alert.getDialogPane();
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert3.css").toExternalForm());
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == ButtonType.OK){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
                    Parent root = loader.load();

                    IniciarSesionController controlador = loader.getController();
                    controlador.initMode(modo);
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
                }
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
