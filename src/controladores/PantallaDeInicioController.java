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
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class PantallaDeInicioController implements Initializable {

    @FXML
    private Button jugarButt;
    @FXML
    private Button ranking;
    @FXML
    private Button partidas;
    @FXML
    private Button lineas;
    @FXML
    private Button barras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partidas.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Partidas.fxml"));
                
                Parent root = loader.load();
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.partidas.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                
                PartidasController controlador = loader.getController();
                
                controlador.initMode(false);
                controlador.mostrarPartidasAct(null);

                stage.setMinHeight(500);
                stage.setMinWidth(600);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");
                
                stage.setScene(scene);
                
                stage.show();
                stage.setOnCloseRequest(e -> {
                    controlador.close();
                    e.consume();
                });
                myStage.close();
                Mediator.getInstance().fireEvent("plays");
            } catch (IOException ex) {
                Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        lineas.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Partidas.fxml"));
                
                Parent root = loader.load();
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.partidas.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                
                PartidasController controlador = loader.getController();
                
                controlador.initMode(false);
                controlador.mostrarPartidasAct(null);
                
                Mediator.getInstance().fireEvent("plays");
                stage.setMinHeight(500);
                stage.setMinWidth(600);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");
                
                stage.setScene(scene);
                
                stage.show();
                stage.setOnCloseRequest(e -> {
                    controlador.close();
                    e.consume();
                });
                
                myStage.close();
                Mediator.getInstance().fireEvent("lines");
            } catch (IOException ex) {
                Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        barras.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Partidas.fxml"));
                
                Parent root = loader.load();
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                Stage myStage = (Stage) this.partidas.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                
                PartidasController controlador = loader.getController();
                
                controlador.initMode(false);
                controlador.mostrarPartidasAct(null);
                
                Mediator.getInstance().fireEvent("plays");
                stage.setMinHeight(500);
                stage.setMinWidth(600);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage.getIcons().add(image);
                stage.setTitle("Conecta4");
                
                stage.setScene(scene);
                
                stage.show();
                stage.setOnCloseRequest(e -> {
                    controlador.close();
                    e.consume();
                });
                
                myStage.close();
                Mediator.getInstance().fireEvent("bar");
            } catch (IOException ex) {
                Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
    }    

    @FXML
    private void jugar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IniciarSesion.fxml"));
            
            Parent root = loader.load();
            
            IniciarSesionController controlador = loader.getController();
            controlador.initit2Player(false);
            controlador.initMode(false);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.jugarButt.getScene().getWindow();
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
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    } 

    @FXML
    private void ranking(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Ranking.fxml"));
            
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        Stage myStage = (Stage) this.ranking.getScene().getWindow();

        RankingController controlador = loader.getController();

        controlador.initMode(false);
        
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
    }

        
}
