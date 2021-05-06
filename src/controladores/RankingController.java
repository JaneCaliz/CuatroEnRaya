/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Connect4;
import model.Player;

/**
 * FXML Controller class
 *
 * @author ydavpacat
 */
public class RankingController implements Initializable {

    @FXML
    private TableView<Player> tablero;
    @FXML
    private TableColumn <Player, Number> ranking;
    @FXML
    private TableColumn <Player, Number> puntos;
    @FXML
    private TableColumn <Player, String> nobre;
    @FXML
    private TableColumn <Player, Image> avatar;
    
    private ObservableList<Player> player;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connect4 connect4 = Connect4.getSingletonConnect4();
            ObservableList<Player> observablePlayers;
            
            observablePlayers = FXCollections.observableList(connect4.getConnect4Ranking());
            tablero.setItems(observablePlayers);
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        avatar.setCellValueFactory(new PropertyValueFactory<Player, Image>("avatar"));
        avatar.setCellFactory(columna -> {
            return new TableCell<Player,Image> () {
                private ImageView view = new ImageView();
                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item == null || empty) setGraphic(null);
                    else{   
                        view.setFitHeight(50.0);
                        view.setFitWidth(50.0);
                        view.setImage(item);
                        setGraphic(view);
                    }
                }
            };
        });
        

    }    
    
}
