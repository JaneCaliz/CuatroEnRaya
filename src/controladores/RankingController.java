/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private ObservableList<Player> player;
    @FXML
    private TableColumn<Player, Player> rankingC;
    @FXML
    private TableColumn<Player, Integer> puntosC;
    @FXML
    private TableColumn<Player, String> nombreC;
    @FXML
    private TableColumn<Player, Image> avatarC;
    
    private Player player1, player2;
    
    private static int rank;
    @FXML
    private Button volverB;
    
    public void initPlayer1(Player p1){
        this.player1 = p1;
    }
    
    public void initPlayer2(Player p2){
        this.player2 = p2;
    }
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

        avatarC.setCellValueFactory(new PropertyValueFactory<Player, Image>("avatar"));
        avatarC.setCellFactory(columna -> {
            return new TableCell<Player, Image>() {
                private ImageView view = new ImageView();

                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
//                        view.setFitHeight(50.0);
//                        view.setFitWidth(50.0);
                        view.fitWidthProperty().bind(
                            Bindings.when(columna.widthProperty().lessThan(item.getWidth() + 40))
                                   .then(columna.widthProperty().subtract(40))
                                   .otherwise(item.getWidth()));
                        view.fitHeightProperty().bind(
                            Bindings.when(columna.widthProperty().lessThan(item.getHeight()+ 30))
                                   .then(columna.widthProperty().subtract(30))
                                   .otherwise(item.getHeight()));
                        view.setImage(item);
                        setGraphic(view);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });
        avatarC.setMaxWidth(1500);

        nombreC.setCellValueFactory(new PropertyValueFactory<Player, String>("nickName"));
        nombreC.setCellFactory(columna -> {
            return new TableCell<Player, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });
        
        
        puntosC.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));
        puntosC.setCellFactory(columna -> {
            return new TableCell<Player,Integer> () {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item == null || empty) setText(null);
                    else{  
                        setText(String.valueOf(item));
                        setAlignment(Pos.CENTER);
                        }
                }
            };
        });
        
        rankingC.setCellValueFactory(new Callback<CellDataFeatures<Player, Player>, ObservableValue<Player>>() {
            @Override public ObservableValue<Player> call(CellDataFeatures<Player, Player> p) {
              return new ReadOnlyObjectWrapper(p.getValue());
            }
        });
        rank = 0;
        rankingC.setCellFactory(new Callback<TableColumn<Player, Player>, TableCell<Player, Player>>() {
          @Override public TableCell<Player, Player> call(TableColumn<Player, Player> param) {
            return new TableCell<Player, Player>() {
              @Override protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (this.getTableRow() != null && item != null) {
                  setText(rank + "");
                  rank = rank + 1;
                    setAlignment(Pos.CENTER);
                } else {
                  setText("");
                }
              }
            };
          }
        });
        rankingC.setSortable(false);
        String css = this.getClass().getResource("/Img/ranking.css").toExternalForm();
        tablero.getStylesheets().add(css);

    }

    @FXML
    private void volverMP(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            Stage myStage = (Stage) this.volverB.getScene().getWindow();
            stage.setMinHeight(520);
            stage.setMinWidth(460);
            
            MenuPrincipalController controlador = loader.getController();
            controlador.initscene();
            controlador.initPlayer(player1);
            if (player2 != null)
                controlador.initPlayer2(player2);
            stage.setMaximized(myStage.isMaximized());
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> {
                controlador.closeWindow();
                e.consume();
            });
            
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
