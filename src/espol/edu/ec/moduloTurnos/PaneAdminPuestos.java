/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloTurnos;

import espol.edu.ec.tda.entidades.Persona;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 *
 * @author Kenny Camba
 */
public class PaneAdminPuestos extends PaneAdmin {
    
    private final TextField puestoBox;
    private final CheckBox autoName;
    private final ComboBox<Puesto> puestos;
    private final ChoiceBox<Persona> empleados;

    
    public PaneAdminPuestos(Map<Puesto, LinkedList<Turno>> atencion) {
        super(atencion, "Puestos");
        autoName = new CheckBox("Auto");
        puestoBox = new TextField();
        puestos = new ComboBox();
        empleados = new ChoiceBox();
        tabPuestos();
    }
    
    private void tabPuestos() {
        visualizar();
        empleados.setConverter(new StringConverter<Persona>(){
            @Override
            public String toString(Persona object) {
                if(object == null)
                    return "";
                String s = object.getCedula() + "";
                if(s.length() < 10)
                    s = "0"+s;
                return s;
            }
            @Override
            public Persona fromString(String string) {
                return null;
            }
        }); 
        puestoBox.setPrefColumnCount(5);
        selectPuesto();
        selectPerson();
        borrar();
        botonEditar();
        crearPuesto(); 
    }
    
    private void visualizar() {
        clear();
        formulario.getChildren().clear();
        delete.setDisable(true);
        registrar.setDisable(false); 
        edit.setDisable(true); 
        edad.setDisable(true);
        nameBox.setDisable(true); 
        lastnameBox.setDisable(true); 
        cedulaBox.setDisable(true); 
        puestos.getItems().clear();
        PaneModulo1.PUESTOS.sort((Puesto p1, Puesto p2) -> p1.hashCode() - p2.hashCode()); 
        puestos.getItems().addAll(PaneModulo1.PUESTOS);
        formulario.getChildren().add(new HBox(10, new Label("Puesto"), puestos));
        formulario.getChildren().add(new HBox(new Text("Informacion del encargado")));
        formulario.getChildren().add(new HBox(10, new Label("Cedula"), cedulaBox));
        formulario.getChildren().add(new HBox(10, new Label("Apellido"), lastnameBox));
        formulario.getChildren().add(new HBox(10, new Label("Nombre"), nameBox));
        formulario.getChildren().add(new HBox(10, new Label("Edad"), edad));
        for(Node n: formulario.getChildren())
            ((HBox)n).setAlignment(Pos.CENTER_LEFT);
    }
    
    private void crear() {
        registrar.setText("Registrar");
        estado.setText(""); 
        puestoBox.setText("");
        empleados.setDisable(false); 
        autoName.setSelected(false); 
        puestoBox.setDisable(false); 
        delete.setDisable(true);
        edit.setDisable(true); 
        formulario.getChildren().clear();
        formulario.getChildren().add(new HBox(10, new Label("Puesto"), puestoBox, autoName));
        formulario.getChildren().add(new HBox(new Text("Encargado del puesto")));
        empleados.getItems().clear();
        edit.setDisable(true); 
        edad.setDisable(true);
        nameBox.setDisable(true); 
        lastnameBox.setDisable(true); 
        empleados.getItems().addAll(this.libres());
        empleados.getItems().add(0, null); 
        formulario.getChildren().add(new HBox(10, new Label("Empleado"), empleados));
        formulario.getChildren().add(new HBox(10, new Label("Apellido"), lastnameBox));
        formulario.getChildren().add(new HBox(10, new Label("Nombre"), nameBox));
        formulario.getChildren().add(new HBox(10, new Label("Edad"), edad));
        formulario.getChildren().add(new HBox(estado));
        for(Node n: formulario.getChildren())
            ((HBox)n).setAlignment(Pos.CENTER_LEFT);
    }
    
