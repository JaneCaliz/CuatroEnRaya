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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void codigo(){
        try {
            

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/Codigo.fxml"));
            
            Parent root2 = loader2.load();
            Stage myStage = (Stage) this.obtener.getScene().getWindow();
            Scene scene2 = new Scene(root2);
            stage2 = new Stage();
            stage2.setAlwaysOnTop(true);
            
            CodigoController controlador = loader2.getController();
            
            stage2.setScene(scene2);  
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();
                        
            myStage.setOnCloseRequest(e -> controlador.cerrar());
                    
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = bounds.getMinX() + (bounds.getWidth() - scene2.getWidth()) * 1;
            double y = bounds.getMinY() + (bounds.getHeight() - scene2.getHeight()) * 1;
            stage2.setX(x);
            stage2.setY(y);
            
        } catch (IOException ex) {
            Logger.getLogger(IntroducirCodigoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initPlayer(Player p){
        this.p = p;
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
       cerrar();  
    }
    
    public void cerrar() throws IOException {
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
        stage2.close();
    }
    
    @FXML
    private void obtener(ActionEvent event) throws IOException {
        if(valor().equals(code.getText())){
            text.setText("");
            stage2.close();
            try{    
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Contraseña");
                alert.setHeaderText("Tu contraseña es:");  
                alert.setContentText(p.getPassword());
                alert.initStyle(StageStyle.UNDECORATED);
                DialogPane dialogPane = alert.getDialogPane();
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert2.css").toExternalForm());
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == ButtonType.OK){
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
                }
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            text.setStyle("-fx-font: 15 SansSerif");
            text.setText("Código incorrecto");
        }
    }
}
