package controladores;

import DBAccess.Connect4DAOException;
import controladores.RankingController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TabPane;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4;
import model.DayRank;
import model.Player;
import model.Round;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class PartidasController implements Initializable {

    @FXML
    private TableView<Round> partidasTablero;
    @FXML
    private TableColumn<Round, LocalDateTime> FechaYHoraC;
    @FXML
    private TableColumn<Round, Player> GanadorC;
    @FXML
    private TableColumn<Round, Player> PerdedorC;
    
    private Player player1, player2;
    @FXML
    private DatePicker fechaIniDP;
    @FXML
    private DatePicker fechaFinDP;
    @FXML

    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis();

    @FXML private LineChart<String, Number> graficaLineas;
    @FXML
    private StackedBarChart<String, Number> graficaBarrasApiladas;
    @FXML
    private NumberAxis yAxisBA;
    @FXML
    private CategoryAxis xAxisBA;
    @FXML
    private NumberAxis yAxisB;
    @FXML
    private CategoryAxis xAxisB;
    @FXML
    private BarChart<String, Number> graficaBarras;
    @FXML
    private RadioMenuItem ganYperRM;
    @FXML
    private RadioMenuItem ganRM;
    @FXML
    private RadioMenuItem perRM;
    @FXML
    private DatePicker pFechaIniDP;
    @FXML
    private DatePicker pFechaFinDP;
    @FXML
    private TextField pNombreTF;
    @FXML
    private MenuButton resultado;
    
    private AutoCompletionBinding<String> autoCompletar;
    @FXML
    private ToggleGroup result;
    boolean modoOscuro;
    @FXML
    private TabPane screen;
    @FXML
    private TextField pNombreTF1;
    @FXML
    private DatePicker fechaIniDP1;
    @FXML
    private DatePicker fechaFinDP1;
    @FXML
    private Button closeWindow;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            graficaLineas.setAnimated(false);
            graficaBarrasApiladas.setAnimated(false);
            graficaBarras.setAnimated(false);
            
            pFechaIniDP.prefWidthProperty().bind(Bindings.max(partidasTablero.widthProperty().divide(3).subtract(5), 220));
            pFechaFinDP.prefWidthProperty().bind(Bindings.max(partidasTablero.widthProperty().divide(3).subtract(5), 220));
            pNombreTF.prefWidthProperty().bind(Bindings.max(partidasTablero.widthProperty().divide(3).subtract(5), 220));
            resultado.prefWidthProperty().bind(Bindings.max(partidasTablero.widthProperty().divide(3).subtract(5), 220));
            
            autoCompletar();
            mostraGraficaAct(null);
            fechaFinDP.valueProperty().addListener((ov, oldValue, newValue) -> {
                mostraGraficaAct(null);
            });
            fechaIniDP.valueProperty().addListener((ov, oldValue, newValue) -> {
                mostraGraficaAct(null);
            });
            pFechaFinDP.valueProperty().addListener((ov, oldValue, newValue) -> {
                mostrarPartidasAct(null);
            });
            pFechaIniDP.valueProperty().addListener((ov, oldValue, newValue) -> {
                mostrarPartidasAct(null);
            });
            ganYperRM.setSelected(true);
            ganYperRM.selectedProperty().addListener((ov, oldValue, newValue) -> {
                mostrarPartidasAct(null);
            });
            pNombreTF1.textProperty().addListener((ov, oldValue, newValue) -> {
                Connect4 db = null;
                    try {
                        db = Connect4.getSingletonConnect4();
                    } catch (Connect4DAOException ex) {
                        Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (db.getPlayer(newValue) != null){
                        mostrarGraficasBarras();
                    }
            });
            fechaFinDP1.valueProperty().addListener((ov, oldValue, newValue) -> {
                Connect4 db = null;
                    try {
                        db = Connect4.getSingletonConnect4();
                    } catch (Connect4DAOException ex) {
                        Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (db.getPlayer(pNombreTF1.getText()) != null){
                        mostrarGraficasBarras();
                    }
            });
            fechaIniDP1.valueProperty().addListener((ov, oldValue, newValue) -> {
                Connect4 db = null;
                    try {
                        db = Connect4.getSingletonConnect4();
                    } catch (Connect4DAOException ex) {
                        Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (db.getPlayer(pNombreTF1.getText()) != null){
                        mostrarGraficasBarras();
                    }
            });
        } catch (IOException ex) {
                Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
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
        }
        else{
            screen.getStylesheets().remove("/Img/darkmode.css");
            screen.getStylesheets().add("/Img/lightmode.css");
        }
    }

    public void mostrarPartidasAct(ActionEvent event) {

        try {
            Connect4 connect4 = Connect4.getSingletonConnect4();
            ObservableList<Round> observablRounds;
            
            ArrayList<Player> listaJugadores = connect4.getConnect4Ranking();
            ArrayList<Round> listaPartidas = new ArrayList<>();
            for(Player p : listaJugadores){
                List <Round> listTwoCopy = new ArrayList<>(connect4.getRoundsPlayer(p));
                listTwoCopy.removeAll(listaPartidas);
                listaPartidas.addAll(listTwoCopy);
            }
              LocalDate fechafin = pFechaFinDP.getValue();
              LocalDate fechaIni = pFechaIniDP.getValue();
               if (fechaIni == null)
                   fechaIni = LocalDate.MIN;
               if (fechafin == null)
                   fechafin = LocalDate.now();

             for (Iterator<Round> iter = listaPartidas.iterator(); iter.hasNext();) {
                   LocalDate aux = iter.next().getLocalDate();
                   if (aux.compareTo(fechaIni) < 0 || aux.compareTo(fechafin) > 0 ) {
                       iter.remove();
                   }
               }

            Collections.sort(listaPartidas);
            Collections.reverse(listaPartidas);
            observablRounds = FXCollections.observableList(listaPartidas);
            FilteredList<Round> filteredData = new FilteredList<>(observablRounds, b -> true);
            pNombreTF.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
		
				if (employee.getWinner().getNickName().equals(newValue) && (ganYperRM.isSelected() || ganRM.isSelected())) {
					return true; 
				} else 
                                if (employee.getLoser().getNickName().equals(newValue) && (ganYperRM.isSelected() || perRM.isSelected())) {
					return true; 
				} else   
				    	 return false; 
			});
                        partidasTablero.refresh();
		});
            ganYperRM.selectedProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (pNombreTF.getText() == "") {
					return true;
				}
		
				if (employee.getWinner().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || ganRM.isSelected())) {
					return true; 
				} else 
                                if (employee.getLoser().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || perRM.isSelected())) {
					return true; 
				} else   
				    	 return false; 
			});
                        partidasTablero.refresh();
		});
            ganRM.selectedProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (pNombreTF.getText() == "") {
					return true;
				}
		
				if (employee.getWinner().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || ganRM.isSelected())) {
					return true; 
				} else 
                                if (employee.getLoser().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || perRM.isSelected())) {
					return true; 
				} else   
				    	 return false; 
			});
                        partidasTablero.refresh();
		});
            perRM.selectedProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (pNombreTF.getText() == "") {
					return true;
				}
		
				if (employee.getWinner().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || ganRM.isSelected())) {
					return true; 
				} else 
                                if (employee.getLoser().getNickName().equals(pNombreTF.getText()) && (ganYperRM.isSelected() || perRM.isSelected())) {
					return true; 
				} else   
				    	 return false; 
			});
                        partidasTablero.refresh();
		});
                
            SortedList<Round> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(partidasTablero.comparatorProperty());
            partidasTablero.setItems(sortedData);
            
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GanadorC.setCellValueFactory(new PropertyValueFactory<Round, Player>("winner"));
        GanadorC.setCellFactory(columna -> {
            return new TableCell<Round, Player>() {
                private ImageView view = new ImageView();
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        HBox hbox = new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        
                        Text text = new Text("    "+item.getNickName());
                        if(modoOscuro){
                            text.setStyle("-fx-fill: #ffffff;");
                        }
                        else{
                            text.setStyle("-fx-fill: #000000;");
                        }
                        hbox.getChildren().add(view);
                        hbox.getChildren().add(text);
                        
                        setGraphic(hbox);
                        
                        view.fitWidthProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getAvatar().getWidth() + 40))
                                   .then(columna.widthProperty().subtract(40))
                                   .otherwise(item.getAvatar().getWidth()), 60));
                        view.fitHeightProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getAvatar().getHeight()+ 30))
                                   .then(columna.widthProperty().subtract(30))
                                   .otherwise(item.getAvatar().getHeight()),70));
                        view.setImage(item.getAvatar());                       
                        setAlignment(Pos.CENTER_RIGHT);
                    }
                }
            };
        });
        GanadorC.prefWidthProperty().bind(partidasTablero.widthProperty().divide(3).subtract(7));
        
        PerdedorC.setCellValueFactory(new PropertyValueFactory<Round, Player>("loser"));
        PerdedorC.setCellFactory(columna -> {
            return new TableCell<Round, Player>() {
                private ImageView view = new ImageView();
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        HBox hbox = new HBox();
                        hbox.setAlignment(Pos.CENTER);

                        Text text = new Text(item.getNickName()+"    ");
                        if(modoOscuro){
                            text.setStyle("-fx-fill: #ffffff;");
                        }
                        else{
                            text.setStyle("-fx-fill: #000000;");
                        }
                        hbox.getChildren().add(text);
                        hbox.getChildren().add(view);
                               
                        setGraphic(hbox);
                        
                        view.fitWidthProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getAvatar().getWidth() + 40))
                                   .then(columna.widthProperty().subtract(40))
                                   .otherwise(item.getAvatar().getWidth()), 60));
                        view.fitHeightProperty().bind(Bindings.min(
                            Bindings.when(columna.widthProperty().lessThan(item.getAvatar().getHeight()+ 30))
                                   .then(columna.widthProperty().subtract(30))
                                   .otherwise(item.getAvatar().getHeight()),70));
                        view.setImage(item.getAvatar());
                     
                        setAlignment(Pos.CENTER_RIGHT);
                        
                    }
                }
            };
        });
        PerdedorC.prefWidthProperty().bind(partidasTablero.widthProperty().divide(3).subtract(7));
        
        FechaYHoraC.setCellValueFactory(new PropertyValueFactory<Round, LocalDateTime>("timeStamp"));
        FechaYHoraC.setCellFactory(columna -> {
            return new TableCell<Round, LocalDateTime>() {
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String formatDateTime = item.format(formatter);
                        setText(formatDateTime);
                        setAlignment(Pos.CENTER);
                    }
                }
        
            };
        });
        FechaYHoraC.prefWidthProperty().bind(partidasTablero.widthProperty().divide(3).subtract(7));
        
    }
    
    public void graficaLineas() throws Connect4DAOException{
        
       Connect4 db = Connect4.getSingletonConnect4();
       
       TreeMap<LocalDate, Integer> tree = db.getRoundCountsPerDay();
        
       graficaLineas.getData().clear();
       xAxis.setLabel("Días");
       yAxis.setLabel("Partidas");
       graficaLineas.setTitle("Número de partidas por día");
             
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       XYChart.Series<String,Number> series = new XYChart.Series<>();
       series.setName("Partidas en el sistema");
       LocalDate fechaIni = fechaIniDP.getValue();
       LocalDate fechafin = fechaFinDP.getValue();
       if (fechaIni == null)
           fechaIni = LocalDate.MIN;
       if (fechafin == null)
           fechafin = LocalDate.now();
       
       ArrayList<LocalDate> lista = new ArrayList<>();
       for(LocalDate ld : tree.keySet()){
           lista.add(ld);
       }

       for (Iterator<LocalDate> iter = lista.iterator(); iter.hasNext();) {
           LocalDate aux = iter.next();
           if (aux.compareTo(fechaIni) < 0 || aux.compareTo(fechafin) > 0 ) {
               iter.remove();
           }
       }
       LocalDate fin = lista.get(lista.size()-1);
       for(LocalDate ld = lista.get(0);
               ld.compareTo(fin) < 0;
               ld = ld.plusDays(1)){
           if(!lista.contains(ld)){
               lista.add(ld);
           }
       }
       
       Collections.sort(lista);
        
       for(LocalDate ld: lista){
           if (tree.get(ld) != null)
           series.getData().add(new XYChart.Data(
                   ld.format(formatter),
                   tree.get(ld)
           ));
           else
           series.getData().add(new XYChart.Data(
                   ld.format(formatter),
                   0
           ));
       }
       
       graficaLineas.getData().add(series);
               
            for (XYChart.Series<String, Number> s : graficaLineas.getData()) {
                for (XYChart.Data<String, Number> d : s.getData()) {
                    Tooltip.install(d.getNode(), new Tooltip(
                            d.getXValue() + "\n" +
                                    "Numero de partidas : " + d.getYValue()));
                    
                    //Adding class on hover
                    d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
                    
                    //Removing class on exit
                    d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
                }
            }
    }

    private void mostraGraficaAct(ActionEvent event) {
        try {
            graficaLineas();
        } catch (Connect4DAOException ex) {
            Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarGraficasBarras(){
        Connect4 db = null;
        try {
            db = Connect4.getSingletonConnect4();
        } catch (Connect4DAOException ex) {
            Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Player player = db.getPlayer(pNombreTF1.getText());
        TreeMap<LocalDate, DayRank> tree = db.getDayRanksPlayer(player);
        graficaBarras.getData().clear();
        graficaBarrasApiladas.getData().clear();
        xAxisBA.setLabel("Date");
        yAxisBA.setLabel("Events");
        graficaBarrasApiladas.setTitle("Número de partidas ganadas/perdidas");
        graficaBarras.setTitle("Número de openentes distintos efrentados");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        XYChart.Series<String,Number> ganadas = new XYChart.Series<>();
        ganadas.setName("Partidas Ganadas");
        XYChart.Series<String,Number> perdidas = new XYChart.Series<>();
        perdidas.setName("Partidas Perdidas");
        XYChart.Series<String,Number> controcantesDistintos = new XYChart.Series<>();
        controcantesDistintos.setName("Número de openentes");
        
       ArrayList<LocalDate> lista = new ArrayList<>();
       for(LocalDate ld : tree.keySet()){
           lista.add(ld);
       }
       
       LocalDate fechaIni = fechaIniDP1.getValue();
       LocalDate fechafin = fechaFinDP1.getValue();
       if (fechaIni == null)
           fechaIni = LocalDate.MIN;
       if (fechafin == null)
           fechafin = LocalDate.now();

       for (Iterator<LocalDate> iter = lista.iterator(); iter.hasNext();) {
           LocalDate aux = iter.next();
           if (aux.compareTo(fechaIni) < 0 || aux.compareTo(fechafin) > 0 ) {
               iter.remove();
           }
       }
       LocalDate fin = lista.get(lista.size()-1);
       for(LocalDate ld = lista.get(0);
               ld.compareTo(fin) < 0;
               ld = ld.plusDays(1)){
           if(!lista.contains(ld)){
               lista.add(ld);
           }
       }
       for(LocalDate ld: lista){
           if (null != tree.get(ld)){
                ganadas.getData().add(new XYChart.Data(
                        ld.format(formatter),
                        tree.get(ld).getWinnedGames()
                ));
                perdidas.getData().add(new XYChart.Data(
                        ld.format(formatter),
                        tree.get(ld).getLostGames()
                ));
                controcantesDistintos.getData().add(new XYChart.Data(
                        ld.format(formatter),
                        tree.get(ld).getOponents()
                ));
           }
       }
      
       graficaBarrasApiladas.getData().addAll(perdidas,ganadas);
       graficaBarras.getData().addAll(controcantesDistintos);
    }

    private void mostrarGraficasBarrasAct(ActionEvent event) {
            mostrarGraficasBarras();
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
                        
            TextFields.bindAutoCompletion(pNombreTF,jugadores);
            TextFields.bindAutoCompletion(pNombreTF1,jugadores);
            
        } catch (Connect4DAOException ex) {
            Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ganYper(ActionEvent event) {
        resultado.setText("Ganadas y Perdidas");
    }

    @FXML
    private void ganAct(ActionEvent event) {
        resultado.setText("Ganadas");
    }

    @FXML
    private void perAct(ActionEvent event) {
        resultado.setText("Perdidas");
    }
    
    public void close(){
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
            
            Stage myStage = (Stage) this.closeWindow.getScene().getWindow();
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

    @FXML
    private void closeWindow(ActionEvent event) {
        close();
    }
}
