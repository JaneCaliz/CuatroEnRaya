/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DBAccess.Connect4DAOException;
import controladores.RankingController;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Connect4;
import model.Player;
import model.Round;



/**
 * FXML Controller class
 *
 * @author ydavpacat
 */
public class PartidasController implements Initializable {

    @FXML
    private TableView<Round> partidasTablero;
    @FXML
    private TableColumn<Round, LocalDateTime> FechYHolaC;
    @FXML
    private TableColumn<Round, Player> GanadorC;
    @FXML
    private TableColumn<Round, Player> PerdedorC;
    
    private Player player1, player2;
    @FXML
    private DatePicker fechaIniDP;
    @FXML
    private Button mostraGrafica;
    @FXML
    private DatePicker fechaFinDP;
    @FXML
    
    
    
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
        //final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        //@FXML private LineChart<String, Number> lineChart;
    @FXML private LineChart<String, Number> gradicaLineas = new LineChart<String,Number>(xAxis,yAxis);


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gradicaLineas.setAnimated(false);
    }  
    public void initPlayer1(Player p1){
        this.player1 = p1;
    }
    
    public void initPlayer2(Player p2){
        this.player2 = p2;
    }

    @FXML
    private void mosratPartidasAct(ActionEvent event) {
        try {
            Connect4 connect4 = Connect4.getSingletonConnect4();
            ObservableList<Round> observablRounds;
            
            //observablePlayers = FXCollections.observableList(connect4.getConnect4Ranking());
            
            ArrayList<Player> listaJugadores = connect4.getConnect4Ranking();
            ArrayList<Round> listaPartidas = new ArrayList<>();
            for(Player p : listaJugadores){
                List <Round> listTwoCopy = new ArrayList<>(connect4.getRoundsPlayer(p));
                listTwoCopy.removeAll(listaPartidas);
                listaPartidas.addAll(listTwoCopy);
            }
            Collections.sort(listaPartidas);
            Collections.reverse(listaPartidas);
            observablRounds = FXCollections.observableList(listaPartidas);
            partidasTablero.setItems(observablRounds);
            
        } catch (Connect4DAOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GanadorC.setCellValueFactory(new PropertyValueFactory<Round, Player>("winner"));
        GanadorC.setCellFactory(columna -> {
            return new TableCell<Round, Player>() {
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getNickName());
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });
        
        PerdedorC.setCellValueFactory(new PropertyValueFactory<Round, Player>("loser"));
        PerdedorC.setCellFactory(columna -> {
            return new TableCell<Round, Player>() {
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getNickName());
                        setAlignment(Pos.CENTER);
                    }
                }
            };
        });
        
        FechYHolaC.setCellValueFactory(new PropertyValueFactory<Round, LocalDateTime>("timeStamp"));
        FechYHolaC.setCellFactory(columna -> {
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
        
    }
    
    public void graficaLineas() throws Connect4DAOException{
        
       Connect4 db = Connect4.getSingletonConnect4();
       
       TreeMap<LocalDate, Integer> tree = db.getRoundCountsPerDay();
        
       
       xAxis.setLabel("Date");
       yAxis.setLabel("Events");
//       gradicaLineas = new LineChart<>(xAxis, yAxis);
       gradicaLineas.setTitle("Events");
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       XYChart.Series<String,Number> series = new XYChart.Series<>();
       series.setName("Events this Year");
//       LocalDate fechaIni = fechaFinDP.getValue();
//       LocalDate fechafin = fechaIniDP.getValue();
       
       ArrayList<LocalDate> lista = new ArrayList<>();
       for(LocalDate ld : tree.keySet()){
           lista.add(ld);
       }
       //Esta es la lista a filtrar
//       for (Iterator<LocalDate> iter = lista.iterator(); iter.hasNext();) {
//           LocalDate aux = iter.next();
//           if (aux.compareTo(fechaIni) < 0 || aux.compareTo(fechafin) > 0 ) {
//               iter.remove();
//           }
//       }
       for(LocalDate ld: lista){
           series.getData().add(new XYChart.Data(
                   ld.format(formatter),
                   tree.get(ld)
           ));
       }
       
       gradicaLineas.getData().add(series);
        System.out.println("Mostrando ");
       
//            series.getData().add(new XYChart.Data(dateFormat.parse("11/Jan/2014"), 23));
//            series.getData().add(new XYChart.Data(dateFormat.parse("09/Feb/2014"), 14));
//            series.getData().add(new XYChart.Data(dateFormat.parse("22/Mar/2014"), 15));
//            series.getData().add(new XYChart.Data(dateFormat.parse("14/Apr/2014"), 24));
//            series.getData().add(new XYChart.Data(dateFormat.parse("22/May/2014"), 34));
//            series.getData().add(new XYChart.Data(dateFormat.parse("07/Jun/2014"), 36));
//            series.getData().add(new XYChart.Data(dateFormat.parse("22/Jul/2014"), 22));
//            series.getData().add(new XYChart.Data(dateFormat.parse("21/Aug/2014"), 45));
//            series.getData().add(new XYChart.Data(dateFormat.parse("04/Sep/2014"), 43));
//            series.getData().add(new XYChart.Data(dateFormat.parse("22/Oct/2014"), 17));
//            series.getData().add(new XYChart.Data(dateFormat.parse("30/Nov/2014"), 29));
//            series.getData().add(new XYChart.Data(dateFormat.parse("10/Dec/2014"), 25));
//
//
//            
//            /**
//             * Browsing through the Data and applying ToolTip
//             * as well as the class on hover
//             */
//            for (XYChart.Series<Number, Number> s : lineChart.getData()) {
//                for (XYChart.Data<Number, Number> d : s.getData()) {
//                    Tooltip.install(d.getNode(), new Tooltip(
//                            d.getXValue().toString() + "\n" +
//                                    "Number Of Events : " + d.getYValue()));
//                    
//                    //Adding class on hover
//                    d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
//                    
//                    //Removing class on exit
//                    d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
//                }
//            }
    }

    @FXML
    private void mostraGraficaAct(ActionEvent event) {
        try {
            graficaLineas();
        } catch (Connect4DAOException ex) {
            Logger.getLogger(PartidasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
