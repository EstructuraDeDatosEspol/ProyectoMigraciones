/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author SSAM
 */
public class PantallaOpciones {
    private Pane root;
    private ModuloRegistro moduloRegistro;

    public PantallaOpciones(Stage stage) {
        root = new Pane();
        
        MenuBox menuBox = new MenuBox(
                new MenuItem("Registrar"),
                new MenuItem("Modificar"),
                new MenuItem("Eliminar"));
        menuBox.setTranslateX(550);
        menuBox.setTranslateY(300);
         
        root.getChildren().addAll(menuBox);
        
        MenuItem[] menu = menuBox.getMenu();
        
        menu[0].setOnMouseClicked(e -> {
            root.getChildren().removeAll(menuBox);
            moduloRegistro =new ModuloRegistro(root,stage,false,true,true,false,true);
        });
        
        menu[1].setOnMouseClicked(e -> {
            root.getChildren().removeAll(menuBox);
            moduloRegistro =new ModuloRegistro(root,stage,true,false,true,false,false);
        });
        
        menu[2].setOnMouseClicked(e -> {
            root.getChildren().removeAll(menuBox);
            moduloRegistro =new ModuloRegistro(root,stage,true,true,false,true,false);
        });
        
        stage.setScene(new Scene(root,2480,700));
    }
    
    public void volver(Pane root){
        root = new Pane();
        this.root = root;
    }
}
