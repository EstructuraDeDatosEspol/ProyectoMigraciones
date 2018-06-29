/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.pane;

import espol.edu.ec.tda.Estado;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.util.LinkedList;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneAtenderTurno {
    
    private final VBox pane;
    private final ComboBox<Puesto> puestos;
    private final ComboBox<Turno> turnos;
    private final Map<Puesto, LinkedList<Turno>> atencion;
    private final Button atender;
    private final PaneScreenTurnos screen;
    private final PaneTurnoGenerador generador;
    
    public PaneAtenderTurno(Map<Puesto, LinkedList<Turno>> atencion, PaneScreenTurnos screen,
            PaneTurnoGenerador generador) {
        pane = new VBox();
        puestos = new ComboBox<>();
        turnos = new ComboBox<>();
        this.atencion = atencion;
        this.screen = screen;
        atender = new Button("Atender");
        this.generador = generador;
        content();
    }

    private void content() {
        pane.setSpacing(20);
        pane.setPadding(new Insets(10,30,10,30)); 
        puestos.getItems().addAll(atencion.keySet());
        turnos.setMinWidth("Turno:00-Puesto:00".length()*10); 
        HBox hb = new HBox(new Text("Puesto"), puestos);
        hb.setAlignment(Pos.CENTER_LEFT); 
        hb.setSpacing(10); 
        HBox hb2 = new HBox(new Text("Turno"), turnos);
        hb2.setAlignment(Pos.CENTER_LEFT); 
        hb2.setSpacing(10); 
        pane.getChildren().addAll(hb, hb2, atender);
        turnos.setDisable(true); 
        cargarTurnos();
        atender.setDisable(true);
        atender ();
    }
    
    private void cargarTurnos() {
        
        puestos.setOnAction(e -> {
            Puesto p = puestos.getValue();
            if(p != null){    
                turnos.getItems().clear();
                turnos.getItems().addAll(atencion.get(p));
                if(!turnos.getItems().isEmpty())
                    turnos.setDisable(false); 
                else{
                    turnos.setDisable(true);
                    atender.setDisable(true);
                }
            }
        });
        
        turnos.setOnAction(e -> {
            Turno t = turnos.getValue();
            if(t != null && t.getEstado() == Estado.ATENDIENDO)
                atender.setDisable(false); 
            else
                atender.setDisable(true); 
        });
    }
    
    private void atender () {
        atender.setOnAction(e -> {
            Turno t = turnos.getValue();
            t.setEstado(Estado.ATENDIDO); 
            turnos.getItems().remove(t);
            if(turnos.getItems().isEmpty())
                turnos.setDisable(true);
            atencion.get(t.getPuesto()).remove(t);
            screen.addTurno(generador.getNext());
        }); 
    }
    
    public Pane getPane() {
        return pane;
    }

}
