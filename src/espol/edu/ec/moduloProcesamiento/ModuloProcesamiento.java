package espol.edu.ec.moduloProcesamiento;

import java.util.ArrayList;
import java.util.Map;
import espol.edu.ec.tda.Stack;
import java.util.ListIterator;
import espol.edu.ec.tda.DoubleLinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Gestionador de la Interfaz Gráfica.
 * Created by MiguelPS on 29-Jun-18.
 */
public class ModuloProcesamiento {

    Parent root;
    ProcesadorDeRegistro datos;

    @FXML TableView tablaTotales;
    @FXML TableColumn colProvincia;
    @FXML TableColumn colEntradas;
    @FXML TableColumn colSalidas;

    @FXML TableView tablaPorRegion;
    @FXML TableColumn colProvinciaRegion;
    @FXML TableColumn colCantidadDeRegistros;

    @FXML ComboBox selectorTipo;
    @FXML ComboBox selectorRegion;

    @FXML public void initialize(){
        colProvincia.setCellValueFactory(new PropertyValueFactory<EntryTotales,String>("nombreProvincia"));
        colEntradas.setCellValueFactory(new PropertyValueFactory<EntryTotales,Integer>("registros"));
        colSalidas.setCellValueFactory(new PropertyValueFactory<EntryTotales,Integer>("registroSalidas"));
        colProvinciaRegion.setCellValueFactory(new PropertyValueFactory<Entry,String>("nombreProvincia"));
        colCantidadDeRegistros.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("registros"));
        datos = new ProcesadorDeRegistro();
        cargarTablaTotales();
    }

    ArrayList<Stack<Map.Entry<String,Integer>>> entradasPorRegion;
    ArrayList<Stack<Map.Entry<String,Integer>>> salidasPorRegion;

    Stack<Map.Entry<String,Integer>> totalEntradas;
    Stack<Map.Entry<String,Integer>> totalSalidas;

    @FXML
    void activarSelectorRegion(){
        selectorRegion.setDisable(false);
    }

    @FXML
    void actualizarTablaPorRegion(){
        
        String tipo = selectorTipo.getValue().toString();
        String region= selectorRegion.getValue().toString();

        if(tipo.equals("Entradas")){
            switch (region){
                case "Costa":agregarDatosATabla(datos.getEntradasCosta());break;
                case "Sierra":agregarDatosATabla(datos.getEntradaSierra());break;
                case "Oriente":agregarDatosATabla(datos.getEntradaOriente());break;
                case "Insular":agregarDatosATabla(datos.getEntradaInsular());break;
                default:break;
            }
        }
        else {
            switch (region){
                case "Costa":agregarDatosATabla(datos.getSalidaCosta());break;
                case "Sierra":agregarDatosATabla(datos.getSalidaSierra());break;
                case "Oriente":agregarDatosATabla(datos.getSalidaOriente());break;
                case "Insular":agregarDatosATabla(datos.getSalidaInsular());break;
                default:break;
            }
        }
    }


     void agregarDatosATabla(Stack<Map.Entry<String,Integer>> pila){
        
        DoubleLinkedList<Map.Entry<String,Integer>> p = (DoubleLinkedList<Map.Entry<String,Integer>>) pila;

        if(!this.tablaPorRegion.getItems().isEmpty()) this.tablaPorRegion.getItems().clear();
       
        ObservableList<Entry> list = FXCollections.observableArrayList();
        
        ListIterator<Map.Entry<String,Integer>> itr = p.iterator(p.size());
        while (itr.hasPrevious()){
            Map.Entry<String,Integer> entry = itr.previous();
            list.add(new Entry(entry.getKey(),entry.getValue()));
        }
        this.tablaPorRegion.setItems(list);
    }

    public void cargarTablaTotales(){

        ObservableList<Entry> list = FXCollections.observableArrayList();

        totalEntradas=datos.getTotalEntradas();
        totalSalidas=datos.getTotalSalidas();

        System.out.println(totalEntradas.size()+totalSalidas.size());

        while (!totalEntradas.isEmpty()){
            Map.Entry<String,Integer> entradas=totalEntradas.pop();
            Map.Entry<String,Integer> salidas=totalSalidas.pop();
            list.add(new EntryTotales(entradas.getKey(),entradas.getValue(),salidas.getValue()));
        }
        this.tablaTotales.setItems(list);

    }

    public class Entry{

        String nombreProvincia;
        Integer registros;

        public Entry(String nombreProvincia, Integer registros) {
            this.nombreProvincia = nombreProvincia;
            this.registros = registros;
        }

        public String getNombreProvincia() {
            return nombreProvincia;
        }

        public Integer getRegistros() {
            return registros;
        }
    }

    public class EntryTotales extends Entry{
        Integer registroSalidas;

        public EntryTotales(String nombreProvincia, Integer registros, Integer registroSalidas) {
            super(nombreProvincia, registros);
            this.registroSalidas=registroSalidas;
        }

        public Integer getRegistroSalidas() {
            return registroSalidas;
        }
    }
}
