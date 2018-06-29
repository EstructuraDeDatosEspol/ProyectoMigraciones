/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.pane;

import espol.edu.ec.main.Main;
import espol.edu.ec.tda.Const;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class PaneModulo1 {
    
    private final static Map<Puesto, LinkedList<Turno>> ATENCION = loadMap();
    private final BorderPane root;
    private final static PaneScreenTurnos SCREEN = Main.getRootTurnShower();
    private final static PaneTurnoGenerador SECCION_TURNO = new PaneTurnoGenerador(ATENCION, SCREEN);
    private final static PaneAtenderTurno SECCION_ATENDER = new PaneAtenderTurno(ATENCION, SCREEN, SECCION_TURNO);
    public PaneModulo1() {
        root = new BorderPane();
        root.setCenter(SECCION_TURNO.getPane()); 
        root.setMinSize(Const.MAX_WIDTH/3.5, Const.MAX_HEIGHT/4);
        leftPane();
        loadMap();
    }
    
    private void leftPane() {
        VBox menu = new VBox();
        menu.setBackground(new Background(new BackgroundFill(Color.SLATEGREY, CornerRadii.EMPTY, Insets.EMPTY))); 
        menu.setSpacing(2); 
        StackPane nuevo = opcion("Generar", new ImageView(new Image(
                PaneModulo1.class.getResourceAsStream("/espol/edu/ec/assets/crear.png"), 30, 30, false, false)));
        StackPane atender = opcion("Atender", new ImageView(new Image(
                PaneModulo1.class.getResourceAsStream("/espol/edu/ec/assets/atender.png"), 30, 30, false, false)));
        StackPane puesto = opcion("Puestos", new ImageView(new Image(
                PaneModulo1.class.getResourceAsStream("/espol/edu/ec/assets/puestos.png"), 30, 30, false, false)));
        menu.getChildren().addAll(nuevo, atender, puesto);
        
        Tooltip.install(nuevo, new Tooltip("Generar nuevo turno"));
        Tooltip.install(atender, new Tooltip("Atender turno"));
        Tooltip.install(puesto, new Tooltip("Administrar puestos de atencion"));
        root.setLeft(menu);
        nuevo.setOnMouseClicked(e-> {
            root.setCenter(SECCION_TURNO.getPane()); 
        });
        
        atender.setOnMouseClicked(e-> {
            root.setCenter(SECCION_ATENDER.getPane()); 
        });
    }
    
    private StackPane opcion(String text, ImageView image) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER); 
        Rectangle r = new Rectangle(100, 50);
        r.setFill(Color.SLATEGREY);
        Text t = new Text(text);
        t.setFill(Color.WHITE); 
        HBox hbox = new HBox(image, t);
        hbox.setSpacing(1);
        hbox.setAlignment(Pos.CENTER_LEFT); 
        pane.getChildren().addAll(r, hbox);
        pane.setOnMouseEntered(e -> {
            r.setFill(Color.DARKSLATEGREY); 
            pane.setCursor(Cursor.HAND);
        }); 
        pane.setOnMouseExited(e-> {
            r.setFill(Color.SLATEGREY); 
            pane.setCursor(Cursor.DEFAULT); 
        });
        
        pane.setOnMousePressed(e -> r.setFill(Color.DARKSLATEGREY));
        pane.setOnMouseReleased(e -> r.setFill(Color.SLATEGREY));
        
        return pane;
    }
    
    public Pane getRoot() {
        return root;
    }
    
    private static Map<Puesto, LinkedList<Turno>> loadMap() {
        Map<Puesto, LinkedList<Turno>> map = new HashMap();
        List<Puesto> puestos = Puesto.cargarPuestos();
        for(Puesto p: puestos) 
            map.put(p, new LinkedList<>());
        return map;
    }
}
