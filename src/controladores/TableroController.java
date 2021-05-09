package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Player;


public class TableroController implements Initializable {

    @FXML
    private Circle Circle_0_0;
    @FXML
    private Circle Circle_0_4;
    @FXML
    private Circle Circle_0_3;
    @FXML
    private Circle Circle_0_2;
    @FXML
    private Circle Circle_0_1;
    @FXML
    private Circle Circle_0_5;
    @FXML
    private Circle Circle_0_6;
    @FXML
    private Circle Circle_1_6;
    @FXML
    private Circle Circle_1_3;
    @FXML
    private Circle Circle_1_0;
    @FXML
    private Circle Circle_2_4;
    @FXML
    private Circle Circle_2_3;
    @FXML
    private Circle Circle_2_2;
    @FXML
    private Circle Circle_2_1;
    @FXML
    private Circle Circle_2_0;
    @FXML
    private Circle Circle_1_5;
    @FXML
    private Circle Circle_1_4;
    @FXML
    private Circle Circle_1_2;
    @FXML
    private Circle Circle_1_1;
    @FXML
    private Circle Circle_2_5;
    @FXML
    private Circle Circle_4_1;
    @FXML
    private Circle Circle_4_0;
    @FXML
    private Circle Circle_3_6;
    @FXML
    private Circle Circle_3_5;
    @FXML
    private Circle Circle_3_4;
    @FXML
    private Circle Circle_3_3;
    @FXML
    private Circle Circle_3_2;
    @FXML
    private Circle Circle_3_1;
    @FXML
    private Circle Circle_3_0;
    @FXML
    private Circle Circle_2_6;
    @FXML
    private Circle Circle_6_2;
    @FXML
    private Circle Circle_6_1;
    @FXML
    private Circle Circle_6_0;
    @FXML
    private Circle Circle_5_6;
    @FXML
    private Circle Circle_5_5;
    @FXML
    private Circle Circle_5_4;
    @FXML
    private Circle Circle_5_1;
    @FXML
    private Circle Circle_5_0;
    @FXML
    private Circle Circle_4_6;
    @FXML
    private Circle Circle_4_5;
    @FXML
    private Circle Circle_4_4;
    @FXML
    private Circle Circle_4_3;
    @FXML
    private Circle Circle_4_2;
    @FXML
    private Circle Circle_5_3;
    @FXML
    private Circle Circle_5_2;
    @FXML
    private Circle Circle_7_2;
    @FXML
    private Circle Circle_7_1;
    @FXML
    private Circle Circle_7_0;
    @FXML
    private Circle Circle_6_6;
    @FXML
    private Circle Circle_6_5;
    @FXML
    private Circle Circle_6_4;
    @FXML
    private Circle Circle_6_3;
    @FXML
    private Circle Circle_7_3;
    @FXML
    private Circle Circle_7_4;
    @FXML
    private Circle Circle_7_5;
    @FXML
    private Circle Circle_7_6;
    
    public Conecta4 tablero;
    @FXML
    private GridPane gPane;
    @FXML
    private Pane exit;
    
    private int victoria;
    @FXML
    private Text p1;
    @FXML
    private Text p2;
    
    private boolean IA;
    
