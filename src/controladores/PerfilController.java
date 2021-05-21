package controladores;

import DBAccess.Connect4DAOException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Connect4;
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
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private Button modificar;
    @FXML
    private HBox avatares;
    @FXML
    private Button avatar1;
    @FXML
    private ImageView iavatar1;
    @FXML
    private Button avatar4;
    @FXML
    private ImageView iavatar4;
    @FXML
    private Button avatar2;
    @FXML
    private ImageView iavatar2;
    @FXML
    private Button avatard;
    @FXML
    private ImageView iavatard;
    @FXML
    private Button avatar3;
    @FXML
    private ImageView iavatar3;
    @FXML
    private Button upload;
    @FXML
    private Button guardar;
    @FXML
    private HBox datepicker;
    @FXML
    private DatePicker date;
    @FXML
    private Text epassword;
    @FXML
    private Text eage;
    @FXML
    private Text eemail;
    Player player1, player2;
    String passw;
    LocalDate year;
    int play;
    boolean modo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volver(ActionEvent event) {
        cerrar();
    }
    
    public void initPlayer(Player p){
        if(player1 == null)
            player1 = p;
        else
            player2 = p;
        
        avatar.setImage(p.getAvatar());
        usuario.setText(p.getNickName());
        String pass = "";
        passw = p.getPassword();
        for(int i = 0; i < passw.length(); i++){
            pass += "•";
        }
        password.setText(pass);
        email.setText(p.getEmail());
        age.setText(p.getBirthdate().toString());
        year = p.getBirthdate();
        points.setText(p.getPoints() + "pts");
    }
    
    public void initOtro(Player p, int i){
        play = i;
        if(i == 1)
            player1 = p;
        else
            player2 = p;
    }
    
    public void initMode(boolean b){
        modo = b;
    }
    
    public void cerrar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.volver.getScene().getWindow();
            
            MenuPrincipalController controlador = loader.getController();
            controlador.initMode(modo);
            controlador.initscene();
            controlador.initPlayer(player1);
            if(player2 != null)
                controlador.initPlayer2(player2);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(520);
            stage.setMinWidth(460);
            
            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setTitle("Conecta4");
            
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
        usuario.setStyle("-fx-text-inner-color: #6d6f73;");
        usuario.setStyle("-fx-control-inner-background: #edf8ff;");
        password.setText(passw);
        password.setEditable(true);
        password.setCursor(Cursor.TEXT);
        email.setEditable(true);
        email.setCursor(Cursor.TEXT);
        points.setVisible(false);
        avatares.setVisible(true);
        guardar.setVisible(true);
        age.setVisible(false);
        datepicker.setVisible(true);
        date.setValue(year);
        date.setStyle("-fx-control-inner-background: #edf8ff;");
        
        modificar.setVisible(false);
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
    private void guardar(ActionEvent event) throws IOException {
        try {
            boolean passw = false, correo = false, age = false;
            String pass = "";
            Connect4 BD = Connect4.getSingletonConnect4();
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
            if(date.getValue() != null && date.getValue().toEpochDay() > LocalDate.now().minusYears(12).toEpochDay()){
                eage.setText("Debes ser mayor de 12 años para jugar");
                age = false;
            }
            else if(date.getValue() == null){
                eage.setText("Campo obligatorio");
                age = false;
            }
            else{
                eage.setText("");
                age = true;
            }
            
            if(passw && correo && age){
                if(play == 1){
                    if(!player1.getAvatar().equals(avatar.getImage())){
                        player1.setAvatar(avatar.getImage());
                    }
                    if(!player1.getPassword().equals(password.getText())){
                        player1.setPassword(password.getText());
                    }
                    if(!player1.getEmail().equals(email.getText())){
                        player1.setEmail(email.getText());
                    }
                    if(!player1.getBirthdate().equals(date.getValue())){
                        player1.setBirthdate(date.getValue());
                    }
                }
                if(play == 2){
                    if(!player2.getAvatar().equals(avatar.getImage())){
                        player2.setAvatar(avatar.getImage());
                    }
                    if(!player2.getPassword().equals(password.getText())){
                        player2.setPassword(password.getText());
                    }
                    if(!player2.getEmail().equals(email.getText())){
                        player2.setEmail(email.getText());
                    }
                    if(!player2.getBirthdate().equals(date.getValue())){
                        player2.setBirthdate(date.getValue());
                    }
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Datos modificados");
                alert.setHeaderText(" Cambios guardados");  
                alert.setContentText("Sus datos han sido modificado con éxito");
                alert.initStyle(StageStyle.UNDECORATED);
                DialogPane dialogPane = alert.getDialogPane();
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert3.css").toExternalForm());
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == ButtonType.OK){
                
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

                    Parent root = loader.load();

                    MenuPrincipalController controlador = loader.getController();
                    controlador.initMode(modo);
                    controlador.initscene();
                    controlador.initPlayer(player1);
                    if(player2 != null)
                        controlador.initPlayer2(player2);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    Stage myStage = (Stage) this.guardar.getScene().getWindow();
                    stage.setMaximized(myStage.isMaximized());
                    stage.setMinHeight(520);
                    stage.setMinWidth(460);
                    
                    Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setTitle("Conecta4");

                    stage.setScene(scene);
                    stage.show();

                    stage.setOnCloseRequest(e -> {
                        controlador.closeWindow();
                        e.consume();
                    });

                    myStage.close();
                    
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
