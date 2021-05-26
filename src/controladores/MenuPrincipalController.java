package controladores;

import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Player;

public class MenuPrincipalController implements Initializable {

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
    @FXML
    private Button prof1;
    @FXML
    private Button prof2;
    @FXML
    private ImageView iprof1;
    @FXML
    private ImageView iprof2;
    @FXML
    private Button mode;
    @FXML
    private ImageView imodo;
    boolean modoOscuro;
    Image darkmode = null, lightmode = null;
    @FXML
    private Button partidas;
    @FXML
    private Button ranking;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        points.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(63), 18).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        profile.resize(button.getHeight(), button.getWidth());
        play1.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(88), 16).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        play2.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(88), 16).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        prof1.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(88), 16).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        prof2.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(88), 16).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        mode.styleProperty().bind(Bindings.concat("-fx-font-size: ", Bindings.max(barra.widthProperty().add(barra.heightProperty()).divide(88), 16).asString(), ";","-fx-base: rgb(100,100,",50,");"));
        darkmode = new Image(getClass().getResource("/Img/moon.png").toExternalForm());
        lightmode = new Image(getClass().getResource("/Img/sun.png").toExternalForm());
    }   
    
    public void initscene() throws FileNotFoundException{
        
        vBox.setTranslateX(300);
        vBox2.setTranslateX(screen.getLayoutX()/2 + 120);
        buttonc.setVisible(false);
        play1.prefWidthProperty().bind(vBox.widthProperty());
        play2.prefWidthProperty().bind(vBox.widthProperty());
        prof1.prefWidthProperty().bind(vBox.widthProperty());
        prof2.prefWidthProperty().bind(vBox.widthProperty());
        mode.prefWidthProperty().bind(vBox.widthProperty());
        
        profile.setOnMouseClicked(event -> {
            if (open ==  0){
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(vBox);

                TranslateTransition slide2 = new TranslateTransition();

                slide.setToX(0);
                slide.play();

                vBox.setTranslateX(300);

                slide.setOnFinished((ActionEvent e) ->{
                    buttonc.setVisible(true);
                });
                open = 1;
            }else if (open == 1){
            
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(vBox);

                TranslateTransition slide2 = new TranslateTransition();

                slide.setToX(300);
                slide.play();

                vBox.setTranslateX(0);

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
        
        prof1.setText("Perfil " + player1.getNickName());
        prof2.disableProperty().setValue(Boolean.TRUE);
        prof2.visibleProperty().setValue(Boolean.FALSE);
        iprof1.setImage(player1.getAvatar());
    }
    
    public void initPlayer2(Player p){
        this.player2 = p;
        play2.setText(" Salir " + player2.getNickName());
        play2.disableProperty().setValue(Boolean.FALSE);
        play2.visibleProperty().setValue(Boolean.TRUE);
        iplay2.setImage(player2.getAvatar());
        
        prof2.setText("Perfil " + player2.getNickName());
        prof2.disableProperty().setValue(Boolean.FALSE);
        prof2.visibleProperty().setValue(Boolean.TRUE);
        iprof2.setImage(player2.getAvatar());
        
        pos(true);
    }
    
    public void pos(boolean b){
        if(b){
            mode.setTranslateY(54);
        }
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
            if(player2 != null)
                controlador.initP2(player2);
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(396);
            stage.setMinWidth(504);
            
            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setTitle("Conecta4");
            
            stage.setScene(scene);
            controlador.initMode(modoOscuro);
            stage.show();
            
            stage.setOnCloseRequest(e -> {
                try {
                    controlador.salir();
                    e.consume();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

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
                controlador.initMode(modoOscuro);
                controlador.initit2Player(true);
                controlador.inititPlayer1(player1);
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(325);
                stage.setMinWidth(425);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");

                stage.setScene(scene);
                stage.show();
                
                stage.setOnCloseRequest(e -> controlador.closeWindow());

                myStage.close();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Tablero.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                TableroController controlador = loader.getController();
                controlador.initMode(modoOscuro);
                controlador.initializeP1(player1);
                controlador.initializeP2(player2);
                
                if(player2 != null)
                    controlador.initP2(player2);
          
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(396);
                stage.setMinWidth(504);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");

                stage.setScene(scene);
                stage.show();
                
                stage.setOnCloseRequest(e -> {
                    try {
                        controlador.salir();
                        e.consume();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                myStage.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeWindow(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir del juego");
            alert.setHeaderText(" ¿Está seguro de querer salir del juego?");  
            String segundo = "";
            if(player2 != null){ segundo = " y de " + player2.getNickName();}
            alert.setContentText("Se cerrará la sesión de " + player1.getNickName() + segundo);
            alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                Stage myStage = (Stage) this.jugarF.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(500);
                stage.setMinWidth(620);

                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");
                
                stage.setScene(scene);
                stage.show();
                
                myStage.close();
                
            }   
            
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
            alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if(player2 != null && result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initscene();
                controlador.initMode(modoOscuro);
                controlador.initPlayer(player2);
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(600);
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
                stage.setMinHeight(500);
                stage.setMinWidth(620);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");

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
            alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.jugarF.getScene().getWindow();

                MenuPrincipalController controlador = loader.getController();
                controlador.initscene();
                controlador.initPlayer(player1);
                controlador.initMode(modoOscuro);
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(600);
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

                player2 = null;
                play1.disableProperty().setValue(Boolean.TRUE);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mostrarRanking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Ranking.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.jugarF.getScene().getWindow();
            
            RankingController controlador = loader.getController();
            
            controlador.initMode(modoOscuro);
            controlador.initPlayer2(player2);
            controlador.initPlayer1(player1);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(430);
            stage.setMinWidth(560);
            
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
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

    @FXML
    private void prof1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Perfil.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.prof1.getScene().getWindow();
            
            PerfilController controlador = loader.getController();
            controlador.initMode(modoOscuro);
            if(player2 != null)
                controlador.initOtro(player2, 2);
            controlador.initPlayer(player1, 1);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(450);
            stage.setMinWidth(620);
            
            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setTitle("Conecta4");

            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> {
                controlador.cerrar();
                e.consume();
            });
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void prof2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Perfil.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.prof2.getScene().getWindow();
            
            PerfilController controlador = loader.getController();
            controlador.initMode(modoOscuro);
            controlador.initOtro(player1, 1);
            controlador.initPlayer(player2, 2);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(450);
            stage.setMinWidth(620);

            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setTitle("Conecta4");
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> {
                controlador.cerrar();
                e.consume();
            });
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mode(ActionEvent event) {
        cambiar();
    }
    
    public void initMode(boolean b){
        modoOscuro = b;
        if(modoOscuro){ 
            imodo.setImage(lightmode);
            mode.setText(" Modo claro");
            
            screen.getStylesheets().remove("/Img/lightmode.css");
            screen.getStylesheets().add("/Img/darkmode.css");
            barra.getStylesheets().remove("/Img/lightmode.css");
            barra.getStylesheets().add("/Img/darkmode.css");
            vBox.getStylesheets().remove("/Img/lightmode.css");
            vBox.getStylesheets().add("/Img/darkmode.css");
            jugarF.getStylesheets().remove("/Img/lightmode.css");
            jugarF.getStylesheets().add("/Img/darkmode.css");
            jugarIA.getStylesheets().remove("/Img/lightmode.css");
            jugarIA.getStylesheets().add("/Img/darkmode.css");
            partidas.getStylesheets().remove("/Img/lightmode.css");
            partidas.getStylesheets().add("/Img/darkmode.css");
            mode.getStylesheets().remove("/Img/lightmode.css");
            mode.getStylesheets().add("/Img/darkmode.css");
            prof1.getStylesheets().remove("/Img/lightmode.css");
            prof1.getStylesheets().add("/Img/darkmode.css");
            prof2.getStylesheets().remove("/Img/lightmode.css");
            prof2.getStylesheets().add("/Img/darkmode.css");      
            play1.getStylesheets().remove("/Img/lightmode.css");
            play1.getStylesheets().add("/Img/darkmode.css");
            play2.getStylesheets().remove("/Img/lightmode.css");
            play2.getStylesheets().add("/Img/darkmode.css");
        }
        else{
            imodo.setImage(darkmode);
            mode.setText(" Modo oscuro");
            
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
            barra.getStylesheets().remove("/Img/darkmode.css");
            barra.getStylesheets().add("/Img/lightmode.css");
            vBox.getStylesheets().remove("/Img/darkmode.css");
            vBox.getStylesheets().add("/Img/lightmode.css");
            jugarF.getStylesheets().remove("/Img/darkmode.css");
            jugarF.getStylesheets().add("/Img/lightmode.css");
            jugarIA.getStylesheets().remove("/Img/darkmode.css");
            jugarIA.getStylesheets().add("/Img/lightmode.css");
            partidas.getStylesheets().remove("/Img/darkmode.css");
            partidas.getStylesheets().add("/Img/lightmode.css");
            mode.getStylesheets().remove("/Img/darkmode.css");
            mode.getStylesheets().add("/Img/lightmode.css");
            prof1.getStylesheets().remove("/Img/darkmode.css");
            prof1.getStylesheets().add("/Img/lightmode.css");
            prof2.getStylesheets().remove("/Img/darkmode.css");
            prof2.getStylesheets().add("/Img/lightmode.css");
            play1.getStylesheets().remove("/Img/darkmode.css");
            play1.getStylesheets().add("/Img/lightmode.css");
            play2.getStylesheets().remove("/Img/darkmode.css");
            play2.getStylesheets().add("/Img/lightmode.css");
        }
    }
    
    public void cambiar(){
        modoOscuro = !modoOscuro;//Ivertir
        
        if(modoOscuro){ 
            imodo.setImage(lightmode);
            
            mode.setText(" Modo claro");
            
            screen.getStylesheets().remove("/Img/lightmode.css");
            screen.getStylesheets().add("/Img/darkmode.css");
            barra.getStylesheets().remove("/Img/lightmode.css");
            barra.getStylesheets().add("/Img/darkmode.css");
            vBox.getStylesheets().remove("/Img/lightmode.css");
            vBox.getStylesheets().add("/Img/darkmode.css");
            jugarF.getStylesheets().remove("/Img/lightmode.css");
            jugarF.getStylesheets().add("/Img/darkmode.css");
            jugarIA.getStylesheets().remove("/Img/lightmode.css");
            jugarIA.getStylesheets().add("/Img/darkmode.css");
            partidas.getStylesheets().remove("/Img/lightmode.css");
            partidas.getStylesheets().add("/Img/darkmode.css");
            mode.getStylesheets().remove("/Img/lightmode.css");
            mode.getStylesheets().add("/Img/darkmode.css");
            prof1.getStylesheets().remove("/Img/lightmode.css");
            prof1.getStylesheets().add("/Img/darkmode.css");
            prof2.getStylesheets().remove("/Img/lightmode.css");
            prof2.getStylesheets().add("/Img/darkmode.css");      
            play1.getStylesheets().remove("/Img/lightmode.css");
            play1.getStylesheets().add("/Img/darkmode.css");
            play2.getStylesheets().remove("/Img/lightmode.css");
            play2.getStylesheets().add("/Img/darkmode.css");
        }
        else{
            imodo.setImage(darkmode);
            
            mode.setText(" Modo oscuro");
            
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
            barra.getStylesheets().remove("/Img/darkmode.css");
            barra.getStylesheets().add("/Img/lightmode.css");
            vBox.getStylesheets().remove("/Img/darkmode.css");
            vBox.getStylesheets().add("/Img/lightmode.css");
            jugarF.getStylesheets().remove("/Img/darkmode.css");
            jugarF.getStylesheets().add("/Img/lightmode.css");
            jugarIA.getStylesheets().remove("/Img/darkmode.css");
            jugarIA.getStylesheets().add("/Img/lightmode.css");
            partidas.getStylesheets().remove("/Img/darkmode.css");
            partidas.getStylesheets().add("/Img/lightmode.css");
            mode.getStylesheets().remove("/Img/darkmode.css");
            mode.getStylesheets().add("/Img/lightmode.css");
            prof1.getStylesheets().remove("/Img/darkmode.css");
            prof1.getStylesheets().add("/Img/lightmode.css");
            prof2.getStylesheets().remove("/Img/darkmode.css");
            prof2.getStylesheets().add("/Img/lightmode.css");
            play1.getStylesheets().remove("/Img/darkmode.css");
            play1.getStylesheets().add("/Img/lightmode.css");
            play2.getStylesheets().remove("/Img/darkmode.css");
            play2.getStylesheets().add("/Img/lightmode.css");
        }
    }

    @FXML
    private void verPartidasAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Partidas.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.jugarF.getScene().getWindow();
            
            PartidasController controlador = loader.getController();
            
            controlador.initMode(modoOscuro);
            controlador.initPlayer2(player2);
            controlador.initPlayer1(player1);
            controlador.mostrarPartidasAct(null);
            
            stage.setMaximized(myStage.isMaximized());
            stage.setMinHeight(500);
            stage.setMinWidth(600);
            
            Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
            stage.getIcons().add(image);
            stage.setTitle("Conecta4");
            
            stage.setScene(scene);
            
            stage.show();
            
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verRankingAction(ActionEvent event) {
        mostrarRanking(event);
    }
}
