/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


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
    private ImageView exit;
    
    private int victoria;
    
    private boolean IA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablero = new Conecta4();
        victoria = 0;
        
        if(IA)
            colocarFichaIA();
        // TODO
    }
    
    public void initializeIA(boolean IA){
        this.IA = IA;
    }
    
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    @FXML
    private void colocarFicha(MouseEvent event) {
        
//        int fila = gPane.getRowIndex(((Circle)event.getSource()));
        System.out.println("Colocando ficha");
        if (victoria == 0){
            int columna = 0;
            try{
             columna = GridPane.getColumnIndex(((Circle)event.getSource()));
            }catch(Exception e){}

    //        Colocar la ficha y la muestra en la interfaz 
            int filaColocado = tablero.ponFicha(columna);
            if (tablero.jugador == 1)
                cambiarColor(columna, filaColocado,"RED");
            else if(tablero.jugador == 2)
                cambiarColor(columna, filaColocado,"YELLOW");

            victoria = tablero.victoria(filaColocado, columna);

            if (victoria == 1){
                System.out.println("Ha ganado el jugador: " + tablero.jugador);
            }else if(IA){
                colocarFichaIA();
                System.out.println("Turno IA");
            }
        }
        
    }
    
    public void colocarFichaIA(){
        Random rand = new Random();
        int randomColum = rand.nextInt(8 + 1);
        int filaColocado = tablero.ponFicha(randomColum );
            if (tablero.jugador == 1)
                cambiarColor(randomColum , filaColocado,"RED");
            else if(tablero.jugador == 2)
                cambiarColor(randomColum , filaColocado,"YELLOW");
        victoria = tablero.victoria(filaColocado, randomColum );

        if (victoria == 1){
                System.out.println("Has perdido");
        }
        
        
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
        Stage myStage = (Stage) this.exit.getScene().getWindow();
        myStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    
    
}
