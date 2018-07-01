/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.pane;

import espol.edu.ec.registro.Persona;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Kenny Camba
 */
public class PaneAdminEmpleados extends PaneAdmin{
    
    private final Button search;
    private final TextField sexBox; 
    private Persona persona;
    
    public PaneAdminEmpleados(Map<Puesto, LinkedList<Turno>> atencion) {
        super(atencion, "Empleados");
        Image image = new Image(
                PaneAdminEmpleados.class.getResource("/espol/edu/ec/assets/search.png").toExternalForm(), 20, 20, true, true);
        search = new Button("", new ImageView(image));
        sexBox = new TextField();
        sexBox.setPrefColumnCount(7);
        visualizar();
        botonCrear();
        botonEditar();
        search();
        botonBorrar();
    }
    
    private void visualizar() {
       clear();
        registrar.setText("Crear"); 
        edit.setText("Editar");
        delete.setText("Borrar"); 
        estado.setText(""); 
        formulario.getChildren().clear();
        delete.setDisable(true);
        registrar.setDisable(false); 
        edit.setDisable(true); 
        edad.setDisable(true);
        search.setDisable(false); 
        nameBox.setDisable(true); 
        lastnameBox.setDisable(true); 
        cedulaBox.setDisable(false); 
        sexBox.setDisable(true); 
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getText().matches("[0-9]*"))
                return change;
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        cedulaBox.setTextFormatter(textFormatter); 
        formulario.getChildren().add(new HBox(10, new Label("Cedula"), cedulaBox, search));
        formulario.getChildren().add(new HBox(10, new Label("Apellido"), lastnameBox));
        formulario.getChildren().add(new HBox(10, new Label("Nombre"), nameBox));
        formulario.getChildren().add(new HBox(10, new Label("Edad"), edad));
        formulario.getChildren().add(new HBox(10, new Label("Sexo"), sexBox));
        formulario.getChildren().add(new HBox(estado));
        for(Node n: formulario.getChildren())
            ((HBox)n).setAlignment(Pos.CENTER_LEFT);
    }
    
    private void botonCrear() {
        registrar.setOnAction(e -> {
            switch(registrar.getText()){
                case "Crear":
                    crear();
                    break;
                case "Guardar":
                    guardar();
                    break;
                default:
                    break;
            }
        });
    }
    
    private void botonEditar(){
        edit.setOnAction(e ->{
            switch(edit.getText()){
                case "Editar":
                    editar();
                    break;
                case "Guardar":
                    guardarCambios();
                    break;
                case "Cancelar":
                    clear();
                    sexBox.setText("");
                    visualizar();
                    break;
                default:
                    break;
            }
        });
    }
    
    private void botonBorrar() {
        delete.setOnAction(e->{
            switch(delete.getText()){
                case "Borrar":
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmacion de accion");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirmar eliminar empleado");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){
                        String cedula = persona.getCedula()+"";
                        if(cedula.length() < 10)
                            cedula = "0"+cedula;
                        PaneModulo1.EMPLEADOS.remove(cedula);
                        Puesto puesto = getPuesto(persona);
                        if(puesto != null){
                            puesto.setEmpleado(null); 
                            atencion.remove(puesto);
                        }
                        clear();
                        sexBox.setText("");
                        visualizar();
                    }else 
                        alert.close();
                    break;
                case "Cancelar":
                    clear();
                    sexBox.setText("");
                    visualizar();
                    break;
                default:
                    break;
            }
        }); 
    }
    
    private void guardarCambios() {
        String cedula = ""+persona.getCedula();
        if(cedula.length() < 10)
            cedula = 0+""+cedula;
        PaneModulo1.EMPLEADOS.remove(cedula);
        if(!isEmpty(cedulaBox) && !isEmpty(nameBox) && !isEmpty(lastnameBox) && !isEmpty(edad) && !isEmpty(sexBox)){
            String ced = cedulaBox.getText();
            if(PaneModulo1.EMPLEADOS.containsKey(cedula)){
                estado.setText("La cedula ya esta registrada"); 
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion de accion");
                alert.setHeaderText(null);
                alert.setContentText("Confirmar cambios realizados");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    persona.setCedula(Integer.parseInt(ced)); 
                    persona.setApellido(lastnameBox.getText());
                    persona.setNombre(nameBox.getText());
                    persona.setEdad(Integer.parseInt(edad.getText()));
                    persona.setSexo(sexBox.getText()); 
                    PaneModulo1.EMPLEADOS.put(ced, persona);
                    sexBox.setText("");
                    registrar.setText("Crear"); 
                    edit.setText("Editar"); 
                    clear();
                    visualizar();
                }else 
                    alert.close();
            }
        }
    }
    
    private void search() {
        search.setOnAction(e ->{
            String cedula = cedulaBox.getText();
            if(cedula.isEmpty())
                estado.setText("El campo de busqueda esta vacío");
            else if(!PaneModulo1.EMPLEADOS.containsKey(cedula)){
                estado.setText("No existe empleado con CI: " + cedula);
                edit.setDisable(true);
                delete.setDisable(true); 
                nameBox.setText("");
                lastnameBox.setText("");
                edad.setText(""); 
                sexBox.setText(""); 
            }else {
                estado.setText(""); 
                persona = PaneModulo1.EMPLEADOS.get(cedula);
                cargarTextFields(persona);
                sexBox.setText(persona.getSexo());
                Puesto pues = getPuesto(persona);
                if(pues != null && !atencion.get(pues).isEmpty()){
                    edit.setDisable(true);  
                    delete.setDisable(true);
                }else{
                    edit.setDisable(false);  
                    delete.setDisable(false);
                }
            }
         });
    }
    
    private Puesto getPuesto(Persona per) {
        for(Puesto p: atencion.keySet())
            if(p.getEmpleado().getCedula() == per.getCedula())
                return p;
        return null;
    }
    
    private void crear() {
        estado.setText("");
        search.setDisable(true); 
        cedulaBox.setText("");
        clear();
        registrar.setDisable(false);
        sexBox.setDisable(false); 
        edit.setDisable(false);
        delete.setDisable(true);
        registrar.setText("Guardar");
        edit.setText("Cancelar");
        edit.setDisable(false);
        edad.setDisable(false);
        nameBox.setDisable(false); 
        lastnameBox.setDisable(false); 
        cedulaBox.setDisable(false); 
    }
    
    private void editar() {
        estado.setText("");
        search.setDisable(true);
        registrar.setDisable(true);
        sexBox.setDisable(false); 
        nameBox.setDisable(false);
        lastnameBox.setDisable(false);
        edad.setDisable(false);
        cedulaBox.setDisable(false); 
        edit.setDisable(false);
        delete.setDisable(false);
        edit.setText("Guardar");
        delete.setDisable(false);
        delete.setText("Cancelar");
    }
    
    private void guardar() {
        if(!isEmpty(cedulaBox) && !isEmpty(nameBox) && !isEmpty(lastnameBox) && !isEmpty(edad) && !isEmpty(sexBox)){
            String cedula = cedulaBox.getText();
            if(PaneModulo1.EMPLEADOS.containsKey(cedula)){
                estado.setText("La cedula ya esta registrada"); 
            }else {
                Persona p = new Persona(
                Integer.parseInt(cedula),nameBox.getText(),lastnameBox.getText(),sexBox.getText(),Integer.parseInt(edad.getText())); 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion de accion");
                alert.setHeaderText(null);
                alert.setContentText("Confirmar creacion de empleado");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    PaneModulo1.EMPLEADOS.put(cedula, p);
                    sexBox.setText("");
                    registrar.setText("Crear"); 
                    edit.setText("Editar"); 
                    clear();
                    visualizar();
                } else 
                    alert.close();
            }
        }
    }
    
    private boolean isEmpty(TextField field) {
        if(field.getText().isEmpty()){
            estado.setText("Por favor llene todos los campos"); 
            return true;
        }
        return false;
    }
    
}
