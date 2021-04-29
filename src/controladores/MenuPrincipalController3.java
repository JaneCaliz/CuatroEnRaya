package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;

public class MenuPrincipalController3 implements Initializable {

    @FXML
    private Button jugarIA;
    @FXML
    private ImageView profile;
    @FXML
    private Button jugarF;    
    @FXML
    private Button play1;
    @FXML
    private Button play2;
    @FXML
    private Button button;
    
    private Player player1, player2;
    @FXML
    private Text points;
    @FXML
    private Button trophy;
    @FXML
    private GridPane barra;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView iplay1;
    @FXML
    private ImageView iplay2;
    
    private boolean menu;
    @FXML
    private Button buttonc;
    @FXML
    private VBox vBox2;
    @FXML
    private Pane pane;
    @FXML
    private VBox screen;
    
    private int open = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        points.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(63), 18).asString(), ";","-fx-base: rgb(100,100,",50,");"));
//        button.resize(pane.getHeight(), pane.getWidth());
        profile.resize(button.getHeight(), button.getWidth());
    }    
    
    public void initscene(){
        
        vBox.setTranslateX(300);
        vBox2.setTranslateX(screen.getLayoutX()/2 + 100);
        buttonc.setVisible(false);
        play1.prefWidthProperty().bind(vBox.widthProperty());
        play2.prefWidthProperty().bind(vBox.widthProperty());
        
        profile.setOnMouseClicked(event -> {
            if (open ==  0){
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(vBox);

                TranslateTransition slide2 = new TranslateTransition();
    //            slide2.setDuration(Duration.seconds(0.4));
    //            slide2.setNode(vBox2);

                slide.setToX(0);
                slide.play();

    //            slide2.setToX(-20);
    //            slide2.play();

                vBox.setTranslateX(300);
    //            vBox2.setTranslateX(-20);

                slide.setOnFinished((ActionEvent e) ->{
                    buttonc.setVisible(true);
                });
                open = 1;
            }else if (open == 1){
            
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(vBox);

                TranslateTransition slide2 = new TranslateTransition();
    //            slide2.setDuration(Duration.seconds(0.4));
    //            slide2.setNode(vBox2);

                slide.setToX(300);
                slide.play();

    //            slide2.setToX(80);
    //            slide2.play();

                vBox.setTranslateX(0);
    //            vBox2.setTranslateX(80);

                slide.setOnFinished((ActionEvent e) ->{
                    buttonc.setVisible(false);
                });
                open = 0;
            }
                
            
        });
        
        buttonc.setOnMouseClicked(event -> {
        });
    }
    
    public void initPlayer(Player p){
        this.player1 = p;
        points.setText(player1.getPoints() + "");
        profile.setImage(player1.getAvatar());
        play1.setText(" Salir " + player1.getNickName());
        play2.disableProperty().setValue(Boolean.TRUE);
        play2.visibleProperty().setValue(Boolean.FALSE);
        iplay1.setImage(player1.getAvatar());
    }
    
    public void initPlayer2(Player p){
        this.player2 = p;
        play2.setText(" Salir " + player2.getNickName());
        play2.disableProperty().setValue(Boolean.FALSE);
        play2.visibleProperty().setValue(Boolean.TRUE);
        iplay2.setImage(player2.getAvatar());
    }
    
    @FXML
    private void jugarIA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            Stage myStage = (Stage) this.jugarIA.getScene().getWindow();

            TableroController controlador = loader.getController();
            controlador.initializeIA(true);
            controlador.initializeP1(player1);
            stage.setMaximized(myStage.isMaximized());
            
            stage.setScene(scene);
            stage.show();

            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void jugarF(ActionEvent event) {
        try{
            if(player2 == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                IniciarSesionController controlador = loader.getController();
                controlador.initit2Player(true);
                controlador.inititPlayer1(player1);
                stage.setMaximized(myStage.isMaximized());


                stage.setScene(scene);
                stage.show();

                myStage.close();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                TableroController controlador = loader.getController();
                controlador.initializeP2(player1);
                controlador.initializeP2(player2);
                stage.setMaximized(myStage.isMaximized());


                stage.setScene(scene);
                stage.show();

                myStage.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.jugarF.getScene().getWindow();
            stage.setMaximized(myStage.isMaximized());

            stage.setScene(scene);
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void play1(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText("¿Está seguro de querer cerrar la sesión de " + player1.getNickName() + "?");  
            DialogPane dialogPane = alert.getDialogPane();
            alert.getDialogPane().getStylesheets()
                   .add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if(player2 != null && result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal3.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController3 controlador = loader.getController();
                controlador.initscene();
                controlador.initPlayer(player2);
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player2 = null;
                play1.disableProperty().setValue(Boolean.TRUE);
            }
            else if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player1 = null;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void play2(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText("¿Está seguro de querer cerrar la sesión de " + player2.getNickName() + "?");    
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initPlayer(player1);
                stage.setMaximized(myStage.isMaximized());

                stage.setScene(scene);
                stage.show();

                myStage.close();

                player2 = null;
                play1.disableProperty().setValue(Boolean.TRUE);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
