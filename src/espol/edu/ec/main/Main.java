/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author SSAM
 */
public class Main extends Application{
    private Pane root;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        root = new Pane();
        root.setId("Panel");
        stage.setScene(new Scene(root, 200, 200));
        stage.show();
    }
}