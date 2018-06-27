/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import espol.edu.ec.pane.PaneScreenTurnos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class ModuloTurno {
    
    private final ComboBox<Atencion> atencion;
    private final Button generar;
    private final Text estado;
    private final TurnoGenerador generator;
    private final VBox pane;
    private final PaneScreenTurnos sistema;
    
    public ModuloTurno(PaneScreenTurnos sistema) {
        this.sistema = sistema;
        pane = new VBox();
        atencion = new ComboBox();
        estado = new Text();
        generator = new TurnoGenerador();
        generar = new Button("Generar Turno");
        for(Atencion a: Atencion.values())
            atencion.getItems().add(a);
        pane.getChildren().addAll(atencion, generar, estado);
        click();
    }
    
    private void click() {
        generar.setOnAction(e->{
            Atencion a = atencion.getValue();
            if(a == null)
                estado.setText("Por favor seleccione un tipo de atencion");
            else {
                Turno t = generator.putInWait(a);
                estado.setText(t.toString());
                if(!sistema.addTurno(t))
                    sistema.atenderTruno(t); 
            }
        });
    }
    
    public Pane getPane(){
        return pane;
    }
}
