/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.pane;

import espol.edu.ec.registro.Persona;
import espol.edu.ec.registro.ReadWriter;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.UnaryOperator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneAdminPuestos {
    
    private final TabPane tabPane;
    private final TextField cedulaBox;
    private final TextField nameBox;
    private final TextField lastnameBox;
    private final TextField edad;
    private final ComboBox<Puesto> puestos;
    private final Map<Puesto, LinkedList<Turno>> atencion;
    private final PaneAtenderTurno atender;
    private final Button delete;
    private final Button edit;
    
    public PaneAdminPuestos(Map<Puesto, LinkedList<Turno>> atencion, PaneAtenderTurno atender) {
        tabPane = new TabPane();
        cedulaBox = new TextField();
        nameBox = new TextField();
        lastnameBox = new TextField();
        edad = new TextField(); 
        puestos = new ComboBox();
        this.atencion = atencion;
        this.atender = atender;
        delete = new Button("Borrar");
        edit = new Button("Editar");
        tabPuestos();
        tabEmpleados();
    }
    
    private void tabPuestos() {
        Tab puestosTab = new Tab("Puestos");
        puestosTab.setClosable(false); 
        HBox content = new HBox();
        content.setPadding(new Insets(10,30,10,30));
        content.setSpacing(30); 
        VBox formulario = new VBox();
        formulario.setSpacing(10);  
        UnaryOperator<Change> filter = change -> {
            if (change.getText().matches("[0-9]*"))
                return change;
            return null;
        };
        delete.setDisable(true);
        edit.setDisable(true); 
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        edad.setTextFormatter(textFormatter);
        edad.setDisable(true);
        edad.setPrefColumnCount(3); 
        nameBox.setPrefColumnCount(7);
        nameBox.setDisable(true); 
        lastnameBox.setPrefColumnCount(7);
        lastnameBox.setDisable(true); 
        cedulaBox.setPrefColumnCount(6); 
        cedulaBox.setDisable(true); 
        puestos.getItems().addAll(atencion.keySet());
        formulario.getChildren().add(new HBox(10, new Label("Puesto"), puestos));
        formulario.getChildren().add(new HBox(new Text("Informacion del encargado")));
        formulario.getChildren().add(new HBox(10, new Label("Cedula"), cedulaBox));
        formulario.getChildren().add(new HBox(10, new Label("Apellido"), lastnameBox));
        formulario.getChildren().add(new HBox(10, new Label("Nombre"), nameBox));
        formulario.getChildren().add(new HBox(10, new Label("Edad"), edad));
        for(Node n: formulario.getChildren())
            ((HBox)n).setAlignment(Pos.CENTER_LEFT);
        content.getChildren().add(formulario);
        VBox botones = new VBox(10, edit, delete);
        content.getChildren().add(botones);
        puestosTab.setContent(content);
        tabPane.getTabs().add(puestosTab);
        selectPuesto();
        borrar();
        editar(formulario);
    }
    
    private void tabEmpleados() {
        Tab empleTab = new Tab("Empleados");
        empleTab.setClosable(false); 
        tabPane.getTabs().add(empleTab);
    }
    
    private void selectPuesto() {
        
        puestos.setOnAction(e -> {
            Puesto p = puestos.getValue();
            if(p != null && p.getEmpleado() != null){
                Persona per = p.getEmpleado();
                cedulaBox.setText("0"+per.getCedula());
                nameBox.setText(per.getNombre());
                lastnameBox.setText(per.getApellido());
                edad.setText(String.valueOf(per.getEdad()));
                if(atencion.get(p).isEmpty()){
                    delete.setDisable(false);
                    edit.setDisable(false);
                }else{
                    delete.setDisable(true);
                    edit.setDisable(true);
                }
            }
        }); 
    }
    
    private void borrar() {
        delete.setOnAction(e ->{
            Puesto p = puestos.getValue();
            puestos.getItems().remove(p);
            puestos.setValue(null); 
            delete.setDisable(true);
            edit.setDisable(true); 
            atencion.remove(p);
            updateFile();
            atender.updatePuestos();
            clear();
        }); 
    }
    
    private void editar(VBox formulario) {
        edit.setOnAction(e ->{
            if(edit.getText().equals("Editar")){
                edit.setText("Guardar");
                delete.setDisable(true);
                formulario.getChildren().add(2, new HBox(10, new Label("Cedula"), new ComboBox<>()));
            }else if(edit.getText().equals("Guardar")){
                edit.setText("Editar");
                delete.setDisable(false);
                formulario.getChildren().remove(2);
            }
        }); 
    }
    
    private Map<String, Persona> libres() {
        Map<String, Persona> empleados = ReadWriter.loadEmpleados();
        for(Puesto p: atencion.keySet()) {
            if(empleados.containsKey(p.getEmpleado().getCedula())) { 
                empleados.remove(p.getEmpleado().getCedula());
            }
        }
        return empleados;
    }
    
    private void clear() {
        cedulaBox.setText("");
        nameBox.setText("");
        lastnameBox.setText("");
        edad.setText(""); 
    }
    
    private void updateFile() {
        String file = new File("").getAbsolutePath();
        file = Paths.get(file, "src", "espol", "edu", "ec", "recursos", "files", "puestos.txt").toString();
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for(Puesto p: atencion.keySet()) {
                bw.write(p.getPuesto() + "," + p.getEmpleado().getCedula());
                bw.newLine();
            }
        }catch(IOException ex) {

        }
    }
    
    public TabPane getPane() {
        return tabPane;
    }
    
    
}
