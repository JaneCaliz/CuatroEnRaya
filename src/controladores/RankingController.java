/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Connect4;
import model.Player;
import model.Round;
import org.controlsfx.control.textfield.TextFields;

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
    
    @FXML
    private Button volverB;
    @FXML
    private TextField nombreTF;
    boolean modoOscuro;
    @FXML
    private VBox screen;
    @FXML
    private Pane line;
    @FXML
    private GridPane head;
    
    public void initPlayer1(Player p1){
        this.player1 = p1;
    }
    
    public void initPlayer2(Player p2){
        this.player2 = p2;
    }
    
    public void initMode(boolean b){
        modoOscuro = b;
        
        if(modoOscuro){ 
            screen.getStylesheets().remove("/Img/lightmode.css");
            screen.getStylesheets().add("/Img/darkmode.css");
            tablero.getStylesheets().remove("/Img/lightmode.css");
            tablero.getStylesheets().add("/Img/darkmode.css");
            volverB.getStylesheets().remove("/Img/lightmode.css");
            volverB.getStylesheets().add("/Img/darkmode.css");
            nombreTF.getStylesheets().remove("/Img/lightmode.css");
            nombreTF.getStylesheets().add("/Img/darkmode.css");
            line.setStyle("-fx-stroke: #414141;" + "-fx-background-color: #414141;");
            head.getStylesheets().remove("/Img/lightmode.css");
            head.getStylesheets().add("/Img/darkmode.css");
        }
        else{
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
            tablero.getStylesheets().remove("/Img/darkmode.css");
            tablero.getStylesheets().add("/Img/lightmode.css");
            volverB.getStylesheets().remove("/Img/darkmode.css");
            volverB.getStylesheets().add("/Img/lightmode.css");
            nombreTF.getStylesheets().remove("/Img/darkmode.css");
            nombreTF.getStylesheets().add("/Img/lightmode.css");
            line.setStyle("-fx-stroke: black;" + "-fx-background-color: black;");
            head.getStylesheets().remove("/Img/darkmode.css");
            head.getStylesheets().add("/Img/lightmode.css");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            autoCompletar();
        } catch (IOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connect4 connect4 = Connect4.getSingletonConnect4();
            ObservableList<Player> observablePlayers;
            observablePlayers = FXCollections.observableList(connect4.getConnect4Ranking());
            FilteredList<Player> filteredData = new FilteredList<>(observablePlayers, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		nombreTF.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getNickName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else   
				    	 return false; // Does not match.
			});
		});
                
            SortedList<Player> sortedData = new SortedList<>(filteredData);
            
            sortedData.comparatorProperty().bind(tablero.comparatorProperty());

            
            tablero.setItems(sortedData);
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
                        view.fitWidthProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getWidth() + 40))
                                   .then(columna.widthProperty().subtract(40))
                                   .otherwise(item.getWidth()), 60));
                        view.fitHeightProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getHeight()+ 30))
                                   .then(columna.widthProperty().subtract(30))
                                   .otherwise(item.getHeight()),70));
                        view.setImage(item);
                        setGraphic(view);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });
        avatarC.setMaxWidth(1500);
        avatarC.impl_setReorderable(false);

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
        nombreC.impl_setReorderable(false);
        
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
        puntosC.impl_setReorderable(false);
        
        rankingC.setCellValueFactory(new Callback<CellDataFeatures<Player, Player>, ObservableValue<Player>>() {
            @Override public ObservableValue<Player> call(CellDataFeatures<Player, Player> p) {
              return new ReadOnlyObjectWrapper(p.getValue());
            }
        });
        
        rankingC.setCellFactory(new Callback<TableColumn<Player, Player>, TableCell<Player, Player>>() {
          @Override public TableCell<Player, Player> call(TableColumn<Player, Player> param) {
            return new TableCell<Player, Player>() {
              @Override protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (this.getTableRow() != null && item != null) {
                        setText(this.getTableRow().getIndex()+1+"");
                        setAlignment(Pos.CENTER);

                } else {
                    setText("");
                }
              }
            };
          }
        });
        rankingC.setSortable(false);
        rankingC.impl_setReorderable(false);
        String css = this.getClass().getResource("/Img/lightmode.css").toExternalForm();
        tablero.getStylesheets().add(css);
        
        rankingC.prefWidthProperty().bind(tablero.widthProperty().multiply(0.15).subtract(6));
        avatarC.prefWidthProperty().bind(tablero.widthProperty().multiply(0.25).subtract(5));
        nombreC.prefWidthProperty().bind(tablero.widthProperty().multiply(0.35).subtract(5));
        puntosC.prefWidthProperty().bind(tablero.widthProperty().multiply(0.25).subtract(5));
        
    }

    @FXML
    private void volverMP(ActionEvent event) {
        closeWindow();
    }
    
    public void closeWindow(){
        if(player1 != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuPrincipal.fxml"));

                Parent root = loader.load();

                MenuPrincipalController controlador = loader.getController();
                controlador.initMode(modoOscuro);
                controlador.initscene();
                controlador.initPlayer(player1);

                if (player2 != null)
                    controlador.initPlayer2(player2);

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                Stage myStage = (Stage) this.volverB.getScene().getWindow();
                stage.setMaximized(myStage.isMaximized());
                stage.setMinHeight(520);
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
            } catch (IOException ex) {
                Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/PantallaDeInicio.fxml"));
                
                Parent root2 = loader2.load();
                
                Stage myStage = (Stage) this.volverB.getScene().getWindow();
                Scene scene2 = new Scene(root2);
                Stage stage2 = new Stage();
                stage2.setScene(scene2);
                stage2.setMinHeight(300);
                stage2.setMinWidth(250);
                
                Image image = new Image(getClass().getResource("/Img/Logo.png").toExternalForm());
                stage2.getIcons().add(image);
                stage2.setTitle("Conecta4");
                
                stage2.show();
                myStage.close();
            } catch (IOException ex) {
                Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void autoCompletar() throws IOException{
        try {
            Connect4 BD = Connect4.getSingletonConnect4();
            List<Player> rankingList = BD.getConnect4Ranking();
            
            Player[] players = rankingList.toArray(new Player[0]);
            
            String[] listaPlayers = new String[players.length];
            
            for(int i = 0; i < players.length; i++){
                listaPlayers[i] = players[i].getNickName();                
            }
            
            List<String> jugadores = Arrays.asList(listaPlayers);
            Collections.sort(jugadores);
                        
            TextFields.bindAutoCompletion(nombreTF,jugadores);
            
        } catch (Connect4DAOException ex) {
            Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

}

