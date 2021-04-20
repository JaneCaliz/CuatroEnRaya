/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodigoController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private Text text;
            
    public static String valor = Integer.toString((int) (Math.random()*10000));
    
    private static String value = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switch(valor.length()){
            case 1:
                value = "000" + valor;
                text.setText(value);
                break;
            case 2:
                value = "00" + valor;
                text.setText(value);
                break;
            case 3:
                value = "0" + valor;
                text.setText(value);
                break;
            default:
                value = valor;
                text.setText(value);
        }
    }    
    
    public static String valor(){
        return value;
    }

    @FXML
    private void close(ActionEvent event) {
        Stage myStage = (Stage) this.cerrar.getScene().getWindow();
        myStage.close();
    }
    
}