    private void crearPuesto() {
        registrar.setOnAction(e ->{
            if(registrar.getText().equals("Crear")){
                crear();
                edit.setDisable(false); 
                edit.setText("Cancelar");
                checkAction();
            }else if(registrar.getText().equals("Registrar")){
                String puesto = puestoBox.getText();
                Persona p = empleados.getValue();
                Puesto puest = new Puesto(puesto, p);
                if(puesto.isEmpty()){
                    estado.setText("El campo puesto eta vacío"); 
                }else if(atencion.containsKey(puest)){
                    estado.setText("El nombre de puesto ya esta en uso"); 
                }else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmacion de accion");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirmar crear puesto de atención: " + puesto);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){
                        if(p != null)
                            atencion.put(puest, new LinkedList<>());
                        PaneModulo1.PUESTOS.add(puest);
                        registrar.setText("Crear");
                        edit.setText("Editar"); 
                        visualizar();
                    } else 
                        alert.close();
                }
            }
        }); 
    }
    
    private void editar() {
        Puesto pues = puestos.getValue();
        estado.setText("");
        registrar.setDisable(true);
        edit.setText("Guardar");
        delete.setText("Cancelar"); 
        delete.setDisable(false);
        puestoBox.setDisable(false); 
        formulario.getChildren().clear();
        formulario.getChildren().add(new HBox(10, new Label("Puesto"), puestoBox));
        puestoBox.setText(pues.getPuesto()); 
        formulario.getChildren().add(new HBox(new Text("Informacion del encargado")));
        empleados.getItems().clear();
        empleados.setDisable(true); 
        empleados.getItems().add(pues.getEmpleado());
        empleados.getItems().addAll(this.libres());
        empleados.getItems().add(0, null); 
        empleados.setValue(pues.getEmpleado()); 
        formulario.getChildren().add(new HBox(10, new Label("Cedula"), empleados));
        formulario.getChildren().add(new HBox(10, new Label("Apellido"), lastnameBox));
        formulario.getChildren().add(new HBox(10, new Label("Nombre"), nameBox));
        formulario.getChildren().add(new HBox(10, new Label("Edad"), edad));
        Button cambiar = new Button("Cambiar");
        formulario.getChildren().add(new HBox(cambiar));
        formulario.getChildren().add(new HBox(estado));
        cambiar.setOnAction(e -> {
            if(cambiar.getText().equals("Cambiar")){
                cambiar.setText("Aplicar"); 
                empleados.setDisable(false); 
            } else if(cambiar.getText().equals("Aplicar")){
                cambiar.setText("Cambiar"); 
                empleados.setDisable(true); 
            }
        }); 
        for(Node n: formulario.getChildren())
            ((HBox)n).setAlignment(Pos.CENTER_LEFT);
    }
    
    private void selectPerson() {
        empleados.setOnAction(e ->{
            Persona p = empleados.getValue();
            if(p != null){
                clear();
                cargarTextFields(p);
            }else
                clear();
        });
    }
    
    private void checkAction() {
        autoName.setOnAction(e ->{
            if(autoName.isSelected()){
                puestoBox.setDisable(true);
                puestoBox.setText(getNum()); 
            }else {
                puestoBox.setDisable(false);
                puestoBox.setText(""); 
            }
        }); 
    }
    
    private String getNum() {
        int num = 1;
        String s;
        for(Puesto p: PaneModulo1.PUESTOS){
            int var = p.hashCode();
            if(num < var) {
                s = "" + num;
                if(s.length() >= 2)
                    return s;
                else return "0"+s;
            }else
                num = var + 1;
        }
        s = "" + num;
        if(s.length() >= 2) return s;
        else return "0"+s;
    }
    
    private void selectPuesto() {
        
        puestos.setOnAction(e -> {
            Puesto p = puestos.getValue();
            if(p != null && p.getEmpleado() != null){
                Persona per = p.getEmpleado();
                String s = per.getCedula() +"";
                if(s.length() < 10)
                    s = "0"+s;
                cedulaBox.setText(s);
                cargarTextFields(per);
                if(atencion.get(p).isEmpty()){
                    delete.setDisable(false);
                    edit.setDisable(false);
                }else{
                    delete.setDisable(true);
                    edit.setDisable(true);
                }
            }else if(p != null && p.getEmpleado() == null) {
                delete.setDisable(false);
                edit.setDisable(false);
                clear();
            }
        }); 
    }
    
    private void borrar() {
        delete.setOnAction(e ->{
            if(delete.getText().equals("Cancelar")){
                registrar.setDisable(false); 
                edit.setDisable(true);
                delete.setDisable(true); 
                delete.setText("Borrar");
                edit.setText("Editar"); 
                visualizar();
            }else if(delete.getText().equals("Borrar")){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion de accion");
                alert.setHeaderText(null);
                alert.setContentText("¿Esta seguro que desea eliminar este puesto de atención?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    Puesto p = puestos.getValue(); 
                    atencion.remove(p);
                    PaneModulo1.PUESTOS.remove(p);
                    visualizar();
                } else 
                    alert.close();
            }
        }); 
    }
    
    private void botonEditar() {
        edit.setOnAction(e ->{
            switch (edit.getText()) {
                case "Editar":
                    editar();
                    break;
                case "Guardar":
                    guardarPuestoModificado();
                    break;
                case "Cancelar":
                    edit.setDisable(true);
                    edit.setText("Editar");    
                    registrar.setText("Crear");
                    visualizar();
                    break;
                default:
                    break;
            }
        }); 
    }
    
    private void guardarPuestoModificado() {
        String name = puestoBox.getText();
        Persona p = empleados.getValue();
        Puesto puesto = puestos.getValue();
        atencion.remove(puesto);
        if(name.isEmpty()){
            estado.setText("El campo puesto eta vacío"); 
        }else if(atencion.containsKey(new Puesto(name))){
            estado.setText("El nombre de puesto ya esta en uso");   
        }else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion de accion");
            alert.setHeaderText(null);
            alert.setContentText("Confirmar modificacion del puesto de atención");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                puesto.setPuesto(name);
                puesto.setEmpleado(p); 
                if(p != null) 
                    atencion.put(puesto, new LinkedList<>());
                edit.setText("Editar");
                delete.setText("Borrar"); 
                visualizar();
            } else 
                alert.close();
        }
    }
    
    private List<Persona> libres() {
        List<Persona> list = new LinkedList<>();
        for(Persona p: PaneModulo1.EMPLEADOS.values()){
            int i = 0;
            Set<Puesto> set = atencion.keySet();
            for(Puesto puesto: set){
                if(p.getCedula() != puesto.getEmpleado().getCedula() && i++ == set.size()-1)
                    list.add(p);
            }
        }
        return list;
    }
}