    private Player player2, player1, initp2;
    @FXML
    private HBox barra;
    private Circle circle;
    @FXML
    private Circle circler;
    @FXML
    private Circle circley;
    @FXML
    private VBox vBox0;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    @FXML
    private VBox vBox3;
    @FXML
    private VBox vBox4;
    @FXML
    private VBox vBox5;
    @FXML
    private VBox vBox6;
    @FXML
    private VBox vBox7;
    @FXML
    private Pane reset;
    boolean modo;
    @FXML
    private VBox screen;    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablero = new Conecta4();
        victoria = 0;
        binding();   
    }
    
    public void initializeIA(boolean IA){
        this.IA = IA;
        if (IA){
            p2.setText("IA");
        }
    }
    
    public void initializeP1(Player p){
        this.player1 = p ;
        p1.setText(player1.getNickName());
    }
    
    public void initializeP2(Player p){
        this.player2 = p ;
        p2.setText(player2.getNickName());
    }
    
    public void initP2(Player p){
        this.initp2 = p;
    }
    
    public void initMode(boolean b){
        modo = b;

        if(b){ 
            screen.getStylesheets().remove("/Img/lightmode.css");
            screen.getStylesheets().add("/Img/darkmode.css");
            barra.getStylesheets().remove("/Img/lightmode.css");
            barra.getStylesheets().add("/Img/darkmode.css");
            p1.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(255,255,",255,");"));
            p2.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(255,255,",255,");"));
//            System.out.println("Modo oscuro tablero");
        }
        else{
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
            barra.getStylesheets().remove("/Img/darkmode.css");
            barra.getStylesheets().add("/Img/lightmode.css");
            p1.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(0,0,",0,");"));
            p2.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(0,0,",0,");"));
//            System.out.println("Modo claro tablero");
        }
    }
    
    @FXML
    private void colocarFicha(MouseEvent event) {
        
        if (victoria == 0){
            int columna = 0;
            try{
                columna = GridPane.getColumnIndex(((VBox)event.getSource()));
            }catch(Exception e){}
            
            double size = gPane.widthProperty().add(gPane.heightProperty()).divide(50).getValue();
            
    //        Colocar la ficha y la muestra en la interfaz 
 
            int filaColocado = tablero.ponFicha(columna);
           
                  
            if (tablero.jugador == 1 && filaColocado!= -1){
                cambiarColor(columna, filaColocado,"RED"); 
                p1.setFont(Font.font("Style", FontWeight.SEMI_BOLD, size));
                p2.setFont(Font.font("Style", FontWeight.BLACK, size));
                
            }
            else if(tablero.jugador == 2 && filaColocado!= -1){
                cambiarColor(columna, filaColocado,"YELLOW");
                p2.setFont(Font.font("Style", FontWeight.SEMI_BOLD, size));
                p1.setFont(Font.font("Style", FontWeight.BLACK, size));
            }
            boolean lleno = false;
            if (filaColocado!= -1){
                lleno = tablero.estaLleno();
                victoria = tablero.victoria(filaColocado, columna);
            }

            if (victoria == 1){
                if(tablero.jugador == 1){
                    if(!IA){
                        resultado("P1");
                    }else{
                        resultado("P1 IA");
                    }
                }else if (tablero.jugador == 2){
                    resultado("P2");
                }
               
            }else if (lleno){
                    resultado("Empate");
            }else if(IA){
                colocarFichaIA();
            }
        }
    }
    
    private void resultado (String resultado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Resultado.fxml"));
            
            Parent root = loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.p1.getScene().getWindow();
            
            ResultadoController controlador = loader.getController();
            controlador.initMode(modo);
            controlador.initPlayer(player1);
            controlador.initPlayer2(player2);
            controlador.initRes(resultado);
            controlador.initIA(IA);
            controlador.initMax(myStage.isMaximized());
            
            if(initp2 != null)
                controlador.initPlayer2(initp2);
            
            stage.setOnHiding(e -> this.closeWindow());
            stage.setOnCloseRequest(e -> abrir());
            
            stage.setMinHeight(235);
            stage.setMinWidth(Double.max(424, controlador.width() + 60.0));

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void colocarFichaIA(){
        Random rand = new Random();
        int randomColum = rand.nextInt(8);
        p2.setFont(Font.font("Style", FontWeight.SEMI_BOLD, gPane.widthProperty().add(gPane.heightProperty()).divide(50).getValue()));
        p1.setFont(Font.font("Style", FontWeight.BLACK, gPane.widthProperty().add(gPane.heightProperty()).divide(50).getValue()));
        int filaColocado = tablero.ponFicha(randomColum );
            if (tablero.jugador == 1 && filaColocado!= -1)
                cambiarColor(randomColum , filaColocado,"RED");
            else if(tablero.jugador == 2 && filaColocado!= -1)
                cambiarColor(randomColum , filaColocado,"YELLOW");
        boolean lleno = false;    
        if (filaColocado!= -1){
            victoria = tablero.victoria(filaColocado, randomColum );
            lleno = tablero.estaLleno();
        }else
            colocarFichaIA();

        if (victoria == 1 && filaColocado!= -1){
                resultado ("IA");
        }else if (lleno && filaColocado!= -1){
                    resultado("Empate");  
        }
    }
    
    public void closeWindow (){
        Stage stage = (Stage) p1.getScene().getWindow();
        stage.close();
    }
    
    public void cambiarColor (int x, int y, String color){
    
        switch(x){
         case 0:
            switch(y){
                case 0:
                Circle_0_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_0_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_0_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_0_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_0_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_0_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_0_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 1:
            switch(y){
                case 0:           
                Circle_1_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_1_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_1_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_1_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_1_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_1_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_1_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 2 :
            switch(y){
                case 0:            
                Circle_2_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_2_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_2_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_2_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_2_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_2_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_2_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 3 : 
            switch(y){
                case 0:           
                Circle_3_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_3_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_3_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_3_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_3_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_3_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_3_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 4 : 
            switch(y){
                case 0:           
                Circle_4_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_4_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_4_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_4_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_4_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_4_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_4_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 5 : 
            switch(y){
                case 0:           
                Circle_5_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_5_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_5_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_5_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_5_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_5_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_5_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 6 :
            switch(y){
                case 0:           
                Circle_6_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_6_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_6_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_6_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_6_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_6_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_6_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        case 7 : 
            switch(y){
                case 0:           
                Circle_7_0.setFill(Paint.valueOf(color));
                break;
                case 1:
                Circle_7_1.setFill(Paint.valueOf(color));
                break;
                case 2:
                Circle_7_2.setFill(Paint.valueOf(color));
                break;
                case 3:
                Circle_7_3.setFill(Paint.valueOf(color));
                break;
                case 4:
                Circle_7_4.setFill(Paint.valueOf(color));
                break;
                case 5:
                Circle_7_5.setFill(Paint.valueOf(color));
                break;
                case 6:
                Circle_7_6.setFill(Paint.valueOf(color));
                break;
            }
            break;
        }
    
    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
        salir();
    }    
    
    public void salir() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Abandonar partida");
        alert.setHeaderText(" ¿Está seguro de querer abandonar la partida?");
        alert.setContentText("Se perderá todo el progreso de la partida actual");      
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert.css").toExternalForm());
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

            Parent root = loader.load();

            MenuPrincipalController controlador = loader.getController();
            controlador.initMode(modo);
            controlador.initscene();
            controlador.initPlayer(player1);

            if (player2 != null)
                controlador.initPlayer2(player2);
            
            if(initp2 != null)
                controlador.initPlayer2(initp2);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.exit.getScene().getWindow();
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
        }
    }
    
    private void binding(){
        Circle_0_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_0_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_1_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_2_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_3_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_4_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_5_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_6_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_0.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_1.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_2.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_3.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_4.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_5.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        Circle_7_6.radiusProperty().bind(Bindings.min(gPane.widthProperty().divide(18), gPane.heightProperty().divide(16)));
        
        vBox0.spacingProperty().bind(Circle_0_0.radiusProperty().divide(8));
        vBox1.spacingProperty().bind(Circle_1_0.radiusProperty().divide(8));
        vBox2.spacingProperty().bind(Circle_2_0.radiusProperty().divide(8));
        vBox3.spacingProperty().bind(Circle_3_0.radiusProperty().divide(8));
        vBox4.spacingProperty().bind(Circle_4_0.radiusProperty().divide(8));
        vBox5.spacingProperty().bind(Circle_5_0.radiusProperty().divide(8));
        vBox6.spacingProperty().bind(Circle_6_0.radiusProperty().divide(8));
        vBox7.spacingProperty().bind(Circle_7_0.radiusProperty().divide(8));
        
        circler.radiusProperty().bind(Circle_0_0.radiusProperty().divide(5));
        circley.radiusProperty().bind(Circle_0_0.radiusProperty().divide(5));
        
//        p1.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(100,100,",50,");"));
//        p2.styleProperty().bind(Bindings.concat("-fx-font-size: ", gPane.widthProperty().add(gPane.heightProperty()).divide(50).asString(), ";","-fx-fill: rgb(100,100,",50,");"));
        
        exit.prefHeightProperty().bind(barra.heightProperty());
    }

    @FXML
    private void reset(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reiniciar partida");
        alert.setHeaderText(" ¿Está seguro de querer reiniciar la partida?");  
        alert.setContentText("Se perderá todo el progreso de la partida actual");   
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/Img/alert.css").toExternalForm());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            blanco();
        }
    }
    
    public void blanco(){
        
        for(int i = 0; i<8; i++){
            for(int j = 0; j<7; j++){
                cambiarColor(i, j, "#ffffff");
            }
        }
        tablero.vacia();
        
        double size = gPane.widthProperty().add(gPane.heightProperty()).divide(50).getValue();
        p2.setFont(Font.font("Style", FontWeight.SEMI_BOLD, size));
        p1.setFont(Font.font("Style", FontWeight.BLACK, size));
    }
    
    private void abrir(){
        try {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

            Parent root2 = loader2.load();

            MenuPrincipalController controlador2 = loader2.getController();
            controlador2.initMode(modo);
            controlador2.initscene();
            controlador2.initPlayer(player1);
            if (player2 != null)
                controlador2.initPlayer2(player2);
            
            if(initp2 != null)
                controlador2.initPlayer2(initp2);

            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();

            Stage myStage2 = (Stage) this.exit.getScene().getWindow();
            stage2.setMaximized(myStage2.isMaximized());
            stage2.setMinHeight(520);
            stage2.setMinWidth(460);

            stage2.setScene(scene2);
            stage2.show();
            stage2.setOnCloseRequest(e -> {
                controlador2.closeWindow();
                e.consume();
            });

            myStage2.close();
        } catch (IOException ex) {
            Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
