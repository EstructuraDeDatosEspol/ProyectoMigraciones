/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloTurnos;

import espol.edu.ec.tda.Atencion;
import espol.edu.ec.tda.Estado;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneTurnoGenerador {
    private final PriorityQueue<Turno> turnos;
    private final Map<Puesto, LinkedList<Turno>> atencion;
    private static int TURNOS_CAPACIDADES_ESPECIALES = 0;
    private static int TURNOS_TERCERA_EDAD = 0;
    private static int TURNOS_ATENCION_NORMAL = 0;
    
    private final ComboBox<Atencion> atenciones;
    private final Button generar;
    private final Text estado;
    private final VBox pane;
    private final PaneScreenTurnos screen;
    
    public PaneTurnoGenerador(Map<Puesto, LinkedList<Turno>> atencion, PaneScreenTurnos screen) {
        turnos = new PriorityQueue<>((Turno t1, Turno t2) -> 
                t2.getTipoAtencion().getPrioridad() - t1.getTipoAtencion().getPrioridad());
        this.atencion = atencion;
        this.screen = screen;
        pane = new VBox();
        atenciones = new ComboBox();
        estado = new Text();
        generar = new Button("Generar Turno");
        atenciones.getItems().addAll(Atencion.values());
        HBox box = new HBox(new Text("Tipo"), atenciones);
        box.setAlignment(Pos.CENTER_LEFT); 
        box.setSpacing(10); 
        pane.setPadding(new Insets(10, 30, 10, 30)); 
        pane.getChildren().addAll(box, generar, estado);
        pane.setSpacing(30); 
        click();
    }
    
    private void click() {
        generar.setOnAction(e->{
            Atencion a = atenciones.getValue();
            if(a == null){
                estado.setFill(Color.RED);
                estado.setText("Seleccione un tipo de atencion");
            }
            else {
                Turno t = this.putInWait(a);
                estado.setFill(Color.BLUE); 
                estado.setText(t.toString());
            }
            atenciones.setValue(null); 
        });
    }
    
    public Turno putInWait(Atencion atencion) {
        System.out.println(turnos);
        System.out.println(this.atencion);
        String numero = "";
        switch(atencion){
            case CAPACIDAD_ESPECIAL:
                numero = atencion.getTipo() + (++TURNOS_CAPACIDADES_ESPECIALES);
                break;
            case TERCERA_EDAD:
                numero = atencion.getTipo() + (++TURNOS_TERCERA_EDAD);
                break;
            case NORMAL:
                numero = atencion.getTipo() + (++TURNOS_ATENCION_NORMAL);
                break;
            default:
                break;
        }
 
        Puesto puesto = asignarPuesto();
        Turno turno = new Turno(numero, atencion, puesto);
        this.atencion.get(puesto).add(turno);
        turnos.offer(turno);
        if(!screen.isFull())
            screen.addTurno(this.getNext(), true);
        return turno;
    }
    
    public Turno viewNext() {
        return turnos.peek();
    }
    
    public Turno getNext() {
        if(turnos.isEmpty())
            return null;
        turnos.peek().setEstado(Estado.ATENDIENDO); 
        return turnos.poll();
    }
    
    private Puesto asignarPuesto() {
        int menor = Integer.MAX_VALUE;
        Puesto p = null;
        for(Map.Entry<Puesto, LinkedList<Turno>> dat: atencion.entrySet()){
            int var = dat.getValue().size();
            if(var == 0)
                return dat.getKey();
            else if(var < menor){
                p = dat.getKey();
                menor = var;
            }
        }  
        return p;
    }
    
    public Pane getPane(){
        return pane;
    }
    
}
