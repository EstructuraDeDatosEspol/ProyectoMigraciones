/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.pane;

import espol.edu.ec.main.Main;
import espol.edu.ec.registro.Persona;
import espol.edu.ec.registro.ReadWriter;
import espol.edu.ec.tda.Const;
import espol.edu.ec.tda.Puesto;
import espol.edu.ec.tda.Turno;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TabPane;
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
    
    public final static Map<String, Persona> EMPLEADOS = ReadWriter.loadEmpleados();
    public final static List<Puesto> PUESTOS = Puesto.cargarPuestos(EMPLEADOS);
    private final static Map<Puesto, LinkedList<Turno>> ATENCION = loadMap(PUESTOS);
    private final BorderPane root;
    private final static PaneScreenTurnos SCREEN = Main.getRootTurnShower();
    private final static PaneTurnoGenerador SECCION_TURNO = new PaneTurnoGenerador(ATENCION, SCREEN);
    private final static PaneAtenderTurno SECCION_ATENDER = new PaneAtenderTurno(ATENCION, SCREEN, SECCION_TURNO);
    private final static PaneAdminPuestos SECCION_PUESTOS = new PaneAdminPuestos(ATENCION);
    private final static PaneAdminEmpleados SECCION_EMPLEADOS = new PaneAdminEmpleados(ATENCION); 
    
    public PaneModulo1() {
        root = new BorderPane();
        root.setCenter(SECCION_TURNO.getPane()); 
        root.setMinSize(Const.MAX_WIDTH/3, Const.MAX_HEIGHT/2.4);
        leftPane();
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
            SECCION_ATENDER.updatePuestos();
            root.setCenter(SECCION_ATENDER.getPane()); 
        });
        
        puesto.setOnMouseClicked(e -> {
            TabPane pane = new TabPane();
            pane.getTabs().addAll(SECCION_PUESTOS.getTab(), SECCION_EMPLEADOS.getTab());
            root.setCenter(pane); 
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
    
    private static Map<Puesto, LinkedList<Turno>> loadMap(List<Puesto> puestos) {
        Map<Puesto, LinkedList<Turno>> map = new HashMap();
        for(Puesto p: puestos) 
            map.put(p, new LinkedList<>());
        return map;
    }
    
    public static void updateFiles() {
        String emple = new File("").getAbsolutePath();
        emple = Paths.get(emple, "src", "espol", "edu", "ec", "recursos", "files", "empleados.txt").toString();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(emple))){
            for(Map.Entry<String, Persona> p: EMPLEADOS.entrySet()){
                Persona per = p.getValue();
                bw.write(p.getKey()+","+per.getNombre()+","+per.getApellido()+","+per.getSexo()+","+per.getEdad());
                bw.newLine();
            }
        }catch(IOException ex){
            Logger.getLogger(PaneModulo1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String puesto = new File("").getAbsolutePath();
        puesto = Paths.get(puesto, "src", "espol", "edu", "ec", "recursos", "files", "puestos.txt").toString();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(puesto))){
            for(Puesto p: PUESTOS){
                if(p.getEmpleado() == null)
                    bw.write(p.getPuesto());
                else{
                    String cedula = p.getEmpleado().getCedula()+ "";
                    if(cedula.length() < 10)
                        cedula = "0"+cedula;
                    bw.write(p.getPuesto()+","+cedula);
                    bw.newLine();
                }
            }
        }catch(IOException ex){
            Logger.getLogger(PaneModulo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
