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
import java.util.function.UnaryOperator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneAdmin {
    
    protected final VBox formulario;
    protected final TextField cedulaBox;
    protected final TextField nameBox;
    protected final TextField lastnameBox;
    protected final TextField edad;
    protected final Map<Puesto, LinkedList<Turno>> atencion;
    protected final Button registrar;
    protected final Button delete;
    protected final Button edit;
    protected final Text estado;
    protected final Tab tab;
    
    public PaneAdmin(Map<Puesto, LinkedList<Turno>> atencion, String name) {
        formulario = new VBox();
        cedulaBox = new TextField();
        nameBox = new TextField();
        lastnameBox = new TextField();
        edad = new TextField(); 
        this.atencion = atencion;
        registrar = new Button("Crear");
        delete = new Button("Borrar");
        edit = new Button("Editar");
        estado = new Text();
        tab = new Tab(name);
        contenido();
    }
    
    private void contenido() {
        tab.setClosable(false); 
        formulario.setSpacing(10);
        HBox content = new HBox();
        content.setPadding(new Insets(10,30,10,30));
        content.setSpacing(30);
        content.getChildren().add(formulario);
        VBox botones = new VBox(10, registrar, edit, delete);
        content.getChildren().add(botones);
        tab.setContent(content); 
        estado.setFill(Color.RED);
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getText().matches("[0-9]*"))
                return change;
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        edad.setTextFormatter(textFormatter);
        edad.setPrefColumnCount(3); 
        nameBox.setPrefColumnCount(7);
        lastnameBox.setPrefColumnCount(7);
        cedulaBox.setPrefColumnCount(6);
    }
    
    protected void cargarTextFields(Persona per) {
        nameBox.setText(per.getNombre());
        lastnameBox.setText(per.getApellido());
        edad.setText(String.valueOf(per.getEdad()));
    }
    
    protected void clear() {
        cedulaBox.setText("");
        nameBox.setText("");
        lastnameBox.setText("");
        edad.setText(""); 
    }
    
    public Tab getTab() {
        return tab;
    }
}
