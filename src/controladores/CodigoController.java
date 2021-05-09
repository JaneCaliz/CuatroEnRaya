package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        cerrar();
    }
    
    public void cerrar(){
        Stage myStage = (Stage) this.cerrar.getScene().getWindow();
        myStage.close();
    }
    
}
