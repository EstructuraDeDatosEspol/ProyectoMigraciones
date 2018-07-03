package espol.edu.ec.moduloProcesamiento;

import java.util.Map;
import espol.edu.ec.tda.Stack;
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

    @FXML TableView tablaTotalesEntradas;
    @FXML TableColumn colProvinciaEntrada;
    @FXML TableColumn colEntradas;
    
    @FXML TableView tablaTotalesSalidas;
    @FXML TableColumn colProvinciaSalida;
    @FXML TableColumn colSalidas;

    @FXML TableView tablaPorRegion;
    @FXML TableColumn colProvinciaRegion;
    @FXML TableColumn colCantidadDeRegistros;

    @FXML ComboBox selectorTipo;
    @FXML ComboBox selectorRegion;
   

    @FXML public void initialize(){
        
        colProvinciaEntrada.setCellValueFactory(new PropertyValueFactory<Entry,String>("nombreProvincia"));
        colEntradas.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("registros"));
        
        colProvinciaSalida.setCellValueFactory(new PropertyValueFactory<Entry,String>("nombreProvincia"));
        colSalidas.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("registros"));
        
        colProvinciaRegion.setCellValueFactory(new PropertyValueFactory<Entry,String>("nombreProvincia"));
        colCantidadDeRegistros.setCellValueFactory(new PropertyValueFactory<Entry,Integer>("registros"));
        
        datos = new ProcesadorDeRegistro();
        cargarTablaTotales();
    }

    Stack<Map.Entry<String,Integer>> totalEntradas;
    Stack<Map.Entry<String,Integer>> totalSalidas;
    

    @FXML
    void actualizarTablaPorRegion(){
        
        String region="";
        String tipo="";
        try {
            tipo = selectorTipo.getValue().toString();
            region= selectorRegion.getValue().toString();
        } catch (Exception e) {
            return;
        }
       
        if(tipo.equals("Entradas")){
            switch (region){
                case "Costa":agregarDatosATabla(datos.getEntradasCosta(), tablaPorRegion);break;
                case "Sierra":agregarDatosATabla(datos.getEntradaSierra(), tablaPorRegion);break;
                case "Oriente":agregarDatosATabla(datos.getEntradaOriente(), tablaPorRegion);break;
                case "Insular":agregarDatosATabla(datos.getEntradaInsular(), tablaPorRegion);break;
                default:break;
            }
        }
        else {
            switch (region){
                case "Costa":agregarDatosATabla(datos.getSalidaCosta(), tablaPorRegion);break;
                case "Sierra":agregarDatosATabla(datos.getSalidaSierra(), tablaPorRegion);break;
                case "Oriente":agregarDatosATabla(datos.getSalidaOriente(), tablaPorRegion);break;
                case "Insular":agregarDatosATabla(datos.getSalidaInsular(), tablaPorRegion);break;
                default:break;
            }
        }
    }

    
    public void cargarTablaTotales(){

        totalEntradas=datos.getTotalEntradas();
        totalSalidas=datos.getTotalSalidas();
        
        agregarDatosATabla(totalEntradas,tablaTotalesEntradas);
        agregarDatosATabla(totalSalidas,tablaTotalesSalidas);
    }
  
    
    void agregarDatosATabla(Stack<Map.Entry<String,Integer>> pila, TableView tabla){
        
        DoubleLinkedList<Map.Entry<String,Integer>> p = (DoubleLinkedList<Map.Entry<String,Integer>>) pila;

        if(!tabla.getItems().isEmpty()) tabla.getItems().clear();
       
        ObservableList<Entry> list = FXCollections.observableArrayList();
        
        while (!pila.isEmpty()){
            Map.Entry<String,Integer> entry = pila.pop();
            list.add(new Entry(entry.getKey(),entry.getValue()));
        }
        tabla.setItems(list);
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
}
